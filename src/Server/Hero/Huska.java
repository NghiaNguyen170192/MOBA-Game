package Server.Hero;

import javax.swing.*;
import java.awt.*;

/**
 * Huska.java
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

public class Huska extends Hero {

    private Image[] spriteImage;


    public Huska(String heroName, int point, int attack) {
        super(heroName, point, attack);
        spriteImage = new Image[6];
        spriteImage[0] = new ImageIcon(this.getClass().getResource("/Server/img/hero/Huska/hero1.png")).getImage();
        spriteImage[1] = new ImageIcon(this.getClass().getResource("/Server/img/hero/Huska/hero2.png")).getImage();
        spriteImage[2] = new ImageIcon(this.getClass().getResource("/Server/img/hero/Huska/hero3.png")).getImage();
        spriteImage[3] = new ImageIcon(this.getClass().getResource("/Server/img/hero/Huska/hero4.png")).getImage();

        avatar = new ImageIcon(this.getClass().getResource("/Server/img/hero/Huska/avatar.png"));
    }

    public Image getSpriteImage(int index) {
        if (index >= 0 && index <= 5) {
            return this.spriteImage[index];
        } else {
            System.out.println("Invalid index");
            return null;
        }

    }

}
