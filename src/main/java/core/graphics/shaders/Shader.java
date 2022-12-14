/**
 * @author David Lapidus
 * @version 1.0
 */

package core.graphics.shaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Shader
{
    /**
     * Default vertex shader directory.
     * @see Shader#Shader(String[], String[])
     */
    public static final String VERTEX_SHADER_DIRECTORY = "src\\main\\java\\core\\graphics\\shaders\\vertex\\";

    /**
     * Default fragment shader directory.
     * @see Shader#Shader(String[], String[])
     */
    public static final String FRAGMENT_SHADER_DIRECTORY = "src\\main\\java\\core\\graphics\\shaders\\fragment\\";

    /**
     * Custom vertex shader directory.
     * @see Shader#Shader(String, String, String[], String[])
     */
    private final String vertexShaderDirectory;

    /**
     * Custom fragment shader directory.
     *
     * @see Shader#Shader(String, String, String[], String[])
     */
    private final String fragmentShaderDirectory;

    private final String[] vertexShaderFilePaths;
    private final String[] fragmentShaderFilePaths;

    /**
     * Constructor with default vertex shader and fragment shader directories.
     * @see #VERTEX_SHADER_DIRECTORY
     * @see #FRAGMENT_SHADER_DIRECTORY
     */
    public Shader(String[] vertexShaderFilePaths, String[] fragmentShaderFilePaths)
    {
        this(VERTEX_SHADER_DIRECTORY, FRAGMENT_SHADER_DIRECTORY, vertexShaderFilePaths, fragmentShaderFilePaths);
    }

    /**
     * Constructor with custom vertex shader and fragment shader directories.
     * @param vertexShaderDirectory custom vertex shader directory.
     * @param fragmentShaderDirectory custom fragment shader directory.
     */
    public Shader(String vertexShaderDirectory, String fragmentShaderDirectory, String[] vertexShaderFilePaths, String[] fragmentShaderFilePaths)
    {
        this.vertexShaderDirectory = vertexShaderDirectory;
        this.fragmentShaderDirectory = fragmentShaderDirectory;
        this.vertexShaderFilePaths = vertexShaderFilePaths;
        this.fragmentShaderFilePaths = fragmentShaderFilePaths;
    }

    /**
     * Reads a shader from a file and converts that to String.
     * @param file a file with a shader to read from.
     * @return a new String from the read file.
     */
    private String readShader(File file)
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

    /**
     * Reads all the vertex shaders from the specified directory.
     * @param directoryPath the specified directory path.
     * @return String array of the read vertex shaders.
     */
    private String[] loadVertexShaders(String directoryPath)
    {
        ArrayList<String> vertexShaders = new ArrayList<>();
        File[] vertexShaderFiles = new File[vertexShaderFilePaths.length];

        for(int i = 0; i < vertexShaderFilePaths.length; i++)
        {
            vertexShaderFiles[i] = new File(directoryPath + vertexShaderFilePaths[i]);
        }


        for(File file : vertexShaderFiles)
        {
            vertexShaders.add(readShader(file));
        }
        return vertexShaders.toArray(new String[0]);
    }

    /**
     * Reads all the fragment shaders from the specified directory.
     * @param directoryPath the specified directory path.
     * @return String array of the read fragment shaders.
     */
    private String[] loadFragmentShaders(String directoryPath)
    {
        ArrayList<String> fragmentShaders = new ArrayList<>();
        File[] fragmentShaderFiles = new File[fragmentShaderFilePaths.length];

        for(int i = 0; i < fragmentShaderFilePaths.length; i++)
        {
            fragmentShaderFiles[i] = new File(directoryPath + fragmentShaderFilePaths[i]);
        }


        for(File file : fragmentShaderFiles)
        {
            fragmentShaders.add(readShader(file));
        }
        return fragmentShaders.toArray(new String[0]);
    }

    /**
     * @return Final vertex shader that consists from all the vertex shaders from the specified vertex shader directory.
     * @see #loadVertexShaders(String)
     */
    public String getVertexShader()
    {
        StringBuilder sb = new StringBuilder();
        String[] vertexShaders = loadVertexShaders(vertexShaderDirectory);

        for(String vertexShader : vertexShaders) 
        {
            sb.append(vertexShader).append("\n");
        }
        return sb.toString();
    }

    /**
     * @return Final fragment shader that consists from all the fragment shaders from the specified fragment shader directory.
     * @see #loadFragmentShaders(String)
     */
    public String getFragmentShader()
    {
        StringBuilder sb = new StringBuilder();
        String[] fragmentShaders = loadFragmentShaders(fragmentShaderDirectory);

        for(String fragmentShader : fragmentShaders)
        {
            sb.append(fragmentShader).append("\n");
        }
        return sb.toString();
    }
}