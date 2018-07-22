package com.company.map;

import com.company.cells.EmptyCell;
import com.company.cells.Monster;
import com.company.cells.Obstacle;
import com.company.cells.You;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private int obstacleCount;
    private int monsterCount;
    private double obstacleChance;
    private double monsterChance;
    private double obstacleCurChance;
    private double monsterCurChance;
    private final int size;
    private Random random;

    public MapGenerator(int size) {
        this.size = size;
        this.random = new Random();
    }

    public GameMap generate() {;
        MapBuilder mapBuilder = new MapBuilder();
        List<CellLine> lines = new ArrayList<>();
        mapBuilder.withLines(lines);
        You you = new You();
        mapBuilder.withYou(you);
        for (int i = 0; i < size; i++) {
            CellLine line = new CellLine();
            for (int j = 0; j < size; j++) {
                if (i == 0 && j == 0) {
                    line.getCells().add(you);
                    mapBuilder.withTop(0);
                    mapBuilder.withLeft(0);
                } else if (shouldAddMonster()) {
                    line.getCells().add(new Monster(1));
                    monsterCount -= 1;
                    monsterCurChance += monsterChance;
                    mapBuilder.addMonster();
                } else if (shouldAddObstacle()) {
                    line.getCells().add(new Obstacle());
                    obstacleCurChance += obstacleChance;
                    obstacleCount -= 1;
                } else {
                    line.getCells().add(EmptyCell.EMPTY_CELL);
                }
            }
            lines.add(line);
        }
        return mapBuilder.build();
    }

    private boolean shouldAddMonster() {
        return monsterCount > 0 && random.nextDouble() < monsterCurChance;
    }

    private boolean shouldAddObstacle() {
        return obstacleCount > 0 && random.nextDouble() < obstacleCurChance;
    }

    public void setObstacleCount(int obstacleCount) {
        this.obstacleCount = obstacleCount;
        this.obstacleChance = (double) obstacleCount / (size * size);
        this.obstacleCurChance = obstacleChance;

    }

    public void setMonsterCount(int monsterCount) {
        this.monsterCount = monsterCount;
        this.monsterChance = (double) monsterCount / (size * size);
        this.monsterCurChance = monsterChance;
    }

}
