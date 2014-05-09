package Client;

import Client.CustomComponent.CPanel;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ChatPanel.java
 * MOBA Turn-based Online Game
 * Assignment 1, COSC2440 Software Architecture: Design and Implementation
 * RMIT International University Vietnam
 * -
 * Copyright 2013 Vuong Do Thanh Huy      (s3342135)
 * Nguyen Quoc Trong Nghia (sxxxxxxx)
 * Kieu Hoang Anh          (sxxxxxxx)
 * -
 * Refer to the NOTICE.txt file in the root of the source tree for
 * acknowledgements of third party works used in this software.
 * -
 * Date created: 13/03/2013
 * Date last modified: 05/05/2013
 */

public class ChatPanel extends CPanel {

    private static String Server = "127.0.0.1";
    private static final int PORT = 57777;

    private Socket s;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;

    private JPanel buddyListPanel;
    private JTextArea buddy;
    private JScrollPane buddyScroll;

    private JPanel msgPanel;
    private JTextArea msg;
    private JScrollPane msgScroll;

    private JPanel typePanel;
    private JTextField type;

    private static boolean isServerStarted = true;

    public ChatPanel(String team, final String ip, final String username) {
        Server = ip;

        // first, connect to server.
        try {
            s = new Socket(Server, PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {

            isServerStarted = false;

        }

        // signal server the team code.
        try {

            dos.writeUTF(team);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //
        /*
            --------------------
            |                  |
            |                  |
            |  BUDDIES STATUS  |
            |                  |
            |                  |
            | ++++++++++++++++ |
            |                  |
            |                  |
            |                  |
            |    MESSAGES      |
            |                  |
            |                  |
            |                  |
            |                  |
            |                  |
            |                  |
            | ++++++++++++++++ |
            |    textbox       |
            --------------------
         */

        this.setLayout(new BorderLayout(5, 5));

        buddyListPanel = new JPanel();
        msgPanel = new JPanel();
        typePanel = new JPanel();

        //
        buddy = new JTextArea(10, 20);
        buddy.setEditable(false);
        buddy.setBackground(Color.LIGHT_GRAY);
        buddy.setText("This is a place" + "\n" + "to show buddies' status.");
        buddyScroll = new JScrollPane(buddy);
        DefaultCaret caret = (DefaultCaret) buddy.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        buddyListPanel.add(buddyScroll);

        //
        msg = new JTextArea(23, 20);
        msg.setEditable(false);
        msg.setBackground(Color.DARK_GRAY);
        msg.setForeground(Color.WHITE);
        final DataInputStream finalDis = dis;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String t = finalDis.readUTF();
                        if (t != "") {
                            t = username + "> " + t + "\n";
                            msg.append(t);
                        }
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        msgScroll = new JScrollPane(msg);
        DefaultCaret caret1 = (DefaultCaret) msg.getCaret();
        caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        msgPanel.add(msgScroll);

        //
        type = new JTextField(20);
        final DataOutputStream finalDos = dos;
        type.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        if (!type.getText().equals("")) {
                            finalDos.writeUTF(type.getText());
                            type.setText("");
                        }
                    } catch (IOException e1) {
                        e1.getMessage();
                        //Sorry! No Chat Server started. Please start a ChatServer instance.
                    }
                }
            }
        });
        typePanel.add(type);

        this.add(buddyListPanel, BorderLayout.NORTH);
        this.add(msgPanel, BorderLayout.CENTER);
        this.add(typePanel, BorderLayout.SOUTH);
        this.setSize(100, 600);

        if (!isServerStarted) {
            type.setText("CHAT Server NOT STARTED! CHAT DISABLED!");
            type.setEnabled(false);
        }
    }
}
