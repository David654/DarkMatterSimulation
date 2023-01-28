package core.simulation.handler;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.math.vector.Vector3;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.PhysicsConstants;
import core.simulation.physics.celestialobjects.Planet;
import core.simulation.physics.celestialobjects.Ring;
import core.simulation.physics.celestialobjects.Star;

import java.util.Comparator;

public class CelestialObjectHandler extends Handler<CelestialObject>
{
    public CelestialObject getCelestialObjectByName(String name)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            if(celestialObject.getName().equals(name))
            {
                return celestialObject;
            }
        }
        return null;
    }

    public void translate(float x, float y)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            Vector3 position = celestialObject.getPosition();
            celestialObject.setPosition(new Vector3(position.getX() + x * PhysicsConstants.AU, position.getY() + y * PhysicsConstants.AU, position.getZ()));

            celestialObject.getOrbit().translate(x, y);
        }
    }

    public void setScale(float scale)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            celestialObject.getOrbit().setScale(scale);
        }
    }

    public int getNumCelestialObjectsWithRings()
    {
        int count = 0;
        for(int i = 0; i < list.size(); i++)
        {
            Ring ring = list.get(i).getRing();
            if(ring != null)
            {
                count++;
            }
        }
        return count;
    }

    public int getNumStars()
    {
        int count = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) instanceof Star)
            {
                count++;
            }
        }
        return count;
    }

    public int getNumPlanets()
    {
        int count = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) instanceof Planet)
            {
                count++;
            }
        }
        return count;
    }

    public double getTotalEnergy()
    {
        double energy = 0;
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            energy += celestialObject.getMass() * celestialObject.getVelocity().getX() / 2;
        }
        return energy;
    }

    public Vector3 getMaxPos()
    {
        double maxDist = 0;
        Vector3 maxPos = new Vector3();

        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            Vector3 pos = celestialObject.getInitialPosition().multiply(1 / PhysicsConstants.AU);
            double dist = pos.distance(new Vector3());
            if(dist > maxDist)
            {
                maxDist = dist;
                maxPos = pos;
            }
        }

        return maxPos;
    }

    public Vector3 getMinPos()
    {
        double minDist = 0;
        Vector3 minPos = new Vector3();

        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            Vector3 pos = celestialObject.getInitialPosition().multiply(1 / PhysicsConstants.AU);
            double dist = pos.distance(new Vector3());
            if(dist < minDist)
            {
                minDist = dist;
                minPos = pos;
            }
        }

        return minPos;
    }

    public void sortCelestialObjectsByDistance()
    {
        list.sort(Comparator.comparingDouble(o -> o.getInitialPosition().length()));
    }

    public void sortCelestialObjectsByMass()
    {
        list.sort(Comparator.comparingDouble(CelestialObject::getMass));
    }

    public void distributeCelestialObjectsRandomly()
    {
        double random = Math.random() * 2 - 1;
        update(PhysicsConstants.TIME_STEP.apply(random * 365 * 500));
    }

    public synchronized void update(double time)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            celestialObject.updatePosition();
        }

        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            celestialObject.update(time);
        }
    }

    public CelestialObject getCelestialObjectWithLargestMass()
    {
        double maxMass = 0;
        CelestialObject object = null;

        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            if(celestialObject.getMass() > maxMass)
            {
                maxMass = celestialObject.getMass();
                object = celestialObject;
            }
        }

        return object;
    }

    public void render(ShapeRenderer shapeRenderer)
    {
        for(int i = 0; i < list.size(); i++)
        {
            CelestialObject celestialObject = list.get(i);
            if(celestialObject.isVisible())
            {
                celestialObject.render(shapeRenderer);
            }
        }
    }
}
