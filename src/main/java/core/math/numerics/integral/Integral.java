package core.math.numerics.integral;

import core.math.numerics.function.Function;
import core.simulation.physics.PhysicsConstants;

public interface Integral
{
    double INCREMENT = 0.0001;

    double calculate(Function f);
}