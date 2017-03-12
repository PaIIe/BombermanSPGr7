import java.util.*;
import java.awt.*;
import java.text.DecimalFormat;

public class BombermanRobertAI extends Thread implements BombermanAI {

	private static final int DONT_WALK_HERE = -100000;
	private static final int WALK_HERE = 100000;
	private static final int WALL_SCORE = 1000;
	private static final int FLAME_SCORE = -250;
	private static final int BONUS_FACTOR = 100;		// 0.1
	private static final int SKULL_FACTOR = 1000;		// 1.0

	private static final int ROUTING_STEPS = 16;
	private static final int ROUTING_FACTOR = 800;		// 0.8

	private static final int MIN_OFFENSIVE = 20;
	private static final int RND_OFFENSIVE = 5;

	private static final int NEARBY_SCORE = -4000;

	private static final int THREAD_SLEEP = 25;

	private static final int NEUTRAL = 0;
	private static final int DEFENSIVE = 1;
	private static final int OFFENSIVE = 2;

	private static final byte ARENA_NULL = 0;
	private static final byte ARENA_BONUS = 3;
	private static final byte ARENA_SKULL = 4;
	private static final byte ARENA_DEBRIS = 12;
	private static final byte ARENA_FLAME = 13;
	private static final byte ARENA_WALL = 14;
	private static final byte ARENA_STEEL = 15;
	private static final byte ARENA_BOMB = 16;

	private static final byte DIRECTION_DOWN = 0;
	private static final byte DIRECTION_RIGHT = 1;
	private static final byte DIRECTION_UP = 2;
	private static final byte DIRECTION_LEFT = 3;
	private static final byte DIRECTION_IDLE = 4;
	private static final byte DIRECTION_DIE = 5;
	private static final byte DIRECTION_WIN = 6;

	private BombermanController controller = null;
	private byte player_id = 0;

	private byte arena[][][] = new byte[15][13][2];
	private byte info_x, info_y;

	private int playerX[] = new int[5];
	private int playerY[] = {17, 17, 17, 17, 17};
	private byte playerD[] = new byte[5];
	private int player_x, player_y;
	private int startX[] = {1, 13, 13, 1, 7};
	private int startY[] = {1, 11, 1, 11, 6};
	
	private int tactics = NEUTRAL;
	private int action = -1;
	private int offensive_counter = 0;

	private int score[][] = new int[15][13];

	private int route_list[] = new int[15*13*4];
	private int route_first = 0, route_last = 0;
	private boolean not_routed[][] = new boolean[15][13];
	private Vector bomb_list[] = new Vector[49];
	private int flame_list[] = new int[15*13*3*2];
	private int flame_first = 0, flame_last = 0;

	private Random random = new Random();

	public void init(BombermanController controller, byte player_id) {

		this.controller = controller;
		this.player_id = player_id;

		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 13; j++)
				arena[i][j][0] = ARENA_STEEL;

		for(int i = 0; i < 49; i++) bomb_list[i] = new Vector();

		player_x = startX[player_id];
		player_y = startY[player_id];

		new Thread(this).start();

