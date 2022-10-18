package core.math.vector;

import core.math.matrix.Matrix3;
import core.math.util.MathUtils;

public class Vector3 implements Vector<Vector3>
{
    private float x;
    private float y;
    private float z;

    public Vector3()
    {
        this(0, 0, 0);
    }

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float getZ()
    {
        return z;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public String toString()
    {
        return "X: " + x + "; Y: " + y + "; Z: " + z;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean equals(Object object)
    {
        Vector3 vector = (Vector3) object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    public static Vector3 unitX()
    {
        return new Vector3(1, 0, 0);
    }

    public static Vector3 unitY()
    {
        return new Vector3(0, 1, 0);
    }

    public static Vector3 unitZ()
    {
        return new Vector3(0, 0, 1);
    }

    public static Vector3 zero()
    {
        return new Vector3();
    }

    public Vector3 abs()
    {
        return new Vector3(MathUtils.abs(x), MathUtils.abs(y), MathUtils.abs(z));
    }

    public float length()
    {
        return MathUtils.sqrt(MathUtils.pow(x, 2) + MathUtils.pow(y, 2) + MathUtils.pow(z, 2));
    }

    public Vector3 add(Vector3 vector)
    {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z);
    }

    public Vector3 subtract(Vector3 vector)
    {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3 multiply(Vector3 vector)
    {
        return new Vector3(x * vector.x, y * vector.y, z * vector.z);
    }

    public Vector3 multiply(float scalar)
    {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public Vector3 divide(Vector3 vector)
    {
        return new Vector3(x / vector.x, y / vector.y, z / vector.z);
    }

    public Vector3 divide(float scalar)
    {
        return new Vector3(x / scalar, y / scalar, z / scalar);
    }

    public Vector3 max(Vector3 vector)
    {
        return new Vector3(MathUtils.max(x, vector.x), MathUtils.max(y, vector.y), MathUtils.max(z, vector.z));
    }

    public Vector3 min(Vector3 vector)
    {
        return new Vector3(MathUtils.min(x, vector.x), MathUtils.min(y, vector.y), MathUtils.min(z, vector.z));
    }

    public float distance(Vector3 vector)
    {
        return (float) MathUtils.sqrt(MathUtils.pow(x - vector.x, 2) + MathUtils.pow(y - vector.y, 2) + MathUtils.pow(z - vector.z, 2));
    }

    public Vector3 clamp(Vector3 min, Vector3 max)
    {
        return this.min(max).max(min);
    }

    public Vector3 normalize()
    {
        return new Vector3(x / length(), y / length(), z / length());
    }

    public float dot(Vector3 vector)
    {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector3 cross(Vector3 vector)
    {
        return new Vector3(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x * vector.y - y * vector.x);
    }

    public Vector3 reflect(Vector3 normal)
    {
        return this.multiply(normal).multiply(normal).multiply(2).subtract(this);
    }

    public Vector3 negate()
    {
        return this.multiply(-1);
    }

    public Vector3 lerp(Vector3 vector, float amount)
    {
        return vector.subtract(this).multiply(amount).add(this);
    }

    public Vector3 multiply(Matrix3 matrix)
    {
        Vector3 vector = new Vector3();

        vector.setX(vector.x + x * matrix.get(0, 0));
        vector.setX(vector.x + y * matrix.get(0, 1));
        vector.setX(vector.x + z * matrix.get(0, 2));

        vector.setY(vector.y + x * matrix.get(1, 0));
        vector.setY(vector.y + y * matrix.get(1, 1));
        vector.setY(vector.y + z * matrix.get(1, 2));

        vector.setZ(vector.z + x * matrix.get(2, 0));
        vector.setZ(vector.z + y * matrix.get(2, 1));
        vector.setZ(vector.z + z * matrix.get(2, 2));
        return vector;
    }
}