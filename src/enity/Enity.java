package enity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enity {
    Panel panel;

    public int x,y;
    public int speedX,speedY;
    public int width,height;

    //PLayer_Image
    public BufferedImage standRight1, standRight2, standLeft1, standLeft2, moveRight1, moveRight2, moveRight3, moveLeft1, moveLeft2, moveLeft3;
    //Gun_Image
    public BufferedImage gunRight1, gunRight2, gunRight3, gunRight4, gunLeft1, gunLeft2, gunLeft3, gunLeft4, gunUp1, gunUp2, gunUp3, gunUp4, gunDown1, gunDown2, gunDown3, gunDown4;
    //Bullets Image
    public BufferedImage bulletRight, bulletLeft, bulletUp, bulletDown;

    public String direction_vertical;
    public String direction_horizontal;
    public String action;

    public int spriteNum_2Frame = 1;
    public int spriteCounter_2Frame = 0;

    public int spriteNum_3Frame = 1;
    public int spriteCounter_3Frame = 0;

    public int spriteNum_4Frame = 1;
    public int spriteCounter_4Frame = 0;

}