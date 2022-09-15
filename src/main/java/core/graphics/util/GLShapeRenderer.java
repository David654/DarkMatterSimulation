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
        gl.glBegin(GL2.GL_LINES);
        for(int i = 0; i < 100; i++)
        {
            float theta = (float) (2f * Math.PI * i / 100);
            gl.glVertex2f(x + (float) Math.cos(theta) * radius, (float) (y + Math.sin(theta) * radius));
        }
        gl.glEnd();
    }
}