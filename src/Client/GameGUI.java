package Client;

import Client.CustomComponent.CPanel;
import Server.Hero.Hero;
import Server.Map.Board;
import Server.Map.DefaultMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * GameGUI.java
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

public class GameGUI extends CPanel {

    private String username;
    private CPanel mainPn, chatPn, messagePn;

    public GameGUI(String username, String team, int id, ArrayList<Hero> heroList, final String ip) {
        this.username = username;

        boolean connectSuccess = Network.connectToServer(team, ip);
        System.out.println(connectSuccess);
        if (!connectSuccess) {
            System.out.println("Connect to server failed. Wrong command or exceed max team members.");
            System.exit(0);
        }

        chatPn = new ChatPanel(team, ip, username);
        setLayout(new BorderLayout());

        DefaultMap map = new DefaultMap();
        Board board = new Board(map, heroList, id);
        JPanel ppp = new JPanel();

        ppp.setLayout(null);
        ppp.add(board);
        add(ppp, BorderLayout.CENTER);

        add(chatPn, BorderLayout.WEST);
    }
}
