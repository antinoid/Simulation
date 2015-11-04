package de.antinoid.simulation.core.graphic.sprite;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author d
 */
public class Spritesheet {

    private final int width;
    private final int height;
    protected int[] pixels;
    
    protected static Spritesheet tiles = new Spritesheet("/textures/spritesheet.png", 256);

    public Spritesheet(String imagePath, int size) {
        this(imagePath, size, size);
    }

    public Spritesheet(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        loadPixels(imagePath);
    }
    
    public int getWidth() {
        return width;
    }

    private void loadPixels(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(imagePath));
            image.getRGB(0, 0, width, height, pixels, 0, width);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
