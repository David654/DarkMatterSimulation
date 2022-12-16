package core.simulation.thread;

import core.simulation.core.Simulation;
import core.simulation.physics.PhysicsConstants;
import core.simulation.physics.body.Body;

public class SimulationThread implements Runnable
{
    private final Simulation simulation;
    private final int start;
    private final int end;

    private Thread thread;
    private boolean running = false;

    public SimulationThread(Simulation simulation, int start, int end)
    {
        this.simulation = simulation;
        this.start = start;
        this.end = end;
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

    private void update()
    {
        for(int i = start; i < end; i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            body.update();
        }
    }

    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = PhysicsConstants.TICK_RATE;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                if(!simulation.isPaused())
                {
                    update();
                }
                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
            }
        }
        stop();
    }
}