package core.simulation.components;

import com.jogamp.opengl.GL2;
import core.gui.core.Window;
import core.math.vector.Vector2;
import core.simulation.handler.BodyHandler;
import core.simulation.input.MouseInput;
import core.simulation.physics.Body;
import core.simulation.physics.Constants;

import java.awt.*;

public class Simulation implements Runnable
{
    private Thread thread;
    private boolean running = false;
    private final BodyHandler bodyHandler;
    private float scale = 0.5f;
    public static double deltaTime;
    private long lastTime = System.nanoTime();
    private boolean paused = false;

    public Simulation()
    {
        bodyHandler = new BodyHandler();
        Body sun = new Body(new Vector2(0, 0), 696_340_000, 1.989e30, new Vector2(0, 0), new Color(228, 91, 25), "Sun", this);
        Body mercury = new Body(new Vector2(-0.387 * Constants.AU, 0), 2_439_700, 3.285e23, new Vector2(0, 47.87 * 1000), new Color(153, 153, 153), "Mercury", this);
        Body venus = new Body(new Vector2(-0.723 * Constants.AU, 0), 6_051_800, 4.867e24, new Vector2(0, -35.02 * 1000), new Color(180, 116, 33), "Venus", this);
        Body earth = new Body(new Vector2(-1 * Constants.AU, 0), 6_371_000, 5.972e24, new Vector2(0, 29.78 * 1000), new Color(50, 87, 129), "Earth", this);
        Body moon = new Body(new Vector2(-1.00257 * Constants.AU, 0), 1_737_400, 7.348e22, new Vector2(0, (29.78 + 1.02) * 1000), new Color(233, 218, 216), "Moon", this);
        Body mars = new Body(new Vector2(-1.52 * Constants.AU, 0), 3_389_500, 6.39e23, new Vector2(0, 24.077 * 1000), new Color(239, 123, 86), "Mars", this);
        Body jupiter = new Body(new Vector2(-5.2 * Constants.AU, 0), 69_911_000, 1.898e27, new Vector2(0, 13.07 * 1000), new Color(177, 126, 91), "Jupiter", this);
        Body saturn = new Body(new Vector2(-9.58 * Constants.AU, 0), 58_232_000, 5.683e26, new Vector2(0, 9.69 * 1000), new Color(196, 167, 111), "Saturn", this);
        Body uranus = new Body(new Vector2(-19.2 * Constants.AU, 0), 25_362_000, 8.681e25, new Vector2(0, 6.81 * 1000), new Color(191, 228, 231), "Uranus", this);
        Body neptune = new Body(new Vector2(-30.05 * Constants.AU, 0), 24_622_000, 1.024e26, new Vector2(0, 5.43 * 1000), new Color(60, 89, 229), "Neptune", this);

        bodyHandler.add(sun);
        bodyHandler.add(mercury);
        bodyHandler.add(venus);
        bodyHandler.add(earth);
        bodyHandler.add(moon);
        bodyHandler.add(mars);
        bodyHandler.add(jupiter);
        bodyHandler.add(saturn);
        bodyHandler.add(uranus);
        bodyHandler.add(neptune);

        start();
    }

    public BodyHandler getBodyHandler()
    {
        return bodyHandler;
    }

    public float getScale()
    {
        return scale;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
        //bodyHandler.setScale(scale);
    }

    public boolean isPaused()
    {
        return paused;
    }

    public void setPaused(boolean paused)
    {
        this.paused = paused;
    }

    public void translate(int mouseX, int mouseY)
    {
        float x = (float) -mouseX / Window.WIDTH;
        float y = (float) mouseY / Window.HEIGHT;
        bodyHandler.translate(x * MouseInput.mouseSensitivityX / scale / MouseInput.scaleStep, y * MouseInput.mouseSensitivityY / scale / MouseInput.scaleStep);
    }

    public void update()
    {
        long time = System.nanoTime();

        bodyHandler.update();

        deltaTime = (int) ((time - lastTime) / 1000000000);
        lastTime = time;

        /*double time = this.time / 3600 / 24;
        int years = (int) (time / 365);
        int days = (int) (time % 365 * 365);
        int months = days / 30;
        days = days % 30;

        //System.out.println(years + " years, " + months + " months, " + days + " days");

        this.time += Constants.timeStep;**/


    }

    public void render(GL2 gl)
    {
        bodyHandler.render(gl);
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = Constants.tickRate;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                if(!paused)
                {
                    update();
                }
                delta--;
            }
            if(running)
            {
                //render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //hud.FPS = frames;
                frames = 0;
            }
        }
        stop();
    }
}