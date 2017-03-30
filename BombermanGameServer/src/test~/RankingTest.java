/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import code.Ranking;
import code.Bomberman;
import code.Boni;
import code.GameObject;

/**
 *
 * @author bestx
 */
public class RankingTest {
    private static Bomberman player;
    private static Bomb bomb;
    
    public RankingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        player.ID = 51;
        bomb.ID= 61;
        
    }
    

}
 @Test
     public void updateBoniTest()
     {
        int Temp = collectedPowerupsplayer1; 
        ranking.updateBoni(player.ID);
        assertEquals(collectedPowerupsplayer1,Temp+1);
     }



 @Test
     public void updateWallsTest()
     {
         int Temp1 = scorePlayer1;
         int Temp2 = destroyedWallsPlayer1;
         ranking.updateWall(bomb.ID);
        assertEquals(scorePlayer1,Temp1+1);
        assertEquals(destroyedWallsPlayer1,Temp2+1);
     }
 @Test
     public void updateKillTest()
     {
         int playerNumber = 1;
         int Temp1 = scorePlayer1;
         ranking.updateWall(bomb.ID,playerNumber);
         assertEquals(scorePlayer1,Temp1-20);
         assertEquals(isSuicidedPlayer1,true);
     }

 @Test
     public void updateStepsTest()
     {
          int Temp = walkedStepsPlayer1; 
          ranking.updateSteps(player.ID);
          assertEquals(walkedStepssPlayer1,Temp+1); 
     }
 @Test
     public void updateBombsTest()
     {
          int Temp = plantedBombsPlayer1; 
          ranking.updateBomb(player.ID);
          assertEquals(plantedBombsPlayer1,Temp+1);   
     }
 @Test
     public void updateLastManTest()
     {
          int Temp = isLastManPlayer1; 
          ranking.updateLastMan(player.ID);
          assertEquals(isLastManPlayer1,true);
     }

  @Test
     public void getStepsTest()
     { 
          assertEquals(walkedStepsPlayer1,getSteps(1));
     }

  @Test
     public void getPlacedBombsTest()
     { 
          assertEquals(plantedBombsPlayer1,getPlacedBombs(1));
     }

  @Test
     public void getKillsTest()
     { 
          assertEquals(killedPlayersPlayer1,getKills(1));
     }


  @Test
     public void getDestroyedWallsTest()
     { 
          assertEquals(DestroyedWallsPlayer1,getDestroyedWalls(1));
     }

@Test
     public void getIsSuicidedTest()
     { 
          assertEquals(isSuicidedPlayer1,getIsSuicided(1));
     }

@Test
     public void getLastManTest()
     { 
          assertEquals(isLastManPlayer1,getLastMan(1));
     }
@Test
     public void getCollectedBoni()
     { 
          assertEquals(collectedPowerUpsPlayer1,getCollectedBoni(1));
     }
@Test
     public void getPonits()
     { 
          assertEquals(scorePlayer1,getPonits(1));
     }
@Test
     public void getName()
     { 
          assertEquals(namePlayer1,getName(1));
     }
}