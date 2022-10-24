package core.math.vector;

import core.math.util.MathUtils;

public class Vector2 implements Vector<Vector2>
{
    private float x;
    private float y;

    public Vector2()
    {
        this(0, 0);
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public String toString()
    {
        return "X: " + x + "; Y: " + y;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean equals(Object object)
    {
        Vector2 vector = (Vector2) object;
        return x == vector.x && y == vector.y;
    }

    public static Vector2 unitX()
    {
        return new Vector2(1, 0);
    }

    public static Vector2 unitY()
    {
        return new Vector2(0, 1);
    }

    public static Vector2 zero()
    {
        return new Vector2();
    }

    public Vector2 abs()
    {
        return new Vector2(MathUtils.abs(x), MathUtils.abs(y));
    }

    public float length()
    {
        return MathUtils.sqrt(MathUtils.pow(x, 2) + MathUtils.pow(y, 2));
    }

    public Vector2 add(Vector2 vector)
    {
        return new Vector2(x + vector.x, y + vector.y);
    }

    public Vector2 subtract(Vector2 vector)
    {
        return new Vector2(x - vector.x, y - vector.y);
    }

    public Vector2 multiply(Vector2 vector)
    {
        return new Vector2(x * vector.x, y * vector.y);
    }

    public Vector2 multiply(float scalar)
    {
        return new Vector2(x * scalar, y * scalar);
    }

    public Vector2 divide(Vector2 vector)
    {
        return new Vector2(x / vector.x, y / vector.y);
    }

    public Vector2 divide(float scalar)
    {
        return new Vector2(x / scalar, y / scalar);
    }

    public Vector2 max(Vector2 vector)
    {
        return new Vector2(MathUtils.max(x, vector.x), MathUtils.max(y, vector.y));
    }

    public Vector2 min(Vector2 vector)
    {
        return new Vector2(MathUtils.min(x, vector.x), MathUtils.min(y, vector.y));
    }

    public float distance(Vector2 vector)
    {
        return MathUtils.sqrt(MathUtils.pow(x - vector.x, 2) + MathUtils.pow(y - vector.y, 2));
    }

    public Vector2 clamp(Vector2 min, Vector2 max)
    {
        return this.min(max).max(min);
    }

    public Vector2 normalize()
    {
        return new Vector2(x / length(), y / length());
    }

    public float dot(Vector2 vector)
    {
        return x * vector.x + y * vector.y;
    }

    public Vector2 cross(Vector2 vector)
    {
        return new Vector2(x * vector.y, y * vector.x);
    }

    public Vector2 reflect(Vector2 normal)
    {
        return this.multiply(normal).multiply(normal).multiply(2).subtract(this);
    }

    public Vector2 negate()
    {
        return this.multiply(-1);
    }

    public Vector2 lerp(Vector2 vector, float amount)
    {
        return vector.subtract(this).multiply(amount).add(this);
    }
}