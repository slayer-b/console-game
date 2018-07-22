package com.company;

import com.company.cells.*;
import com.company.map.CellLine;
import com.company.map.Drawer;
import com.company.map.GameMap;
import com.company.map.MapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static com.company.cells.CellType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameMapTest {

    private GameMap gameMap;

    @BeforeEach
    public void before() {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.withLines(new ArrayList<>());
        mapBuilder.withTop(0);
        mapBuilder.withLeft(0);
        mapBuilder.withYou(new You());
        gameMap = mapBuilder.build();
        Drawer drawer = Mockito.mock(Drawer.class);
        gameMap.setDrawer(drawer);
    }

    @Test
    void goToEmpty() {
        gameMap.getLines().add(newCellLine(YOU, EMPTY));

        gameMap.goTo(1, 0);

        assertEquals(EMPTY, gameMap.getLines().get(0).getCells().get(0).getType());
    }

    @Test
    void goToAliveMonster() {
        gameMap.getLines().add(newCellLine(YOU, MONSTER));

        gameMap.goTo(1, 0);

        assertEquals(YOU, gameMap.getLines().get(0).getCells().get(0).getType());
    }

    @Test
    void goToDeadMonster() {
        gameMap.getLines().add(newCellLine(YOU, MONSTER));

        Cell monster = gameMap.getLines().get(0).getCells().get(1);
        while (monster.isAlive()) {
            gameMap.goTo(1, 0);
        }

        assertEquals(CORPSE, gameMap.getLines().get(0).getCells().get(0).getType());
    }

    @Test
    void goToObstacle() {
        gameMap.getLines().add(newCellLine(YOU, OBSTACLE));

        gameMap.goTo(1, 0);

        assertEquals(YOU, gameMap.getLines().get(0).getCells().get(0).getType());
    }

    private static CellLine newCellLine(CellType... types) {
        CellLine cellLine = new CellLine();
        for (CellType type : types) {
            switch (type) {
                case EMPTY:
                    cellLine.getCells().add(EmptyCell.EMPTY_CELL);
                    break;
                case YOU:
                    cellLine.getCells().add(new You());
                    break;
                case MONSTER:
                    cellLine.getCells().add(new Monster(1));
                    break;
                case OBSTACLE:
                    cellLine.getCells().add(new Obstacle());
                    break;
            }
        }

        return cellLine;
    }
}