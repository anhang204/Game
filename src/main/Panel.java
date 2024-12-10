package main;

import enity.Background.Heart;
import enity.Bullet;
import enity.Gun;
import enity.Monsters.Warrior;
import enity.Monsters.Boss;
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
    Boss boss = new Boss(player);

    public static ArrayList<Bullet> bullets;
    public static ArrayList<Warrior> warriors;
    public static Boss activeBoss = null; // Biến để lưu thông tin boss

    private long startTime = 0;
    private boolean stopWarriorCreation = false; // Cờ ngăn tạo thêm warrior mới
    private boolean bossCreated = false; // Cờ ngăn tạo nhiều boss

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

        if (player.spriteNum_14Frame == 2) {
            heart.started_action = false;
        }

        if (player.action != "death") {
            gun.update();
            bullet.update1();

            if (!stopWarriorCreation) {
                warrior.update1();
            }

            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).update2();
                if (bullets.get(i).update2()) {
                    bullets.remove(i);
                }
            }

            for (int i = 0; i < warriors.size(); i++) {
                Warrior warrior = warriors.get(i);
                if (warrior.update2()) {
                    warriors.remove(i);
                    i--; // Điều chỉnh chỉ số sau khi xóa
                }
            }


            if (startTime == 0) {
                startTime = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - startTime >= 20000) {
                if (!stopWarriorCreation) {
                    clearWarriors();
                    stopWarriorCreation = true;
                    createBoss();
                }
            }
        } else {
            for (int i = 0; i < warriors.size(); i++) {
                warriors.remove(i);
            }
        }

        for (int i = 0; i < Panel.bullets.size(); i++) {
            Bullet bullet = Panel.bullets.get(i);
            if (bullet.update()) {
                i--;
            }
        }
        if (activeBoss != null) {
            activeBoss.update1();
            if (activeBoss.update2()) {
                activeBoss = null; // Xóa Boss khi chết
            }
        }


    }

    private void clearWarriors() {
        warriors.clear();
    }

    private void createBoss() {
        if (!bossCreated) {
            activeBoss = new Boss(player);
            bossCreated = true; // Chỉ tạo boss một lần
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

        if (activeBoss != null) {
            activeBoss.draw(g2);
        }

        g2.dispose();
    }
}
