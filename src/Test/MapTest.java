package Test;

import Client.CustomComponent.CPanel;
import Server.Map.DefaultMap;

import javax.swing.*;
import java.awt.*;

/**
 * MapTest.java
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

public class MapTest extends CPanel {
    private int[][] mapPattern;
    public static DefaultMap defaultMap = new DefaultMap();
    public static final int GRID_WIDTH = 20;
    public static final int GRID_HEIGHT = 20;
    private int width;
    private int height;
    private static int mapWidth = 1500;
    private static int mapHeight = 1500;
    private static int mapX = 0;
    private static int mapY = 0;
    private static ImageIcon treeImage = new ImageIcon(MapTest.class.getResource("/Server/img/tree.png"));
    private static ImageIcon rockImage = new ImageIcon(MapTest.class.getResource("/Server/img/rock.jpg"));
    private static ImageIcon baseImage = new ImageIcon(MapTest.class.getResource("/Server/img/tow1.png"));
    private static ImageIcon towImage = new ImageIcon(MapTest.class.getResource("/Server/img/tow2.png"));
    private static ImageIcon grassImage = new ImageIcon(MapTest.class.getResource("/Server/img/grass.png"));
    private static ImageIcon imageArray[] = {grassImage, baseImage, towImage, rockImage, treeImage};


    public MapTest() {

        this.width = defaultMap.getMapWidth();
        this.height = defaultMap.getMapHeight();
        this.mapPattern = defaultMap.getMapPattern();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int x = 0, y = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawImage(grassImage.getImage(), x, y, GRID_WIDTH, GRID_HEIGHT, this);
                g.drawImage(imageArray[mapPattern[i][j]].getImage(), x, y, GRID_WIDTH, GRID_HEIGHT, this);
                g.drawRect(x, y, GRID_WIDTH, GRID_HEIGHT);
                x += GRID_WIDTH;
            }
            y += GRID_HEIGHT;
            x = 0;
        }
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame();

        frame.setSize(1024, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final MapTest map = new MapTest();
        frame.add(map);
        System.out.println(defaultMap.getTileImage(1, 1));
    }

}
