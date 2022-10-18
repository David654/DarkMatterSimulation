package core.simulation.components.physics;

import com.jogamp.opengl.GL2;
import core.graphics.awt.color.GLColor;
import core.math.vector.Vector2;

import java.math.BigDecimal;

public class Body
{
    private Vector2 position;
    private float radius;
    private BigDecimal mass;
    private Vector2 velocity;
    private GLColor color;

    public Body(Vector2 position, float radius, BigDecimal mass, Vector2 velocity, GLColor color)
    {
        this.position = position;
        this.radius = radius;
        this.mass = mass;
        this.velocity = velocity;
        this.color = color;
    }

    public Vector2 getPosition()
    {
        return position;
    }

    public void setPosition(Vector2 position)
    {
        this.position = position;
    }

    public float getRadius()
    {
        return radius;
    }

    public void setRadius(float radius)
    {
        this.radius = radius;
    }

    public BigDecimal getMass()
    {
        return mass;
    }

    public void setMass(BigDecimal mass)
    {
        this.mass = mass;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2 velocity)
    {
        this.velocity = velocity;
    }

    public GLColor getColor()
    {
        return color;
    }

    public void setColor(GLColor color)
    {
        this.color = color;
    }

    public void update()
    {

    }

    public void render(GL2 gl)
    {

    }
}