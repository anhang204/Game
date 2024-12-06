package enity.Monsters;

import enity.Enity;
import enity.Player;
import main.KeyHander;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Warrior extends Enity {
    int heart = 2;
    int speed = 5;
    int distance_attack = 70;

    private long spamMonsterTimer;
    private long spamMonsterDelay;

    Panel panel;
    KeyHander keyHander;
    Player player;

    public Warrior(Player player) {
        this.panel = player.panel;
        this.keyHander = player.keyHander;
        this.player = player;

        setDefaltValues_Warrior();
        getWarriorImage();

        spamMonsterTimer = System.nanoTime();
        spamMonsterDelay = 10;
    }

    public void setDefaltValues_Warrior(){
        width = panel.tileSize*2;
        height = panel.tileSize*2;

        Random rand = new Random();
        int randomPosition = rand.nextInt(4) + 1;
        if (randomPosition == 1){
            y = 0 - height;
            x = rand.nextInt(panel.boardWidth+1);
        }
        else if (randomPosition == 2){
            y = panel.boardHeight;
            x = rand.nextInt(panel.boardWidth+1);
        }
        else if (randomPosition == 3){
            x = panel.boardWidth;
            y = rand.nextInt(panel.boardHeight+1);
        }
        else if (randomPosition == 4){
            x = 0 - width;
            y = rand.nextInt(panel.boardHeight+1);
        }

        attackArea = new Rectangle(x,y,width,height);
        damageArea = new Rectangle(x,y,width,height);
    }

    public void getWarriorImage(){
        try {
            warriorMoveRight1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Right-1.png.png"));
            warriorMoveRight2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Right-2.png.png"));
            warriorMoveRight3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Right-3.png.png"));
            warriorMoveRight4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Right-4.png.png"));
            warriorMoveRight5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Right-5.png.png"));

            warriorMoveLeft1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Left-1.png.png"));
            warriorMoveLeft2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Left-2.png.png"));
            warriorMoveLeft3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Left-3.png.png"));
            warriorMoveLeft4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Left-4.png.png"));
            warriorMoveLeft5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/move/Warrior_Move_Left-5.png.png"));

            warriorAttack1Right1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-1.png.png"));
            warriorAttack1Right2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-2.png.png"));
            warriorAttack1Right3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-3.png.png"));
            warriorAttack1Right4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-4.png.png"));
            warriorAttack1Right5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-5.png.png"));
            warriorAttack1Right6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-6.png.png"));
            warriorAttack1Right7 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-7.png.png"));
            warriorAttack1Right8 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Right-8.png.png"));

            warriorAttack1Left1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-1.png.png"));
            warriorAttack1Left2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-2.png.png"));
            warriorAttack1Left3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-3.png.png"));
            warriorAttack1Left4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-4.png.png"));
            warriorAttack1Left5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-5.png.png"));
            warriorAttack1Left6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-6.png.png"));
            warriorAttack1Left7 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-7.png.png"));
            warriorAttack1Left8 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack1/Warrior_Attack1_Left-8.png.png"));

            warriorAttack2Right1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-1.png.png"));
            warriorAttack2Right2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-2.png.png"));
            warriorAttack2Right3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-3.png.png"));
            warriorAttack2Right4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-4.png.png"));
            warriorAttack2Right5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-5.png.png"));
            warriorAttack2Right6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Right-6.png.png"));

            warriorAttack2Left1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-1.png.png"));
            warriorAttack2Left2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-2.png.png"));
            warriorAttack2Left3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-3.png.png"));
            warriorAttack2Left4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-4.png.png"));
            warriorAttack2Left5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-5.png.png"));
            warriorAttack2Left6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/attack2/Warrior_Attack2_Left-6.png.png"));

            warriorHurtRight = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/hurt/Warrior_Hurt_Right-1.png.png"));
            warriorHurtLeft = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/hurt/Warrior_Hurt_Left-1.png.png"));

            warriorDeathRight1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-1.png.png"));
            warriorDeathRight2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-2.png.png"));
            warriorDeathRight3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-3.png.png"));
            warriorDeathRight4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-4.png.png"));
            warriorDeathRight5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-5.png.png"));
            warriorDeathRight6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-6.png.png"));
            warriorDeathRight7 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-7.png.png"));
            warriorDeathRight8 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-8.png.png"));
            warriorDeathRight9 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-9.png.png"));
            warriorDeathRight10 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-10.png.png"));
            warriorDeathRight11 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-11.png.png"));
            warriorDeathRight12 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-12.png.png"));
            warriorDeathRight13 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Right-13.png.png"));

            warriorDeathLeft1 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-1.png.png"));
            warriorDeathLeft2 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-2.png.png"));
            warriorDeathLeft3 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-3.png.png"));
            warriorDeathLeft4 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-4.png.png"));
            warriorDeathLeft5 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-5.png.png"));
            warriorDeathLeft6 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-6.png.png"));
            warriorDeathLeft7 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-7.png.png"));
            warriorDeathLeft8 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-8.png.png"));
            warriorDeathLeft9 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-9.png.png"));
            warriorDeathLeft10 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-10.png.png"));
            warriorDeathLeft11 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-11.png.png"));
            warriorDeathLeft12 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-12.png.png"));
            warriorDeathLeft13 = ImageIO.read(getClass().getResourceAsStream("/monsters/warrior/death/Warrior_Death_Left-13.png.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update1(){
        if (keyHander.enter_Pressed == true ){
                Panel.warriors.add(new Warrior(this.player));
                spamMonsterTimer = System.nanoTime();
        }
    }

    public boolean update2(){
        double distance_to_playerX = player.x-x;
        double distance_to_playerY = player.y-y;

        double distance_to_player = Math.sqrt(Math.pow(distance_to_playerX,2) + Math.pow(distance_to_playerY,2));

        if (distance_to_player == 0){
            distance_to_player = 1;
        }
        double speedX = (speed/distance_to_player)*distance_to_playerX;
        double speedY = (speed/distance_to_player)*distance_to_playerY;

        if (distance_to_playerX >= 0 && distance_to_player > distance_attack){
            action = "moveRight";
        }
        else if (distance_to_playerX < 0 && distance_to_player > distance_attack){
            action = "moveLeft";
        }
        else if (distance_to_playerX >= 0 && distance_to_player <= distance_attack){
            action = "attack1Right";
        }
        else if (distance_to_playerX < 0 && distance_to_player <= distance_attack ){
            action = "attack1Left";
        }

        attackArea = new Rectangle(x,y,width,height);
        damageArea = new Rectangle(x,y,width,height);

        if((action == "attack1Right" || action == "attack1Left") && (action != "death") && (player.damageArea.intersects(this.attackArea))){
            if(player.heart <= 0){
                player.action = "death";
            }
            else {
                player.action = "hurt";
            }
        }

        if (action == "moveRight" || action == "moveLeft") {
            spriteCounter_5Frame++;
            if (spriteCounter_5Frame > 12) {
                if (spriteNum_5Frame == 1) {
                    spriteNum_5Frame = 2;
                } else if (spriteNum_5Frame == 2) {
                    spriteNum_5Frame = 3;
                } else if (spriteNum_5Frame == 3) {
                    spriteNum_5Frame = 4;
                } else if (spriteNum_5Frame == 4) {
                    spriteNum_5Frame = 5;
                } else if (spriteNum_5Frame == 5) {
                    spriteNum_5Frame = 1;
                }
                spriteCounter_5Frame = 0;
                x += speedX;
                y += speedY;
            }
        }

        if (action == "attack1Right" || action == "attack1Left") {
            spriteCounter_8Frame++;
            if (spriteCounter_8Frame > 12) {
                if (spriteNum_8Frame == 1) {
                    spriteNum_8Frame = 2;
                } else if (spriteNum_8Frame == 2) {
                    spriteNum_8Frame = 3;
                } else if (spriteNum_8Frame == 3) {
                    spriteNum_8Frame = 4;
                } else if (spriteNum_8Frame == 4) {
                    spriteNum_8Frame = 5;
                } else if (spriteNum_8Frame == 5) {
                    spriteNum_8Frame = 6;
                } else if (spriteNum_8Frame == 6){
                    spriteNum_8Frame = 7;
                } else if (spriteNum_8Frame == 7){
                    spriteNum_8Frame = 8;
                } else if (spriteNum_8Frame == 8){
                    spriteNum_8Frame = 1;
                }
                spriteCounter_8Frame = 0;
                x += speedX;
                y += speedY;
            }
        }
        return false;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if (action == "moveRight") {
            if (spriteNum_5Frame == 1){
                image = warriorMoveRight1;
            }
            if (spriteNum_5Frame == 2){
                image = warriorMoveRight2;
            }
            if (spriteNum_5Frame == 3){
                image = warriorMoveRight3;
            }
            if (spriteNum_5Frame == 4){
                image = warriorMoveRight4;
            }
            if (spriteNum_5Frame == 5){
                image = warriorMoveRight5;
            }
        }

        if (action == "moveLeft") {
            if (spriteNum_5Frame == 1){
                image = warriorMoveLeft1;
            }
            if (spriteNum_5Frame == 2){
                image = warriorMoveLeft2;
            }
            if (spriteNum_5Frame == 3){
                image = warriorMoveLeft3;
            }
            if (spriteNum_5Frame == 4){
                image = warriorMoveLeft4;
            }
            if (spriteNum_5Frame == 5){
                image = warriorMoveLeft5;
            }
        }

        if (action == "attack1Right") {
            if (spriteNum_8Frame == 1){
                image = warriorAttack1Right1;
            }
            if (spriteNum_8Frame == 2){
                image = warriorAttack1Right2;
            }
            if (spriteNum_8Frame == 3){
                image = warriorAttack1Right3;
            }
            if (spriteNum_8Frame == 4){
                image = warriorAttack1Right4;
            }
            if (spriteNum_8Frame == 5){
                image = warriorAttack1Right5;
            }
            if (spriteNum_8Frame == 6){
                image = warriorAttack1Right6;
            }
            if (spriteNum_8Frame == 7){
                image = warriorAttack1Right7;
            }
            if (spriteNum_8Frame == 8){
                image = warriorAttack1Right8;
            }
        }

        if (action == "attack1Left") {
            if (spriteNum_8Frame == 1){
                image = warriorAttack1Left1;
            }
            if (spriteNum_8Frame == 2){
                image = warriorAttack1Left2;
            }
            if (spriteNum_8Frame == 3){
                image = warriorAttack1Left3;
            }
            if (spriteNum_8Frame == 4){
                image = warriorAttack1Left4;
            }
            if (spriteNum_8Frame == 5){
                image = warriorAttack1Left5;
            }
            if (spriteNum_8Frame == 6){
                image = warriorAttack1Left6;
            }
            if (spriteNum_8Frame == 7){
                image = warriorAttack1Left7;
            }
            if (spriteNum_8Frame == 8){
                image = warriorAttack1Left8;
            }
        }



        g2.drawImage(image, x, y, width, height,null);
    }
}