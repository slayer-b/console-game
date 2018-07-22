package com.company.cells;

public enum CellType {
    YOU("@", "you"), MONSTER("M", "monster"), EMPTY(".", "empty"), OBSTACLE("T", "obstacle"),
    CORPSE("C", "corpse");

    private final String symbol;
    private final String name;

    CellType(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
