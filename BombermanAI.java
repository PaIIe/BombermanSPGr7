import java.util.Random;

interface BombermanAI {

	public void init(BombermanController controller, byte player_id);
	public void arena(byte x, byte y, byte arena_type);
	public void player(byte i, int x, int y, byte direction);
	public void info(byte bomb_flamelength);
}

class BombermanDefaultAI extends Thread implements BombermanAI {

	private BombermanController controller = null;
	private byte player_id = 0;

	private byte arena[][][] = new byte[15][13][2];
	private byte infox, infoy;
	private byte playerX[] = new byte[5];
	private byte playerY[] = {127, 127, 127, 127, 127};
	private byte playerD[] = new byte[5];

	public void init(BombermanController controller, byte player_id) {

		this.controller = controller;
		this.player_id = player_id;

		for(int i = 0; i < 15; i++)
			for(int j = 0; j < 13; j++)
				arena[i][j][0] = 1;

		new Thread(this).start();

		System.out.println("please note: just for demonstration!! implement your own bot!!");
	}

	public void arena(byte x, byte y, byte arena_type) {

		if(arena_type <= 2) arena[x][y][0] = 1;					// steel 1
		else if(arena_type <= 8) arena[x][y][0] = 2;				// wall 2
		else if(arena_type <= 11)						// bomb 16-64
			if(arena[x][y][0] < 16) arena[x][y][0] = 16;
			else arena[x][y][0]++;
		else if(arena_type == 12) arena[x][y][0] = 0;				// nothing 0
		else if(arena_type <= 15) arena[x][y][0] = (byte)(20 - arena_type);	// good extra 5-7
		else if(arena_type == 16) arena[x][y][0] = 4;				// skull 4
		else if(arena_type <= 52) arena[x][y][0] = 3;				// explosion 3
		else arena[x][y][0] = 0;						// smoke 0
		
		infox = x;
		infoy = y;
	}

	public void player(byte i, int x, int y, byte direction) {

		playerX[i] = (byte)((x + 8) / 16);
		playerY[i] = (byte)((y + 22) / 16);
		playerD[i] = direction;
	}

	public void info(byte bomb_flamelength) {

		arena[infox][infoy][1] = bomb_flamelength;
	}

	public void run() {

		Random random = new Random();
		int action = -1, x = 0, y = 0;

		while(true) {

			if((alive()) &&
			   (((random.nextInt() % 30) == 0) ||
			    (arena[playerX[player_id] + x][playerY[player_id] + y][0] != 0))) {

				do {
					action = (Math.abs(random.nextInt()) % BombermanController.KEY_FIRE);

					switch(action) {

						case BombermanController.KEY_UP:
							x = 0;
							y = -1;
							break;
						case BombermanController.KEY_DOWN:
							x = 0;
							y = +1;
							break;
						case BombermanController.KEY_LEFT:
							x = -1;
							y = 0;
							break;
						case BombermanController.KEY_RIGHT:
							x = +1;
							y = 0;
							break;
					}
				} while(arena[playerX[player_id] + x][playerY[player_id] + y][0] != 0);
			}

			controller.key(BombermanController.KEY_UP, false);
			controller.key(BombermanController.KEY_DOWN, false);
			controller.key(BombermanController.KEY_LEFT, false);
			controller.key(BombermanController.KEY_RIGHT, false);
			controller.key(BombermanController.KEY_FIRE, false);
			if(alive()) controller.key(action, true);                                     

			try {

				Thread.sleep(50);
			} catch(InterruptedException e) {}
		}
	}

	private boolean alive() {

		return	(playerX[player_id] >= 1 &&
			 playerX[player_id] <= 13 &&
			 playerY[player_id] >= 1 &&
			 playerY[player_id] <= 11);
	}
}
