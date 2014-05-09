package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

public class Sprite extends JPanel implements Runnable {

    private int totalPictures = 5;
    private int current = 0;
    private int delay = 100;
    private Image[] img;
    private static int x = 0, y = 0;

    public Sprite() {
        img = new Image[6];
        img[0] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf1.png")).getImage();
        img[1] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf2.png")).getImage();
        img[2] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf3.png")).getImage();
        img[3] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf4.png")).getImage();
        img[4] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf5.png")).getImage();
        img[5] = new ImageIcon(this.getClass().getResource("/Server/img/hero/smurf6.png")).getImage();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        if (img[current] != null) {
            g2.drawImage(img[current], x, y, this);
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

    public static void main(String[] args) {

        final Sprite s = new Sprite();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Thread t = new Thread(s);
        t.start();
        frame.add(s);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("pressed right");
                    x += 2;

                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                    System.out.println("pressed left");
                    x -= 2;
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("pressed up");
                    y -= 2;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    System.out.println("pressed down");
                    y += 2;
                }
                s.repaint();
            }
        });

        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
