package Client;

import Client.CustomComponent.CLabel;
import Client.CustomComponent.CPanel;
import Server.Database.DbUtil;
import Server.Database.MessageDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginGUI.java
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

public class LoginGUI extends CPanel implements GameInterface {
    private static ImageIcon background = new ImageIcon(LoginGUI.class.getResource("/Server/img/background.jpg"));
    private static JTextField usernameTF;
    private static JPasswordField passTF;
    private static CPanel loginPanel, mainPanel;
    private static CLabel usernameLb, passLb;
    private static JButton loginBt, exitBt;
    private DbUtil dbUtil;
    private MessageDao dao;
    private static ApplicationContext ctx;
    private static String ServerIp;

    public LoginGUI(final String ip) {
        super(new GridBagLayout(), background);
        ServerIp = ip;
        init();
        initBtn();
        getLoginPanel();
        add(mainPanel);
    }

    private void init() {
        usernameTF = new JTextField(10);
        passTF = new JPasswordField(10);
        usernameLb = new CLabel("Username", TEXT_SIZE);
        passLb = new CLabel("Password", TEXT_SIZE);
        loginPanel = new CPanel();
        loginBt = new JButton("Login");
        exitBt = new JButton("Exit");
        mainPanel = new CPanel();
    }

    private void getLoginPanel() {
        mainPanel.setLayout(new BorderLayout());
        loginPanel.setLayout(new GridLayout(4, 2));
        loginPanel.add(usernameLb);
        loginPanel.add(usernameTF);
        loginPanel.add(passLb);
        loginPanel.add(passTF);
        loginPanel.add(new CPanel());
        loginPanel.add(new CPanel());
        loginPanel.add(loginBt);
        loginPanel.add(exitBt);
        loginPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(loginPanel);
        mainPanel.setBorder(BorderFactory.createTitledBorder("SIGN-UP"));
    }

    private void initBtn() {
        loginBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        exitBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void login() {
        ctx = new ClassPathXmlApplicationContext("/Client/spring-db.xml");
        dbUtil = ctx.getBean("dbUtil", DbUtil.class);
        dao = ctx.getBean("dao", MessageDao.class);
//        dbUtil.createDb();

        String id = usernameTF.getText();
        String pwd = new String(passTF.getPassword());
        System.out.println(dao.login(id, pwd));
        if (dao.login(id, pwd)) {
            System.out.println("LOGIN OK!");
            Main.changePanel(new ChooseHeroGUI(ServerIp, id));
        } else {
            System.out.println("LOGIN FAILED!");
            JOptionPane.showMessageDialog(this, "Login failed!", "Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
