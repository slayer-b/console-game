package com.company.cells;

public class Corpse implements Cell {

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
        return CellType.CORPSE;
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
