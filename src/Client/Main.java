package Client;

import javax.swing.*;
import java.util.Scanner;

/**
 * Main.java
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

public class Main extends JFrame {

    private static JPanel currentPanel;
    private static Main mainFrame;

    public Main() {
        currentPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setTitle("MOBA Turn-based Game");
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        add(currentPanel);
    }

    public static Main getMainFrame() {
        return mainFrame;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Please input SERVER IP: (press Enter when done)");
        String ip = input.nextLine();

        try {
            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Cannot load the Nimbus theme!!!");
        }

        mainFrame = new Main();
        changePanel(new LoginGUI(ip));
    }

    public static void changePanel(JPanel newPanel) {
        mainFrame.remove(currentPanel);
        currentPanel = newPanel;
        mainFrame.add(currentPanel);
        mainFrame.validate();
        System.gc();
    }
}
