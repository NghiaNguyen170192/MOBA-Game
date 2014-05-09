package Server.Hero;

import javax.swing.*;
import java.awt.*;

/**
 * Hero.java
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

public abstract class Hero {
    private String heroName;
    private int point;
    private int attack;
    private int x;
    private int y;
    protected ImageIcon avatar;

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public Hero(String heroName, int point, int attack) {
        this.heroName = heroName;
        this.point = point;
        this.attack = attack;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void plusPoint(int point) {
        this.point += point;
    }

    public void minusPoint(int point) {
        this.point -= point;

    }

    public int getAttack() {
        return this.attack;
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

    public abstract Image getSpriteImage(int index);

    @Override
    public String toString() {
        String t = getHeroName() + "#" + getPoint() + "#" + getX() + "#" + getY();
        return t;
    }
}
