package core.simulation.physics.celestialobjects;

import core.math.vector.Vector3;

public class Star extends CelestialObject
{
    /**
     * A measure of the brightness of a star.
     */
    private double apparentMagnitude;

    /**
     * Constructor.
     *
     * @param initialPosition the initial position of the body
     * @param radius          the radius of the body
     * @param mass            the mass of the body
     * @param velocity        the velocity of the body
     * @param rotationSpeed
     * @param axialTilt
     * @param apparentMagnitude
     * @param name            the name of the body
     */
    public Star(Vector3 initialPosition, double radius, double mass, Vector3 velocity, double rotationSpeed, double axialTilt, double apparentMagnitude, String name)
    {
        super(initialPosition, new Vector3(radius), mass, velocity, rotationSpeed, axialTilt, 0, name);
        this.apparentMagnitude = apparentMagnitude;
    }

    public double getApparentMagnitude()
    {
        return apparentMagnitude;
    }

    public void setApparentMagnitude(double apparentMagnitude)
    {
        this.apparentMagnitude = apparentMagnitude;
    }
}