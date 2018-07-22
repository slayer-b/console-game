package com.company.cells;

public class Monster implements Cell {
    private final int level;
    private int hp;
    private int dmg;

    public Monster(int level) {
        this.level = level;
        this.hp = level * 10;
        this.dmg = level * 2;
    }

    @Override
    public boolean moveTo(Cell cell) {
        return !isAlive();
    }

    @Override
    public boolean hit(int dmg) {
        if (isAlive()) {
            hp -= dmg;
            return !isAlive();
        } else {
            return false;
        }
    }

    @Override
    public int getDmg() {
        return dmg;
    }

    @Override
    public CellType getType() {
        return CellType.MONSTER;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public int getExp() {
        return level;
    }
}
