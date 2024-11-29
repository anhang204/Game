package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHander implements KeyListener {
    public boolean w_Pressed, s_Pressed, a_Pressed, d_Pressed;
    public boolean up_Pressed, down_Pressed, left_Pressed, right_Pressed;
    @Override public void keyPressed(KeyEvent e) {
        //Player keyboard:
        if (e.getKeyCode() == KeyEvent.VK_W) {
            w_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            s_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            a_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            d_Pressed = true;
        }

        //Gun keyboard:
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left_Pressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right_Pressed = true;
        }
    }

    @Override public void keyReleased(KeyEvent e) {
        //Player keyboard:
        if (e.getKeyCode() == KeyEvent.VK_W) {
            w_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            s_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            a_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            d_Pressed = false;
        }

        //Gun keyboard:
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left_Pressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right_Pressed = false;
        }
    }

    @Override public void keyTyped(KeyEvent e) {
    }
}

