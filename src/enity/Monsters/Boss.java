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

public class Boss extends Enity {
    int heart = 2;
    int speed = 20;
    int distance_attack = 70;
    private boolean isDead = false;

    private long spamMonsterTimer;

    Panel panel;
    KeyHander keyHander;
    Player player;

    public Boss(Player player) {
        this.panel = player.panel;
        this.keyHander = player.keyHander;
        this.player = player;

        setDefaltValues_Warrior();
        getWarriorImage();

        spamMonsterTimer = System.nanoTime();
    }


    public void setDefaltValues_Warrior() {
        width = panel.tileSize * 2;
        height = panel.tileSize * 2;

        Random rand = new Random();
        int randomPosition = rand.nextInt(4) + 1;
        if (randomPosition == 1) {
            y = 0 - height;
            x = rand.nextInt(panel.boardWidth + 1);
        } else if (randomPosition == 2) {
            y = panel.boardHeight;
            x = rand.nextInt(panel.boardWidth + 1);
        } else if (randomPosition == 3) {
            x = panel.boardWidth;
            y = rand.nextInt(panel.boardHeight + 1);
        } else if (randomPosition == 4) {
            x = 0 - width;
            y = rand.nextInt(panel.boardHeight + 1);
        }

        attackArea = new Rectangle(x, y, width, height);
        damageArea = new Rectangle(x, y, width, height);
    }

