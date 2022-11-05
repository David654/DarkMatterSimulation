package core.graphics.awt.shader;

import com.jogamp.opengl.GL2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class GLShaderProgram
{
    private final String[] vertexShader;
    private final String[] fragmentShader;

    public GLShaderProgram(String vertexShader, String fragmentShader)
    {
        this.vertexShader = loadShader(vertexShader);
        this.fragmentShader = loadShader(fragmentShader);
    }

    private String[] loadShader(String file)
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

    public void attachShaders(GL2 gl)
    {
        int vertexShaderProgram = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
        int fragmentShaderProgram = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

        gl.glShaderSource(vertexShaderProgram, 1, vertexShader, null, 0);
        gl.glCompileShader(vertexShaderProgram);

        gl.glShaderSource(fragmentShaderProgram, 1, fragmentShader, null, 0);
        gl.glCompileShader(fragmentShaderProgram);

        int shaderProgram = gl.glCreateProgram();
        gl.glAttachShader(shaderProgram, vertexShaderProgram);
        gl.glAttachShader(shaderProgram, fragmentShaderProgram);
        gl.glLinkProgram(shaderProgram);
        gl.glValidateProgram(shaderProgram);

        IntBuffer intBuffer = IntBuffer.allocate(1);
        gl.glGetProgramiv(shaderProgram, GL2.GL_LINK_STATUS, intBuffer);

        if(intBuffer.get(0) != 1)
        {
            gl.glGetProgramiv(shaderProgram, GL2.GL_INFO_LOG_LENGTH, intBuffer);
            int size = intBuffer.get(0);
            System.err.println("Program link error: ");
            if(size > 0)
            {
                ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                gl.glGetProgramInfoLog(shaderProgram, size, intBuffer, byteBuffer);
                for(byte b: byteBuffer.array())
                {
                    System.err.print((char) b);
                }
            }
            else
            {
                System.out.println("Unknown error");
            }
            System.exit(1);
        }
        gl.glUseProgram(shaderProgram);
    }

    public void dispose()
    {

    }
}