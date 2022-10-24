package core.simulation.physics;

public class Constants
{
    public static final int tickRate = 60;
    public static final float G = (float) (6.67e-11);
    public static float timeStep = 3600 * 24f / tickRate;
    public static final long AU = (long) (1.496e11);
    public static float scale = 250f / AU;
}