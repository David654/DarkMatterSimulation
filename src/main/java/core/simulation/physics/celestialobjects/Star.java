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
     * @param obliquity
     * @param apparentMagnitude
     * @param name            the name of the body
     */
    public Star(Vector3 initialPosition, double radius, double mass, Vector3 velocity, double rotationSpeed, double obliquity, double apparentMagnitude, String name)
    {
        super(initialPosition, new Vector3(radius), mass, velocity, rotationSpeed, obliquity, 0, name);
        this.apparentMagnitude = apparentMagnitude;
    }

    public Star(Star star)
    {
        this(star.getInitialPositionAU(), star.getRadius() * 0.001, star.getMass(), star.getVelocity().multiply(0.001), star.getRotationSpeed(), star.getObliquity(), star.getApparentMagnitude(), star.getName());
        this.setColor(star.getColor());
        this.setTexture(star.getTexture());
        this.setTexturePath(star.getTexturePath());
        this.setRing(star.getRing());
        if(this.getRing() != null)
        {
            this.getRing().setTexture(star.getRing().getTexture());
        }
        this.setBumpTexture(star.getBumpTexture());
        this.setBumpTexturePath(star.getBumpTexturePath());
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