package core.simulation.physics.celestialobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.graphics.core.Scene;
import core.util.TextureUtils;
import core.util.MathUtils;
import core.math.vector.Vector3;
import core.simulation.handler.CelestialObjectHandler;
import core.simulation.physics.PhysicsConstants;

import java.awt.*;

public class CelestialObject
{
    /**
     * The starting position of the body in astronomical units (AU).
     */
    private Vector3 initialPosition;

    /**
     * The current position of the body in astronomical units (AU), which is regularly updated.
     * @see #update(double) 
     */
    private Vector3 position;

    private Vector3 tmpPosition;

    /**
     * The dimension of the body.
     */
    private Vector3 dimensions;

    /**
     * The mass of the body in kg.
     */
    private double mass;

    /**
     * The velocity of the body in km/s.
     */
    private Vector3 velocity;

    private Vector3 initialVelocity;

    /**
     * The acceleration of the body.
     * @see #update(double) 
     */
    private Vector3 acceleration;

    /**
     * The speed of the body rotation around its own axis.
     */
    private double rotationSpeed;

    /**
     * The angle at which the body orbit around the Sun is tilted relative to the ecliptic plane.
     */
    private double orbitalInclination;

    /**
     * The angle between the body's rotational axis and its orbital axis.
     */
    private double axialTilt;

    /**
     * The color of the body.
     */
    private Color color;

    /**
     * The name of the body.
     */
    private String name;

    /**
     * Whether the body is being rendered or not.
     * @see CelestialObjectHandler#render(ShapeRenderer)
     */
    private boolean isVisible;

    /**
     * An instance of the class Orbit for creating an orbit of the body.
     * @see Orbit
     */
    private Orbit orbit;

    /**
     * The texture of the body.
     */
    private Texture texture;

    /**
     * The bump texture of the body.
     */
    private Texture bumpTexture;

    private Ring ring;

    private double time = 0;

    private double dt = 0;


    private Vector3 k1p;
    private Vector3 k2p;
    private Vector3 k3p;
    private Vector3 k4p;

    private Vector3 k1a;
    private Vector3 k2a;
    private Vector3 k3a;
    private Vector3 k4a;

    private Vector3 k1v;
    private Vector3 k2v;
    private Vector3 k3v;
    private Vector3 k4v;

    /**
     * Constructor.
     * @param initialPosition the initial position of the body
     * @param dimensions the dimensions of the body
     * @param mass the mass of the body
     * @param velocity the velocity of the body
     * @param name the name of the body
     */
    public CelestialObject(Vector3 initialPosition, Vector3 dimensions, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, double orbitalInclination, String name)
    {
        this.initialPosition = initialPosition;
     //   this.initialPosition = MathUtils.rotateZ(initialPosition, Math.toRadians(orbitalInclination));
       // this.initialPosition.setX(initialPosition.getX());
        //System.out.println(name + ": " + this.initialPosition);
        this.initialPosition = this.initialPosition.multiply(PhysicsConstants.AU);
        position = this.initialPosition;
        position = MathUtils.rotateZ(position, Math.toRadians(orbitalInclination));
        tmpPosition = position;
        this.dimensions = dimensions.multiply(1000);
        this.mass = mass;
        this.velocity = velocity.multiply(1000);
        initialVelocity = this.velocity;
        acceleration = new Vector3();
        this.rotationSpeed = rotationSpeed;
        this.axialTilt = axialTilt;
        this.orbitalInclination = orbitalInclination;
        this.name = name;
        texture = new Texture(TextureUtils.DEFAULT_PLANET_TEXTURE_PATH);
        isVisible = true;

        //orbit = new Orbit(Scene.simulation);
    }

    public boolean equals(Object o)
    {
        CelestialObject object = (CelestialObject) o;
        return position.equals(object.getPosition()) && dimensions.equals(object.getDimensions()) && mass == object.getMass()
                && velocity.equals(object.getVelocity()) && rotationSpeed == object.getRotationSpeed() && axialTilt == object.getAxialTilt()
                && orbitalInclination == object.getOrbitalInclination() && name.equals(object.getName());
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
        this.position = position.multiply(PhysicsConstants.AU);
        this.initialPosition = this.position;
        this.position = MathUtils.rotateZ(this.position, Math.toRadians(orbitalInclination));
    }

    public Vector3 getInitialPositionAU()
    {
        return initialPosition.multiply(1 / PhysicsConstants.AU);
    }

    public Vector3 getTmpPosition()
    {
        return tmpPosition;
    }

