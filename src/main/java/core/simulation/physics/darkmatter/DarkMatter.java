package core.simulation.physics.darkmatter;

import core.simulation.handler.DarkMatterHandler;
import core.simulation.physics.PhysicsConstants;

import java.awt.*;

public class DarkMatter
{
    private double density;
    private double radius1;
    private double radius2;
    private double mass;

    public DarkMatter(double density, double radius1, double radius2, double mass)
    {
        this.density = density;
        this.radius1 = radius1;
        this.radius2 = radius2;
        this.mass = mass;
    }

    public double getDensity()
    {
        return density;
    }

    public void setDensity(double density)
    {
        this.density = density;
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

    public double getMass()
    {
        return mass;
    }

    private void setMass(double mass)
    {
        this.mass = mass * PhysicsConstants.E_V_TO_KG;
    }

    public Color getColor()
    {
        return new Color(DarkMatterHandler.GRADIENT.getRGB((int) (density / 1000 * (DarkMatterHandler.GRADIENT.getWidth() - 1)), 0));
    }
}