/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.CustomComponent;

import javax.swing.*;
import java.awt.*;

/**
 * CDialog.java
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


public class CDialog extends JDialog {

    public Image image;

    public CDialog(ImageIcon icon) {
        image = icon.getImage();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

    }


}
