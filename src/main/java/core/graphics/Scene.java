package core.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import core.graphics.geom.Circle;
import core.graphics.geom.Rectangle;

public class Scene implements GLEventListener
{
    private Rectangle rect;
    private Circle circle;

    public void init(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);
        rect = new Rectangle(0, 0, 0.5f, 0.2f);
        circle = new Circle(-0.5f, 0.2f, 0.3f);
    }

    public void display(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glColor3f(1, 0, 0);
        rect.fill(gl);

        gl.glColor3f(0, 1, 0);
        circle.draw(gl);
    }

    public void dispose(GLAutoDrawable glAutoDrawable)
    {
        System.exit(0);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {

    }
}