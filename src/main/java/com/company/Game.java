package com.company;

import com.company.map.Drawer;
import com.company.map.GameMap;
import com.company.map.MapGenerator;

import java.io.*;
import java.util.HashMap;

public class Game {

    private final transient Drawer drawer;
    private GameMap map;
    private HashMap<String, Runnable> actions;

    public Game(Drawer drawer) {
        this.drawer = drawer;
        this.actions = new HashMap<>();
    }

    public void play() {
        drawer.drawWelcome();

        actions.put("s", this::start);
        actions.put("e", this::exit);
        actions.put("l", this::load);

        while (true) {
            String next = drawer.nextAction();
            Runnable runnable = actions.get(next);
            if (runnable == null) {
                drawer.showAvailableActions();
                continue;
            }
            runnable.run();
            if (map.isNoMonsters()) {
                drawer.showWin();
                exit();
            }
        }
    }

    private void exit() {
        System.exit(0);
    }

    private void draw() {
        drawer.drawMap(map);
    }

    private void top() {
        map.goTo(0, -1);
        drawer.drawMap(map);
    }

    private void bottom() {
        map.goTo(0, +1);
        drawer.drawMap(map);
    }

    private void left() {
        map.goTo(-1, 0);
        drawer.drawMap(map);
    }

    private void right() {
        map.goTo(+1, 0);
        drawer.drawMap(map);
    }

    private void save() {
        File file = new File(drawer.askFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            drawer.drawFileNotFound();
        } catch (IOException e) {
            drawer.drawSomethingWrongFile();
        }
    }

    private void load() {
        File file = new File(drawer.askFileName());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            map = (GameMap) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            drawer.drawFileNotFound();
        } catch (IOException e) {
            drawer.drawSomethingWrongFile();
        } catch (ClassNotFoundException e) {
            drawer.drawClassNotFound();
        }

        map.setDrawer(drawer);

        installDefaultActions();
    }

    private void start() {
        drawer.drawAskSize();
        int size = drawer.drawAskDigit("size", 50);

        drawer.drawAsk("Monsters");
        int monsters = drawer.drawAskDigit("monsters", size);

        drawer.drawAsk("Obstacles");
        int obstacles = drawer.drawAskDigit("obstacles", size);

        MapGenerator generator = new MapGenerator(size);
        generator.setMonsterCount(monsters);
        generator.setObstacleCount(obstacles);

        map = generator.generate();
        map.setDrawer(drawer);

        installDefaultActions();
    }

    private void installDefaultActions() {
        draw();
        actions.remove("s");
        actions.put("m", this::draw);
        actions.put("t", this::top);
        actions.put("b", this::bottom);
        actions.put("l", this::left);
        actions.put("r", this::right);
        actions.put("s", this::save);
    }

}
