package Server.Map;

import javax.swing.*;
import java.awt.*;

/**
 * DefaultMap.java
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

public class DefaultMap {
    private int mapWidth = 30;
    private int mapHeight = 30;
    private int mapPattern[][];
    private int tileWidth;
    private int tileHeight;
    private Image[] tileImage;

    public DefaultMap() {

        mapPattern = new int[][]{
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                {4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 4},
                {4, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 4},
                {4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
                {4, 3, 3, 3, 4, 4, 4, 4, 4, 0, 0, 4, 4, 4, 0, 4, 4, 0, 4, 4, 4, 0, 0, 0, 3, 2, 3, 3, 3, 4},
                {4, 3, 2, 3, 4, 4, 4, 0, 0, 0, 0, 4, 4, 4, 0, 4, 4, 0, 4, 4, 4, 0, 0, 3, 3, 3, 3, 3, 3, 4},
                {4, 3, 3, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 3, 3, 3, 0, 3, 2, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 4, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 3, 3, 3, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 4, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 3, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 4, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 3, 2, 3, 4},
                {4, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 2, 3, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 0, 3, 3, 3, 4, 0, 0, 4, 4, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 0, 3, 2, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 0, 0, 3, 3, 3, 0, 0, 4, 0, 0, 4, 4, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 2, 3, 0, 3, 3, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 3, 2, 3, 4},
                {4, 3, 3, 3, 2, 3, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 3, 3, 3, 4},
                {4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
                {4, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 3, 3, 3, 4},
                {4, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4},
                {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
        };

        this.tileImage = new Image[5];
        this.tileImage[0] = new ImageIcon(this.getClass().getResource("/Server/img/grass.png")).getImage();
        this.tileImage[1] = new ImageIcon(this.getClass().getResource("/Server/img/tow1.png")).getImage();
        this.tileImage[2] = new ImageIcon(this.getClass().getResource("/Server/img/tow2.png")).getImage();
        this.tileImage[3] = new ImageIcon(this.getClass().getResource("/Server/img/rock.jpg")).getImage();
        this.tileImage[4] = new ImageIcon(this.getClass().getResource("/Server/img/tree.png")).getImage();

        this.tileWidth = this.tileImage[0].getWidth(null);
        this.tileHeight = this.tileImage[0].getHeight(null);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapPattern(int x, int y, int value) {
        mapPattern[x][y] = value;
    }

    public int[][] getMapPattern() {
        return mapPattern;
    }

    public int getTileWidth() {
        return this.tileWidth;
    }

    public int getTileHeight() {
        return this.tileHeight;
    }

    public Image getTileImage(int x, int y) {
        return this.tileImage[getValue(x, y)];
    }

    public int getValue(int x, int y) {
        return this.mapPattern[x][y];
    }
}
