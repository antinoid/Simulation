package de.antinoid.simulation.core.graphic.world;

import de.antinoid.simulation.core.graphic.Screen;
import de.antinoid.simulation.core.graphic.sprite.Sprite;

/**
 *
 * @author d
 */
public class Tile {

    private int x, y;
    private final Sprite sprite;
    
    public static Tile waterTile = new WaterTile(Sprite.water);
    public static Tile grassTile1 = new GrassTile(Sprite.grass1);
    public static Tile grassTile2 = new GrassTile(Sprite.grass2);
    public static Tile grassTile3 = new GrassTile(Sprite.grass3);
    public static Tile grassTile4 = new GrassTile(Sprite.grass4);
    
    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }
    
    public void render(int x, int y, Screen screen) {
        
    }
}
