package com.company.map;

import com.company.cells.Cell;
import com.company.cells.Corpse;
import com.company.cells.You;

import java.io.Serializable;
import java.util.List;

public class GameMap implements Serializable {
    private final List<CellLine> lines;
    private int youTop;
    private int youLeft;
    private final You you;
    private transient Drawer drawer;
    private int monstersCount;

    public GameMap(MapBuilder mapBuilder) {
        this.lines = mapBuilder.getLines();
        this.youTop = mapBuilder.getTop();
        this.youLeft = mapBuilder.getLeft();
        this.you = mapBuilder.getYou();
        this.monstersCount = mapBuilder.getMonsterCount();
    }

    public List<CellLine> getLines() {
        return lines;
    }

    public void goTo(int left, int top) {
        int targetTop = youTop + top;
        if (targetTop < lines.size() && targetTop >= 0) {
            CellLine cellLine = lines.get(targetTop);
            int targetLeft = youLeft + left;
            if (targetLeft < cellLine.getCells().size() && targetLeft >= 0) {
                Cell target = getCell(targetLeft, targetTop);
                boolean targetKilled = target.hit(you.getDmg());
                boolean youDead = you.hit(target.getDmg());
                if (youDead) {
                    drawer.drawDeath();
                }
                boolean youMove = you.moveTo(target);
                boolean targetMove = target.moveTo(you);
                if (youMove && targetMove) {
                    if (targetKilled) {
                        you.gainExp(target.getExp());
                        drawer.drawTargetKilled(target.getType().getName());
                        setCell(youLeft, youTop, new Corpse());
                        monstersCount -= 1;
                        if (monstersCount == 0) {
                            drawer.showWin();
                        }
                    } else {
                        setCell(youLeft, youTop, target);
                    }
                    setCell(targetLeft, targetTop, you);
                    youTop = targetTop;
                    youLeft = targetLeft;
                }
            }
        }
    }

    private Cell getCell(int left, int top) {
        return lines.get(top).getCells().get(left);
    }

    private void setCell(int left, int top, Cell cell) {
        lines.get(top).getCells().set(left, cell);
    }

    public boolean isNoMonsters() {
        return monstersCount == 0;
    }

    public void setDrawer(Drawer drawer) {
        this.drawer = drawer;
    }

}
