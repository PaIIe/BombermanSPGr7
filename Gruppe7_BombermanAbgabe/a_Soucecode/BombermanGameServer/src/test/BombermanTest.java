/*
/ * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import dev.code.bomberman.Bomberman;
import dev.code.bomberman.Direction;
import dev.code.bomberman.Bomb;
import dev.code.bomberman.GameObject;

/**
 *
 * @author bestx
 */
/*
public class BombermanTest {
    
    public BombermanTest() {
      private static Bomb bomb;
      private static Bomberman bomberman;
      private static Direction direction;
    
    
    @BeforeClass
    public static void setUpClass() {
        row = 0;
        column = 0;
        bomberman.ID = 51;
    }
    
   
     @Test
     public void setStartPosTest()
     {
         bomberman.setStartPos(5,8);
         assertEquals(bomberman.getRow(),5);
         assertEquals(bomberman.getColumn(),8);
     }
    
     @Test
     public void WalkTest()
     {
         bool kick = true;
         bomb.setRow = bomberman.getRow - 1;
         bomb.ID = 61;
         bomberman.walk(NORTH);
         assertEquals(bomb.slideNorth, true)
     }
     
     @Test
     public void placeBombTest()
     {
         bomberman.setColumn(1);
         bomberman.setRow(1);
         bomberman.placeBomb(bomb);
         assertEquals(bomb.getColumn(), bomberman.getColumn());
         assertEquals(bomb.getRow(), bomberman.getRow());
     }
     @Test
     public void gotHitTest()
     {
       bomberman.armor = 0;
       bomberman.gotHit();
       assertEquals(bomberman.getAliveStatus,false);
       assertEquals(bomberman.getRow,-1);
       assertEquals(bomberman.getColumn.-1);
       
     @Test
     public void counterArmorTest()
     {
         bomberman.armorTimer = 1;
         bomberman.counterArmor();
         bomberman.setID = 55;
         assertEquals(bomberman.getID(),51);
         assertEquals(bomberman.getArmor,false);
     }
     @Test
     public void increaseMaxBombs()
     {
         int Temp = maxBomb;
         bomberman.increaseMaxBombs();
         assertEquals(maxBomb,Temp + 1);
     }
    
     @Test
     public void increaseBombRadius()
     {
         bomberman.radiusBomb = 1;
         bomberman.maxRadius = 2;
         int Temp = radiusBomb;
         bomberman.increaseMaxBombs();
         assertEquals(bomberman.radiusBomb,Temp + 1);
     }
     @Test
     public void setBombradiusToMax()
     {
         bomberman.setBombradiusToMax();
         assertEquals(bomberman.radiusBomb,bomberman.maxRadius);
     }
     
     }
     
*/