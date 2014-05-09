package Server.GameServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

/**
 * GameWorker.java
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

public class GameWorker implements Runnable, Observer {

    private Socket socket;
    private CmdPool cmdPool;
    private DataInputStream dis;
    private DataOutputStream dos;

    public GameWorker(Socket socket, CmdPool cmdPool) {
        this.socket = socket;
        this.cmdPool = cmdPool;
        cmdPool.addObserver(this);
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
        System.out.println("cmdPool number: " + cmdPool.getTeam());
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            cmdPool.setCmd(dis.readUTF());
                            System.out.println("RECEIVED: " + cmdPool.getCmd());
                            Thread.sleep(20);
                        }
                    } catch (IOException e) {
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        } catch (Exception e) {
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        String s = ((CmdPool) o).getCmd();
        try {
            if (cmdPool.getCmd() != "") {
                System.out.println("SENDING: " + cmdPool.getCmd());
                dos.writeUTF(cmdPool.getCmd());
            }
            Thread.sleep(10);
        } catch (Exception e) {
        }
    }
}
