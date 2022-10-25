package core.simulation.handler;

import com.jogamp.opengl.GL2;
import core.simulation.physics.Body;

public class BodyHandler extends Handler<Body>
{
    public void update()
    {
        for(int i = 0; i < list.size(); i++)
        {
            Body body = list.get(i);
            if(body.isVisible())
            {
                body.update();
            }
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