		System.out.println("BombermanRobertAI version 26.jul.2002 by robert hockauf.");
	}

	public void arena(byte x, byte y, byte arena_type) {

		if(arena_type <= 2) arena[x][y][0] = ARENA_STEEL;				// steel 15
		else if(arena_type <= 3) arena[x][y][0] = ARENA_WALL;				// wall 14
		else if(arena_type <= 8) arena[x][y][0] = ARENA_DEBRIS;				// destroyed wall 12
		else if(arena_type <= 11)						// bomb 16-64
			if(arena[x][y][0] < ARENA_BOMB) arena[x][y][0] = ARENA_BOMB;
			else arena[x][y][0]++;
		else if(arena_type == 12) arena[x][y][0] = ARENA_NULL;				// nothing 0
		else if(arena_type <= 15) arena[x][y][0] = (byte)(16 - arena_type);	// bonus 1-3
		else if(arena_type == 16) arena[x][y][0] = ARENA_SKULL;				// skull 4
		else if(arena_type <= 52) arena[x][y][0] = ARENA_FLAME;				// explosion 13
		else arena[x][y][0] = ARENA_NULL;						// smoke 0
		
		info_x = x;
		info_y = y;
	}

	public void player(byte i, int x, int y, byte direction) {

		playerX[i] = (x + 8) / 16;
		playerY[i] = (y + 22) / 16;

		if(direction < 7) playerD[i] = DIRECTION_DOWN;
		else if(direction < 14) playerD[i] = DIRECTION_RIGHT;
		else if(direction < 21) playerD[i] = DIRECTION_UP;
		else if(direction < 28) playerD[i] = DIRECTION_LEFT;
		else if(direction < 31) playerD[i] = DIRECTION_IDLE;
		else if(direction < 35) playerD[i] = DIRECTION_DIE;
		else playerD[i] = DIRECTION_WIN;

		if(i == player_id && playerY[i] <= 11) {

			player_x = playerX[i];
			player_y = playerY[i];
		}
	}

	public void info(byte bomb_flamelength) {

		arena[info_x][info_y][1] = bomb_flamelength;
	}

	public void run() {

		while(true) {

			controller.key(BombermanController.KEY_UP, false);
			controller.key(BombermanController.KEY_DOWN, false);
			controller.key(BombermanController.KEY_LEFT, false);
			controller.key(BombermanController.KEY_RIGHT, false);
			controller.key(BombermanController.KEY_FIRE, false);

			if(alive()) {

				score_route();
				score_flames();
//log();
				action = -1;
				int max = score[player_x][player_y];

				if(score[player_x][player_y-1] > max) {

					max = score[player_x][player_y-1];
					action = BombermanController.KEY_UP;
				}
				if(score[player_x][player_y+1] > max) {

					max = score[player_x][player_y+1];
					action = BombermanController.KEY_DOWN;
				}
				if(score[player_x-1][player_y] > max) {

					max = score[player_x-1][player_y];
					action = BombermanController.KEY_LEFT;
				}
				if(score[player_x+1][player_y] > max) {

					max = score[player_x+1][player_y];
					action = BombermanController.KEY_RIGHT;
				}

				/**
				 *  become offensive if nothing else to do
				 */
				if(action < 0 && max == 0 && tactics != OFFENSIVE) offensive_counter++;
				if(offensive_counter > MIN_OFFENSIVE) {

					offensive_counter = MIN_OFFENSIVE;
					tactics = OFFENSIVE;
				}

				controller.key(action, true);
				controller.key(BombermanController.KEY_FIRE, drop_bomb());
			} else {

				/**
				 *  move player to start position if died or won
				 */
				player_x = startX[player_id];
				player_y = startY[player_id];
				tactics = NEUTRAL;
			}                                

			try {

				Thread.sleep(THREAD_SLEEP);
			} catch(InterruptedException e) {}
		}
	}

	private boolean alive() {

		return (playerD[player_id] <= DIRECTION_IDLE);
	}

	/*private boolean player_nearby(int x, int y, int d) {

		for(int i = 0; i < 5; i++)
			if(i != player_id && playerY[i] <= 11)
				if(Math.abs(playerX[i]-x) <= d && Math.abs(playerY[i]-y) <= d)
					return true;
		return false;
	}

	private boolean dead_end(int x, int y) {

		if(player_x == x) {

			int di = 1;
			if(player_y > y) di = -1;

			for(int i = player_y + di; i <= y; i += di)
				if(arena[x][i][0] >= ARENA_SKULL ||
				   arena[x-1][i][0] < ARENA_SKULL ||
				   arena[x+1][i][0] < ARENA_SKULL) return false;

			return true;
		}

		if(player_y == y) {

			int di = 1;
			if(player_x > x) di = -1;

			for(int i = player_x + di; i <= y; i += di)
				if(arena[i][y][0] >= ARENA_SKULL ||
				   arena[i][y-1][0] < ARENA_SKULL ||
				   arena[i][y+1][0] < ARENA_SKULL) return false;

			return true;
		}

		return false;
	}*/

	private int score_static(int i, int j) {

		int sc = 0;

		switch(arena[i][j][0]) {

			case ARENA_NULL:
				switch(tactics) {

					case NEUTRAL:
						/**
						 *  avoid dead ends if another player nearby
						 */
						/*if(player_nearby(player_x, player_y, 2) && dead_end(i, j))
							return 0;*/

						/**
						 *  the more walls the better (even skulls)
						 */
						if(arena[i-1][j][0] == ARENA_WALL) sc = WALL_SCORE;
						if(arena[i-1][j][0] == ARENA_SKULL) sc += WALL_SCORE / 4;
						if(arena[i+1][j][0] == ARENA_WALL) sc += WALL_SCORE;
						if(arena[i+1][j][0] == ARENA_SKULL) sc += WALL_SCORE / 4;
						if(arena[i][j-1][0] == ARENA_WALL) sc += WALL_SCORE;
						if(arena[i][j-1][0] == ARENA_SKULL) sc += WALL_SCORE / 4;
						if(arena[i][j+1][0] == ARENA_WALL) sc += WALL_SCORE;
						if(arena[i][j+1][0] == ARENA_SKULL) sc += WALL_SCORE / 4;
						break;
					case DEFENSIVE:
						/**
						 *  the more ways the better (even flames)
						 */
						if(arena[i-1][j][0] <= ARENA_BONUS) sc = WALL_SCORE;
						if(arena[i-1][j][0] == ARENA_FLAME) sc += WALL_SCORE / 4;
						if(arena[i+1][j][0] <= ARENA_BONUS) sc += WALL_SCORE;
						if(arena[i+1][j][0] == ARENA_FLAME) sc += WALL_SCORE / 4;
						if(arena[i][j-1][0] <= ARENA_BONUS) sc += WALL_SCORE;
						if(arena[i][j-1][0] == ARENA_FLAME) sc += WALL_SCORE / 4;
						if(arena[i][j+1][0] <= ARENA_BONUS) sc += WALL_SCORE;
						if(arena[i][j+1][0] == ARENA_FLAME) sc += WALL_SCORE / 4;
						break;
					case OFFENSIVE:
						/**
						 *  the more other player the better
						 */
						for(int k = 0; k < 5; k++)
							if(k != player_id && i == playerX[k] && j == playerY[k])
								sc += WALL_SCORE;
						break;
					default:
				}
				break;
			case 1:
			case 2:
			case ARENA_BONUS:
				sc = (WALK_HERE * BONUS_FACTOR) / 1000;
				break;
			case ARENA_SKULL:
				sc = (DONT_WALK_HERE * SKULL_FACTOR) / 1000;
				break;
			default:
				sc = DONT_WALK_HERE;
		}

		return sc;
	}

	private void clear_route() {

		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 13; j++)
				not_routed[i][j] = true;
	}

	private void do_route(int i, int j, int steps, int sc) {

		if((--steps) == 0) return;
		sc = (sc * ROUTING_FACTOR) / 1000;

		if(not_routed[i-1][j] && arena[i-1][j][0] <= ARENA_BONUS) {
			not_routed[i-1][j] = false;
			if(sc > score[i-1][j]) score[i-1][j] = sc;
			route_list[route_last++] = i-1;
			route_list[route_last++] = j;
			route_list[route_last++] = steps;
			route_list[route_last++] = sc;
		}
		if(not_routed[i+1][j] && arena[i+1][j][0] <= ARENA_BONUS) {
			not_routed[i+1][j] = false;
			if(sc > score[i+1][j]) score[i+1][j] = sc;
			route_list[route_last++] = i+1;
			route_list[route_last++] = j;
			route_list[route_last++] = steps;
			route_list[route_last++] = sc;
		}
		if(not_routed[i][j-1] && arena[i][j-1][0] <= ARENA_BONUS) {
			not_routed[i][j-1] = false;
			if(sc > score[i][j-1]) score[i][j-1] = sc;
			route_list[route_last++] = i;
			route_list[route_last++] = j-1;
			route_list[route_last++] = steps;
			route_list[route_last++] = sc;
		}
		if(not_routed[i][j+1] && arena[i][j+1][0] <= ARENA_BONUS) {
			not_routed[i][j+1] = false;
			if(sc > score[i][j+1]) score[i][j+1] = sc;
			route_list[route_last++] = i;
			route_list[route_last++] = j+1;
			route_list[route_last++] = steps;
			route_list[route_last++] = sc;
		}
	}

	private void score_route() {

		/**
		 *  reset score and fill bomb lists
		 */
		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 13; j++) {

				score[i][j] = DONT_WALK_HERE;
				int k = arena[i][j][0]-ARENA_BOMB;
				if(k > 48) k = 48;
				if(arena[i][j][0] >= ARENA_BOMB)
					bomb_list[k].addElement(new Point(i, j));
		}

		for(int i = 1; i <= 13; i++)
			for(int j = 1; j <= 11; j++) {

				int sc = score_static(i, j);
				if(sc < 0 || sc > score[i][j]) score[i][j] = sc;
				if(arena[i][j][0] <= ARENA_BONUS) {

					clear_route();
					not_routed[i][j] = false;
					route_first = 0;
					route_last = 0;
					route_list[route_last++] = i;
					route_list[route_last++] = j;
					route_list[route_last++] = ROUTING_STEPS;
					route_list[route_last++] = sc;
					while(route_first < route_last)
						do_route(route_list[route_first++], route_list[route_first++], route_list[route_first++], route_list[route_first++]);
				}
			}
	}

	private void do_bombs() {

		clear_route();

		for(int i = 48; i >= 0; i--)
			while(!bomb_list[i].isEmpty()) {

				Point p = (Point)bomb_list[i].firstElement();

				if(not_routed[p.x][p.y]) {

					not_routed[p.x][p.y] = false;
					flame_list[flame_last++] = p.x;
					flame_list[flame_last++] = p.y;
					flame_list[flame_last++] = -1;
				
					for(int j = 1; j <= arena[p.x][p.y][1]; j++)
						if(arena[p.x-j][p.y][0] >= ARENA_BOMB) {

							if(not_routed[p.x-j][p.y]) {

								if((arena[p.x][p.y][0] - arena[p.x-j][p.y][0]) > 1)
									bomb_list[i-1].addElement(new Point(p.x-j, p.y));
								break;
							}
							flame_list[flame_last++] = p.x-j;
							flame_list[flame_last++] = p.y;
							flame_list[flame_last++] = i;
						} else
						if(arena[p.x-j][p.y][0] <= ARENA_BONUS || arena[p.x-j][p.y][0] == ARENA_FLAME) {
							flame_list[flame_last++] = p.x-j;
							flame_list[flame_last++] = p.y;
							flame_list[flame_last++] = i;
						} else break;

					for(int j = 1; j <= arena[p.x][p.y][1]; j++)
						if(arena[p.x+j][p.y][0] >= ARENA_BOMB) {

							if(not_routed[p.x+j][p.y]) {

								if((arena[p.x][p.y][0] - arena[p.x+j][p.y][0]) > 1)
									bomb_list[i-1].addElement(new Point(p.x+j, p.y));
								break;
							}
							flame_list[flame_last++] = p.x+j;
							flame_list[flame_last++] = p.y;
							flame_list[flame_last++] = i;
						} else
						if(arena[p.x+j][p.y][0] <= ARENA_BONUS || arena[p.x+j][p.y][0] == ARENA_FLAME) {
							flame_list[flame_last++] = p.x+j;
							flame_list[flame_last++] = p.y;
							flame_list[flame_last++] = i;
						} else break;

					for(int j = 1; j <= arena[p.x][p.y][1]; j++)
						if(arena[p.x][p.y-j][0] >= ARENA_BOMB) {

							if(not_routed[p.x][p.y-j]) {

								if((arena[p.x][p.y][0] - arena[p.x][p.y-j][0]) > 1)
									bomb_list[i-1].addElement(new Point(p.x, p.y-j));
								break;
							}
							flame_list[flame_last++] = p.x;
							flame_list[flame_last++] = p.y-j;
							flame_list[flame_last++] = i;
						} else
						if(arena[p.x][p.y-j][0] <= ARENA_BONUS || arena[p.x][p.y-j][0] == ARENA_FLAME) {
							flame_list[flame_last++] = p.x;
							flame_list[flame_last++] = p.y-j;
							flame_list[flame_last++] = i;
						} else break;

					for(int j = 1; j <= arena[p.x][p.y][1]; j++)
						if(arena[p.x][p.y+j][0] >= ARENA_BOMB) {

							if(not_routed[p.x][p.y+j]) {

								if((arena[p.x][p.y][0] - arena[p.x][p.y+j][0]) > 1)
									bomb_list[i-1].addElement(new Point(p.x, p.y+j));
								break;
							}
							flame_list[flame_last++] = p.x;
							flame_list[flame_last++] = p.y+j;
							flame_list[flame_last++] = i;
						} else
						if(arena[p.x][p.y+j][0] <= ARENA_BONUS || arena[p.x][p.y+j][0] == ARENA_FLAME) {
							flame_list[flame_last++] = p.x;
							flame_list[flame_last++] = p.y+j;
							flame_list[flame_last++] = i;
						} else break;
						
				}
				bomb_list[i].removeElementAt(0);
			}
	}

	private void score_flames() {

		/**
		 *  get list of (future) flames ordered by time
		 */
		flame_first = 0;
		flame_last = 0;
		do_bombs();
		clear_route();

		boolean run_away = false;

		while(flame_first < flame_last) {

			int x = flame_list[flame_first++];
			int y = flame_list[flame_first++];
			int sc = flame_list[flame_first++];

			/**
			 *  become defensive when going to be flamed
			 */
			if(x == player_x && y == player_y)
				run_away = true;
			
			if(not_routed[x][y])
				score[x][y] += (sc + 1) * FLAME_SCORE;

			not_routed[x][y] = false;
		}

		if(run_away) tactics = DEFENSIVE; else tactics = NEUTRAL;
	}

	private boolean drop_bomb() {
	
		switch(tactics) {

			case NEUTRAL:
				if(action >= 0) break;

				/**
				 *  only drop bomb if at least one wall (or skull) will be destroyed and there is a way to escape
				 */
				int w = 0, e = 0;
 				if(arena[player_x-1][player_y][0] == ARENA_WALL || arena[player_x-1][player_y][0] == ARENA_SKULL) w++;
				else if(score[player_x-1][player_y] >= 0) e++;
				if(arena[player_x+1][player_y][0] == ARENA_WALL || arena[player_x+1][player_y][0] == ARENA_SKULL) w++;
				else if(score[player_x+1][player_y] >= 0) e++;
				if(arena[player_x][player_y-1][0] == ARENA_WALL || arena[player_x][player_y-1][0] == ARENA_SKULL) w++;
				else if(score[player_x][player_y-1] >= 0) e++;
				if(arena[player_x][player_y+1][0] == ARENA_WALL || arena[player_x][player_y+1][0] == ARENA_SKULL) w++;
				else if(score[player_x][player_y+1] >= 0) e++;

				if(w > 0 && e > 0) return true;
				break;
			case OFFENSIVE:
				if((random.nextInt() % RND_OFFENSIVE) == 0) return true;
				break;
			case DEFENSIVE:
				/**
				 *  don't drop a bomb if in defensive mode
				 */
			default:
		}
		return false;
	}

	/*private void log() {

		DecimalFormat f = new DecimalFormat("000000");
		f.setMinimumIntegerDigits(6);
		System.err.println(player_x+","+player_y+" "+tactics);
		for(int j = 0; j < 13; j++) {

			for(int i = 0; i < 15; i++) {
				if(score[i][j] >= 0) System.err.print(" ");
				System.err.print(f.format(score[i][j]));
				switch(arena[i][j][0]) {

					case 0:
						System.err.print(". ");
						break;
					case 1:
					case 2:
					case 3:
						System.err.print("g ");
						break;
					case 4:
						System.err.print("s ");
						break;
					case 12:
						System.err.print("d ");
						break;
					case 13:
						System.err.print("f ");
						break;
					case 14:
						System.err.print("w ");
						break;
					case 15:
						System.err.print("# ");
						break;
					default:
						System.err.print("b ");
				}
			}
			System.err.println("");
		}
		System.err.println("");
		//System.exit(0);
	}*/
}
