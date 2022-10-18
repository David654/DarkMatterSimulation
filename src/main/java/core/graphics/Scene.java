package core.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import core.graphics.awt.shader.GLShader;
import core.graphics.geom.Circle;
import core.graphics.geom.Rectangle;
import launcher.DesktopLauncher;

import java.io.File;
import java.io.IOException;

public class Scene implements GLEventListener
{
    private Rectangle rect;
    private Circle circle;
    private GLShader shader;
    private Texture texture;

    public void init(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);
        rect = new Rectangle(0, 0, 0.5f, 0.2f);
        circle = new Circle(-0.5f, 0.2f, 0.3f);
        try {
            texture = TextureIO.newTexture(new File("src\\main\\resources\\img 1.png"), true);
            texture.enable(gl);
            texture.bind(gl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shader = new GLShader(gl, "src\\main\\java\\core\\graphics\\awt\\shader\\vertex.glsl", "src\\main\\java\\core\\graphics\\awt\\shader\\fragment.glsl");

    }

    public void display(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        //gl.glColor3f(1, 0, 0);
        //rect.fill(gl);

        //gl.glColor3f(0, 1, 0);
        //circle.draw(gl);

        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2f(-0.5f, 0.5f);
        gl.glVertex2f(0.5f, 0.5f);
        gl.glVertex2f(0.5f, -0.5f);
        gl.glVertex2f(-0.5f, -0.5f);
        gl.glEnd();

        texture.disable(gl);
    }

    public void dispose(GLAutoDrawable glAutoDrawable)
    {
        System.exit(0);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {

    }
}