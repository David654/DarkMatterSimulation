package core.simulation.handler;

import core.util.MathUtils;
import core.math.vector.Vector3;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.darkmatter.DarkMatter;
import core.simulation.starsystems.StarSystem;

public class DarkMatterHandler extends Handler<DarkMatter>
{
    public void initDarkMatter(StarSystem starSystem)
    {
        for(int i = 0; i < starSystem.getBodyHandler().getSize() - 7; i++)
        {
            CelestialObject celestialObject1 = starSystem.getBodyHandler().get(i);
            CelestialObject celestialObject2 = starSystem.getBodyHandler().get(i + 1);

            Vector3 position1 = MathUtils.applyUnaryOperator(celestialObject1.getPosition(), starSystem.getPositionScale());
            Vector3 position2 = MathUtils.applyUnaryOperator(celestialObject2.getPosition(), starSystem.getPositionScale());
            double dist = position1.distance(position2);
            double radius1 = position1.length();
            double radius2 = position2.length();
          //  System.out.println(starSystem.getPositionScale().apply(radius2));

            this.add(new DarkMatter(Math.random(), radius1, radius2, 300));
        }
    }
}