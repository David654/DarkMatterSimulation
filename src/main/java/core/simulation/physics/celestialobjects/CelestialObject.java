package core.simulation.physics.celestialobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.assets.textures.Textures;
import core.graphics.core.Scene;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.darkmatter.DarkMatter;
import core.util.TextureUtils;
import core.util.MathUtils;
import core.math.vector.Vector3;
import core.simulation.handler.CelestialObjectHandler;
import core.simulation.physics.PhysicsConstants;
import core.util.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class CelestialObject
{
    /**
     * The starting position of the celestial object in astronomical units (AU).
     * 1 AU = 149 597 870 700 m.
     */
    private Vector3 initialPosition;

    /**
     * The current position of the celestial object in astronomical units (AU), which is regularly updated.
     * @see #update(double) 
     */
    private Vector3 position;

    /**
     * The dimension of the celestial objects in kilometres.
     */
    private Vector3 dimensions;

    /**
     * The mass of the celestial object in kilograms.
     */
    private double mass;

    private Vector3 initialVelocity;

    /**
     * The velocity of the celestial object in kilometers per second.
     */
    private Vector3 velocity;

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
    private double obliquity;

    /**
     * The color of the celestial object.
     */
    private Color color = Color.BLACK;

    /**
     * The name of the celestial object.
     */
    private String name;

    /**
     * Whether the celestial object is being rendered or not.
     * @see CelestialObjectHandler#render(ShapeRenderer)
     */
    private boolean isVisible;

    /**
     * An instance of the class Orbit for creating an orbit of the celestial object.
     * @see Orbit
     */
    private Orbit orbit;

    /**
     * The texture of the celestial object.
     */
    private Texture texture;

    private String texturePath;

    /**
     * The bump texture of the celestial object.
     */
    private Texture bumpTexture = null;

    private String bumpTexturePath;

    /**
     * The ring of the celestial object.
     */
    private Ring ring;

    private DarkMatter darkMatter;

    private boolean clockwise;
    private double lastAngle = 0;
    private ArrayList<Vector3> positions;
    public static int INDEX = 1;

    private double time = 0;

    /**
     * Constructor.
     * @param initialPosition the initial position of the celestial object
     * @param dimensions the dimensions of the celestial object
     * @param mass the mass of the celestial object
     * @param velocity the velocity of the celestial object
     * @param rotationSpeed the rotation speed of the celestial object
     * @param obliquity the obliquity of the celestial object
     * @param orbitalInclination the orbital inclination of the celestial object
     * @param name the name of the celestial object
     */
    public CelestialObject(Vector3 initialPosition, Vector3 dimensions, double mass, Vector3 velocity, double rotationSpeed, double obliquity, double orbitalInclination, String name)
    {
        this.initialPosition = initialPosition;
        this.initialPosition = this.initialPosition.multiply(PhysicsConstants.AU);
        position = this.initialPosition;
        position = MathUtils.rotateZ(position, Math.toRadians(orbitalInclination));
        this.dimensions = dimensions.multiply(1000);
        this.mass = mass;
        this.velocity = velocity.multiply(1000);
        initialVelocity = this.velocity;
        this.rotationSpeed = rotationSpeed;
        this.obliquity = obliquity;
        this.orbitalInclination = orbitalInclination;
        this.name = name;

        clockwise = velocity.getZ() >= 0;

        texturePath = Textures.DEFAULT_PLANET_TEXTURE_PATH;
        isVisible = true;

        positions = new ArrayList<>();

        BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.add(this);

        //orbit = new Orbit(Scene.simulation);
    }

    @Override
    public boolean equals(Object o)
    {
        CelestialObject object = (CelestialObject) o;
        return position.equals(object.getPosition()) && dimensions.equals(object.getDimensions()) && mass == object.getMass()
                && velocity.equals(object.getVelocity()) && rotationSpeed == object.getRotationSpeed() && obliquity == object.getObliquity()
                && orbitalInclination == object.getOrbitalInclination() && name.equals(object.getName());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(mass, velocity, name);
    }

    public Vector3 getInitialPosition()
    {
        return initialPosition;
    }

    public Vector3 getInitialPositionAU()
    {
        return initialPosition.multiply(1 / PhysicsConstants.AU);
    }

    public void setInitialPosition(Vector3 initialPosition)
    {
        this.initialPosition = initialPosition;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public Vector3 getPositionAU()
    {
        return position.multiply(1 / PhysicsConstants.AU);
    }

    public void setPosition(Vector3 position)
    {
        this.position = position.multiply(PhysicsConstants.AU);
        this.initialPosition = this.position;
        this.position = MathUtils.rotateZ(this.position, Math.toRadians(orbitalInclination));
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
        this.initialPosition = velocity;
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

    public double getObliquity()
    {
        return obliquity;
    }

    public void setObliquity(double obliquity)
    {
        this.obliquity = obliquity;
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
        return Utils.getColorString(color);
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public void setColor(String texturePath)
    {
        BufferedImage image = TextureUtils.readImage(texturePath);
        BasicCelestialObjects.THUMBNAILS.add(image);
        color = TextureUtils.getMostCommonColor(image);
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

    public String getTexturePath()
    {
        return texturePath;
    }

    public void setTexturePath(String texturePath)
    {
        this.texturePath = texturePath;
        BasicCelestialObjects.TEXTURE_PATHS.add(texturePath);
    }

    public void loadTexture()
    {
        this.texture = new Texture(texturePath);
    }

    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }

    public Texture getBumpTexture()
    {
        return bumpTexture;
    }

    public void setBumpTexture(Texture bumpTexture)
    {
        this.bumpTexture = bumpTexture;
    }

    public String getBumpTexturePath()
    {
        return bumpTexturePath;
    }

    public void setBumpTexturePath(String texturePath)
    {
        this.bumpTexturePath = texturePath;
    }

    public Ring getRing()
    {
        return ring;
    }

    public void setRing(Ring ring)
    {
        this.ring = ring;
    }

    public DarkMatter getDarkMatter()
    {
        return darkMatter;
    }

    public void setDarkMatter(DarkMatter darkMatter)
    {
        this.darkMatter = darkMatter;
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
                double value = PhysicsConstants.G * celestialObject.getTotalMass() / Math.pow(r, 3);
                acceleration = acceleration.add(dist.multiply(value));
            }
        }
        return acceleration;
    }

    private double getDarkMatterMass()
    {
        double volume = 4.0 / 3 * Math.PI * dimensions.getX() * dimensions.getY() * dimensions.getZ();
        return volume * darkMatter.getDensity() * 10e14 * PhysicsConstants.E_V_TO_KG;
    }

    public double getTotalMass()
    {
        //System.out.println(getDarkMatterMass());
        return mass * (1 + darkMatter.getDensity());
    }

    public double getEccentricity()
    {
        double angle = Math.toDegrees(initialPosition.getAngleBetween360(position));
        if(!clockwise)
        {
            angle = 360 - angle;
        }
        positions.add(position.multiply(0.001));

        if(angle < lastAngle)
        {
            double aphelion = Collections.max(positions, Comparator.comparingDouble(Vector3::length)).length();
            double perihelion = Collections.min(positions, Comparator.comparingDouble(Vector3::length)).length();

            double eccentricity = (aphelion - perihelion) / (aphelion + perihelion);
            //System.out.println(name + ":\nAphelion: " + aphelion + "\nPerihelion: " + perihelion + "\nEccentricity: " + eccentricity);
            //System.out.println("Velocity: " + velocity.length() / 1000 + " km/s");
            //System.out.println(Scene.simulation.getStarSystem().getBodyHandler().get(0).getDarkMatter().getDensity() + " " + (Scene.simulation.getStarSystem().getBodyHandler().get(0).getTotalMass() / 1e30) + " " + (velocity.length() / 1000) + " " + eccentricity);

            if(eccentricity == 0)
            {
                System.out.println(name);
                System.out.println("\n\n");
                INDEX++;
                Scene.simulation.getStarSystem().getBodyHandler().get(0).getDarkMatter().setDensity(0);
            }

            velocity = new Vector3(initialVelocity);
            Scene.simulation.getStarSystem().getBodyHandler().get(0).getDarkMatter().setDensity(Scene.simulation.getStarSystem().getBodyHandler().get(0).getDarkMatter().getDensity() + 0.1);

            positions.clear();
        }
        lastAngle = angle;
        return angle;
    }

    public void update(double deltaTime)
    {
        //dt += time;
        time += deltaTime;

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
        //double dt = Math.signum(deltaTime) * Math.min(Math.abs(deltaTime), PhysicsConstants.TIME_STEP.apply(1.0));

        // RK4
        /*  Vector3 k1a = getGravitationalAcceleration(position);
        Vector3 k1v = velocity;

        Vector3 k2a = getGravitationalAcceleration(position.add(k1v.multiply(deltaTime * 0.5)));
        Vector3 k2v = velocity.add(k1a.multiply(deltaTime * 0.5));

        Vector3 k3a = getGravitationalAcceleration(position.add(k2v.multiply(deltaTime * 0.5)));
        Vector3 k3v = velocity.add(k2a.multiply(deltaTime * 0.5));

        Vector3 k4a = getGravitationalAcceleration(position.add(k3v.multiply(deltaTime)));
        Vector3 k4v = velocity.add(k3a.multiply(deltaTime));

        Vector3 dvdt = k1a.add(k2a.add(k3a).multiply(2)).add(k4a).multiply(deltaTime / 6.0);
        Vector3 dxdt = k1v.add(k2v.add(k3v).multiply(2)).add(k4v).multiply(deltaTime / 6.0);

        velocity = velocity.add(dvdt);
        position = position.add(dxdt);**/

        // Leapfrog
        /*Vector3 acceleration = getGravitationalAcceleration(position);
        Vector3 initialVelocity = velocity.add(acceleration.multiply(deltaTime / 2));
        position = position.add(initialVelocity.multiply(deltaTime));

        acceleration = getGravitationalAcceleration(position);
        velocity = initialVelocity.add(acceleration.multiply(deltaTime));
        position = position.add(velocity.multiply(deltaTime));**/

        Vector3 acceleration = getGravitationalAcceleration(position);
        velocity = velocity.add(acceleration.multiply(deltaTime / 2));
        position = position.add(velocity.multiply(deltaTime));

        acceleration = getGravitationalAcceleration(position);
        velocity = velocity.add(acceleration.multiply(deltaTime / 2));

        //



        /*if(name.equals("Earth"))
        {
            double e = getEccentricity();
            if(time % (PhysicsConstants.TIME_STEP.apply(182.5) * 60) == 0)
            {
                System.out.println(time / 3600 / 24 + " " + velocity.multiply(0.001).length());
            }
        }**/

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