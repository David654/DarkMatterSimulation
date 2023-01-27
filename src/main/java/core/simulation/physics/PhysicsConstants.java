package core.simulation.physics;

import java.util.function.UnaryOperator;

public class PhysicsConstants
{
    public static final int TICK_RATE = 60;
    public static final double G = 6.67e-11;
    public static final double DELTA_TIME = 0.00001;
    public static double DAYS = 0.1;
    public static final UnaryOperator<Double> TIME_STEP = t -> 3600 * 24f * t / TICK_RATE;
    public static final double AU = 149_597_870_700.0;
    public static final double E_V_TO_KG = 1.78e-36;
}