    public void setTmpPosition(Vector3 tmpPosition)
    {
        this.tmpPosition = tmpPosition;
    }

    public Vector3 getDimensions()
    {
        return dimensions;
    }

    public void setDimensions(Vector3 dimensions)
    {
        this.dimensions = dimensions;
    }

    public double getRadius()
    {
        return (dimensions.getX() + dimensions.getY() + dimensions.getZ()) / 3;
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
        this.initialVelocity = this.velocity;
    }

    public double getRotationSpeed()
    {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed)
    {
        this.rotationSpeed = rotationSpeed;
    }

    public double getAxialTilt()
    {
        return axialTilt;
    }

    public void setAxialTilt(double axialTilt)
    {
        this.axialTilt = axialTilt;
    }

    public double getOrbitalInclination()
    {
        return orbitalInclination;
    }

    public void setOrbitalInclination(double orbitalInclination)
    {
        this.orbitalInclination = orbitalInclination;
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

    public void setColor(String texturePath)
    {
        color = TextureUtils.getMostCommonColor(texturePath);
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

    public Texture getBumpTexture()
    {
        return bumpTexture;
    }

    public void setBumpTexture(String texturePath)
    {
        this.bumpTexture = new Texture(texturePath);
    }

    public Ring getRing()
    {
        return ring;
    }

    public void setRing(Ring ring)
    {
        this.ring = ring;
    }

    public Vector3 getCenter(Vector3 position)
    {
        return new Vector3(position.getX() + dimensions.getX(), position.getY() + dimensions.getY(), position.getZ() + dimensions.getZ());
    }

    /**
     * The core update logic method. Uses Newton's law of gravitation F = G(m1 * m2) / R^2
     * @return the Vector3 class with the final force vector.
     */
    private Vector3 getGravitationalAcceleration(Vector3 position)
    {
        Vector3 acceleration = new Vector3();
        for(int i = 0; i < Scene.simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = Scene.simulation.getStarSystem().getBodyHandler().get(i);
            if(!celestialObject.equals(this) && celestialObject.isVisible())
            {
                Vector3 dist = celestialObject.getCenter(celestialObject.getPosition()).subtract(this.getCenter(position));
                double r = dist.length();
                double value = PhysicsConstants.G * celestialObject.getMass() / Math.pow(r, 3);
                acceleration = acceleration.add(dist.multiply(value));
            }
        }
        return acceleration;
    }

    private Vector3 calculateOrbitalVelocity(Vector3 position)
    {
        Vector3 acceleration = new Vector3();
        for(int i = 0; i < Scene.simulation.getStarSystem().getDarkMatterHandler().getSize(); i++)
        {
            CelestialObject celestialObject = Scene.simulation.getStarSystem().getBodyHandler().get(i);
            if(!celestialObject.equals(this) && celestialObject.isVisible())
            {
                Vector3 dist = celestialObject.getCenter(celestialObject.getPosition()).subtract(this.getCenter(position));
                double r = dist.length();
                double value = PhysicsConstants.G * celestialObject.getMass() / Math.pow(r, 3);
                acceleration = acceleration.add(dist.multiply(value));
            }
        }
        return acceleration;
    }

    public void update(double deltaTime)
    {
        //dt += time;

        //velocity = velocity.add(initialAcceleration.add(acceleration).multiply(dt));
        //acceleration = getAcceleration(p);

        /*k3v = getAcceleration(position.add(k2v.add(k1v).multiply(dt / 4)));
        k3v = velocity.add(k2v.multiply(dt * 0.5));

        Vector3 dvdt = k1v.add(k2v).add(k3v.multiply(4)).multiply(dt / 6);
        Vector3 dxdt = k1v.add(k2v).add(k3v.multiply(4)).multiply(dt / 6);


        Vector3 dvdt1 = velocity.add(k1v.add(k2v).multiply(dt / 2));
        Vector3 dvdt2 = velocity.add(dvdt);

        double error = dvdt1.distance(dvdt2);
        double t = 0.01;
        System.out.println(error + ", " + t);
        if(error > t)
        {
            dt = 0.9 * dt * Math.pow((t / error), 1 / 3.0);
        }

        velocity = dvdt2;
        position = position.add(dxdt);**/

        //tmpVelocity = tmpVelocity.add(dvdt);
        //tmpPosition = tmpPosition.add(dxdt);

        Vector3 lastVelocity = velocity;
        Vector3 lastPosition = position;

        //acceleration = getAcceleration(position);



        //tmpVelocity.subtract(velocity.subtract(lastVelocity));
        //tmpPosition.subtract(position.subtract(lastPosition));


       // velocity = velocity.add(dvdt);
       // position = position.add(dxdt);

       // position = position.add(velocity.multiply(dt)).add(acceleration.multiply(Math.pow(dt, 2) / 2));
        //velocity = velocity.add(acceleration.multiply(dt));
        double dt = Math.signum(deltaTime) * Math.min(Math.abs(deltaTime), PhysicsConstants.TIME_STEP.apply(1.0));

        if(!this.equals(Scene.simulation.getStarSystem().getBodyHandler().getCelestialObjectWithLargestMass()))
        {
            for(double i = 0; i < Math.abs(deltaTime); i += Math.abs(dt))
            {
                k1a = getGravitationalAcceleration(position);
                k1v = velocity;

                k2a = getGravitationalAcceleration(position.add(k1v.multiply(dt * 0.5)));
                k2v = velocity.add(k1a.multiply(dt * 0.5));

                k3a = getGravitationalAcceleration(position.add(k2v.multiply(dt * 0.5)));
                k3v = velocity.add(k2a.multiply(dt * 0.5));

                k4a = getGravitationalAcceleration(position.add(k3v.multiply(dt)));
                k4v = velocity.add(k3a.multiply(dt));

                Vector3 dvdt = k1a.add(k2a.add(k3a).multiply(2)).add(k4a).multiply(dt / 6.0);
                Vector3 dxdt = k1v.add(k2v.add(k3v).multiply(2)).add(k4v).multiply(dt / 6.0);

                velocity = velocity.add(dvdt);
                position = position.add(dxdt);
            }
        }

       // tmpVelocity = tmpVelocity.subtract(velocity.subtract(lastVelocity));
      //  tmpPosition = tmpPosition.subtract(position.subtract(lastPosition));


        //acceleration = getAcceleration(position);
       /* Integral integral = new DefiniteIntegral(0, dt);
        acceleration = getAcceleration(position);
        Function fx = t -> getAcceleration(position.add(velocity.multiply(t))).getX();
        Function fy = t -> getAcceleration(position.add(velocity.multiply(t))).getY();
        Function fz = t -> getAcceleration(position.add(velocity.multiply(t))).getZ();

        Vector3 dvdt = new Vector3(integral.calculate(fx), integral.calculate(fy), integral.calculate(fz));

        velocity = velocity.add(dvdt.multiply(dt));

        integral = new DefiniteIntegral(0, dt);
        fx = t -> velocity.multiply(t).getX();
        fy = t -> velocity.multiply(t).getY();
        fz = t -> velocity.multiply(t).getZ();

        Vector3 dxdt = new Vector3(integral.calculate(fx), integral.calculate(fy), integral.calculate(fz));
        position = position.add(dxdt.multiply(dt));**/
       // System.out.println(position.multiply(1 / PhysicsConstants.AU));



        //System.out.println(position.multiply(1 / PhysicsConstants.AU));
        //System.out.println(velocity);


        //Vector3 k1 = new Vector3(fX.f(dt), );

        //System.out.println(new DefiniteIntegral(0, 5).calculate(x -> Math.pow(x, 2)));

        //velocity = new Vector3(dx, dy, dz);

       // velocity = velocity.add(new Vector3(dx, dy, dz));


       // for(double i = 0; i < dt; i += increment)
       // {
            //velocity = velocity.add(initialAcceleration.add(acceleration).multiply(0.5 * increment));
           // velocity = velocity.add(acceleration.multiply(increment));
       //}
       // System.out.println("Update");
        //orbit.addPoint(position);

        /*angle += velocity.length() / position.length() * PhysicsConstants.timeStep;

        if(angle > 4 * Math.PI)
        {
            //orbit.clear();
            angle = 0;
        }**/
    }

    public void updatePosition()
    {
        //position = currPosition;



        double increment = PhysicsConstants.TIME_STEP.apply(1.0);
        //for(double i = 0; i < dt; i += increment)
        //{
           // position = position.add(velocity.multiply(increment));
       // }

        //position = position.add(velocity.multiply(dt));
    }

    public void render(ShapeRenderer shapeRenderer)
    {
        //textRenderer.draw(name, (int) (width / 2 + width / 2 * pos.getX()), (int) (height / 2 + height / 2 * pos.getY()));
        //textRenderer.endRendering();

        //System.out.println(Window.WIDTH + ", " + Window.SCENE_WIDTH);
    }
}