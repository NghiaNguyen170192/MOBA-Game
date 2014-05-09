package Client;

import Server.Hero.Hero;
import Server.Map.Board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Network.java
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

public class Network {

    private static String Server = "127.0.0.1";
    private static final int PORT = 58888;
    private static Socket s;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;

    public static boolean connectToServer(final String team, final String ip) {
        Server = ip;
        try {
            s = new Socket(Server, PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception e) {
            return false;
        }
        try {
            dos.writeUTF(team);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public static boolean startListeningforData(final Board board) {
        final DataInputStream finalDis = dis;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String t = finalDis.readUTF();
                        if (t != "") {
                            System.out.println("cmd RECV::" + t);
                            // A|HERO|MOVE|X|Y
                            String arr[] = t.split("\\|");
                            final int HERO_1 = 2;
                            final int HERO_2 = 3;
                            final int HERO_3 = 4;
                            final int HERO_4 = 5;

                            for (int i = 2; i < 6; i++) {
                                String heroData[] = arr[i].split("#");
                                final int HERO_NAME = 0;
                                final int HERO_POINT = 1;
                                final int HERO_X = 2;
                                final int HERO_Y = 3;

                                String name = heroData[HERO_NAME];
                                int p = Integer.parseInt(heroData[HERO_POINT]);
                                int x = Integer.parseInt(heroData[HERO_X]);
                                int y = Integer.parseInt(heroData[HERO_Y]);

                                for (Hero h : board.getHero()) {
                                    if (h.getHeroName().equalsIgnoreCase(name)) {
                                        h.setPoint(p);
                                        h.setX(x);
                                        h.setY(y);
                                    }
                                }
                                board.repaint();
                            }

                        }
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        return true;
    }

    public static boolean sendHeroData(final ArrayList<Hero> heroList) {
        try {
            String t = "A|HERO";

            for (Hero h : heroList) {
                String a = h.toString();
                t = t + "|" + a;
            }
            dos.writeUTF(t);

        } catch (IOException e1) {
            return false;
        }
        return true;
    }
}
