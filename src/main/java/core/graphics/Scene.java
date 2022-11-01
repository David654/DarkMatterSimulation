package core.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.texture.Texture;
import core.graphics.awt.shader.GLShader;
import core.graphics.util.GLShapeRenderer;
import core.gui.core.Window;
import core.simulation.components.Simulation;

import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

public class Scene implements GLEventListener
{
    private GLShapeRenderer glShapeRenderer;
    private GLShader shader;
    private Texture texture;

    private final Simulation simulation;

    public Scene(Simulation simulation)
    {
        this.simulation = simulation;
    }

    public void init(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = (float) Window.WIDTH * 3 / 4 / Window.HEIGHT;
        gl.glOrtho(-aspect, aspect, -1.0f, 1.0f, -1.0f, 1.0f);

        glShapeRenderer = new GLShapeRenderer(gl);
        /*try {
            texture = TextureIO.newTexture(new File("src\\main\\resources\\img 1.png"), true);
            texture.enable(gl);
            texture.bind(gl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shader = new GLShader(gl, "src\\main\\java\\core\\graphics\\awt\\shader\\vertex.glsl", "src\\main\\java\\core\\graphics\\awt\\shader\\fragment.glsl");**/

    }

    public void display(GLAutoDrawable glAutoDrawable)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        simulation.render(gl);
    }

    public void dispose(GLAutoDrawable glAutoDrawable)
    {
        System.exit(0);
    }

    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height)
    {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = (float) width / height;
        gl.glOrtho(-aspect, aspect, -1.0f, 1.0f, -1.0f, 1.0f);
    }
}