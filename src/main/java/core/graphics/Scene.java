package core.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import core.graphics.awt.shader.Shader;
import core.graphics.util.ShapeRenderer;
import core.gui.core.Window;
import core.simulation.components.Simulation;

import java.io.File;
import java.io.IOException;

public class Scene implements GLEventListener
{
    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private Shader shader;

    private final Simulation simulation;

    public Scene(Simulation simulation)
    {
        this.simulation = simulation;
    }

    private void initGraphicsUtilities(GL2 gl)
    {
        shapeRenderer = new ShapeRenderer(gl);
    }

    private void loadShaders(GL2 gl)
    {
        shader = new Shader(gl);
        shader.attachShaders();
    }

    private void loadTextures(GL2 gl)
    {
         try
         {
            texture = TextureIO.newTexture(new File("src\\main\\resources\\img 1.png"), true);
            texture.enable(gl);
            texture.bind(gl);
         }
         catch(IOException e)
         {
             throw new RuntimeException(e);
         }
    }

    public void init(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = (float) Window.WIDTH * 3 / 4 / Window.HEIGHT;
        gl.glOrtho(-aspect, aspect, -1.0f, 1.0f, -1.0f, 1.0f);

        initGraphicsUtilities(gl);
        loadShaders(gl);
        //loadTextures(gl);
    }

    public void display(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        //gl.glEnable(GL2.GL_TEXTURE_2D);

        simulation.render(gl);

        //gl.glUseProgram(shader.getProgramName());

        //gl.glRotatef(-45, 0, 0, 1);
        //gl.glScalef(0.1f, 0.1f, 0.1f);

        gl.glEnd();
        gl.glFlush();
    }

    public void dispose(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        shader.destroy(gl);
        gl.glDeleteProgram(shader.getProgramName());
        System.exit(0);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        Window.SCENE_WIDTH = width;

        double aspect = (double) width / height;
        gl.glOrtho(-aspect, aspect, -1.0, 1.0, -1.0, 1.0);
    }
}