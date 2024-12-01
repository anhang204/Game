package enity;

import main.KeyHander;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Gun extends Enity{
    int fireRate = 5;
    int gunRecoil = 5;

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

            gunUp1 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Up-1.png.png"));
            gunUp2 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Up-2.png.png"));
            gunUp3 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Up-3.png.png"));
            gunUp4 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Up-4.png.png"));

            gunDown1 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Down-1.png.png"));
            gunDown2 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Down-2.png.png"));
            gunDown3 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Down-3.png.png"));
            gunDown4 = ImageIO.read(getClass().getResourceAsStream("/gun/Gun_Down-4.png.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    int count;
    public void update() {
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
        if (keyHander.right_Pressed == true || keyHander.left_Pressed == true || keyHander.up_Pressed == true || keyHander.down_Pressed == true) {
            spriteCounter_4Frame++;
            if(spriteCounter_4Frame > fireRate) {
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
        if (!(keyHander.right_Pressed == true || keyHander.left_Pressed == true || keyHander.up_Pressed == true || keyHander.down_Pressed == true)) {
            spriteCounter_4Frame = 0;
            spriteNum_4Frame = 1;
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
                    x +=gunRecoil;
                    image = gunRight2;
                }
                if (spriteNum_4Frame == 3){
                    x += gunRecoil*2;
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
                    x -= gunRecoil;
                    image = gunLeft2;
                }
                if (spriteNum_4Frame == 3){
                    x -= gunRecoil*2;
                    image = gunLeft3;
                }
                if (spriteNum_4Frame == 4){
                    x -= 8;
                    image = gunLeft4;
                }
            }
            else if (keyHander.up_Pressed == true) {
                y = player.y - 8;
                x = player.x + 8;
                if (spriteNum_4Frame == 1) {
                    image = gunUp1;
                }
                if (spriteNum_4Frame == 2) {
                    y -= gunRecoil;
                    image = gunUp2;
                }
                if (spriteNum_4Frame == 3) {
                    y -= gunRecoil * 2;
                    image = gunUp3;
                }
                if (spriteNum_4Frame == 4) {
                    y -= 8;
                    image = gunUp4;
                }
            }
            else if (keyHander.down_Pressed == true) {
                y = player.y + 8;
                x = player.x + 8;
                if (spriteNum_4Frame == 1) {
                    image = gunDown1;
                }
                if (spriteNum_4Frame == 2) {
                    y += gunRecoil;
                    image = gunDown2;
                }
                if (spriteNum_4Frame == 3) {
                    y += gunRecoil * 2;
                    image = gunDown3;
                }
                if (spriteNum_4Frame == 4) {
                    y += 8;
                    image = gunDown4;
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
            if (keyHander.right_Pressed == true) {
                y = player.y + 10;
                if (spriteNum_4Frame == 1){
                    image = gunRight1;
                }
                if (spriteNum_4Frame == 2){
                    x +=gunRecoil;
                    image = gunRight2;
                }
                if (spriteNum_4Frame == 3){
                    x += gunRecoil*2;
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
                    x -= gunRecoil;
                    image = gunLeft2;
                }
                if (spriteNum_4Frame == 3){
                    x -= gunRecoil*2;
                    image = gunLeft3;
                }
                if (spriteNum_4Frame == 4){
                    x -= 8;
                    image = gunLeft4;
                }
            }
            else if (keyHander.up_Pressed == true) {
                y = player.y - 8;
                x = player.x + 2;
                if (spriteNum_4Frame == 1) {
                    image = gunUp1;
                }
                if (spriteNum_4Frame == 2) {
                    y -= gunRecoil;
                    image = gunUp2;
                }
                if (spriteNum_4Frame == 3) {
                    y -= gunRecoil * 2;
                    image = gunUp3;
                }
                if (spriteNum_4Frame == 4) {
                    y -= 8;
                    image = gunUp4;
                }
            }
            else if (keyHander.down_Pressed == true) {
                y = player.y + 8;
                x = player.x + 2;
                if (spriteNum_4Frame == 1) {
                    image = gunDown1;
                }
                if (spriteNum_4Frame == 2) {
                    y += gunRecoil;
                    image = gunDown2;
                }
                if (spriteNum_4Frame == 3) {
                    y += gunRecoil * 2;
                    image = gunDown3;
                }
                if (spriteNum_4Frame == 4) {
                    y += 8;
                    image = gunDown4;
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
