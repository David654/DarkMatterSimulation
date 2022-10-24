package core.graphics.util;

import com.jogamp.opengl.GL2;

public class GLShapeRenderer
{
    private final GL2 gl;

    public GLShapeRenderer(GL2 gl)
    {
        this.gl = gl;
    }

    public void drawRect(float x, float y, float width, float height)
    {
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + width, y);
        gl.glVertex2f(x + width, y + height);
        gl.glVertex2f(x, y + height);
        gl.glEnd();
    }

    public void fillRect(float x, float y, float width, float height)
    {
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + width, y);
        gl.glVertex2f(x + width, y + height);
        gl.glVertex2f(x, y + height);
        gl.glEnd();
    }

    public void drawCircle(float x, float y, float radius)
    {
        int parts = 256;
        gl.glBegin(GL2.GL_LINE_LOOP);
        for(int i = 0; i < parts; i++)
        {
            double angle = 2 * Math.PI * i / parts;
            gl.glVertex2d(x + Math.cos(angle) * radius,y + Math.sin(angle) * radius);
        }
        gl.glEnd();
    }

    public void fillCircle(float x, float y, float radius)
    {
        int parts = 256;
        gl.glBegin(GL2.GL_POLYGON);
        for(int i = 0; i < parts; i++)
        {
            double angle = 2 * Math.PI * i / parts;
            gl.glVertex2d(x + Math.cos(angle) * radius,y + Math.sin(angle) * radius);
        }
        gl.glEnd();
    }
}