package com.company.cells;

public class EmptyCell implements Cell {
    public static Cell EMPTY_CELL = new EmptyCell();

    @Override
    public boolean moveTo(Cell cell) {
        return true;
    }

    @Override
    public boolean hit(int dmg) {
        return false;
    }

    @Override
    public int getDmg() {
        return 0;
    }

    @Override
    public CellType getType() {
        return CellType.EMPTY;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public int getExp() {
        return 0;
    }
}
