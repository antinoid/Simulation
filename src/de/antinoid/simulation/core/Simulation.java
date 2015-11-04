package de.antinoid.simulation.core;

import de.antinoid.simulation.core.physic.PhysicState;
import de.antinoid.simulation.core.graphic.Renderer;

/**
 *
 * @author d
 */
public class Simulation implements Runnable {

    private Renderer renderer;
    private PhysicState physicState;
    private InputState inputState;
    private Thread runner;
    private boolean isRunning = false;
    
    public Simulation() {
        physicState = new PhysicState();
        inputState = new InputState();
        renderer = new Renderer(physicState, inputState);
        runner = new Thread(this, "runner");
    }
    
    public void start() {
        isRunning = true;
        renderer.start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        runner.start();
    }
    
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while(isRunning) {
            render();
            update();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }
    
    private void render() {
        //System.out.println("render");
        renderer.render();
    }
    
    private void update() {
        if(inputState.up) {
            inputState.yOffset++;
        } else if(inputState.down) {
            inputState.yOffset--;
        } else if(inputState.left) {
            inputState.xOffset++;
        } else if(inputState.right) {
            inputState.xOffset--;
        }
        physicState.update();
    }
    
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.start();
    }
}
