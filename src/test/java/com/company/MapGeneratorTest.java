package com.company;

import com.company.cells.Cell;
import com.company.cells.CellType;
import com.company.map.CellLine;
import com.company.map.GameMap;
import com.company.map.MapGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapGeneratorTest {

    private MapGenerator mapGenerator;

    @BeforeEach
    public void before() {
        mapGenerator = new MapGenerator(5);
    }

    @Test
    void generate() {
        mapGenerator.setObstacleCount(0);
        mapGenerator.setMonsterCount(0);

        GameMap map = mapGenerator.generate();

        for (CellLine cellLine : map.getLines()) {
            for (Cell cell : cellLine.getCells()) {
                assertNotEquals(CellType.MONSTER, cell.getType());
                assertNotEquals(CellType.OBSTACLE, cell.getType());
            }
        }

    }
}