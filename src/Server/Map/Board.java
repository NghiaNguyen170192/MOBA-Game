package Server.Map;

import Client.Network;
import Server.Hero.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Board.java
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

public class Board extends JPanel implements MouseMotionListener, MouseListener {
    private DefaultMap map;

    private ArrayList<Hero> hero;

    private int mX, mY, eX = -1, eY = -1, x, y;
    public static final int GRID_WIDTH = 50;
    public static final int GRID_HEIGHT = 50;
    private int heroId;
    private Maze m;

    private int dX;
    private int dY;

    public Board() {
    }

    public ArrayList<Hero> getHero() {
        return hero;
    }

    public Board(DefaultMap map, ArrayList<Hero> heroList, int id) {
        this.heroId = id;

        this.map = map;
        this.hero = heroList;
        m = new Maze(this.map, this.hero, heroId);

        hero.get(0).setX(2);
        hero.get(0).setY(2);

        hero.get(1).setX(3);
        hero.get(1).setY(3);
//
        hero.get(2).setX(2);
        hero.get(2).setY(6);

        hero.get(3).setX(5);
        hero.get(3).setY(7);

        setBounds(0, 0, 30 * GRID_WIDTH, 30 * GRID_HEIGHT);
        setLayout(new BorderLayout());
        setFocusable(true);

        addMouseListener(this);
        addMouseMotionListener(this);

        Network.startListeningforData(this);

        m.startX = hero.get(heroId).getX();
        m.startY = hero.get(heroId).getY();

        m.findBestPath();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;
        int y = 0;

        g.setColor(Color.WHITE);
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                g.setColor(Color.black);
                g.drawString(j + "," + (i - 1), x, y);

                g.drawImage(map.getTileImage(7, 7), x, y, GRID_WIDTH, GRID_HEIGHT, null);
                g.drawImage(map.getTileImage(i, j), x, y, GRID_WIDTH, GRID_HEIGHT, null);
                x += GRID_WIDTH;

                for (int k = 0; k < m.getBestList().size(); k++) {
                    g.setColor(Color.red);

                    g.drawRect(m.getBestList().get(k).getY() * GRID_WIDTH,
                            m.getBestList().get(k).getX() * GRID_HEIGHT,
                            GRID_WIDTH, GRID_HEIGHT);
                }

                for (int k = 0; k < hero.size(); k++) {
                    g.drawImage(hero.get(k).getSpriteImage(k), hero.get(k).getX() * GRID_WIDTH, hero.get(k).getY() * GRID_HEIGHT, GRID_WIDTH, GRID_HEIGHT, null);
                }

            }
            x = 0;
            y += GRID_HEIGHT;
        }

        //Start
        g.setColor(Color.red);
        g.drawRect(hero.get(heroId).getX() * GRID_WIDTH, hero.get(heroId).getY() * GRID_HEIGHT, GRID_WIDTH, GRID_HEIGHT);

        //Goal
        g.setColor(Color.pink);
        g.drawRect(m.goalX * GRID_WIDTH, m.goalY * GRID_HEIGHT, GRID_WIDTH, GRID_HEIGHT);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        int a, b;
        int positionX, positionY;
        if (x < 50) {
            eX = 0;
        } else {
            a = x % GRID_WIDTH;
            eX = (x - a);
        }
        if (y < 50) {
            eY = 0;
        } else {
            b = y % GRID_HEIGHT;
            eY = (y - b);
        }

        positionX = mX / GRID_WIDTH - mX % GRID_WIDTH;
        positionY = mY / GRID_HEIGHT - mY % GRID_HEIGHT;

        if (m.isMove() == true) {
            m.startX = mX / GRID_WIDTH - mX % GRID_WIDTH;
            m.startY = mY / GRID_HEIGHT - mY % GRID_HEIGHT;

            m.clearBestList();
            m.findBestPath();

            hero.get(heroId).setX(m.startX);
            hero.get(heroId).setY(m.startY);

        } else {
            if (m.isHeroExist(positionX, positionY) == true) {
                if (m.getHero(positionX, positionY) != null) {
                    if (m.isTeamMate(m.getHero(positionX, positionY)) != true) {
                        attack(hero.get(heroId), m.getHero(positionX, positionY));
                    }
                }

            }
        }

        repaint();

        Network.sendHeroData(hero);
    }

    public int mapBound(int low, int high, int value) {
        if (value < low) {
            return low;
        } else if (value > high) {
            return high;
        } else {
            return value;
        }
    }

    private void attack(Hero attacker, Hero attacked) {

        attacker.plusPoint(attacker.getAttack());
        attacked.minusPoint(attacker.getAttack());
        System.out.println("Attacker Hero " + attacker.getHeroName() + " " + attacker.getPoint() + " point");
        System.out.println("Attacked Hero " + attacked.getHeroName() + " " + attacked.getPoint() + " point");
    }


    @Override
    public void mousePressed(MouseEvent e) {
        dX = e.getX();
        dY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int xPoint = e.getX() - dX + getLocation().x;
        int yPoint = e.getY() - dY + getLocation().y;

        setLocation(mapBound(530 - 30 * GRID_WIDTH, 0, xPoint),
                mapBound(530 - 30 * GRID_HEIGHT + 30, 0, yPoint));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int a, b;

        if (x < 50) {
            mX = 0;
        } else {
            a = x % GRID_WIDTH;
            mX = (x - a);
        }
        if (y < 50) {
            mY = 0;
        } else {
            b = y % GRID_HEIGHT;
            mY = (y - b);
        }

        m.goalX = mX / GRID_WIDTH;
        m.goalY = mY / GRID_HEIGHT;
        m.findBestPath();
        m.goalX = (mX / GRID_WIDTH);
        m.goalY = mY / GRID_HEIGHT;
        m.findBestPath();

        repaint();
    }
}
