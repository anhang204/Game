package main;

import enity.Background.Heart;
import enity.Bullet;
import enity.Gun;
import enity.Monsters.Warrior;
import enity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel implements Runnable {

    //SCREEN SETTING
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 20;
    public final int boardWidth = maxScreenCol * tileSize;
    public final int boardHeight = maxScreenRow * tileSize;

    //FPS
    final int FPS = 60;

    //System
    KeyHander keyHander = new KeyHander();
    Thread gameThread;

    //Enity and object
    Player player = new Player(this, keyHander);
    Heart heart = new Heart(player);
    Gun gun = new Gun(player);
    Bullet bullet = new Bullet(gun);
    Warrior warrior = new Warrior(player);

    public static ArrayList<Bullet> bullets;
    public static ArrayList<Warrior> warriors;

    public Panel() {
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHander);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        bullets = new ArrayList<Bullet>();
        warriors = new ArrayList<Warrior>();

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime = System.nanoTime();

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.update();
        heart.update();
        if(player.spriteNum_14Frame == 2){
            heart.started_action = false;
        }
        //When player alive
        if(player.action != "death" ) {
            gun.update();
            bullet.update1();
            warrior.update1();

            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update2();
                if (bullets.get(i).update2() == true) {
                    bullets.remove(i);
                }
            }
            for (int i = 0; i < warriors.size(); i++) {
                warriors.get(i).update2();
                if (warriors.get(i).update2() == true) {
                    warriors.remove(i);
                }
            }
        }

        //When player death
        else{
            for (int i = 0; i < warriors.size(); i++) {
                warriors.remove(i);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);
        heart.draw(g2);
        gun.draw(g2);

        if (bullets != null) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(g2);
            }
        }

        if (warriors != null) {
            for (int i = 0; i < warriors.size(); i++) {
                warriors.get(i).draw(g2);
            }
        }

        g2.dispose();
    }
}