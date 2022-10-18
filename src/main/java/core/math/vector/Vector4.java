package core.math.vector;

import core.math.matrix.Matrix3;
import core.math.util.MathUtils;

public class Vector4 implements Vector<Vector4>
{
    private float x;
    private float y;
    private float z;
    private float w;

    public Vector4()
    {
        this(0, 0, 0, 0);
    }

    public Vector4(float x, float y, float z, float w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public float getW()
    {
        return w;
    }

    public void setW(float w)
    {
        this.w = w;
    }

    public String toString()
    {
        return "X: " + x + "; Y: " + y + "; Z: " + z + "; W: " + w;
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public boolean equals(Object object)
    {
        Vector4 vector = (Vector4) object;
        return x == vector.x && y == vector.y && z == vector.z;
    }

    public static Vector4 unitX()
    {
        return new Vector4(1, 0, 0, 0);
    }

    public static Vector4 unitY()
    {
        return new Vector4(0, 1, 0, 0);
    }

    public static Vector4 unitZ()
    {
        return new Vector4(0, 0, 1, 0);
    }

    public static Vector4 unitW()
    {
        return new Vector4(0, 0, 0, 1);
    }

    public static Vector4 zero()
    {
        return new Vector4();
    }

    public Vector4 abs()
    {
        return new Vector4(MathUtils.abs(x), MathUtils.abs(y), MathUtils.abs(z), MathUtils.abs(w));
    }

    public float length()
    {
        return MathUtils.sqrt(MathUtils.pow(x, 2) + MathUtils.pow(y, 2) + MathUtils.pow(z, 2));
    }

    public Vector4 add(Vector4 vector)
    {
        return new Vector4(x + vector.x, y + vector.y, z + vector.z, w + vector.w);
    }

    public Vector4 subtract(Vector4 vector)
    {
        return new Vector4(x - vector.x, y - vector.y, z - vector.z, w - vector.w);
    }

    public Vector4 multiply(Vector4 vector)
    {
        return new Vector4(x * vector.x, y * vector.y, z * vector.z, w * vector.w);
    }

    public Vector4 multiply(float scalar)
    {
        return new Vector4(x * scalar, y * scalar, z * scalar, w * scalar);
    }

    public Vector4 divide(Vector4 vector)
    {
        return new Vector4(x / vector.x, y / vector.y, z / vector.z, w / vector.w);
    }

    public Vector4 divide(float scalar)
    {
        return new Vector4(x / scalar, y / scalar, z / scalar, w / scalar);
    }

    public Vector4 max(Vector4 vector)
    {
        return new Vector4(MathUtils.max(x, vector.x), MathUtils.max(y, vector.y), MathUtils.max(z, vector.z), MathUtils.max(w, vector.w));
    }

    public Vector4 min(Vector4 vector)
    {
        return new Vector4(MathUtils.min(x, vector.x), MathUtils.min(y, vector.y), MathUtils.min(z, vector.z), MathUtils.min(w, vector.w));
    }

    public float distance(Vector4 vector)
    {
        return (float) MathUtils.sqrt(MathUtils.pow(x - vector.x, 2) + MathUtils.pow(y - vector.y, 2) + MathUtils.pow(z - vector.z, 2));
    }

    public Vector4 clamp(Vector4 min, Vector4 max)
    {
        return this.min(max).max(min);
    }

    public Vector4 normalize()
    {
        return new Vector4(x / length(), y / length(), z / length(), w / length());
    }

    public float dot(Vector4 vector)
    {
        return x * vector.x + y * vector.y + z * vector.z;
    }

    public Vector4 cross(Vector4 vector)
    {
        return null;
    }

    public Vector4 reflect(Vector4 normal)
    {
        return this.multiply(normal).multiply(normal).multiply(2).subtract(this);
    }

    public Vector4 negate()
    {
        return this.multiply(-1);
    }

    public Vector4 lerp(Vector4 vector, float amount)
    {
        return vector.subtract(this).multiply(amount).add(this);
    }

    public Vector4 multiply(Matrix3 matrix)
    {
        Vector4 vector = new Vector4();

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