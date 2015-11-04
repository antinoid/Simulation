package de.antinoid.simulation.core.graphic;

import de.antinoid.simulation.core.Globals;
import de.antinoid.simulation.core.InputState;
import de.antinoid.simulation.core.physic.PhysicState;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/**
 *
 * @author d
 */
public class Renderer extends Canvas {

    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private Screen screen;
    
    // TODO Observers for states
    private PhysicState physicState;
    private InputState inputState;
    
    public Renderer(PhysicState physicState, InputState inputState) {
        this(new Dimension(Globals.WIDTH, Globals.HEIGHT), physicState, inputState);
    }
    
    public Renderer(Dimension dim, PhysicState physicState, InputState inputState) {        
        createFrame(dim);
        image = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        screen = new Screen(dim);
        this.physicState = physicState;
        this.inputState = inputState;
        addKeyListener(inputState);
    }
    
    private void createFrame(Dimension dim) {
        setPreferredSize(dim);
        
        frame = new JFrame("Antinoid Simulation");
        frame.setSize(Globals.WIDTH, Globals.HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(this);
        frame.pack();
    }
    
    public void start() {
        frame.setVisible(true);
        requestFocus();
    }
    
    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        screen.render(inputState.xOffset, inputState.yOffset);
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        
        Graphics g = bs.getDrawGraphics();
        
        // TODO draw
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
        
        g.dispose();
        bs.show();
    }
}
