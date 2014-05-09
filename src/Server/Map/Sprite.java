package Server.Map;

import Client.CustomComponent.CPanel;
import Server.Hero.Hero;

import java.awt.*;

/**
 * Sprite.java
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

public class Sprite extends CPanel implements Runnable {

    private int totalPictures;
    private int current = 0;
    private int delay = 100;
    private Image[] img;
    private static int x = 0, y = 0;
    private final int SPEED = 5;
    private int width = 50, height = 50;
    private Hero h;

    public Sprite(Hero h) {
        this.h = h;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (img[current] != null) {
            g2.drawImage(img[current], x, y, width, height, this);
        }
    }

    @Override
    public void run() {
        while (true) {
            current++;
            if (current >= totalPictures) {
                try {
                    current = 0;
                    repaint();
                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    repaint();
                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void setStart(int fromX, int fromY) {
        x = fromX;
        y = fromY;
    }

    public void moveTo(final int toX, final int toY) {


        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int tempX = toX - x;
                int tempY = toY - y;
                while ((x != toX) || (y != toY)) {
                    if (x != toX) {
                        if ((tempX > 0)) {
                            x += SPEED;
                        } else {
                            x -= SPEED;
                        }
                    }
                    if (y != toY) {
                        if (tempY > 0) {
                            y += SPEED;
                        } else {
                            y -= SPEED;
                        }
                    }

                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

    }

}
