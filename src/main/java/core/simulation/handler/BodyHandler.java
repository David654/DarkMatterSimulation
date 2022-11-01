package core.simulation.handler;

import com.jogamp.opengl.GL2;
import core.math.vector.Vector2;
import core.simulation.physics.Body;
import core.simulation.physics.Constants;

public class BodyHandler extends Handler<Body>
{
    public void translate(float x, float y)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            Vector2 position = body.getPosition();
            body.setPosition(new Vector2(position.getX() + x * Constants.AU, position.getY() + y * Constants.AU));

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

    public void render(GL2 gl)
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            if(body.isVisible())
            {
                body.render(gl);
            }
        }
    }
}
