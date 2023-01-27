package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class MarsAndMoonsSystem extends StarSystem
{
    public MarsAndMoonsSystem()
    {
        super("Mars and Its Moons Phobos and Deimos");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> Math.pow(r, 1 / (2.0)) / 100;
    }

    public void setPositionScale()
    {
        positionScale = p -> p / PhysicsConstants.AU * 600000;
    }

    public void setRingScale()
    {

    }

    public void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.MARS);
        celestialObjectHandler.add(BasicCelestialObjects.PHOBOS);
        celestialObjectHandler.add(BasicCelestialObjects.DEIMOS);
    }
}