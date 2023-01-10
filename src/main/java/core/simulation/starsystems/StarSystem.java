package core.simulation.starsystems;

import core.math.vector.Vector3;
import core.simulation.handler.CelestialObjectHandler;
import core.simulation.handler.DarkMatterHandler;
import core.simulation.physics.celestialobjects.CelestialObject;

import java.util.function.UnaryOperator;

public abstract class StarSystem
{
    protected String name;
    protected final CelestialObjectHandler celestialObjectHandler;
    protected final DarkMatterHandler darkMatterHandler;
    protected UnaryOperator<Double> radiusScale;
    protected UnaryOperator<Double> positionScale;
    protected UnaryOperator<Double> ringRadiusScale;

    public StarSystem(String name)
    {
        this.name = name;
        celestialObjectHandler = new CelestialObjectHandler();
        darkMatterHandler = new DarkMatterHandler();
        setRadiusScale();
        setPositionScale();
        setRingScale();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public CelestialObjectHandler getBodyHandler()
    {
        return celestialObjectHandler;
    }

    public DarkMatterHandler getDarkMatterHandler()
    {
        return darkMatterHandler;
    }

    public abstract void setRadiusScale();

    public abstract void setPositionScale();

    public abstract void setRingScale();

    public UnaryOperator<Double> getRadiusScale()
    {
        return radiusScale;
    }

    public UnaryOperator<Double> getPositionScale()
    {
        return positionScale;
    }

    public UnaryOperator<Double> getRingRadiusScale()
    {
        return ringRadiusScale;
    }

    public void update(double timeStep)
    {
        celestialObjectHandler.update(timeStep);
    }

    protected abstract void initCelestialObjects();

    public void initStarSystem()
    {
        initCelestialObjects();

        CelestialObject celestialObjectWithLargestMass = celestialObjectHandler.getCelestialObjectWithLargestMass();
        int index = celestialObjectHandler.indexOf(celestialObjectWithLargestMass);

        celestialObjectWithLargestMass.setVelocity(new Vector3());
        celestialObjectWithLargestMass.setPosition(new Vector3());
        celestialObjectHandler.set(index, celestialObjectWithLargestMass);
        celestialObjectHandler.sortCelestialObjectsByDistance();
        for(int i = 0; i < celestialObjectHandler.getSize(); i++)
        {
            CelestialObject celestialObject = celestialObjectHandler.get(i);
            System.out.println(celestialObject.getName() + ": " + celestialObject.getInitialPositionAU());
        }
    }

    public void initDarkMatter()
    {
        darkMatterHandler.initDarkMatter(this);
    }
}