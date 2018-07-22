package com.company.cells;

import java.io.Serializable;

public interface Cell extends Serializable {

    /**
     * move to given cell
     * @return true if should move, else false
     */
    boolean moveTo(Cell cell);

    /**
     * @return true if dead after this hit
     */
    boolean hit(int dmg);

    int getDmg();

    CellType getType();

    boolean isAlive();

    int getExp();
}
