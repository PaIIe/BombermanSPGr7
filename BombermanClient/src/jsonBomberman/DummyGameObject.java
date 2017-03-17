package jsonBomberman;

import dev.code.bomberman.gamefield.GameObject;

public class DummyGameObject extends GameObject
{
    public DummyGameObject(int row, int column)
    {
        this.setID(-1);
        this.setRow(row);
        this.setColumn(column);
        this.setSolid(false);
    }
}
