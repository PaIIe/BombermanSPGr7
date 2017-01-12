package code;

public class GameField {
	private int height;
	private int width;
	private String[][] matrix;
	
	public void generateMap(){};
	public int getHeight(){return 1;};
	public int getWidth(){return 1;};
	public String[][] getMatrix()
	{
		return this.matrix;
	}
	public String getMatrixElement(int row, int column)
	{
		return this.matrix[row][column];
	}
}
