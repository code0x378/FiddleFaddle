package com.fattysmurff.fiddlefaddle.swing;

import javax.swing.*;

public class FiddleFaddle {
    public static void main(String s[]) {

        MainFrame frame = new MainFrame();
        frame.setSize(300, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
