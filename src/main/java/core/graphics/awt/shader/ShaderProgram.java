package core.graphics.awt.shader;

import com.jogamp.opengl.GL2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ShaderProgram
{
    private final String[] vertexShader;
    private final String[] fragmentShader;

    public ShaderProgram(String vertexShader, String fragmentShader)
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
            return shader.toString().split("\n");
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

    int[] getStrLen(String[] s)
    {
        int[] a = new int[s.length];
        for(int i = 0; i<s.length ; i++)
        {
            a[i] = s[i].length();
        }
        return a;
    }

    public void attachShaders(GL2 gl)
    {
        IntBuffer intBuffer = IntBuffer.allocate(1);
        int shaderProgram = 0;

        int vertexShaderProgram = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
        int fragmentShaderProgram = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

        System.out.println("glCreateShader(vertex)=" + vertexShaderProgram);
        System.out.println("glCreateShader(fragment)=" + fragmentShaderProgram);

        gl.glShaderSource(vertexShaderProgram, vertexShader.length, vertexShader, getStrLen(vertexShader), 0);
        gl.glCompileShader(vertexShaderProgram);
        gl.glGetShaderiv(vertexShaderProgram,GL2.GL_COMPILE_STATUS, intBuffer);
        System.out.println("glCompileShader(vertex)=" + intBuffer.get(0));

        gl.glShaderSource(fragmentShaderProgram, fragmentShader.length, fragmentShader, getStrLen(fragmentShader), 0);
        gl.glCompileShader(fragmentShaderProgram);
        gl.glGetShaderiv(fragmentShaderProgram,GL2.GL_COMPILE_STATUS, intBuffer);
        System.out.println("glCompileShader(fragment)=" + intBuffer.get(0));

        if(intBuffer.get(0) == GL2.GL_FALSE)
        {
            gl.glGetProgramiv(shaderProgram, GL2.GL_INFO_LOG_LENGTH, intBuffer);

            int size = intBuffer.get(0);
            if (size > 0)
            {
                ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                gl.glGetProgramInfoLog(shaderProgram, size, intBuffer, byteBuffer);
                for(byte b : byteBuffer.array())
                {
                    System.err.print((char) b);
                }
            }
            else
            {
                System.out.println("Unknown");
            }
        }

        shaderProgram = gl.glCreateProgram();
        System.out.println("glCreateProgram=" + shaderProgram);

        gl.glAttachShader(shaderProgram, vertexShaderProgram);
        gl.glAttachShader(shaderProgram, fragmentShaderProgram);

        gl.glLinkProgram(shaderProgram);
        gl.glGetProgramiv(fragmentShaderProgram, GL2.GL_LINK_STATUS, intBuffer);
        System.out.println("glLinkProgram()=" + intBuffer.get(0));

        gl.glValidateProgram(shaderProgram);
        gl.glGetProgramiv(fragmentShaderProgram, GL2.GL_VALIDATE_STATUS, intBuffer);
        System.out.println("glValidateProgram()=" + intBuffer.get(0));

        gl.glGetProgramiv(shaderProgram, GL2.GL_LINK_STATUS, intBuffer);

        if(intBuffer.get(0) != GL2.GL_TRUE)
        {
            System.out.println("GL_LINK_STATUS=" + intBuffer.get(0));

            gl.glGetProgramiv(shaderProgram, GL2.GL_INFO_LOG_LENGTH, intBuffer);
            int size = intBuffer.get(0);

            System.out.println("GL_INFO_LOG_LENGHT=" + size);

            System.out.println("Program link error: ");
            if(size > 0)
            {
                ByteBuffer byteBuffer = ByteBuffer.allocate(size);
                gl.glGetProgramInfoLog(shaderProgram, size, intBuffer, byteBuffer);
                for(byte b : byteBuffer.array())
                {
                    System.err.print((char) b);
                }
            }
            else
            {
                System.out.println("Unknown");
            }
        }
        gl.glUseProgram(shaderProgram);
    }

    public void dispose()
    {

    }
}