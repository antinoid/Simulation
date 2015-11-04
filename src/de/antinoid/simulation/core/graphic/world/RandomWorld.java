package de.antinoid.simulation.core.graphic.world;

import de.antinoid.simulation.core.graphic.world.World;
import java.util.Random;

/**
 *
 * @author d
 */
public class RandomWorld extends World {

    Random random = new Random();
    
    public RandomWorld(int width, int height) {
        super(width, height);
    }
    
    @Override
    protected void generateWorld() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(6);
            }
        }
    }

}
