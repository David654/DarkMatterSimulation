package core.graphics.awt.shader;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.glsl.ShaderCode;
import com.jogamp.opengl.util.glsl.ShaderProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Shader extends ShaderProgram
{
    public static final String VERTEX_SHADER_SOURCE = "src\\main\\java\\core\\graphics\\awt\\shader\\vertex.glsl";
    public static final String FRAGMENT_SHADER_SOURCE = "src\\main\\java\\core\\graphics\\awt\\shader\\fragment.glsl";

    private final GL2 gl;
    private final String vertexShaderSource;
    private final String fragmentShaderSource;
    private int programName;

    public Shader(GL2 gl)
    {
        this(gl, VERTEX_SHADER_SOURCE, FRAGMENT_SHADER_SOURCE);
    }

    public Shader(GL2 gl, String vertexShaderSource, String fragmentShaderSource)
    {
        this.gl = gl;
        this.vertexShaderSource = vertexShaderSource;
        this.fragmentShaderSource = fragmentShaderSource;
    }

    public int getProgramName()
    {
        return programName;
    }

    private String loadShader(String file)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder shader = new StringBuilder();
            String line;
            while((line = br.readLine()) != null)
            {
                shader.append(line).append("\n");
            }
            return shader.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void attachShaders()
    {
        ShaderCode vertexShader = new ShaderCode(GL2.GL_VERTEX_SHADER, 1, new String[][] {{loadShader(vertexShaderSource)}});
        ShaderCode fragmentShader = new ShaderCode(GL2.GL_FRAGMENT_SHADER, 1, new String[][] {{loadShader(fragmentShaderSource)}});

        this.add(vertexShader);
        this.add(fragmentShader);

        this.init(gl);
        programName = this.program();
        this.link(gl, System.out);
        this.validateProgram(gl, System.out);
        this.useProgram(gl, true);
    }
}