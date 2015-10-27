package main;

import java.awt.Dimension;

/**
 *
 * @author d
 */
public class Screen {

    private int width, height;
    public int[] pixels;
    
    int counter = 0;
    int time = 0;
    
    public Screen(Dimension dim) {
        this.width = dim.width;
        this.height = dim.height;
        pixels = new int[width * height];
    }
    
    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    public void render() {
        counter++;
        if(counter % 100 == 0) {
            time++;
        }
        /*for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xA37E37;
            }
        }*/
        pixels[time + time * width] = 0x00FF00;
    }
}
