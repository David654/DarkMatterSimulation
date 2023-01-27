package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class NeptuneAndTritonSystem extends StarSystem
{
    public NeptuneAndTritonSystem()
    {
        super("Neptune and Its Moon Triton");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> r / 500000;
    }

    public void setPositionScale()
    {
        positionScale = p -> p / PhysicsConstants.AU * 40000;
    }

    public void setRingScale()
    {
        ringRadiusScale = r -> Math.pow(r, 1 / 1.52) * 0.000160555;
    }

    protected void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.NEPTUNE);
        celestialObjectHandler.add(BasicCelestialObjects.TRITON);
    }
}