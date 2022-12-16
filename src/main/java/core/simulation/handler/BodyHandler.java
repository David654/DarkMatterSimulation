package core.simulation.handler;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import core.math.vector.Vector3;
import core.simulation.physics.body.Body;
import core.simulation.physics.PhysicsConstants;

public class BodyHandler extends Handler<Body>
{
    public void translate(float x, float y)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            Vector3 position = body.getPosition();
            body.setPosition(new Vector3(position.getX() + x * PhysicsConstants.AU, position.getY() + y * PhysicsConstants.AU, position.getZ()));

            body.getOrbit().translate(x, y);
        }
    }

    public void setScale(float scale)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            body.getOrbit().setScale(scale);
        }
    }

    public void update()
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            body.update();
        }
    }

    public void render(ShapeRenderer shapeRenderer)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            if(body.isVisible())
            {
                body.render(shapeRenderer);
            }
        }
    }
}
