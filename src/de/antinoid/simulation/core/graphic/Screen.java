package de.antinoid.simulation.core.graphic;

import de.antinoid.simulation.core.graphic.sprite.Sprite;
import de.antinoid.simulation.core.Globals;
import de.antinoid.simulation.core.graphic.world.Tile;
import java.awt.Dimension;
import java.util.Random;

/**
 *
 * @author d
 */
public class Screen {

    private final int width, height;
    private int[] tiles = new int[Globals.TILE_MAP_SIZE];
    protected int[] pixels;
    Random random = new Random();
    
    public Screen(Dimension dim) {
        this.width = dim.width;
        this.height = dim.height;
        pixels = new int[width * height];
        
        for(int i = 0; i < Globals.TILE_MAP_SIZE; i++) {
            tiles[i] = random.nextInt(0xFFFFFF);
        }
        tiles[0] = 0;
    }
    
    protected void clear() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }
    
    protected void render(int xOffset, int yOffset) {
        for(int y = 0; y < height; y++) {
            int y2 = y + yOffset;
            if(y2 < 0 || y2 >= height) continue;
            for(int x = 0; x < width; x++) {
                int x2 = x + xOffset;
                if(x2 < 0 || x2 >= width) continue;
                    pixels[x2 + y2 * width] = Sprite.water.getPixel(x, y);
            }            
        }
    }
    
    protected void renderTile(int x, int y, Tile tile) {
        
    }
}
