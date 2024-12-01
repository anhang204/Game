package enity;

import main.KeyHander;
import main.Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends Enity{
    int bulletSpeed = 15;

    private long firingTimer;
    private long firingDelay;


    Panel panel;
    KeyHander keyHander;
    Gun gun;

    public Bullet(Gun gun){
        this.panel = gun.panel;
        this.keyHander = gun.keyHander;
        this.gun = gun;

        setDefautValues_Gun();
        getBulletImage();

        firingTimer = System.nanoTime();
        firingDelay = 375;
    }

    public void setDefautValues_Gun(){
        x = gun.x;
        y = gun.y + 30;
        width = gun.width/2;
        height = gun.height/2;
    }

    public void getBulletImage() {
        try{
            bulletRight = ImageIO.read(getClass().getResourceAsStream("/bullet/Bullets-2.png.png"));
            bulletLeft = ImageIO.read(getClass().getResourceAsStream("/bullet/Bullets-1.png.png"));
            bulletUp = ImageIO.read(getClass().getResourceAsStream("/bullet/Bullets-3.png.png"));
            bulletDown = ImageIO.read(getClass().getResourceAsStream("/bullet/Bullets-4.png.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    double startedDelay = 100;
    boolean haveBeenStared;

    public void update1 (){
        if (keyHander.left_Pressed == true || keyHander.right_Pressed == true || keyHander.up_Pressed == true || keyHander.down_Pressed == true) {
            long eslapsed = (System.nanoTime() - firingTimer) / 1000000;
            if (eslapsed > startedDelay || haveBeenStared == true) {
                if (eslapsed > startedDelay && haveBeenStared != true) {
                    Panel.bullets.add(new Bullet(this.gun));
                    firingTimer = System.nanoTime();
                    haveBeenStared = true;
                }
                else if (eslapsed > firingDelay) {
                    Panel.bullets.add(new Bullet(this.gun));
                    firingTimer = System.nanoTime();
                    haveBeenStared = true;
                }
            }
        }
        if (!(keyHander.left_Pressed == true || keyHander.right_Pressed == true || keyHander.up_Pressed == true || keyHander.down_Pressed == true)){
            haveBeenStared = false;
            firingTimer = System.nanoTime();
        }
    }

    boolean loopRight,loopLeft,loopUp,loopDown;
    public boolean update2() {
        if (keyHander.right_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
            x = gun.x + 55;
            y = gun.y + 30;
            loopRight = true;
        }
        if (loopRight == true) {
            x += bulletSpeed;
            if (x > panel.boardWidth) {
                return true;
            }
        }

        if (keyHander.left_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
            x = gun.x;
            y = gun.y + 30;
            loopLeft = true;
        }
        if (loopLeft == true) {
            x -= bulletSpeed;
            if (x < 0) {
                return true;
            }
        }

        if (gun.action == "right") {
            if (keyHander.up_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
                x = gun.x + 45;
                y = gun.y;
                loopUp = true;
            }
            if (loopUp == true) {
                y -= bulletSpeed;
                if (y < 0) {
                    return true;
                }
            }

            if (keyHander.down_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
                x = gun.x + 40;
                y = gun.y + 90;
                loopDown = true;
            }
            if (loopDown == true) {
                y += bulletSpeed;
                if (y > panel.boardHeight) {
                    return true;
                }
            }
        }

        if (gun.action == "left") {
            if (keyHander.up_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
                x = gun.x + 30;
                y = gun.y;
                loopUp = true;
            }
            if (loopUp == true) {
                y -= bulletSpeed;
                if (y < 0) {
                    return true;
                }
            }

            if (keyHander.down_Pressed == true && loopRight != true && loopLeft != true && loopUp != true && loopDown != true) {
                x = gun.x + 25;
                y = gun.y + 90;
                loopDown = true;
            }
            if (loopDown == true) {
                y += bulletSpeed;
                if (y > panel.boardHeight) {
                    return true;
                }
            }
        }
        return false;
    }
    public void draw (Graphics2D g2){
        BufferedImage image = null;
        if (loopRight == true) {
            image = bulletRight;
        } else if (loopLeft == true) {
            image = bulletLeft;
        } else if (loopUp == true) {
            image = bulletUp;
        } else if (loopDown == true) {
            image = bulletDown;
        }
        g2.drawImage(image, x, y, null);
    }
}

