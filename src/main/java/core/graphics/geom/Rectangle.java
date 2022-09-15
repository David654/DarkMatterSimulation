package core.graphics.geom;

import com.jogamp.opengl.GL2;

public class Rectangle implements Drawable
{
    private float x;
    private float y;
    private float width;
    private float height;

    public Rectangle(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public float getWidth()
    {
        return width;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public void draw(GL2 gl)
    {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + width, y);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x + width, y);
        gl.glVertex2f(x + width, y + height);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x + width, y + height);
        gl.glVertex2f(x, y + height);
        gl.glEnd();

        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x, y + height);
        gl.glVertex2f(x, y);
        gl.glEnd();

    }

    public void fill(GL2 gl)
    {
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + width, y);
        gl.glVertex2f(x + width, y + height);
        gl.glVertex2f(x, y + height);
        gl.glEnd();
    }
}