package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Multitrack Player");
        frame.setContentPane(new AudioGUI().multitrackGuiPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);



}
}
