package core.simulation.components;

import com.jogamp.opengl.GL2;
import core.graphics.awt.color.GLColor;
import core.math.vector.Vector2;
import core.simulation.handler.BodyHandler;
import core.simulation.physics.Body;
import core.simulation.physics.Constants;

import java.math.BigDecimal;

public class Simulation
{
    private final BodyHandler bodyHandler;

    public Simulation()
    {
        bodyHandler = new BodyHandler();
        //Body sun = new Body(new Vector2(0, 0), 64, new BigDecimal("1.989e30"), 0, GLColor.ORANGE);
        //Body mercury = new Body(new Vector2(-0.4f * Constants.AU, 0), 4, new BigDecimal("3.285e23"), new Vector2(47.87f * 1000, 0), GLColor.SLATE);
        //Body venus = new Body(new Vector2(-0.7f * Constants.AU, 0), 8, new BigDecimal("4.867e24"), new Vector2(-35.02f * 1000, 0), GLColor.FIREBRICK);
        //Body earth = new Body(new Vector2(-1 * Constants.AU, 0), 8, new BigDecimal("5.972e24"), new Vector2(29.78f * 1000, 0), GLColor.SKY);
        Body moon = new Body(new Vector2(-1.00257f * Constants.AU, 0), 4, new BigDecimal("7.348e22"), new Vector2(29.78f * 1000, 0), GLColor.GRAY, "Moon");
        Body mars = new Body(new Vector2(-1.5f * Constants.AU, 0), 8, new BigDecimal("6.39e23"), new Vector2(24.077f * 1000, 0), GLColor.RED, "Mars");
        //Body jupiter = new Body(new Vector2(-5.2f * Constants.AU, 0), 32, new BigDecimal("1.898e27"), new Vector2(13.07f * 1000, 0), GLColor.GOLDENROD);
        //Body saturn = new Body(new Vector2(-9.6f * Constants.AU, 0), 32, new BigDecimal("5.683e26"), new Vector2(9.69f * 1000, 0), GLColor.GOLD);
        //Body uranus = new Body(new Vector2(-19.2f * Constants.AU, 0), 16, new BigDecimal("8.681e25"), new Vector2(6.81f * 1000, 0), GLColor.ROYAL);
        Body neptune = new Body(new Vector2(-30f * Constants.AU, 0), 16, new BigDecimal("1.024e26"), new Vector2(5.43f * 1000, 0), GLColor.CYAN, "Neptune");

//        bodyHandler.add(sun);
//        bodyHandler.add(mercury);
//        bodyHandler.add(venus);
//        bodyHandler.add(earth);
        bodyHandler.add(moon);
        bodyHandler.add(mars);
        /*bodyHandler.add(jupiter);
        bodyHandler.add(saturn);
        bodyHandler.add(uranus);*/
        bodyHandler.add(neptune);
    }

    public BodyHandler getBodyHandler()
    {
        return bodyHandler;
    }

    public void update()
    {
        bodyHandler.update();
    }

    public void render(GL2 gl)
    {
        bodyHandler.render(gl);
    }
}