package enity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enity {
    Panel panel;

    public int x,y;
    public int speed,speedX,speedY;
    public int width,height;

    //PLayer_Image
    public BufferedImage standRight1, standRight2, standLeft1, standLeft2, moveRight1, moveRight2, moveRight3, moveLeft1, moveLeft2, moveLeft3
    , hurtRight1, hurtRight2, hurtRight3, hurtRight4, hurtRight5, hurtRight6, hurtRight7, hurtRight8, hurtRight9, hurtRight10, hurtRight11, hurtRight12, hurtRight13, hurtRight14
    , hurtLeft1, hurtLeft2, hurtLeft3, hurtLeft4, hurtLeft5, hurtLeft6, hurtLeft7, hurtLeft8, hurtLeft9, hurtLeft10, hurtLeft11, hurtLeft12, hurtLeft13, hurtLeft14
    , deathRight1, deathRight2, deathRight3, deathRight4, deathRight5, deathLeft1, deathLeft2, deathLeft3, deathLeft4, deathLeft5;

    //Heart_Image
    public BufferedImage Heart1, Heart2, Heart3, Heart4, Heart5, Heart6, Heart7, Heart8, Heart9, Heart10, Heart11, Heart12, Heart13, Heart14, Heart15, Heart16, Heart17;

    //Gun_Image
    public BufferedImage gunRight1, gunRight2, gunRight3, gunRight4, gunLeft1, gunLeft2, gunLeft3, gunLeft4, gunUp1, gunUp2, gunUp3, gunUp4, gunDown1, gunDown2, gunDown3, gunDown4;

    //Bullets Image
    public BufferedImage bulletRight, bulletLeft, bulletUp, bulletDown;

    //Warrior Image
    public BufferedImage warriorMoveLeft1,warriorMoveLeft2,warriorMoveLeft3,warriorMoveLeft4,warriorMoveLeft5,warriorMoveRight1,warriorMoveRight2,warriorMoveRight3,warriorMoveRight4,warriorMoveRight5
    ,warriorAttack1Right1,warriorAttack1Right2,warriorAttack1Right3,warriorAttack1Right4,warriorAttack1Right5,warriorAttack1Right6,warriorAttack1Right7,warriorAttack1Right8
    ,warriorAttack1Left1,warriorAttack1Left2,warriorAttack1Left3,warriorAttack1Left4,warriorAttack1Left5,warriorAttack1Left6,warriorAttack1Left7,warriorAttack1Left8
    ,warriorAttack2Right1,warriorAttack2Right2,warriorAttack2Right3,warriorAttack2Right4,warriorAttack2Right5,warriorAttack2Right6
    ,warriorAttack2Left1,warriorAttack2Left2,warriorAttack2Left3,warriorAttack2Left4,warriorAttack2Left5,warriorAttack2Left6
    ,warriorHurtLeft,warriorHurtRight
    ,warriorDeathRight1,warriorDeathRight2,warriorDeathRight3,warriorDeathRight4,warriorDeathRight5,warriorDeathRight6,warriorDeathRight7,warriorDeathRight8,warriorDeathRight9,warriorDeathRight10,warriorDeathRight11,warriorDeathRight12,warriorDeathRight13
    ,warriorDeathLeft1,warriorDeathLeft2,warriorDeathLeft3,warriorDeathLeft4,warriorDeathLeft5,warriorDeathLeft6,warriorDeathLeft7,warriorDeathLeft8,warriorDeathLeft9,warriorDeathLeft10,warriorDeathLeft11,warriorDeathLeft12,warriorDeathLeft13;

    public Rectangle damageArea = new Rectangle(0,0,0,0);
    public Rectangle attackArea = new Rectangle(0,0,0,0);

    public String direction_vertical;
    public String direction_horizontal;
    public String action;

    public int spriteNum_2Frame = 1;
    public int spriteCounter_2Frame = 0;

    public int spriteNum_3Frame = 1;
    public int spriteCounter_3Frame = 0;

    public int spriteNum_4Frame = 1;
    public int spriteCounter_4Frame = 0;

    public int spriteNum_5Frame = 1;
    public int spriteCounter_5Frame = 0;

    public int spriteNum_8Frame =1;
    public int spriteCounter_8Frame = 0;

    public int spriteNum_14Frame =1;
    public int spriteCounter_14Frame = 0;

}