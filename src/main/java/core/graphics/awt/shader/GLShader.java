package core.graphics.awt.shader;

import com.jogamp.opengl.GL2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GLShader
{
    private final GL2 gl;
    private final String[] vertexShader;
    private final String[] fragmentShader;

    public GLShader(GL2 gl, String vertexShader, String fragmentShader)
    {
        this.gl = gl;
        this.vertexShader = readShader(vertexShader);
        this.fragmentShader = readShader(fragmentShader);
    }

    private String[] readShader(String file)
    {
        BufferedReader brv;
        try
        {
            brv = new BufferedReader(new FileReader(file));
            StringBuilder shader = new StringBuilder();
            String line;
            while((line = brv.readLine()) != null)
            {
                shader.append(line).append("\n");
            }
            return shader.toString().split("\\n");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String[] getVertexShader()
    {
        return vertexShader;
    }

    public String[] getFragmentShader()
    {
        return fragmentShader;
    }

    public void initShader()
    {
        int vertex = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
        int fragment = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

        gl.glShaderSource(vertex, 1, vertexShader, null);
        gl.glCompileShader(vertex);

        gl.glShaderSource(fragment, 1, fragmentShader, null);
        gl.glCompileShader(fragment);

        int shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, vertex);
        gl.glAttachShader(shaderProgram, fragment);
        gl.glLinkProgram(shaderProgram);
        gl.glValidateProgram(shaderProgram);

        gl.glUseProgram(shaderProgram);
    }
}