    public void getWarriorImage() {
        try {
            bossWalkRight1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-1.png.png"));
            bossWalkRight2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-2.png.png"));
            bossWalkRight3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-3.png.png"));
            bossWalkRight4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-4.png.png"));
            bossWalkRight5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-5.png.png"));
            bossWalkRight6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-6.png.png"));
            bossWalkRight7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-7.png.png"));
            bossWalkRight8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Right-8.png.png"));

            bossWalkLeft1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-1.png.png"));
            bossWalkLeft2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-2.png.png"));
            bossWalkLeft3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-3.png.png"));
            bossWalkLeft4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-4.png.png"));
            bossWalkLeft5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-5.png.png"));
            bossWalkLeft6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-6.png.png"));
            bossWalkLeft7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-7.png.png"));
            bossWalkLeft8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/walk/Boss_Walk_Left-8.png.png"));

            bossAttack1Right1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-1.png.png"));
            bossAttack1Right2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-2.png.png"));
            bossAttack1Right3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-3.png.png"));
            bossAttack1Right4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-4.png.png"));
            bossAttack1Right5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-5.png.png"));
            bossAttack1Right6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-6.png.png"));
            bossAttack1Right7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-7.png.png"));
            bossAttack1Right8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-8.png.png"));
            bossAttack1Right9 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-9.png.png"));
            bossAttack1Right10 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-10.png.png"));
            bossAttack1Right11 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-11.png.png"));
            bossAttack1Right12 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-12.png.png"));
            bossAttack1Right13 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-13.png.png"));
            bossAttack1Right14 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Right-14.png.png"));

            bossAttack1Left1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-1.png.png"));
            bossAttack1Left2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-2.png.png"));
            bossAttack1Left3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-3.png.png"));
            bossAttack1Left4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-4.png.png"));
            bossAttack1Left5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-5.png.png"));
            bossAttack1Left6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-6.png.png"));
            bossAttack1Left7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-7.png.png"));
            bossAttack1Left8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-8.png.png"));
            bossAttack1Left9 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-9.png.png"));
            bossAttack1Left10 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-10.png.png"));
            bossAttack1Left11 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-11.png.png"));
            bossAttack1Left12 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-12.png.png"));
            bossAttack1Left13 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-13.png.png"));
            bossAttack1Left14 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack1/Boss_Attack1_Left-14.png.png"));

            bossAttack2Left1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-1.png.png"));
            bossAttack2Left2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-2.png.png"));
            bossAttack2Left3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-3.png.png"));
            bossAttack2Left4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-4.png.png"));
            bossAttack2Left5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-5.png.png"));
            bossAttack2Left6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-6.png.png"));
            bossAttack2Left7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-7.png.png"));
            bossAttack2Left8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Left-8.png.png"));

            bossAttack2Right1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-1.png.png"));
            bossAttack2Right2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-2.png.png"));
            bossAttack2Right3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-3.png.png"));
            bossAttack2Right4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-4.png.png"));
            bossAttack2Right5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-5.png.png"));
            bossAttack2Right6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-6.png.png"));
            bossAttack2Right7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-7.png.png"));
            bossAttack2Right8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Right-8.png.png"));

            bossAttack2Object1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-1.png.png"));
            bossAttack2Object2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-2.png.png"));
            bossAttack2Object3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-3.png.png"));
            bossAttack2Object4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-4.png.png"));
            bossAttack2Object5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-5.png.png"));
            bossAttack2Object6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-6.png.png"));
            bossAttack2Object7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-7.png.png"));
            bossAttack2Object8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/attack2/Boss_Attack2_Object-8.png.png"));

            bossStandLeft1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Left-1.png.png"));
            bossStandLeft2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Left-2.png.png"));
            bossStandLeft3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Left-3.png.png"));
            bossStandLeft4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Left-4.png.png"));
            bossStandLeft5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Left-5.png.png"));

            bossStandRight1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Right-1.png.png"));
            bossStandRight2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Right-2.png.png"));
            bossStandRight3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Right-3.png.png"));
            bossStandRight4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Right-4.png.png"));
            bossStandRight5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/stand/Boss_Stand_Right-5.png.png"));

            bossDeathRight1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-1.png.png"));
            bossDeathRight2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-2.png.png"));
            bossDeathRight3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-3.png.png"));
            bossDeathRight4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-4.png.png"));
            bossDeathRight5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-5.png.png"));
            bossDeathRight6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-6.png.png"));
            bossDeathRight7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-7.png.png"));
            bossDeathRight8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-8.png.png"));
            bossDeathRight9 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-9.png.png"));
            bossDeathRight10 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-10.png.png"));
            bossDeathRight11 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-11.png.png"));
            bossDeathRight12 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-12.png.png"));
            bossDeathRight13 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-13.png.png"));
            bossDeathRight14 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Right-14.png.png"));

            bossDeathLeft1 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-1.png.png"));
            bossDeathLeft2 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-2.png.png"));
            bossDeathLeft3 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-3.png.png"));
            bossDeathLeft4 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-4.png.png"));
            bossDeathLeft5 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-5.png.png"));
            bossDeathLeft6 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-6.png.png"));
            bossDeathLeft7 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-7.png.png"));
            bossDeathLeft8 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-8.png.png"));
            bossDeathLeft9 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-9.png.png"));
            bossDeathLeft10 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-10.png.png"));
            bossDeathLeft11 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-11.png.png"));
            bossDeathLeft12 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-12.png.png"));
            bossDeathLeft13 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-13.png.png"));
            bossDeathLeft14 = ImageIO.read(getClass().getResourceAsStream("/monsters/boss/death/Boss_Death_Left-14.png.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeDamage(int damage) {
        if (isDead) return;
        heart -= damage;

        if (heart <= 0) {
            die();
        }
    }

    public void die() {
        isDead = true;
        action = "death";
    }

    public boolean isAlive() {
        return !isDead;
    }

    public void update1() {
        if (keyHander.enter_Pressed && Panel.activeBoss == null) {
            Panel.activeBoss = new Boss(this.player);
            spamMonsterTimer = System.nanoTime();
        }
    }


    public boolean update2() {
        double distance_to_playerX = player.x - x;
        double distance_to_playerY = player.y - y;

        double distance_to_player = Math.sqrt(Math.pow(distance_to_playerX, 2) + Math.pow(distance_to_playerY, 2));

        if (distance_to_player == 0) {
            distance_to_player = 1;
        }
        double speedX = (speed / distance_to_player) * distance_to_playerX;
        double speedY = (speed / distance_to_player) * distance_to_playerY;

        if (isDead) {
            return true;
        }
        if (distance_to_playerX >= 0 && distance_to_player > distance_attack) {
            action = "moveRight";
        } else if (distance_to_playerX < 0 && distance_to_player > distance_attack) {
            action = "moveLeft";
        } else if (distance_to_playerX >= 0 && distance_to_player <= distance_attack) {
            action = "attack1Right";
        } else if (distance_to_playerX < 0 && distance_to_player <= distance_attack) {
            action = "attack1Left";
        }

        attackArea = new Rectangle(x, y, width, height);
        damageArea = new Rectangle(x, y, width, height);


        if ((action == "attack1Right" || action == "attack1Left") && (action != "death") && (player.damageArea.intersects(this.attackArea))) {
            if (player.heart <= 0) {
                player.action = "death";
            } else {
                player.action = "hurt";
            }
        }

        if (action == "moveRight" || action == "moveLeft") {
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
                } else if (spriteNum_8Frame == 6) {
                    spriteNum_8Frame = 7;
                } else if (spriteNum_8Frame == 7) {
                    spriteNum_8Frame = 8;
                } else if (spriteNum_8Frame == 8) {
                    spriteNum_8Frame = 1;
                }
                spriteCounter_8Frame = 0;
                x += speedX;
                y += speedY;
            }
        }

        if (action == "attack1Right" || action == "attack1Left") {
            spriteCounter_14Frame++;
            if (spriteCounter_14Frame > 12) {
                if (spriteNum_14Frame == 1) {
                    spriteNum_14Frame = 2;
                } else if (spriteNum_14Frame == 2) {
                    spriteNum_14Frame = 3;
                } else if (spriteNum_14Frame == 3) {
                    spriteNum_14Frame = 4;
                } else if (spriteNum_14Frame == 4) {
                    spriteNum_14Frame = 5;
                } else if (spriteNum_14Frame == 5) {
                    spriteNum_14Frame = 6;
                } else if (spriteNum_14Frame == 6) {
                    spriteNum_14Frame = 7;
                } else if (spriteNum_14Frame == 7) {
                    spriteNum_14Frame = 8;
                } else if (spriteNum_14Frame == 8) {
                    spriteNum_14Frame = 9;
                } else if (spriteNum_14Frame == 9) {
                    spriteNum_14Frame = 10;
                } else if (spriteNum_14Frame == 10) {
                    spriteNum_14Frame = 11;
                } else if (spriteNum_14Frame == 11) {
                    spriteNum_14Frame = 12;
                } else if (spriteNum_14Frame == 12) {
                    spriteNum_14Frame = 13;
                } else if (spriteNum_14Frame == 13) {
                    spriteNum_14Frame = 14;
                } else if (spriteNum_14Frame == 14) {
                    spriteNum_14Frame = 1;
                }
                spriteCounter_14Frame = 0;
                x += speedX;
                y += speedY;
            }
        }

        if (action == "death") {
            spriteCounter_14Frame++;
            if (spriteCounter_14Frame > 15) {
                if (spriteNum_14Frame == 1) {
                    spriteNum_14Frame = 2;
                } else if (spriteNum_14Frame == 2) {
                    spriteNum_14Frame = 3;
                } else if (spriteNum_14Frame == 3) {
                    spriteNum_14Frame = 4;
                } else if (spriteNum_14Frame == 4) {
                    spriteNum_14Frame = 5;
                } else if (spriteNum_14Frame == 5) {
                    spriteNum_14Frame = 6;
                } else if (spriteNum_14Frame == 6) {
                    spriteNum_14Frame = 7;
                } else if (spriteNum_14Frame == 7) {
                    spriteNum_14Frame = 8;
                } else if (spriteNum_14Frame == 8) {
                    spriteNum_14Frame = 9;
                } else if (spriteNum_14Frame == 9) {
                    spriteNum_14Frame = 10;
                } else if (spriteNum_14Frame == 10) {
                    spriteNum_14Frame = 11;
                } else if (spriteNum_14Frame == 11) {
                    spriteNum_14Frame = 12;
                } else if (spriteNum_14Frame == 12) {
                    spriteNum_14Frame = 13;
                } else if (spriteNum_14Frame == 13) {
                    spriteNum_14Frame = 14;
                } else if (spriteNum_14Frame == 14) {
                    spriteNum_14Frame = 1;
                }
                spriteCounter_14Frame = 0;
            }
        }

        return false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (action == "moveRight") {
            if (spriteNum_8Frame == 1) {
                image = bossWalkRight1;
            }
            if (spriteNum_8Frame == 2) {
                image = bossWalkRight2;
            }
            if (spriteNum_8Frame == 3) {
                image = bossWalkRight3;
            }
            if (spriteNum_8Frame == 4) {
                image = bossWalkRight4;
            }
            if (spriteNum_8Frame == 5) {
                image = bossWalkRight5;
            }
            if (spriteNum_8Frame == 6) {
                image = bossWalkRight6;
            }
            if (spriteNum_8Frame == 7) {
                image = bossWalkRight7;
            }
            if (spriteNum_8Frame == 8) {
                image = bossWalkRight8;
            }
        }

        if (action == "moveLeft") {
            if (spriteNum_8Frame == 1) {
                image = bossWalkLeft1;
            }
            if (spriteNum_8Frame == 2) {
                image = bossWalkLeft2;
            }
            if (spriteNum_8Frame == 3) {
                image = bossWalkLeft3;
            }
            if (spriteNum_8Frame == 4) {
                image = bossWalkLeft4;
            }
            if (spriteNum_8Frame == 5) {
                image = bossWalkLeft5;
            }
            if (spriteNum_8Frame == 6) {
                image = bossWalkLeft6;
            }
            if (spriteNum_8Frame == 7) {
                image = bossWalkLeft7;
            }
            if (spriteNum_8Frame == 8) {
                image = bossWalkLeft8;
            }
        }

        if (action == "attack1Right") {
            if (spriteNum_14Frame == 1) {
                image = bossAttack1Right1;
            }
            if (spriteNum_14Frame == 2) {
                image = bossAttack1Right2;
            }
            if (spriteNum_14Frame == 3) {
                image = bossAttack1Right3;
            }
            if (spriteNum_14Frame == 4) {
                image = bossAttack1Right4;
            }
            if (spriteNum_14Frame == 5) {
                image = bossAttack1Right5;
            }
            if (spriteNum_14Frame == 6) {
                image = bossAttack1Right6;
            }
            if (spriteNum_14Frame == 7) {
                image = bossAttack1Right7;
            }
            if (spriteNum_14Frame == 8) {
                image = bossAttack1Right8;
            }
            if (spriteNum_14Frame == 9) {
                image = bossAttack1Right9;
            }
            if (spriteNum_14Frame == 10) {
                image = bossAttack1Right10;
            }
            if (spriteNum_14Frame == 11) {
                image = bossAttack1Right11;
            }
            if (spriteNum_14Frame == 12) {
                image = bossAttack1Right12;
            }
            if (spriteNum_14Frame == 13) {
                image = bossAttack1Right13;
            }
            if (spriteNum_14Frame == 14) {
                image = bossAttack1Right14;
            }
        }

        if (action == "attack1Left") {
            if (spriteNum_14Frame == 1) {
                image = bossAttack1Left1;
            }
            if (spriteNum_14Frame == 2) {
                image = bossAttack1Left2;
            }
            if (spriteNum_14Frame == 3) {
                image = bossAttack1Left3;
            }
            if (spriteNum_14Frame == 4) {
                image = bossAttack1Left4;
            }
            if (spriteNum_14Frame == 5) {
                image = bossAttack1Left5;
            }
            if (spriteNum_14Frame == 6) {
                image = bossAttack1Left6;
            }
            if (spriteNum_14Frame == 7) {
                image = bossAttack1Left7;
            }
            if (spriteNum_14Frame == 8) {
                image = bossAttack1Left8;
            }
            if (spriteNum_14Frame == 9) {
                image = bossAttack1Left9;
            }
            if (spriteNum_14Frame == 10) {
                image = bossAttack1Left10;
            }
            if (spriteNum_14Frame == 11) {
                image = bossAttack1Left11;
            }
            if (spriteNum_14Frame == 12) {
                image = bossAttack1Left12;
            }
            if (spriteNum_14Frame == 13) {
                image = bossAttack1Left13;
            }
            if (spriteNum_14Frame == 14) {
                image = bossAttack1Left14;
            }
        }


        if (action == "death") {
            if (direction_horizontal == "right") {
                if (spriteNum_14Frame == 1) {
                    image = bossDeathRight1;
                }
                if (spriteNum_14Frame == 2) {
                    image = bossDeathRight2;
                }
                if (spriteNum_14Frame == 3) {
                    image = bossDeathRight3;
                }
                if (spriteNum_14Frame == 4) {
                    image = bossDeathRight4;
                }
                if (spriteNum_14Frame == 5) {
                    image = bossDeathRight5;
                }
                if (spriteNum_14Frame == 6) {
                    image = bossDeathRight6;
                }
                if (spriteNum_14Frame == 7) {
                    image = bossDeathRight7;
                }
                if (spriteNum_14Frame == 8) {
                    image = bossDeathRight8;
                }
                if (spriteNum_14Frame == 9) {
                    image = bossDeathRight9;
                }
                if (spriteNum_14Frame == 10) {
                    image = bossDeathRight10;
                }
                if (spriteNum_14Frame == 11) {
                    image = bossDeathRight11;
                }
                if (spriteNum_14Frame == 12) {
                    image = bossDeathRight12;
                }
                if (spriteNum_14Frame == 13) {
                    image = bossDeathRight13;
                }
                if (spriteNum_14Frame == 14) {
                    image = bossDeathRight14;
                }
            }
            if (direction_horizontal == "left") {
                if (spriteNum_14Frame == 1) {
                    image = bossDeathLeft1;
                }
                if (spriteNum_14Frame == 2) {
                    image = bossDeathLeft2;
                }
                if (spriteNum_14Frame == 3) {
                    image = bossDeathLeft3;
                }
                if (spriteNum_14Frame == 4) {
                    image = bossDeathLeft4;
                }
                if (spriteNum_14Frame == 5) {
                    image = bossDeathLeft5;
                }
                if (spriteNum_14Frame == 6) {
                    image = bossDeathLeft6;
                }
                if (spriteNum_14Frame == 7) {
                    image = bossDeathLeft7;
                }
                if (spriteNum_14Frame == 8) {
                    image = bossDeathLeft8;
                }
                if (spriteNum_14Frame == 9) {
                    image = bossDeathLeft9;
                }
                if (spriteNum_14Frame == 10) {
                    image = bossDeathLeft10;
                }
                if (spriteNum_14Frame == 11) {
                    image = bossDeathLeft11;
                }
                if (spriteNum_14Frame == 12) {
                    image = bossDeathLeft12;
                }
                if (spriteNum_14Frame == 13) {
                    image = bossDeathLeft13;
                }
                if (spriteNum_14Frame == 14) {
                    image = bossDeathLeft14;
                }
            }
        }


        g2.drawImage(image, x, y, width, height, null);
    }
}
