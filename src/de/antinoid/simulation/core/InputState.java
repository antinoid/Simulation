package de.antinoid.simulation.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author d
 */
public class InputState implements KeyListener{

    public int xOffset;
    public int yOffset;
    
    //private boolean[] keys = new boolean[120];
    protected boolean up, down, left, right;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
            System.out.println("up");
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
            System.out.println("down");
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
            System.out.println("left");
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
            System.out.println("right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {if(e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        } else if(e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
    }
    
    
}
