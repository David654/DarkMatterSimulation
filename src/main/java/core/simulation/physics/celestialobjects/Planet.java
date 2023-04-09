package core.simulation.physics.celestialobjects;

import core.math.vector.Vector3;

public class Planet extends CelestialObject
{
    /**
     * Constructor.
     *
     * @param initialPosition the initial position of the body
     * @param dimensions      the dimensions of the body
     * @param mass            the mass of the body
     * @param velocity        the velocity of the body
     * @param rotationSpeed
     * @param axialTilt
     * @param orbitalInclination
     * @param name            the name of the body
     */
    public Planet(Vector3 initialPosition, Vector3 dimensions, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, double orbitalInclination, String name)
    {
        super(initialPosition, dimensions.multiply(0.5), mass, velocity, rotationSpeed, axialTilt, orbitalInclination, name);
    }

    public Planet(Vector3 initialPosition, double equatorialRadius, double polarRadius, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, double orbitalInclination, String name)
    {
        super(initialPosition, new Vector3(equatorialRadius, polarRadius, equatorialRadius), mass, velocity, rotationSpeed, axialTilt, orbitalInclination, name);
    }

    public Planet(Vector3 initialPosition, double radius, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, double orbitalInclination, String name)
    {
        super(initialPosition, new Vector3(radius), mass, velocity, rotationSpeed, axialTilt, orbitalInclination, name);
    }

    public Planet(Planet planet)
    {
        this(planet.getInitialPositionAU(), planet.getDimensions().multiply(0.002), planet.getMass(), planet.getVelocity().multiply(0.001), planet.getRotationSpeed(), planet.getObliquity(), planet.getOrbitalInclination(), planet.getName());
        this.setColor(planet.getColor());
        this.setTexture(planet.getTexture());
        this.setTexturePath(planet.getTexturePath());
        this.setRing(planet.getRing());
        if(this.getRing() != null)
        {
            this.getRing().setTexture(planet.getRing().getTexture());
        }
        this.setBumpTexture(planet.getBumpTexture());
        this.setBumpTexturePath(planet.getBumpTexturePath());
    }
}