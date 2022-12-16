package core.simulation.physics.body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.graphics.textures.TextureManager;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.PhysicsConstants;

import java.awt.*;

public class Body
{
    /**
     * The starting position of the body in astronomical units (AU).
     */
    private Vector3 initialPosition;

    /**
     * The current position of the body in astronomical units (AU), which is regularly updated.
     * @see #update()
     */
    private Vector3 position;

    /**
     * The radius of the body in m.
     */
    private double radius;

    /**
     * The mass of the body in kg.
     */
    private double mass;

    /**
     * The velocity of the body in m/s.
     */
    private Vector3 velocity;

    /**
     * The speed of the body rotation around its own axis.
     */
    private double rotationSpeed;

    private double axisInclination;

    /**
     * The color of the body.
     */
    private Color color;

    /**
     * The name of the body.
     */
    private String name;

    /**
     * An instance of the class Simulation for interacting with other bodies.
     * @see Simulation
     */
    private final Simulation simulation;

    /**
     * Whether the body is being rendered or not.
     * @see core.simulation.handler.BodyHandler#render(ShapeRenderer) 
     */
    private boolean isVisible;

    /**
     * An instance of the class Orbit for creating an orbit of the body.
     * @see Orbit
     */
    private Orbit orbit;

    private double angle;

    /**
     * The texture of the body.
     */
    private Texture texture;

    private Ring ring;

    private double time = 0;

    /**
     * Constructor.
     * @param initialPosition the initial position of the body
     * @param radius the radius of the body
     * @param mass the mass of the body
     * @param velocity the velocity of the body
     * @param name the name of the body
     * @param simulation an instance of the class Simulation
     */
    public Body(Vector3 initialPosition, double radius, double mass, Vector3 velocity, double rotationSpeed, double axisInclination, String name, Simulation simulation)
    {
        this.initialPosition = initialPosition.multiply(PhysicsConstants.AU);
        position = this.initialPosition;
        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
        this.rotationSpeed = rotationSpeed;
        this.axisInclination = axisInclination;
        this.name = name;
        this.simulation = simulation;
        texture = new Texture(TextureManager.DEFAULT_PLANET_TEXTURE_PATH);
        isVisible = true;

        orbit = new Orbit(simulation);
    }

    public Vector3 getInitialPosition()
    {
        return initialPosition;
    }

    public void setInitialPosition(Vector3 initialPosition)
    {
        this.initialPosition = initialPosition;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public void setPosition(Vector3 position)
    {
        this.position = position;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
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

    public Vector3 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector3 velocity)
    {
        this.velocity = velocity;
    }

    public double getRotationSpeed()
    {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed)
    {
        this.rotationSpeed = rotationSpeed;
    }

    public double getAxisInclination()
    {
        return axisInclination;
    }

    public void setAxisInclination(double axisInclination)
    {
        this.axisInclination = axisInclination;
    }

    public Color getColor()
    {
        return color;
    }

    public String getColorString()
    {
        return "rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
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

    public Texture getTexture()
    {
        return texture;
    }

    public void setTexture(String texturePath)
    {
        this.texture = new Texture(texturePath);
    }

    public Ring getRing()
    {
        return ring;
    }

    public void setRing(Ring ring)
    {
        this.ring = ring;
    }

    private Vector3 getCenter()
    {
        return new Vector3(position.getX() + radius, position.getY() + radius, position.getZ() + radius);
    }

    /**
     * The core update logic method. Uses Newton's law of gravitation F = G(m1 * m2) / R^2
     * @return the Vector3 class with the final force vector.
     */
    private Vector3 getGravitationalAttraction()
    {
        Vector3 force = new Vector3();
        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            if(!body.equals(this) && body.isVisible())
            {
                Vector3 center = getCenter();
                double m1 = mass;
                double m2 = body.getMass();
                Vector3 dist = body.getCenter().subtract(center);

                double r = dist.length();
                double value = PhysicsConstants.G * m1 * m2 / Math.pow(r, 2);
                double valueX = value * dist.getX() / r;
                double valueY = value * dist.getY() / r;
                double valueZ = value * dist.getZ() / r;

                force = force.add(new Vector3(valueX, valueY, valueZ));
            }
        }
        return force;
    }

    public synchronized void update()
    {
        Vector3 force = getGravitationalAttraction();
        Vector3 acceleration = force.multiply(1 / mass);
        //velocity = velocity.add(acceleration.multiply(PhysicsConstants.timeStep));

        double increment = 0.0001;

       // for(double t = increment; t < PhysicsConstants.TIME_STEP; t += increment)
      //  {

      //  }

        velocity = velocity.add(acceleration.multiply(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS)));
        position = position.add(velocity.multiply(PhysicsConstants.TIME_STEP.apply(PhysicsConstants.DAYS)));

        //orbit.addPoint(position);

        /*angle += velocity.length() / position.length() * PhysicsConstants.timeStep;

        if(angle > 4 * Math.PI)
        {
            //orbit.clear();
            angle = 0;
        }**/
    }

    public void render(ShapeRenderer shapeRenderer)
    {
        //textRenderer.draw(name, (int) (width / 2 + width / 2 * pos.getX()), (int) (height / 2 + height / 2 * pos.getY()));
        //textRenderer.endRendering();

        //System.out.println(Window.WIDTH + ", " + Window.SCENE_WIDTH);
    }
}