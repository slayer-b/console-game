package com.company.cells;

public class You implements Cell {
    private int hp;
    private int exp;
    private int dmg;

    public You() {
        this.hp = 100;
        this.dmg = 3;
    }

    @Override
    public boolean moveTo(Cell cell) {
        return true;
    }

    @Override
    public int getDmg() {
        return dmg;
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
    public CellType getType() {
        return CellType.YOU;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public void gainExp(int exp) {
        this.exp = exp;
    }

    @Override
    public int getExp() {
        return 0;
    }
}
