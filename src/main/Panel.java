package main;

import enity.Background.Heart;
import enity.Bullet;
import enity.Gun;
import enity.Monsters.Warrior;
import enity.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    //GAME STATE
    public int gameState = 0;
    public final int menuState = 0;
    public final int playState = 1;
    public final int gameoverState = 2;
    public final int winState = 3;
    public BufferedImage background;

    //Control Menu
    public int commandNum = 0;

    public Panel() {
        this.setPreferredSize(new Dimension(boardWidth, boardHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHander);
        this.setFocusable(true);

        try{
            background = ImageIO.read(getClass().getResourceAsStream("/background/Asphalt-Road-Urban-Day-22x16-1-1.png.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
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

        //Play state
        if (gameState == playState) {
            player.update();
            heart.update();
            if (player.spriteNum_14Frame == 2) {
                heart.started_action = false;
            }
            //When player alive
            if (player.action != "death") {
                gun.update();
                bullet.update1();
                warrior.update1();

                //Control when bullet hit warrior
                List<Bullet> bulletsToRemove = new ArrayList<>();
                List<Warrior> warriorsToRemove = new ArrayList<>();

                for (int i = 0; i < bullets.size(); i++) {
                    for (int j = 0; j < warriors.size(); j++) {
                        if (bullets.get(i).attackArea.intersects(warriors.get(j).damageArea)) {
                            bulletsToRemove.add(bullets.get(i));
                            if (warriors.get(j).heart <= 0) {
                                warriorsToRemove.add(warriors.get(j));
                            } else {
                                warriors.get(j).action = "hurt";
                                warriors.get(j).heart -= 1;
                            }
                        }
                    }
                }

                bullets.removeAll(bulletsToRemove);
                warriors.removeAll(warriorsToRemove);

                for (int i = 0; i < bullets.size(); i++) {
                    bullets.get(i).update2();
                    if (bullets.get(i).update2() == true) {
                        bullets.remove(i);
                    }
                }
                for (int i = 0; i < warriors.size(); i++) {
                    warriors.get(i).update2();
                    if (warriors.get(i).action == "death") {
                        warriors.remove(i);
                    }
                }

            }

            //When player death
            if (player.action == "death") {
                for (int i = 0; i < warriors.size(); i++) {
                    warriors.remove(i);
                }
                gameState = gameoverState;
            }

            if (warriors.size() == 0 && warrior.numberOfWarrior == 8){
                gameState = winState;
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.drawImage(background,0,0,boardWidth,boardHeight,null);

        if (gameState == menuState) {
            //Title Name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "Last Day On Earth";
            int x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            int y = tileSize*5;
            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            //Player Image
            player.draw(g2);
            gun.draw(g2);

            //Control Menu
            if (keyHander.w_Pressed == true || keyHander.up_Pressed == true) {
                commandNum = 0;
            }
            else if (keyHander.s_Pressed == true || keyHander.down_Pressed == true) {
                commandNum = 1;
            }

            //Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,45F));

            text = "New Game";
            x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            y = tileSize * 13;
            g2.drawString(text,x,y);
            if (commandNum == 0) {
                g2.drawString(">", x - tileSize ,y);
                if (keyHander.enter_Pressed == true) {
                    gameState = playState;
                }
            }

            text = "Quit";
            x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            y = tileSize * 14;
            g2.drawString(text,x,y);
            if (commandNum == 1) {
                g2.drawString(">", x - tileSize ,y);
                if (keyHander.enter_Pressed == true) {
                    System.exit(0);
                }
            }
        }

        if (gameState == playState) {
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
        }

        //Win state
        if (gameState == winState) {
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "YOU WIN!!";
            int x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            int y = tileSize*5;
            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            player.update();
            player.draw(g2);

            //Control
            if (keyHander.w_Pressed == true || keyHander.up_Pressed == true) {
                commandNum = 0;
            }
            else if (keyHander.s_Pressed == true || keyHander.down_Pressed == true) {
                commandNum = 1;
            }

            //Menu
            text = "Quit";
            x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            y = tileSize * 14;
            g2.drawString(text,x,y);
            if (commandNum == 1) {
                g2.drawString(">", x - tileSize ,y);
                if (keyHander.enter_Pressed == true) {
                    System.exit(0);
                }
            }
        }

        //Game over state
        if (gameState == gameoverState){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "GAME OVER";
            int x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            int y = tileSize*5;
            g2.setColor(Color.WHITE);
            g2.drawString(text,x,y);

            player.update();
            player.draw(g2);

            //Control
            if (keyHander.w_Pressed == true || keyHander.up_Pressed == true) {
                commandNum = 0;
            }
            else if (keyHander.s_Pressed == true || keyHander.down_Pressed == true) {
                commandNum = 1;
            }

            //Menu

            text = "Quit";
            x = boardWidth/2 - g2.getFontMetrics().stringWidth(text)/2;
            y = tileSize * 14;
            g2.drawString(text,x,y);
            if (commandNum == 1) {
                g2.drawString(">", x - tileSize ,y);
                if (keyHander.enter_Pressed == true) {
                    System.exit(0);
                }
            }
        }

        g2.dispose();
    }
}