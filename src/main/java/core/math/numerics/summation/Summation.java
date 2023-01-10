package core.math.numerics.summation;

import core.math.numerics.function.Function;

public class Summation
{
    private int m;
    private int n;

    public Summation(int m, int n)
    {
        this.m = m;
        this.n = n;
    }

    public int getM()
    {
        return m;
    }

    public void setM(int m)
    {
        this.m = m;
    }

    public int getN()
    {
        return n;
    }

    public void setN(int n)
    {
        this.n = n;
    }

    public double calculate(Function function)
    {
        double sum = 0;

        for(int i = m; i <= n; i++)
        {
            sum += function.f(i);
        }

        return sum;
    }
}