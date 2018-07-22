package com.company;

import com.company.map.ConsoleDrawer;

public class Main {

    public static void main(String[] args) {
        ConsoleDrawer drawer = new ConsoleDrawer();
        Game game = new Game(drawer);
        game.play();
    }
}
