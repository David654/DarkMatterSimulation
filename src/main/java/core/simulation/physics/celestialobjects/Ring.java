package core.simulation.physics.celestialobjects;

import com.badlogic.gdx.graphics.Texture;
import core.assets.textures.Textures;
import core.math.vector.Vector3;

public class Ring
{
    private Vector3 position;
    private double radius1;
    private double radius2;
    private Texture texture;
    private String texturePath;

    public Ring(Vector3 position, double radius1, double radius2)
    {
        this(position, radius1, radius2, Textures.DEFAULT_PLANET_TEXTURE_PATH);
    }

    public Ring(Vector3 position, double radius1, double radius2, String texturePath)
    {
        this.position = position;
        this.radius1 = radius1 * 1000;
        this.radius2 = radius2 * 1000;
        this.texturePath = texturePath;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public void setPosition(Vector3 position)
    {
        this.position = position;
    }

    public double getRadius1()
    {
        return radius1;
    }

    public void setRadius1(double radius1)
    {
        this.radius1 = radius1;
    }

    public double getRadius2()
    {
        return radius2;
    }

    public void setRadius2(double radius2)
    {
        this.radius2 = radius2;
    }

    public String getTexturePath()
    {
        return texturePath;
    }

    public Texture getTexture()
    {
        return texture;
    }

    public void setTexture(String texturePath)
    {
        this.texture = new Texture(texturePath);
    }

    public void setTexture(Texture texture)
    {
        this.texture = texture;
    }
}