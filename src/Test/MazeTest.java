package Test;

import Server.Hero.Hero;
import Server.Hero.Huska;
import Server.Map.DefaultMap;
import Server.Map.Maze;
import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * MazeTest.java
 * MOBA Turn-based Online Game
 * Assignment 1, COSC2440 Software Architecture: Design and Implementation
 * RMIT International University Vietnam
 * -
 * Copyright 2013 Vuong Do Thanh Huy      (s3342135)
 * Nguyen Quoc Trong Nghia (s3343711)
 * Kieu Hoang Anh          (s3275058)
 * -
 * Refer to the NOTICE.txt file in the root of the source tree for
 * acknowledgements of third party works used in this software.
 * -
 * Date created: 13/03/2013
 * Date last modified: 05/05/2013
 */

public class MazeTest {

    private static DefaultMap map;
    private static ArrayList<Hero> herolist;
    private static Maze maze;
    private static final int HERO_ID = 0;

    @BeforeClass
    public static void setUp() {
        map = new DefaultMap();
        herolist = new ArrayList<Hero>();
        Huska h1 = new Huska("Huska1", 0, 15);
        Huska h2 = new Huska("Huska2", 0, 15);
        Huska h3 = new Huska("Huska3", 0, 15);
        Huska h4 = new Huska("Huska4", 0, 15);
        herolist.add(h1);
        herolist.add(h2);
        herolist.add(h3);
        herolist.add(h4);
        herolist.get(0).setX(2);
        herolist.get(0).setY(2);
        herolist.get(1).setX(3);
        herolist.get(1).setY(3);
        herolist.get(2).setX(2);
        herolist.get(2).setY(6);
        herolist.get(3).setX(5);
        herolist.get(3).setY(7);
        maze = new Maze(map, herolist, HERO_ID);
    }

    @Test
    public void testHeroExist() {
        maze.initMazeWithHeroAndAdj();
        Assert.assertTrue(maze.isHeroExist(3, 3));
        Assert.assertTrue(maze.isHeroExist(2, 6));
        Assert.assertTrue(maze.isHeroExist(5, 7));
    }

    @Test
    public void testHeroNotExist() {
        maze.initMazeWithHeroAndAdj();
        Assert.assertFalse(maze.isHeroExist(2, 2));
    }

    @Test
    public void testFindBestPathNotFound() {
        maze.startX = herolist.get(HERO_ID).getX();
        maze.startY = herolist.get(HERO_ID).getY();
        maze.goalX = maze.startX + 10;
        maze.goalY = maze.startY + 10;
        maze.clearBestList();
        maze.findBestPath();
        Assert.assertTrue(maze.getBestList().isEmpty());
    }

    @Test
    public void testFindBestPathFound() {
        maze.startX = herolist.get(HERO_ID).getX();
        maze.startY = herolist.get(HERO_ID).getY();
        maze.goalX = maze.startX + 2;
        maze.goalY = maze.startY + 0;
        maze.clearBestList();
        maze.findBestPath();
        Assert.assertFalse("Best List should NOT be empty.", maze.getBestList().isEmpty());
    }

    @Test
    public void testTeamMate() {
        Assert.assertTrue(maze.isTeamMate(herolist.get(0)));
        Assert.assertFalse(maze.isTeamMate(herolist.get(3)));
    }

}