/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.CustomComponent;

/**
 * CPanel.java
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

import javax.swing.*;
import java.awt.*;

public class CPanel extends JPanel {

    public Image image;
    private String s;
    private boolean isSelected;
    private int height = getHeight(), width = getWidth();

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public CPanel() {
        setOpaque(false);
    }

    public CPanel(LayoutManager layout) {
        this.setLayout(layout);
        setOpaque(false);
    }

    public CPanel(LayoutManager layout, Color c) {
        this.setLayout(layout);
        this.setBackground(c);
//        setOpaque(false);
    }

    public void setImage(ImageIcon icon) {
        this.image = icon.getImage();
    }

    public CPanel(ImageIcon icon, int hw) {
        this(new BorderLayout(), icon);
        height = hw;
        width = hw;
    }

    public CPanel(String text) {
        this.s = text;
        setOpaque(false);
    }

    public CPanel(ImageIcon icon) {
        this(new BorderLayout(), icon);
    }

    public CPanel(LayoutManager layout, ImageIcon icon) {

        //this panel is used for paint the image
        setLayout(layout);
        setOpaque(false);
        image = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if(s!=null){
//            g.drawString(s, getWidth(), getHeight());
//        }
        g.drawImage(image, 0, 0, width, height, this);
    }
}
