package core.simulation.starsystems;

import core.math.vector.Vector3;
import core.simulation.handler.CelestialObjectHandler;
import core.simulation.handler.DarkMatterHandler;
import core.simulation.physics.celestialobjects.CelestialObject;

import java.util.ArrayList;
import java.util.function.UnaryOperator;

public abstract class StarSystem
{
    public static final ArrayList<StarSystem> STAR_SYSTEMS = initStarSystems();

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
    }

    private static ArrayList<StarSystem> initStarSystems()
    {
        ArrayList<StarSystem> starSystems = new ArrayList<>();
        starSystems.add(new SolarSystem());
        starSystems.add(new EarthMoonSystem());
        starSystems.add(new MarsAndMoonsSystem());
        starSystems.add(new JupiterAndMoonsSystem());
        starSystems.add(new SaturnAndMoons());
        starSystems.add(new UranusAndMoonsSystem());
        starSystems.add(new NeptuneAndTritonSystem());
        starSystems.add(new PlutoAndCharonSystem());
        return starSystems;
    }

    public static String[] getStarSystemNames()
    {
        String[] names = new String[STAR_SYSTEMS.size()];
        for(int i = 0; i < names.length; i++)
        {
            names[i] = STAR_SYSTEMS.get(i).getName();
        }
        return names;
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

    public UnaryOperator<Double> getRadiusScale()
    {
        return radiusScale;
    }

    public UnaryOperator<Double> getPositionScale()
    {
        return positionScale;
    }

    public void update(double timeStep)
    {
        celestialObjectHandler.update(timeStep);
    }

    protected abstract void initCelestialObjects();

    public final void initStarSystem()
    {
        celestialObjectHandler.clear();
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

    public final void initDarkMatter()
    {
        darkMatterHandler.initDarkMatter(this);

        for(int i = 0; i < celestialObjectHandler.getSize(); i++)
        {
            CelestialObject celestialObject = celestialObjectHandler.get(i);
            celestialObject.setDarkMatter(darkMatterHandler.get(i));
        }
    }
}