package core.simulation.starsystems;

import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.PhysicsConstants;

public class SolarSystem extends StarSystem
{
    public SolarSystem()
    {
        super("Solar System");
    }

    public void setRadiusScale()
    {
        radiusScale = r -> Math.pow(r, 1 / (3.0)) / 20;
    }

    public void setPositionScale()
    {
        positionScale = p -> Math.signum(p) * Math.log(Math.abs(p) / PhysicsConstants.AU + 1) * 200;
        //positionScale = p -> (Math.signum(p) > 0 ? Math.log(0.5 * p / PhysicsConstants.AU + 1) : -Math.log(-0.5 * p / PhysicsConstants.AU + 1)) * 200;
        //positionScale = p -> Math.signum(p) * (Math.log10(0.1 * Math.abs(p) / PhysicsConstants.AU + 10) - 1) * 20e3;
        //positionScale = p -> Math.abs(p) <= 1 ? Math.signum(p) * MathUtils.logAB(100, Math.abs(p) / PhysicsConstants.AU + 1) : Math.signum(p) * Math.log(Math.abs(p) / PhysicsConstants.AU + 1) * 200;
       // positionScale = p -> Math.signum(p) * Math.pow(Math.log(Math.abs(p) / PhysicsConstants.AU + 1), 1) * 200;
    }

    public void setRingScale()
    {
        //ringRadiusScale = r -> Math.pow(r, 1 / (3.0)) / 12;
       // ringRadiusScale = r -> r * 3.09333e-7;
        ringRadiusScale = r -> Math.pow(r, 1 / 1.5) * 0.000160555;
    }

    public void initCelestialObjects()
    {
        celestialObjectHandler.add(BasicCelestialObjects.SUN);
        celestialObjectHandler.add(BasicCelestialObjects.MERCURY);
        celestialObjectHandler.add(BasicCelestialObjects.VENUS);
        celestialObjectHandler.add(BasicCelestialObjects.EARTH);
        celestialObjectHandler.add(BasicCelestialObjects.MARS);
        celestialObjectHandler.add(BasicCelestialObjects.JUPITER);
        celestialObjectHandler.add(BasicCelestialObjects.SATURN);
        celestialObjectHandler.add(BasicCelestialObjects.URANUS);
        celestialObjectHandler.add(BasicCelestialObjects.NEPTUNE);

        celestialObjectHandler.add(BasicCelestialObjects.MOON);
        celestialObjectHandler.add(BasicCelestialObjects.PLUTO);
        celestialObjectHandler.add(BasicCelestialObjects.CERES);
        celestialObjectHandler.add(BasicCelestialObjects.MAKEMAKE);
        celestialObjectHandler.add(BasicCelestialObjects.ERIS);
        celestialObjectHandler.add(BasicCelestialObjects.HAUMEA);
    }
}