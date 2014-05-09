package Server.Map;

import Server.Hero.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Maze.java
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

public class Maze {
    private int rows;
    private int columns;
    private Square[][] elements;
    private Square goal;

    private List<Square> opened = new ArrayList<Square>();
    private List<Square> closed = new ArrayList<Square>();
    private List<Square> bestList = new ArrayList<Square>();
    private DefaultMap map = new DefaultMap();
    private List<Hero> heroList;

    public int startX;
    public int startY;
    public int goalX;
    public int goalY;
    private int heroId;
    private int[][] mapPattern;
    private boolean move;

    public Maze(DefaultMap defaultMap, ArrayList<Hero> heroList, int heroId) {
        this.map = defaultMap;
        this.heroList = heroList;
        this.heroId = heroId;

        this.rows = map.getMapHeight();
        this.columns = map.getMapWidth();
        elements = new Square[columns][rows];
        init();
    }

    private void init() {
        createSquares();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setStartAndGoal() {
        elements[startY][startX].setStart(true);
        goal = elements[goalY][goalX];
        goal.setEnd(true);

        bestList.clear();
        opened.clear();
        closed.clear();
    }

    private void generateAdjacencies() {
        mapPattern = map.getMapPattern();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (mapPattern[i][j] == 0 || mapPattern[i][j] == 3) {
                    elements[i][j].setV('+');
                } else {
                    elements[i][j].setV(' ');
                }
            }
        }

        for (int k = 0; k < heroList.size(); k++) {
            if (k != this.heroId) {
                elements[heroList.get(k).getY()][heroList.get(k).getX()].setV(' ');
                elements[heroList.get(k).getY()][heroList.get(k).getX()].setHeroExist(true);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                elements[i][j].calculateAdjacencies();
            }
        }
    }

    private void createSquares() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                elements[i][j] = null;
                elements[i][j] = new Square(i, j, this);
            }
        }
    }

    public Square getSquare(int x, int y) {

        return elements[x][y];
    }

    public void setSquare(Square square) {

        elements[square.getX()][square.getY()] = square;
    }

    public void findBestPath() {
        createSquares();
        setStartAndGoal();
        generateAdjacencies();
        move = false;

        Set<Square> adjacencies = elements[startY][startX].getAdjacencies();
        for (Square adjacency : adjacencies) {
            adjacency.setParent(elements[startY][startX]);
            if (adjacency.isStart() == false) {
                opened.add(adjacency);
            }
        }

        while (opened.size() > 0) {
            Square best = findBestPassThrough();
            opened.remove(best);
            closed.add(best);
            if (best.isEnd()) {
                //Goal found
                populateBestList(goal);

                if (bestList.size() > 5) {
                    clearBestList();
                    move = false;
                } else {
                    move = true;
                    return;
                }

            } else {
                Set<Square> neighbors = best.getAdjacencies();
                for (Square neighbor : neighbors) {
                    if (opened.contains(neighbor)) {
                        Square tmpSquare = new Square(neighbor.getX(),
                                neighbor.getY(), this);
                        tmpSquare.setParent(best);
                        if (tmpSquare.getPassThrough(goal) >= neighbor
                                .getPassThrough(goal)) {
                            continue;
                        }
                    }

                    if (closed.contains(neighbor)) {
                        Square tmpSquare = new Square(neighbor.getX(),
                                neighbor.getY(), this);
                        tmpSquare.setParent(best);
                        if (tmpSquare.getPassThrough(goal) >= neighbor
                                .getPassThrough(goal)) {
                            continue;
                        }
                    }


                    neighbor.setParent(best);

                    opened.remove(neighbor);
                    closed.remove(neighbor);
                    opened.add(0, neighbor);
                }
            }
        }
    }

    private void populateBestList(Square square) {

        bestList.add(square);
        if (square.getParent().isStart() == false) {
            populateBestList(square.getParent());
        }

        return;
    }

    private Square findBestPassThrough() {

        Square best = null;
        for (Square square : opened) {
            if (best == null
                    || square.getPassThrough(goal) < best.getPassThrough(goal)) {
                best = square;
            }
        }

        return best;
    }

    public List<Square> getBestList() {
        return this.bestList;
    }

    public void clearBestList() {
        bestList.clear();
    }

    public boolean isMove() {
        return move;
    }

    public boolean isHeroExist(int x, int y) {
        return elements[y][x].isHeroExist();
    }

    public Hero getHero(int x, int y) {
        Hero h = null;
        if (elements[y][x].isHeroExist() == true) {
            for (int i = 0; i < heroList.size(); i++) {
                if (x == heroList.get(i).getX() && y == heroList.get(i).getY()) {
                    h = heroList.get(i);
                    break;
                }
            }
        }
        return h;
    }

    public boolean isTeamMate(Hero attacked) {
        boolean result = false;
        for (int i = 0; i < heroList.size(); i++) {
            if (attacked == heroList.get(i)) {
                if (heroId % 2 == 0 && i % 2 == 0) {
                    result = true;
                } else if (heroId % 2 == 1 && i % 2 == 1) {
                    result = true;
                } else {
                    result = false;
                }

            }
        }
        System.out.println(result);
        return result;
    }

    public void initMazeWithHeroAndAdj() {
        createSquares();
        generateAdjacencies();
    }
}
