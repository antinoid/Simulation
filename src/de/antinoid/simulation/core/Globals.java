package de.antinoid.simulation.core;

/**
 *
 * @author d
 */
public class Globals {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    public static final int TILE_MAP_ROWS = 64;
    public static final int TILE_MAP_COLUMNS = 64;
    public static final int TILE_MAP_SIZE = TILE_MAP_ROWS * TILE_MAP_COLUMNS;
    public static final int TILE_SIZE = 32;
    public static final int TILE_MASK = (int) (Math.log(TILE_SIZE)/Math.log(2));
}
