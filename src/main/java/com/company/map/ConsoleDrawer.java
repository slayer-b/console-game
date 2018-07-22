package com.company.map;

import com.company.cells.Cell;

import java.util.Scanner;

public class ConsoleDrawer implements Drawer {

    private Scanner scanner;
    public ConsoleDrawer() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void drawMap(GameMap map) {
        for (CellLine cellLine : map.getLines()) {
            for (Cell cell : cellLine.getCells()) {
                System.out.print(cell.getType().getSymbol());
            }
            System.out.println();
        }
    }

    @Override
    public void drawWelcome() {
        System.out.println("You are inside of a game :)");
        System.out.println("To win you should kill all monsters");
        System.out.println("To start game please press 's'");
        System.out.println("To load game please press 'l'");
        System.out.println("To exit game press 'e'");
    }

    @Override
    public void drawAskSize() {
        System.out.println("The field is a square area");
        System.out.println("Please enter size of a square");
    }

    @Override
    public void drawAsk(String name) {
        System.out.println("Please enter the quantity of " + name);
    }

    @Override
    public int drawAskDigit(String name, int to) {
        System.out.println(name + " should be a positive value that is less then " + to);
        int i = scanner.nextInt();
        while (i < 1 || i > to) {
            System.out.println(name + " should be a positive value that is less then " + to);
            i = scanner.nextInt();
        }
        return i;
    }

    @Override
    public void showAvailableActions() {
        System.out.println("'l' - go left");
        System.out.println("'r' - go right");
        System.out.println("'t' - go top");
        System.out.println("'b' - go bottom");
        System.out.println("'a' - look around (see map)");
        System.out.println("'s' - save your game");
    }

    @Override
    public void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    @Override
    public void drawTargetKilled(String name) {
        System.out.println("You killed a " + name);
    }

    @Override
    public void drawDeath() {
        System.out.println("You are dead :(");
        System.out.println("But you can try again");
    }

    @Override
    public String nextAction() {
        System.out.println("Please enter What you want to do: ");
        String a = scanner.next();
        while (a.length() != 1) {
            System.out.println("Command should contain only one character");
            showAvailableActions();
            a = scanner.next();
        }
        return a;
    }

    @Override
    public String askFileName() {
        System.out.println("Please enter file name where you want to save");
        return scanner.next();
    }

    @Override
    public void drawFileNotFound() {
        System.out.println("File not found.");
    }

    @Override
    public void drawSomethingWrongFile() {
        System.out.println("Something gone wrong while saving your file.");
    }

    @Override
    public void drawClassNotFound() {
        System.out.println("Maybe you have not loaded some classes");
    }

    @Override
    public void showWin() {
        System.out.println("You are the winner !!!");
    }
}
