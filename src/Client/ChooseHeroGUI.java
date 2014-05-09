package Client;

import Client.CustomComponent.CButton;
import Client.CustomComponent.CPanel;
import Server.Hero.Hero;
import Server.Hero.Huska;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * ChooseHeroGUI.java
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

public class ChooseHeroGUI extends CPanel {

    private CPanel mainPn, teamAPn, teamBPn, heroesPn, topPn, botPn;
    private CPanel teamA1, teamA2, teamB1, teamB2;
    private JLabel teamALb, teamBLb;
    private JRadioButton teamARb, teamBRb;
    private ButtonGroup teamBg;
    private JButton submitBt, backBt;
    private final String TEAM_A_TXT = "Team A", TEAM_B_TXT = "Team B", TEAM_SELECT_TXT = "Team select", HERO_SELECT_TXT = "Heroes select", SUBMIT_TXT = "Submit", BACK_TXT = "Back";
    private ArrayList<Hero> heroList = new ArrayList<Hero>();
    private int IMG_SIZE = 30;
    private ImageIcon selectAvatar;
    private String username;

    public ChooseHeroGUI(final String ip, final String id) {
        super(new BorderLayout());
        this.username = id;

        Huska h1 = new Huska("Huska1", 0, 15);
        Huska h2 = new Huska("Huska2", 0, 15);
        Huska h3 = new Huska("Huska3", 0, 15);
        Huska h4 = new Huska("Huska4", 0, 15);
        heroList.add(h1);
        heroList.add(h2);
        heroList.add(h3);
        heroList.add(h4);

        init();

        generateHeroesPn(ip);
        generateTopPn();
        generateBotPn();
        generateMainPn();

        add(mainPn, BorderLayout.CENTER);
    }

    private void init() {
        mainPn = new CPanel(new BorderLayout());
        mainPn.setVisible(true);

        teamAPn = new CPanel(new GridLayout(5, 1));
        teamBPn = new CPanel(new GridLayout(5, 1));

        heroesPn = new CPanel(new GridLayout(3, 3));

        topPn = new CPanel();
        botPn = new CPanel(new GridLayout(1, 2));

        teamAPn.setBorder(BorderFactory.createTitledBorder(TEAM_A_TXT));
        teamBPn.setBorder(BorderFactory.createTitledBorder(TEAM_B_TXT));
        topPn.setBorder(BorderFactory.createTitledBorder(TEAM_SELECT_TXT));
        heroesPn.setBorder(BorderFactory.createTitledBorder(HERO_SELECT_TXT));

        teamBg = new ButtonGroup();
        teamARb = new JRadioButton();
        teamBRb = new JRadioButton();
        teamALb = new JLabel(TEAM_A_TXT);
        teamBLb = new JLabel(TEAM_B_TXT);

        teamARb.setSelected(true);
        teamARb.setActionCommand(TEAM_A_TXT);
        teamBRb.setActionCommand(TEAM_B_TXT);

        teamBg.add(teamARb);
        teamBg.add(teamBRb);

        submitBt = new JButton(SUBMIT_TXT);
        backBt = new JButton(BACK_TXT);
    }


    private void generateMainPn() {
        mainPn.add(teamAPn, BorderLayout.WEST);
        mainPn.add(teamBPn, BorderLayout.EAST);
        mainPn.add(heroesPn, BorderLayout.CENTER);
        mainPn.add(topPn, BorderLayout.NORTH);
        mainPn.add(botPn, BorderLayout.SOUTH);
    }


    private void generateTopPn() {
        topPn.add(teamALb);
        topPn.add(teamARb);
        topPn.add(teamBLb);
        topPn.add(teamBRb);
    }

    private void generateBotPn() {
        submitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Please click directly on the hero icon");
            }
        });
        backBt.setEnabled(false);

        botPn.add(submitBt);
        botPn.add(backBt);
    }

    private void startGame(String team, int heroId, String ip) {
        Main.changePanel(new GameGUI(username, team, heroId, heroList, ip));
    }

    private void generateHeroesPn(final String ip) {

        teamARb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("radiobtn A clicked!");
                heroesPn.removeAll();
                heroesPn.add(new CButton(heroList.get(0).getAvatar(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Team A 0");
                        startGame("A", 0, ip);
                    }
                }));
                heroesPn.add(new CButton(heroList.get(2).getAvatar(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Team A 2");
                        startGame("A", 2, ip);
                    }
                }));
                heroesPn.updateUI();
            }
        });

        teamBRb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("radiobtn B clicked!");
                heroesPn.removeAll();
                heroesPn.add(new CButton(heroList.get(1).getAvatar(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Team B 1");
                        startGame("B", 1, ip);
                    }
                }));
                heroesPn.add(new CButton(heroList.get(3).getAvatar(), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Team B 3");
                        startGame("B", 3, ip);
                    }
                }));
                heroesPn.updateUI();
            }
        });
        heroesPn.setEnabled(false);
    }
}
