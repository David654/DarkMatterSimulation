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
}