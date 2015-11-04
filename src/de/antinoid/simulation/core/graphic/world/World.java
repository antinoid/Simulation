package de.antinoid.simulation.core.graphic.world;

import de.antinoid.simulation.core.graphic.Screen;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author d
 */
public class World {

    protected int width, height;
    protected int[] tiles;
    
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateWorld();
    }
    
    public World(String worldPath) {
        loadWorld(worldPath);
    }
    
    protected void generateWorld() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                
            }
        }
    }
    
    private void loadWorld(String path) {
        try {
            //BufferedImage image = ImageIO.read(null)
        } catch (Exception e) {
        }
    }
    
    public void update() {
        
    }
    
    public void render(int xScroll, int yScroll, Screen screen) {
        
    }
}
