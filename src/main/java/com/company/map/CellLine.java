package com.company.map;

import com.company.cells.Cell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CellLine implements Serializable {
    private final List<Cell> cells;

    public CellLine() {
        this.cells = new ArrayList<>();
    }

    public List<Cell> getCells() {
        return cells;
    }
}
