package enity;

import main.KeyHander;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Gun extends Enity{
    Panel panel;
    KeyHander keyHander;
    Player player;

    public Gun(Player player) {
        this.panel = player.panel;
        this.keyHander = player.keyHander;
        this.player = player;
        setDefautValues_Gun();
        getGunImage();
    }

    int distance = 5;
    public void setDefautValues_Gun(){
        x = player.x - distance;
        y = player.y + 10;
        width = player.width;
        height = player.height;
        speedX = player.speedX;
        speedY = player.speedY;
        action = "right";
    }

    public void getGunImage() {
        try{
            gunRight1 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Right-1.png.png"));
            gunRight2 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Right-2.png.png"));
            gunRight3 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Right-3.png.png"));
            gunRight4 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Right-4.png.png"));

            gunLeft1 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Left-1.png.png"));
            gunLeft2 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Left-2.png.png"));
            gunLeft3 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Left-3.png.png"));
            gunLeft4 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Left-4.png.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    int count;
    public void update() {
        //Gun when move:
        if(keyHander.w_Pressed == true) {
            y -= speedY;
            count = 1;
        }
        else if (keyHander.w_Pressed == false && count == 1) {
            count = 0;
        }

        if(keyHander.s_Pressed == true) {
            y += speedY;
            count = 2;
        }
        else if (keyHander.s_Pressed == false && count == 2 ) {
            count = 0;
        }

        if(keyHander.d_Pressed == true) {
            action = "right";
            x += speedX;
            count = 3;
        }
        else if (keyHander.d_Pressed == false && count == 3) {
            action = "right";
            count = 0;
        }

        if(keyHander.a_Pressed == true) {
            action = "left";
            x -= speedX;
            count = 4;
        }
        else if (keyHander.a_Pressed == false && count == 4) {
            action = "left";
            count = 0;
        }

        if (action == "right") {
            x = player.x - distance;
        }
        if (action == "left") {
            x = player.x + distance;
        }

        //Gun when fire bullets
        if (keyHander.right_Pressed == true || keyHander.left_Pressed == true ) {
            spriteCounter_4Frame++;
            if(spriteCounter_4Frame > 5) {
                if(spriteNum_4Frame == 1) {
                    spriteNum_4Frame = 2;
                }
                else if(spriteNum_4Frame == 2) {
                    spriteNum_4Frame = 3;
                }
                else if(spriteNum_4Frame == 3) {
                    spriteNum_4Frame = 4;
                }
                else if(spriteNum_4Frame == 4) {
                    spriteNum_4Frame = 1;
                }
                spriteCounter_4Frame = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //Gun when fire bullet right
        if (action == "right") {
            if (keyHander.right_Pressed == true) {
                y = player.y + 10;
                if (spriteNum_4Frame == 1){
                    image = gunRight1;
                }
                if (spriteNum_4Frame == 2){
                    x += 5;
                    image = gunRight2;
                }
                if (spriteNum_4Frame == 3){
                    x += 10;
                    image = gunRight3;
                }
                if (spriteNum_4Frame == 4){
                    x += 8;
                    image = gunRight4;
                }
            }
            else if (keyHander.left_Pressed == true) {
                y = player.y + 10;
                if (spriteNum_4Frame == 1){
                    image = gunLeft1;
                }
                if (spriteNum_4Frame == 2){
                    x -= 5;
                    image = gunLeft2;
                }
                if (spriteNum_4Frame == 3){
                    x -= 10;
                    image = gunLeft3;
                }
                if (spriteNum_4Frame == 4){
                    x -= 8;
                    image = gunLeft4;
                }
            }
            else if (keyHander.right_Pressed == false){
                image = gunRight1;
                if (player.spriteNum_2Frame == 1) {
                    y = player.y + 14;
                }
                else if (player.spriteNum_2Frame == 2) {
                    y = player.y + 10;
                }
            }
        }

        else if (action == "left") {
            if (keyHander.left_Pressed == true) {
                y = player.y + 10;
                if (spriteNum_4Frame == 1){
                    image = gunLeft1;
                }
                if (spriteNum_4Frame == 2){
                    x -= 5;
                    image = gunLeft2;
                }
                if (spriteNum_4Frame == 3){
                    x -= 10;
                    image = gunLeft3;
                }
                if (spriteNum_4Frame == 4){
                    x -= 8;
                    image = gunLeft4;
                }
            }
            else if (keyHander.right_Pressed == true) {
                y = player.y + 10;
                if (spriteNum_4Frame == 1){
                    image = gunRight1;
                }
                if (spriteNum_4Frame == 2){
                    x += 5;
                    image = gunRight2;
                }
                if (spriteNum_4Frame == 3){
                    x += 10;
                    image = gunRight3;
                }
                if (spriteNum_4Frame == 4){
                    x += 8;
                    image = gunRight4;
                }
            }
            else if (keyHander.left_Pressed == false){
                image = gunLeft1;
                if (player.spriteNum_2Frame == 1) {
                    y = player.y + 14;
                }
                else if (player.spriteNum_2Frame == 2) {
                    y = player.y + 10;
                }
            }
        }
        g2.drawImage(image, x, y, width, height,null);
    }
}
