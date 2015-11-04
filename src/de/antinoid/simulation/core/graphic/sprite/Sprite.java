package de.antinoid.simulation.core.graphic.sprite;

/**
 *
 * @author d
 */
public class Sprite {

    private final int column, row;
    private final Spritesheet sheet;
    private final int size;
    private final int[] pixels;
    
    public static Sprite water = new Sprite(16, 0, 0, Spritesheet.tiles);
    public static Sprite forest = new Sprite(16, 1, 0, Spritesheet.tiles);
    public static Sprite grass1 = new Sprite(16, 2, 0, Spritesheet.tiles);
    public static Sprite grass2 = new Sprite(16, 3, 0, Spritesheet.tiles);
    public static Sprite grass3 = new Sprite(16, 4, 0, Spritesheet.tiles);
    public static Sprite grass4 = new Sprite(16, 5, 0, Spritesheet.tiles);
        
    public Sprite(int size, int x, int y, Spritesheet sheet) {
        this.size = size;
        this.column = x * size;
        this.row = y * size;
        this.sheet = sheet;
        pixels = new int[size * size];
        loadPixels();
    }
    
    private void loadPixels() {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.pixels[(x + column) + (y + row) * sheet.getWidth()];
            }
        }
    }
    
    public int getPixel(int x, int y) {
        return pixels[(x & (size-1)) + (y & (size-1)) * size];
    }
}
