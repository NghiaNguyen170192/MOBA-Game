package Test;

import Client.Network;
import Server.Hero.Hero;
import Server.Hero.Huska;
import Server.Map.Board;
import Server.Map.DefaultMap;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TestBoard.java
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

public class TestBoard {

    public static void main(String[] args) throws IOException {

        boolean connectSuccess = Network.connectToServer("A", "127.0.0.1");
        System.out.println(connectSuccess);
        if (!connectSuccess) {
            System.out.println("Connect to server failed. Wrong command or exceed max team members.");
            System.exit(0);
        }

        JFrame frame = new JFrame();

        DefaultMap map = new DefaultMap();
        ArrayList<Hero> herolist = new ArrayList<Hero>();

        Huska h1 = new Huska("Huska1", 0, 15);
        Huska h2 = new Huska("Huska2", 0, 15);
        Huska h3 = new Huska("Huska3", 0, 15);
        Huska h4 = new Huska("Huska4", 0, 15);
        herolist.add(h1);
        herolist.add(h2);
        herolist.add(h3);
        herolist.add(h4);

        Board board = new Board(map, herolist, 0);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(board);
        frame.setSize(1024, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
