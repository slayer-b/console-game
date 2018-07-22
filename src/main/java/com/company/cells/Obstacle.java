package com.company.cells;

public class Obstacle implements Cell {

    @Override
    public boolean moveTo(Cell cell) {
        return false;
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
        return CellType.OBSTACLE;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public int getExp() {
        return 0;
    }
}
