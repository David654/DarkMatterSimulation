package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class PlutoAndCharonSystem extends StarSystem
{
    public PlutoAndCharonSystem()
    {
        super("Pluto and Its Moon Charon");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> r / 50000;
    }

    public void setPositionScale()
    {
        positionScale = p -> p / PhysicsConstants.AU * 640000;
    }

    protected void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.PLUTO);
        celestialObjectHandler.add(BasicCelestialObjects.CHARON);
    }
}