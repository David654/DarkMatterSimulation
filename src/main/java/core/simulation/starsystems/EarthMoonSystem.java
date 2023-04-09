package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class EarthMoonSystem extends StarSystem
{
    public EarthMoonSystem()
    {
        super("Earth and Moon");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> r / 100000;
    }

    public void setPositionScale()
    {
        positionScale = p -> p / PhysicsConstants.AU * 200000;
    }

    public void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.EARTH);
        celestialObjectHandler.add(BasicCelestialObjects.MOON);
    }
}