package core.graphics.geom;

import com.jogamp.opengl.GL2;

public class Circle implements Drawable
{
    private float x;
    private float y;
    private float radius;
    private final int numSegments = 100;

    public Circle(float x, float y, float radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public void draw(GL2 gl)
    {
        gl.glBegin(GL2.GL_LINES);
        for(int i = 0; i < numSegments; i++)
        {
            float theta = (float) (2f * Math.PI * i / numSegments);
            gl.glVertex2f(x + (float) Math.cos(theta) * radius, (float) (y + Math.sin(theta) * radius));
            gl.glVertex2f(x + (float) Math.cos(theta + 0.1) * radius, (float) (y + Math.sin(theta + 0.1) * radius));
        }
        gl.glEnd();
    }

    public void fill(GL2 gl)
    {
        float angleIncrement = (float) (2 * Math.PI / numSegments);
        gl.glBegin(GL2.GL_POLYGON);
        for(int i = 0; i < numSegments; i++)
        {
            float theta = i * angleIncrement;
            gl.glVertex2f(x + (float) Math.cos(theta) * radius, y + (float) Math.sin(theta) * radius);
        }
        gl.glEnd();
    }
}