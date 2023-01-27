package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class UranusAndMoonsSystem extends StarSystem
{
    public UranusAndMoonsSystem()
    {
        super("Uranus and Its 5 Largest Moons");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> Math.pow(r, 1 / (3.0)) / 20;
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
        celestialObjectHandler.add(BasicCelestialObjects.URANUS);
        celestialObjectHandler.add(BasicCelestialObjects.ARIEL);
        celestialObjectHandler.add(BasicCelestialObjects.MIRANDA);
        celestialObjectHandler.add(BasicCelestialObjects.OBERON);
        celestialObjectHandler.add(BasicCelestialObjects.TITANIA);
        celestialObjectHandler.add(BasicCelestialObjects.UMBRIEL);
    }
}