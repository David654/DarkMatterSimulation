package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class SaturnAndMoons extends StarSystem
{
    public SaturnAndMoons()
    {
        super("Saturn and Its 8 Largest Moons");
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
        ringRadiusScale = r -> Math.pow(r, 1 / 1.5) * 0.000160555;
    }

    protected void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.SATURN);
        celestialObjectHandler.add(BasicCelestialObjects.DIONE);
        celestialObjectHandler.add(BasicCelestialObjects.ENCELADUS);
        celestialObjectHandler.add(BasicCelestialObjects.HYPERION);
        celestialObjectHandler.add(BasicCelestialObjects.IAPETUS);
        celestialObjectHandler.add(BasicCelestialObjects.MIMAS);
        celestialObjectHandler.add(BasicCelestialObjects.RHEA);
        celestialObjectHandler.add(BasicCelestialObjects.TETHYS);
        celestialObjectHandler.add(BasicCelestialObjects.TITAN);
    }
}