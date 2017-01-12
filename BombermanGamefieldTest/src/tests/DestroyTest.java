package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Bomb;
import Wall;

public class DestroyTest 
{
	@Test
	public void BombDestroysWall()
	{
		Bomb bomb = new Bomb();
		bomb.explode(); // explode function triggers 
		
	}
	
}
