package core.assets.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import core.graphics.core.Shader;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.celestialobjects.Ring;

import java.util.HashMap;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager
{
    private HashMap<String, Texture> textures;

    public AssetManager()
    {
        textures = new HashMap<>();
    }

    public ShaderProgram loadShaders()
    {
        String[] vertexShaderFilePaths = new String[] {"vertex.glsl"};
        String[] fragmentShaderFilePaths = new String[] {"uniforms.glsl", "sdf.glsl", "util.glsl", "texturing.glsl", "bodies.glsl", "darkmatter.glsl", "map.glsl", "lighting.glsl", "raymarching.glsl" , "fragment.glsl"};

        Shader shader = new Shader(vertexShaderFilePaths, fragmentShaderFilePaths);
        String vertexShader = shader.getVertexShader();
        String fragmentShader = shader.getFragmentShader();

        ShaderProgram.pedantic = false;
        ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        if(!shaderProgram.isCompiled())
            System.err.println(shaderProgram.getLog());
        return shaderProgram;
    }

    public void loadTextures()
    {
        for(int i = 0; i < BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size(); i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);
            String texturePath = celestialObject.getTexturePath();
            this.load(texturePath, Texture.class);
        }

        for(int i = 0; i < BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size(); i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);

            Ring ring = celestialObject.getRing();
            if(ring != null)
            {
                String ringTexturePath = celestialObject.getRing().getTexturePath();
                this.load(ringTexturePath, Texture.class);
            }

            if(celestialObject.getBumpTexturePath() != null)
            {
                String bumpTexturePath = celestialObject.getBumpTexturePath();
                this.load(bumpTexturePath, Texture.class);
            }
        }
    }

    public void setTextures()
    {
        this.finishLoading();
        int counter = 0;

        for(int i = 0; i < BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size(); i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);
            String texturePath = celestialObject.getTexturePath();

            Texture texture = this.get(texturePath, Texture.class);
            BasicCelestialObjects.TEXTURES.add(texture);
            celestialObject.setTexture(texture);
            counter++;
            System.out.println("Loading textures... (" + counter + " / " + BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size() + ")");
        }

        for(int i = 0; i < BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size(); i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);

            Ring ring = celestialObject.getRing();
            if(ring != null)
            {
                String ringTexturePath = celestialObject.getRing().getTexturePath();

                Texture ringTexture = this.get(ringTexturePath, Texture.class);
                BasicCelestialObjects.TEXTURES.add(ringTexture);
                celestialObject.getRing().setTexture(ringTexture);
            }

            if(celestialObject.getBumpTexturePath() != null)
            {
                String bumpTexturePath = celestialObject.getBumpTexturePath();

                Texture bumpTexture = this.get(bumpTexturePath, Texture.class);
                BasicCelestialObjects.TEXTURES.add(bumpTexture);
                celestialObject.setBumpTexture(bumpTexture);
            }
        }
    }
}