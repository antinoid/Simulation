package de.antinoid.simulation.oldcrap;

/**
 *
 * @author d
 */
public class MassPoint {

    private float x, y;
    private float lastX, lastY;
    private float accX, accY;
    
    public MassPoint(int x, int y) {
        this.x = this.lastX = x;
        this.y = this.lastY = y;
    }
}
