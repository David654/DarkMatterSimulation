package core.simulation.physics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.awt.TextRenderer;
import core.graphics.awt.color.GLColor;
import core.graphics.util.ShapeRenderer;
import core.gui.core.Window;
import core.math.vector.Vector2;
import core.simulation.components.Simulation;

import java.awt.*;

public class Body
{
    private Vector2 initialPosition;
    private double radius;
    private double mass;
    private Vector2 velocity;
    private Vector2 position;
    private Color color;
    private String name;

    private final Simulation simulation;
    private boolean isVisible;
    private Orbit orbit;

    private final TextRenderer textRenderer;
    private double time;
    private double angle;

    public Body(Vector2 initialPosition, double radius, double mass, Vector2 velocity, Color color, String name, Simulation simulation)
    {
        this.initialPosition = initialPosition;
        position = initialPosition;
        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
        this.color = color;
        this.name = name;
        this.simulation = simulation;
        isVisible = true;

        orbit = new Orbit(simulation);
        textRenderer = new TextRenderer(new Font("Segoe UI", Font.PLAIN, 12));
    }

    public Vector2 getInitialPosition()
    {
        return initialPosition;
    }

    public void setInitialPosition(Vector2 initialPosition)
    {
        this.initialPosition = initialPosition;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }

    public double getMass()
    {
        return mass;
    }

    public void setMass(double mass)
    {
        this.mass = mass;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isVisible()
    {
        return isVisible;
    }

    public void setVisible(boolean visible)
    {
        isVisible = visible;
    }

    public Orbit getOrbit()
    {
        return orbit;
    }

    public void setOrbit(Orbit orbit)
    {
        this.orbit = orbit;
    }

    private Vector2 getCenter()
    {
        return new Vector2(position.getX() + radius, position.getY() + radius);
    }

    private Vector2 getGravitationalAttraction()
    {
        Vector2 force = new Vector2();
        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            if(!body.equals(this) && body.isVisible())
            {
                Vector2 center = getCenter();
                double m1 = mass;
                double m2 = body.getMass();
                Vector2 dist = body.getCenter().subtract(center);

                double r = dist.length();

                double value = Constants.G * m1 * m2 / Math.pow(r, 2);
                double valueX = value * dist.getX() / r;
                double valueY = value * dist.getY() / r;
                double angle = Math.atan2(dist.getY(), dist.getX());

                force = force.add(new Vector2(value * Math.cos(angle), value * Math.sin(angle)));
            }
        }
        return force;
    }

    public void update()
    {
        Vector2 force = getGravitationalAttraction();
        Vector2 acceleration = force.multiply(1 / mass);
        velocity = velocity.add(acceleration.multiply(Constants.timeStep));
        position = position.add(velocity.multiply(Constants.timeStep));
        //Vector2 acceleration = new Vector2(force.getX() / mass, force.getY() / mass);
       // position = initialPosition.add(velocity.multiply(time)).add(acceleration.multiply(Math.pow(time, 2) / 2));
        time += Constants.timeStep;

        orbit.addPoint(position);


        angle += velocity.length() / position.length() * Constants.timeStep;

        if(angle > 4 * Math.PI)
        {
            //orbit.clear();
            angle = 0;
        }
    }

    public void render(GL2 gl)
    {
        Vector2 pos = new Vector2(position.getX() / Constants.AU * simulation.getScale() * 2, position.getY() / Constants.AU * simulation.getScale() * 2);
        ShapeRenderer shapeRenderer = new ShapeRenderer(gl);

        GLColor glColor = GLColor.toGLColor(color);
        gl.glColor3f(glColor.getRed(), glColor.getGreen(), glColor.getBlue());
        shapeRenderer.fillCircle((float) pos.getX(), (float) pos.getY(), (float) (radius / 1.39286e9 * simulation.getScale()));

        orbit.render(gl);

        int width = Window.SCENE_WIDTH;
        int height = Window.HEIGHT;

        textRenderer.beginRendering(width, height);
        textRenderer.setColor(Color.WHITE);
        textRenderer.setSmoothing(true);

        Vector2 center = getCenter().multiply(1 / Constants.AU * simulation.getScale() * 2);
        //textRenderer.draw(name, (int) (width / 2 + center.getX() * width / 2), (int) (height / 2 + center.getY() * height / 2 + height * radius / 1.39286e9 * simulation.getScale() / 1.5));
        textRenderer.draw(name, (int) (width / 2 + width / 2 * pos.getX()), (int) (height / 2 + height / 2 * pos.getY()));
        textRenderer.endRendering();

        //System.out.println(Window.WIDTH + ", " + Window.SCENE_WIDTH);
    }
}