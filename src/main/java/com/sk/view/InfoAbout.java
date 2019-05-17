package com.sk.view;

import javax.swing.*;

public class InfoAbout {
    public JPanel infoPanel;
    public static void main(String[] args) {

        JFrame infoFrame = new JFrame("InfoAbout");
        infoFrame.setContentPane(new InfoAbout().infoPanel);
        infoFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        infoFrame.pack();
        infoFrame.setVisible(true);

    }
}
