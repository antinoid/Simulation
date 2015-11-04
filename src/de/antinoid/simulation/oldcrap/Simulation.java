package de.antinoid.simulation.oldcrap;

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
    
    static {
        //System.setProperty("sun.java2d.trace", "timestamp,log,count");
        //System.setProperty("sun.java2d.opengl", "True");
        //System.setProperty("sun.java2d.d3d", "True"); //default on windows
    }
    
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
    
    // old working
    private void loop1(final double delta) {
        
        double nextTime = (double)System.nanoTime() / 1000000000.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while(running) {
            double now = (double)System.nanoTime() / 1000000000.0;
            
            if(now >= nextTime) {
                nextTime += delta;
                update();
                updates++;
                if( now < nextTime) {
                    render();
                    frames++;
                }
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
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Updates: " + updates);
                System.out.println("Frames: " + frames);
                updates = 0;
                frames = 0;
            }
        }
    }
    
    // new try
    // unstable shit :/
    public void loop2(final double fps) {        
        long lastTime = System.nanoTime();
        final double ns = 1000000000 / fps;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        
        while(running) {
            
            long now = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                System.out.println("updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    @Override
    public void run() {
        loop1(1 / Globals.FPS);
        //loop2(Globals.FPS);
    }
    
    private void update() {
       // System.out.println("update");
       // update stress test
        if(Math.random() < 0.0) {
            for(int i = 0; i < 100000; i++) {
                Math.cos(Math.cos(Math.cos(i * Math.sqrt(i))));
            }
        }
    }
    
    private void render() {
        //System.out.println("render");
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        screen.clear();
        screen.render();
        
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        
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