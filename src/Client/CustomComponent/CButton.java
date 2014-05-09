package Client.CustomComponent;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * CButton.java
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

public class CButton extends JButton {

    private int index;

    public CButton(Icon icon) {
        super(icon);
        setOpaque(false);
    }

    public CButton(Icon icon, ActionListener al) {
        super(icon);
        this.setActionCommand(icon.toString());
        this.addActionListener(al);

        setOpaque(false);
    }

    public int getIndex() {
        return index;
    }

    public CButton(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }


}
