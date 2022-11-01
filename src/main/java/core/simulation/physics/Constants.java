package core.simulation.physics;

public class Constants
{
    public static final int tickRate = 60;
    public static final double G = 6.67e-11;
    public static final double timeStep = 3600 * 24f / tickRate;
    public static final double AU = 149_597_870_700.0;
    public static final double scale = 250f / AU;
}