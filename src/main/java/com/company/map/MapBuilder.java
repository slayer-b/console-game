package com.company.map;

import com.company.cells.You;

import java.util.List;

public class MapBuilder {
    private List<CellLine> lines;
    private int top;
    private int left;
    private You you;
    private int monsterCount;

    public MapBuilder() {
        this.monsterCount = 0;
    }

    public MapBuilder withLines(List<CellLine> lines) {
        this.lines = lines;
        return this;
    }

    public MapBuilder withTop(int top) {
        this.top = top;
        return this;
    }

    public MapBuilder withLeft(int left) {
        this.left = left;
        return this;
    }

    public MapBuilder withYou(You you) {
        this.you = you;
        return this;
    }

    public MapBuilder addMonster() {
        this.monsterCount += 1;
        return this;
    }

    public GameMap build() {
        return new GameMap(this);
    }

    public List<CellLine> getLines() {
        return lines;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public You getYou() {
        return you;
    }

    public int getMonsterCount() {
        return monsterCount;
    }
}
