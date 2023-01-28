package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class JupiterAndMoonsSystem extends StarSystem
{
    public JupiterAndMoonsSystem()
    {
        super("Jupiter and Its 4 Largest Moons");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> Math.pow(r, 1 / (3.0)) / 20;
    }

    public void setPositionScale()
    {
        positionScale = p -> p / PhysicsConstants.AU * 20000;
    }

    public void setRingScale()
    {

    }

    public void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.JUPITER);
        celestialObjectHandler.add(BasicCelestialObjects.AMALTHEA);
        celestialObjectHandler.add(BasicCelestialObjects.IO);
        celestialObjectHandler.add(BasicCelestialObjects.EUROPA);
        celestialObjectHandler.add(BasicCelestialObjects.GANYMEDE);
        celestialObjectHandler.add(BasicCelestialObjects.CALLISTO);
    }
}