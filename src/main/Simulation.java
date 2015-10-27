package main;

import java.awt.Canvas;
import java.awt.Color;
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
public class Simulation extends Canvas implements Runnable {

    private Thread loop;
    private JFrame frame;
    private boolean running = false;
    
    private BufferedImage image = new BufferedImage(Globals.HEIGHT, Globals.WIDTH, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    
    private Screen screen;
    
    
    public Simulation() {
        Dimension dim = new Dimension(Globals.WIDTH, Globals.HEIGHT);
        setPreferredSize(dim);
        screen = new Screen(dim);
    }
    
    public void createFrame(Canvas canvas) {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setTitle("Simulation");
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);        
        frame.setVisible(true);
    }
    
    public void start() {
        loop = new Thread(this);
        running = true;
        loop.start();
    }
    
    public void stop() {
        running = false;
    }
    
    public void pause() {
        running = false;
    }
    
    @Override
    public void run() {
        // 
        final double delta = 1 / Globals.FPS;
        double nextTime = (double)System.nanoTime() / 1000000000.0;
        
        while(running) {
            
            double now = (double)System.nanoTime() / 1000000000.0;
            
            if(now >= nextTime) {
                nextTime += delta;
                update();
                render();
            } else {
                // calculate sleep time
                int sleepTime = (int)(1000.0 * (nextTime - now));
                
                if(sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    private void update() {
        System.out.println("update");
    }
    
    private void render() {
        System.out.println("render");
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        screen.render();
        
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //g.setColor(Color.black);
        //g.fillRect(0, 0, Globals.WIDTH, Globals.HEIGHT);
        g.drawImage(image, 0, 0, Globals.WIDTH, Globals.HEIGHT, null);
        
        g.dispose();
        bs.show();
    }
    
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.createFrame(simulation);
        simulation.start();
    }
}