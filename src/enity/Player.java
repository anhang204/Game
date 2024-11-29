package enity;

import main.KeyHander;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Enity{
    Panel panel;
    KeyHander keyHander;

    public Player(Panel panel, KeyHander keyHander) {
        this.panel = panel;
        this.keyHander = keyHander;

        setDefaultValues_Player();
        getPlayerImage();
    }

    public void setDefaultValues_Player() {
        String direction = "standRight";
        x = panel.boardWidth/2 - panel.tileSize/2;
        y = panel.boardHeight/2 - panel.tileSize/2;
        width = panel.tileSize * 2;
        height = panel.tileSize * 2;
        speedX = 4;
        speedY = 4;
        direction_horizontal = "right";
    }

    public void getPlayerImage() {
        try{
            standRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Stand_Right-1.png.png"));
            standRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Stand_Right-2.png.png"));

            standLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Stand_Left-1.png.png"));
            standLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Stand_Left-2.png.png"));

            moveRight1 = ImageIO.read(getClass().getResourceAsStream("/player/Right-1.png.png"));
            moveRight2 = ImageIO.read(getClass().getResourceAsStream("/player/Right-2.png.png"));
            moveRight3 = ImageIO.read(getClass().getResourceAsStream("/player/Right-3.png.png"));

            moveLeft1 = ImageIO.read(getClass().getResourceAsStream("/player/Left-1.png.png"));
            moveLeft2 = ImageIO.read(getClass().getResourceAsStream("/player/Left-2.png.png"));
            moveLeft3 = ImageIO.read(getClass().getResourceAsStream("/player/Left-3.png.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    int count;
    public void update(){
        if(keyHander.w_Pressed == true) {
            count = 1;
            y -= speedY;
            direction_vertical = "up";
            if (direction_horizontal == "left") {
                action = "moveLeft";
            }
            if (direction_horizontal == "right") {
                action = "moveRight";
            }
        }
        else if (keyHander.w_Pressed == false && count == 1) {
            if (action == "moveLeft") {
                action = "standLeft";
            }
            if (action == "moveRight") {
                action = "standRight";
            }
        }

        if(keyHander.s_Pressed == true) {
            count = 2;
            y += speedY;
            direction_vertical = "down";
            if (direction_horizontal == "left") {
                action = "moveLeft";
            }
            if (direction_horizontal == "right") {
                action = "moveRight";
            }
        }
        else if (keyHander.s_Pressed == false && count == 2  ) {
            if (action == "moveLeft") {
                action = "standLeft";
            }
            if (action == "moveRight") {
                action = "standRight";
            }
        }

        if(keyHander.a_Pressed == true) {
            count = 3;
            action = "moveLeft";
            x -= speedX;
            direction_horizontal = "left";
        }
        else if (keyHander.a_Pressed == false && count == 3) {
            action = "standLeft";
        }

        if(keyHander.d_Pressed == true) {
            count = 4;
            action = "moveRight";
            x += speedX;
            direction_horizontal = "right";
        }
        else if (keyHander.d_Pressed == false && count == 4) {
            action = "standRight";
        }

        if (action == "moveRight" || action == "moveLeft") {
            spriteCounter_3Frame++;
            if(spriteCounter_3Frame > 13) {
                if(spriteNum_3Frame == 1) {
                    spriteNum_3Frame = 2;
                }
                else if(spriteNum_3Frame == 2) {
                    spriteNum_3Frame = 3;
                }
                else if(spriteNum_3Frame == 3) {
                    spriteNum_3Frame = 1;
                }
                spriteCounter_3Frame = 0;
            }
        }

        if (action == "standLeft" || action == "standRight") {
            spriteCounter_2Frame++;
            if(spriteCounter_2Frame > 20) {
                if(spriteNum_2Frame == 1) {
                    spriteNum_2Frame = 2;
                }
                else if(spriteNum_2Frame == 2) {
                    spriteNum_2Frame = 1;
                }
                spriteCounter_2Frame = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, panel.tileSize, panel.tileSize);
        BufferedImage image = null;
        if (action == "moveRight") {
            if (spriteNum_3Frame == 1){
                image = moveRight1;
            }
            if (spriteNum_3Frame == 2){
                image = moveRight2;
            }
            if (spriteNum_3Frame == 3){
                image = moveRight3;
            }
        }
        else if (action == "moveLeft") {
            if (spriteNum_3Frame == 1){
                image = moveLeft1;
            }
            if (spriteNum_3Frame == 2){
                image = moveLeft2;
            }
            if (spriteNum_3Frame == 3){
                image = moveLeft3;
            }
        }
        else if (action == "standRight") {
            if (spriteNum_2Frame == 1){
                image = standRight1;
            }
            if (spriteNum_2Frame == 2){
                image = standRight2;
            }
        }

        else if (action == "standLeft") {
            if (spriteNum_2Frame == 1){
                image = standLeft1;
            }
            if (spriteNum_2Frame == 2){
                image = standLeft2;
            }
        }

        else {
            action = "standRight";
            if (spriteNum_2Frame == 1){
                image = standRight1;
            }
            if (spriteNum_2Frame == 2){
                image = standRight2;
            }
        }
        g2.drawImage(image, x, y, width, height, null);
    }
}