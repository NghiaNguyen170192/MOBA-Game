package Server.Map;

import java.util.HashSet;
import java.util.Set;

/**
 * Square.java
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

public class Square {

    private int x;
    private int y;
    private char v = ' ';
    private boolean start;
    private boolean end;
    private boolean heroExist;
    private double localCost; // cost of getting from this square to goal
    private double parentCost; // cost of getting from parent square to this node

    private Maze maze;
    private Set<Square> adjacencies = new HashSet<Square>();

    private Square parent;

    public Square(int x, int y, Maze maze) {
        this.x = x;
        this.y = y;
        this.maze = maze;
    }

    public Square(int x, int y, Maze maze, char v) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.v = v;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public Set<Square> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(Set<Square> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public Square getParent() {
        return parent;
    }

    public void setParent(Square parent) {
        this.parent = parent;
    }

    public void calculateAdjacencies() {
        int bottom = x + 1;
        int right = y + 1;

        if (bottom < maze.getRows()) {
            if (isAdjacentToBottom(maze)) {
                maze.getSquare(bottom, y).addAdjacency(this);
                this.addAdjacency(maze.getSquare(bottom, y));
            }
        }

        if (right < maze.getColumns()) {
            if (isAdjacentToRight(maze)) {
                maze.getSquare(x, right).addAdjacency(this);
                this.addAdjacency(maze.getSquare(x, right));
            }
        }
    }

    public boolean isAdjacentToBottom(Maze maze) {
        if ((v == '+') && (maze.getSquare(x + 1, y).getV() == '+')) {
            return true;
        }
        return false;
    }

    public boolean isAdjacentToRight(Maze maze) {
        if ((v == '+') && (maze.getSquare(x, y + 1).getV() == '+')) {
            return true;
        }
        return false;
    }

    public void addAdjacency(Square square) {
        adjacencies.add(square);
    }

    public void removeAdjacency(Square square) {
        adjacencies.remove(square);
    }

    public double getPassThrough(Square goal) {
        if (isStart()) {
            return 0.0;
        }
        return getEstimateCost(goal) + getParentCost();
    }

    public double getEstimateCost(Square goal) {
        if (isStart()) {
            return 0.0;
        }

        localCost = 1.0 * (Math.abs(x - goal.getX()) + Math.abs(y - goal.getY()));
        return localCost;
    }

    public double getParentCost() {
        if (isStart()) {
            return 0.0;
        }

        if (parentCost == 0.0) {
            parentCost = 1.0 + .5 * (parent.getParentCost() - 1.0);
        }

        return parentCost;
    }

    public char getV() {
        return v;
    }

    public void setV(char v) {
        this.v = v;
    }

    public boolean isHeroExist() {
        return this.heroExist;
    }

    public void setHeroExist(boolean exist) {
        this.heroExist = exist;
    }
}
