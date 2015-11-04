package de.antinoid.simulation.oldcrap;

import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author d
 */
public class Screen {

    private static final int TILE_AMOUNT = 64;
    private static final int TILE_SIZE = 32;
    
    private int width, height;
    public int[] pixels;
    public int[] tiles = new int[TILE_AMOUNT * TILE_AMOUNT];
    
    private Random random = new Random();
    
    public Screen(Dimension dim) {
        this.width = dim.width;
        this.height = dim.height;
        pixels = new int[width * height];
        
        for(int i = 0; i < TILE_AMOUNT * TILE_AMOUNT; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }
    
    public void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    public void render() {
        //generateTiles();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int tileIndex = (x >> 4) + (y >> 4) * TILE_AMOUNT;          
                pixels[y + x * height] = tiles[tileIndex];
                
            }
        }
    }
    
    private void generateTiles() {
        for(int i = 0; i < TILE_AMOUNT * TILE_AMOUNT; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
    }
}
