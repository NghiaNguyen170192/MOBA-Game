package Server.Map;

/**
 * CustomMap.java
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

public class CustomMap {
    private int width;
    private int height;
    private int[][] mapPattern;
    private int x;
    private int y;
    private int obstacle;

    public CustomMap(int width, int height) {
        this.width = width;
        this.height = height;
        mapPattern = new int[height][width];
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getMapPattern() {
        return mapPattern;
    }

    public void setMapDetails(int x, int y, int obstacle) {
        mapPattern[x][y] = obstacle;
    }
}
