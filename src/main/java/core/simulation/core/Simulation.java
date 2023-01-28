package core.simulation.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.simulation.input.core.MouseInput;
import core.simulation.physics.PhysicsConstants;
import core.simulation.starsystems.*;

import java.awt.*;

public class Simulation
{
    private final StarSystem starSystem;
    private float scale = 0.5f;
    private double time = 0;
    private boolean paused = false;

    public Simulation()
    {
        starSystem = new SolarSystem();
        initCelestialObjects();
        initDarkMatter();
        createThreads(1);
    }

    public StarSystem getStarSystem()
    {
        return starSystem;
    }

    public double getTime()
    {
        return time;
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

    private void initCelestialObjects()
    {
        starSystem.initStarSystem();
    }

    private void initDarkMatter()
    {
        starSystem.initDarkMatter();
    }

    public void translate(int mouseX, int mouseY)
    {
        float x = (float) -mouseX / Window.WIDTH;
        float y = (float) mouseY / Window.HEIGHT;
        starSystem.getBodyHandler().translate(x * MouseInput.mouseSensitivityX / scale / MouseInput.scaleStep, y * MouseInput.mouseSensitivityY / scale / MouseInput.scaleStep);
    }

    private void createThreads(int threads)
    {
        int bodies = starSystem.getBodyHandler().getSize() / threads;
        for(int i = 0; i < starSystem.getBodyHandler().getSize(); i += bodies)
        {
            //SimulationThread thread = new SimulationThread(this, i, i + bodies);
            //thread.start();
        }
    }

    public void distributeBodiesRandomly()
    {
       // starSystem.getBodyHandler().update(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS + Math.random() * 100));
        starSystem.getBodyHandler().distributeCelestialObjectsRandomly();
    }

    public void update()
    {
        //long time = System.nanoTime();

        time += PhysicsConstants.DELTA_TIME;
        starSystem.update(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS));

       // deltaTime = (int) ((time - lastTime) / 1000000000);
        //lastTime = time;

        //System.out.println();

        /*double time = this.time / 3600 / 24;
        int years = (int) (time / 365);
        int days = (int) (time % 365 * 365);
        int months = days / 30;
        days = days % 30;

        //System.out.println(years + " years, " + months + " months, " + days + " days");

        this.time += Constants.timeStep;**/


    }

    public void render(ShapeRenderer shapeRenderer)
    {
        starSystem.getBodyHandler().render(shapeRenderer);
    }
}