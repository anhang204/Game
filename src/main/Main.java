package main;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        Panel panel = new Panel();
        this.add(panel);
        this.pack();

        this.setTitle("Last Day On Earth");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.startGameThread();
    }
    public static void main(String[] args) {
        System.out.println("hello");
        new Main();
    }
}