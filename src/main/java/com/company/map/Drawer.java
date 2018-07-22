package com.company.map;

public interface Drawer {

    void drawMap(GameMap map);

    void drawWelcome();

    void drawAskSize();

    void clear();

    int drawAskDigit(String name, int to);

    String nextAction();

    void showAvailableActions();

    void drawTargetKilled(String name);

    void drawDeath();

    void drawAsk(String name);

    String askFileName();

    void drawFileNotFound();

    void drawSomethingWrongFile();

    void drawClassNotFound();

    void showWin();
}
