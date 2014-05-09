package Server.GameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * GameServer.java
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

public class GameServer {

    private static final int SERVER_PORT = 58888;

    private static GameServer gameServer;
    private static ServerSocket serverSocket;

    private static int countA = 1;
    private static int countB = 1;

    private GameServer() {
    }

    public static GameServer createServer() {
        if (gameServer == null) {
            gameServer = new GameServer();
        }
        return gameServer;
    }

    public void initServer() throws IOException {
        serverSocket = new ServerSocket(SERVER_PORT);

    }

    public void startServer() throws IOException {

        CmdPool teamA = new CmdPool("A");
        CmdPool teamB = new CmdPool("B");

        while (true) {
            Socket socket = serverSocket.accept();
            if (new DataInputStream(socket.getInputStream()).readUTF().equals("A") && countA <= 2) {
                Thread worker = new Thread(new GameWorker(socket, teamA));
                countA++;
                worker.start();
            } else if (new DataInputStream(socket.getInputStream()).readUTF().equals("B") && countB <= 2) {
                Thread worker = new Thread(new GameWorker(socket, teamB));
                countB++;
                worker.start();
            } else {
                new DataOutputStream(socket.getOutputStream()).writeUTF("REJECTED");
            }
        }
    }
}
