package Server.ChatServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ChatServer.java
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

public class ChatServer {

    private static final int SERVER_PORT = 57777;

    private static ChatServer chatServer;
    private static ServerSocket serverSocket;

    private ChatServer() {
    }

    public static ChatServer createServer() {
        if (chatServer == null) {
            chatServer = new ChatServer();
        }
        return chatServer;
    }

    public void initServer() throws IOException {
        serverSocket = new ServerSocket(SERVER_PORT);
    }

    public void startServer() throws IOException {
        MessagePool msgPoolA = new MessagePool("A");
        MessagePool msgPoolB = new MessagePool("B");
        while (true) {
            Socket socket = serverSocket.accept();
            if (new DataInputStream(socket.getInputStream()).readUTF().equals("A")) {
                Thread worker = new Thread(new ServerWorker(socket, msgPoolA));
                worker.start();
            } else {
                Thread worker = new Thread(new ServerWorker(socket, msgPoolB));
                worker.start();
            }
        }
    }

}
