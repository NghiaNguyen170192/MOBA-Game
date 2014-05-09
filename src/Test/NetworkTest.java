package Test;

import Client.Network;
import Server.GameServer.GameServer;
import Server.Hero.Hero;
import Server.Map.Board;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;

/**
 * NetworkTest.java
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

public class NetworkTest {

    @BeforeClass
    public static void setUp() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GameServer game = GameServer.createServer();
                try {
                    game.initServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("game server started!");
                try {
                    game.startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread.sleep(2000);
    }

    @Test
    public void testConnectToServer() throws Exception {
        Assert.assertEquals(Network.connectToServer("A", "127.0.0.1"), true);
    }

    @Test
    public void testStartListeningforData() throws Exception {
        Assert.assertEquals(Network.startListeningforData(new Board()), true);
    }

    @Test
    public void testSendHeroData() throws Exception {
        Assert.assertEquals(Network.sendHeroData(new ArrayList<Hero>()), true);
    }

}
