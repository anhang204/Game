
package main;

import enity.Background.Heart;
import enity.Bullet;
import enity.Gun;
import enity.Monsters.Boss;
import enity.Monsters.Warrior;
import enity.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Objects;

public class Panel extends JPanel implements Runnable {

    // SCREEN SETTING
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 30;
    final int maxScreenRow = 20;
    public final int boardWidth = maxScreenCol * tileSize;
    public final int boardHeight = maxScreenRow * tileSize;


    // FPS
    final int FPS = 60;

    // System
    KeyHander keyHander = new KeyHander();
    Thread gameThread;

    // Entity and object
    Player player = new Player(this, keyHander);
    Heart heart = new Heart(player);
    Gun gun = new Gun(player);
    Bullet bullet = new Bullet(gun);
    Warrior warrior = new Warrior(player);

    public static ArrayList<Bullet> bullets;
    public static ArrayList<Warrior> warriors;
    public static Boss activeBoss = null;
    private long startTime = 0;
    private boolean stopWarriorCreation = false;
    private boolean bossCreated = false;

    // Background image
    private Image backgroundImage;

    private Sound sound = new Sound();

    public Panel() {
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHander);
        this.setFocusable(true);

        sound.playLoopedSound("game-music.wav");

        // Load the background image
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/background/main_bg.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNotify() {
        super.addNotify();
        this.requestFocusInWindow();
    }
    public void startGameThread() {
        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        bullets = new ArrayList<>();
        warriors = new ArrayList<>();

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            try {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            } catch (Exception e) {
                e.printStackTrace(); // Catch any runtime errors during the game loop
            }
        }
    }


    public void update() {
        if (player != null) {
            player.update();
            heart.update();

            if (!player.action.equals("death")) {
                gun.update();
                bullet.update1();

                if (!stopWarriorCreation) {
                    warrior.update1();
                }

                for (int i = 0; i < bullets.size(); i++) {
                    if (bullets.get(i).update2()) {
                        bullets.remove(i);
                    }
                }

                for (int i = 0; i < warriors.size(); i++) {
                    if (warriors.get(i).update2()) {
                        warriors.remove(i);
                        i--;
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
                sound.stopSound();
                sound.playSound("gameover_music.wav");
                warriors.clear();
            }

            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).update()) {
                    i--;
                }
            }

            if (activeBoss != null) {
                activeBoss.update1();
                if (activeBoss.update2()) {
                    activeBoss = null;
                }
            }
        }
    }


    private void clearWarriors() {
        warriors.clear();
    }

    private void createBoss() {
        if (!bossCreated) {
            if (player != null) { // Ensure player is not null
                try {
                    activeBoss = new Boss(player);
                    bossCreated = true;
                } catch (Exception e) {
                    e.printStackTrace(); // Log any exception during Boss creation
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw the background image
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        }
        // Draw other game elements
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

