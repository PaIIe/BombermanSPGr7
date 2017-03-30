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
import code.Bomberman;
import code.Boni;
import code.GameObject;

/**
 *
 * @author bestx
 */
public class BoniTest {
    private static Boni boni;
    private static Bomberman bomberman;
    
    
    @BeforeClass
    public static void bombermanHitBoni()

	{
            bomberman.setColumn(boni.getColumn());
            bomberman.setRow(boni.getRow());
        }

     @Test
    
    public void increaseBombNumberTest()

	{
            int temp = bomberman.getMaxBombs();
            boni.increaseBombNumber(bomberman.ID);
            assertEquals(bomberman.getMaxBombs(), temp + 1);
        }
    @Test
    public void setArmorTest()
            {
                boni.setArmor();
                assertEquals(bomberman.getArmor(), true);
            }
    @Test
    public void increaseExplosionRadiusTest()

	{
            int temp = bomberman.getBombRadius();
            boni.increaseExplosionRadius();
            assertEquals(bomberman.getBombRadius(), temp + 1);

	}
    
    @Test
    public void setToMaxRadiusTest()
    {
            int temp = bomberman.setBombRadius();
            boni.setToMaxRadius();
            assertEquals(bomberman.setBombRadius(), temp + 1);
    
    }
  
}
