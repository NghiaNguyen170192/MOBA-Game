package Test;

import Server.ChatServer.ChatServer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * ChatTest.java
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

public class ChatTest {
    private static Socket sTeamA;
    private static DataInputStream disTeamA = null;
    private static DataOutputStream dosTeamA = null;
    private static Socket sTeamB;
    private static DataInputStream disTeamB = null;
    private static DataOutputStream dosTeamB = null;

    @BeforeClass
    public static void setUp() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ChatServer chat = ChatServer.createServer();
                try {
                    chat.initServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("chat server started!");
                try {
                    chat.startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(2000); // safe time to wait until the server full startup.
    }

    @Test
    public void testChat() throws Exception {
        try {
            sTeamA = new Socket("127.0.0.1", 57777);
            disTeamA = new DataInputStream(sTeamA.getInputStream());
            dosTeamA = new DataOutputStream(sTeamA.getOutputStream());
        } catch (Exception e) {
        }

        try {

            dosTeamA.writeUTF("A");
        } catch (IOException e) {
        }

        String s = "test chat message";
        dosTeamA.writeUTF(s);
        Assert.assertEquals(disTeamA.readUTF(), s);
    }

    @Test
    public void testLeakChatBetweenTwoTeams() {
        try {
            sTeamA = new Socket("127.0.0.1", 57777);
            disTeamA = new DataInputStream(sTeamA.getInputStream());
            dosTeamA = new DataOutputStream(sTeamA.getOutputStream());
        } catch (Exception e) {
        }

        try {

            dosTeamA.writeUTF("A");
        } catch (IOException e) {
        }

        try {
            sTeamB = new Socket("127.0.0.1", 57777);
            disTeamB = new DataInputStream(sTeamB.getInputStream());
            dosTeamB = new DataOutputStream(sTeamB.getOutputStream());
        } catch (Exception e) {
        }

        try {

            dosTeamB.writeUTF("B");
        } catch (IOException e) {
        }

        String msgA = "test chat message A";
        String msgB = "test chat message B";
        try {
            dosTeamA.writeUTF(msgA);
            dosTeamB.writeUTF(msgB);
        } catch (IOException e) {
        }
        try {
            Assert.assertFalse(disTeamA.readUTF().equals(msgB));
            Assert.assertFalse(disTeamB.readUTF().equals(msgA));
        } catch (IOException e) {
        }
    }
}
