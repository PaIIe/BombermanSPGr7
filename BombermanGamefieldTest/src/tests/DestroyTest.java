package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Bomb;
import code.Wall;
import code.GameField;

public class DestroyTest 
{
	@Test
	public void BombDestroysWall(int row, int column, int time, int radius)
	{
		Bomb bomb = new Bomb(row, column, time, radius);
		bomb.explode(); // explode function triggers destroyWall function if wall is in explosion radius with parameters(x, y) "destroyWall(x, y)" for location in matrix
		assertEquals("EF", GameField.getMatrixElement(x, y));

	}
	
}
