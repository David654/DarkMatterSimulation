package core.math.numerics.integral;

import core.math.numerics.function.Function;

public class DefiniteIntegral implements Integral
{
    private double a;
    private double b;

    public DefiniteIntegral(double a, double b)
    {
        this.a = a;
        this.b = b;
    }

    public double getA()
    {
        return a;
    }

    public void setA(double a)
    {
        this.a = a;
    }

    public double getB()
    {
        return b;
    }

    public void setB(double b)
    {
        this.b = b;
    }

    public double calculate(Function function)
    {
        int N = 1000;                    // precision parameter
        double h = (b - a) / (N - 1);     // step size

        // 1/3 terms
        double sum = 1.0 / 3.0 * (function.f(a) + function.f(b));

        // 4/3 terms
        for(int i = 1; i < N - 1; i += 2)
        {
            double x = a + h * i;
            sum += 4.0 / 3.0 * function.f(x);
        }

        // 2/3 terms
        for(int i = 2; i < N - 1; i += 2)
        {
            double x = a + h * i;
            sum += 2.0 / 3.0 * function.f(x);
        }

        return sum * h;
    }
}