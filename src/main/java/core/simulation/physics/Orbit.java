package core.simulation.physics;

import com.jogamp.opengl.GL2;
import core.math.vector.Vector2;
import core.simulation.components.Simulation;
import core.simulation.handler.Handler;

import java.util.ArrayList;

public class Orbit extends Handler<Vector2>
{
    private final Simulation simulation;
    private final ArrayList<Vector2> points;

    public Orbit(Simulation simulation)
    {
        this.simulation = simulation;
        points = new ArrayList<>();
    }

    public void addPoint(Vector2 point)
    {
        if(!points.contains(point))
        {
            points.add(point);
        }
    }

    public void translate(float x, float y)
    {
        for(int i = 0; i < points.size(); i++)
        {
            Vector2 point = points.get(i);
            point = point.add(new Vector2(x * Constants.AU, y * Constants.AU));
            points.set(i, point);
        }
    }

    public void setScale(float scale)
    {
        for(int i = 0; i < points.size(); i++)
        {
            Vector2 point = points.get(i);
            point = point.multiply(scale);
            points.set(i, point);
        }
    }

    public void clear()
    {
        points.clear();
    }

    public void update()
    {

    }

    public void render(GL2 gl)
    {
        gl.glLineWidth(0.1f);
        gl.glBegin(GL2.GL_LINES);

        for(int i = 0; i < points.size() - 1; i++)
        {
            Vector2 point1 = points.get(i);
            Vector2 point2 = points.get(i + 1);
            gl.glVertex2f((float) (point1.getX() / Constants.AU * simulation.getScale() * 2), (float) ((float) point1.getY() / Constants.AU * simulation.getScale() * 2));
            gl.glVertex2f((float) ((float) point2.getX() / Constants.AU * simulation.getScale() * 2), (float) ((float) point2.getY() / Constants.AU * simulation.getScale() * 2));
        }

        gl.glEnd();
    }
}