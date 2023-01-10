package core.simulation.physics.celestialobjects;

import core.math.vector.Vector3;

public class Star extends CelestialObject
{
    /**
     * Constructor.
     *
     * @param initialPosition the initial position of the body
     * @param radius          the radius of the body
     * @param mass            the mass of the body
     * @param velocity        the velocity of the body
     * @param rotationSpeed
     * @param axialTilt
     * @param name            the name of the body
     */
    public Star(Vector3 initialPosition, double radius, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, String name)
    {
        super(initialPosition, new Vector3(radius), mass, velocity, rotationSpeed, axialTilt, 0, name);
    }
}