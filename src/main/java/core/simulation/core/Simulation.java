package core.simulation.core;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.graphics.textures.TextureManager;
import core.math.vector.Vector3;
import core.simulation.handler.BodyHandler;
import core.simulation.handler.DarkMatterHandler;
import core.simulation.input.core.MouseInput;
import core.simulation.physics.body.Body;
import core.simulation.physics.body.Ring;
import core.simulation.physics.darkmatter.DarkMatter;
import core.simulation.thread.SimulationThread;

import java.awt.*;

public class Simulation
{
    private final BodyHandler bodyHandler;
    private final DarkMatterHandler darkMatterHandler;
    private float scale = 0.5f;
    public static double deltaTime;
    private long lastTime = System.nanoTime();
    private boolean paused = false;

    public Simulation()
    {
        bodyHandler = new BodyHandler();
        darkMatterHandler = new DarkMatterHandler();
        initBodies();
        initDarkMatter();
        createThreads(10);
    }

    private void initBodies()
    {
        float scale = 7;
        Body sun = new Body(new Vector3(0, 0, 0), 696_340_000, 1.989e30, new Vector3(0, 0, 0), 0, 0, "Sun", this);
        Body mercury = new Body(new Vector3(-0.387, 0, 0), 2_439_700 * scale * scale, 3.285e23, new Vector3(0, 0, 47.87 * 1000), 10.83 / 3.6, 0.034, "Mercury", this);
        Body venus = new Body(new Vector3(-0.723, 0, 0), 6_051_800 * scale * scale, 4.867e24, new Vector3(0, 0, -35.02 * 1000), 6.52 / 3.6, 177.4, "Venus", this);
        Body earth = new Body(new Vector3(-1, 0, 0), 6_371_000 * scale * scale, 5.972e24, new Vector3(0, 0, 29.78 * 1000), 1574 / 3.6, 23.4, "Earth", this);
        Body moon = new Body(new Vector3(-1.00257, 0, 0), 1_737_400 * scale * scale, 7.348e22, new Vector3(0, 0, (29.78 + 1.02) * 1000), 16.7 / 3.6, 6.7, "Moon", this);
        Body mars = new Body(new Vector3(-1.52, 0, 0), 3_389_500 * scale * scale, 6.39e23, new Vector3(0, 0, 24.077 * 1000), 866 / 3.6, 25.2, "Mars", this);
        Body jupiter = new Body(new Vector3(-5.2, 0, 0), 69_911_000 * scale, 1.898e27, new Vector3(0, 0, 13.07 * 1000), 45_583 / 3.6, 3.1, "Jupiter", this);
        Body saturn = new Body(new Vector3(-9.58, 0, 0), 58_232_000 * scale, 5.683e26, new Vector3(0, 0, 9.69 * 1000), 36_840 / 3.6, 26.7, "Saturn", this);
        Body uranus = new Body(new Vector3(-19.2, 0, 0), 25_362_000 * scale, 8.681e25, new Vector3(0, 0, 6.81 * 1000), 14_794 / 3.6, 97.8, "Uranus", this);
        Body neptune = new Body(new Vector3(-30.05, 0, 0), 24_622_000 * scale, 1.024e26, new Vector3(0, 0, 5.43 * 1000), 9_719 / 3.6, 28.3, "Neptune", this);

        sun.setColor(new Color(228, 91, 25));
        mercury.setColor(new Color(153, 153, 153));
        venus.setColor(new Color(180, 116, 33));
        earth.setColor(new Color(50, 87, 129));
        moon.setColor(new Color(233, 218, 216));
        mars.setColor(new Color(239, 123, 86));
        jupiter.setColor(new Color(177, 126, 91));
        saturn.setColor(new Color(196, 167, 111));
        uranus.setColor(new Color(191, 228, 231));
        neptune.setColor(new Color(60, 89, 229));

        sun.setTexture(TextureManager.SUN_TEXTURE_PATH);
        mercury.setTexture(TextureManager.MERCURY_TEXTURE_PATH);
        venus.setTexture(TextureManager.VENUS_TEXTURE_PATH);
        earth.setTexture(TextureManager.EARTH_TEXTURE_PATH);
        moon.setTexture(TextureManager.MOON_TEXTURE_PATH);
        mars.setTexture(TextureManager.MARS_TEXTURE_PATH);
        jupiter.setTexture(TextureManager.JUPITER_TEXTURE_PATH);
        saturn.setTexture(TextureManager.SATURN_TEXTURE_PATH);
        saturn.setRing(new Ring(saturn.getPosition(), 66_900_000 * scale, 480_000_000 * scale, TextureManager.SATURN_RING_TEXTURE_PATH));
        uranus.setTexture(TextureManager.URANUS_TEXTURE_PATH);
        neptune.setTexture(TextureManager.NEPTUNE_TEXTURE_PATH);

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
    }

    private void initDarkMatter()
    {
        float scale = 10;
        double radius1 = 0;
        double radius2 = bodyHandler.get(0).getRadius();

        for(int i = 0; i < bodyHandler.getSize() - 1; i++)
        {
            darkMatterHandler.add(new DarkMatter(Math.random(), radius1 * scale, radius2 * scale, 300));
            radius1 = bodyHandler.get(i).getRadius();
            radius2 = bodyHandler.get(i + 1).getRadius();
        }
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

    private void createThreads(int threads)
    {
        int bodies = bodyHandler.getSize() / threads;
        for(int i = 0; i < bodyHandler.getSize(); i += bodies)
        {
            SimulationThread thread = new SimulationThread(this, i, i + bodies);
            thread.start();
        }
    }

    public void update()
    {
        long time = System.nanoTime();

        //bodyHandler.update();

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

    public void render(ShapeRenderer shapeRenderer)
    {
        bodyHandler.render(shapeRenderer);
    }
}