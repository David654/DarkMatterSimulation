package core.math.vector;

public class Vector3 implements IVector<Vector3>
{
    private double x;
    private double y;
    private double z;

    public Vector3()
    {
        this(0, 0, 0);
    }

    public Vector3(double a)
    {
        this.x = a;
        this.y = a;
        this.z = a;
    }

    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
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
        return new Vector3(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public double length()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
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

    public Vector3 multiply(double scalar)
    {
        return new Vector3(x * scalar, y * scalar, z * scalar);
    }

    public Vector3 divide(Vector3 vector)
    {
        return new Vector3(x / vector.x, y / vector.y, z / vector.z);
    }

    public Vector3 divide(double scalar)
    {
        return new Vector3(x / scalar, y / scalar, z / scalar);
    }

    public Vector3 max(Vector3 vector)
    {
        return new Vector3(Math.max(x, vector.x), Math.max(y, vector.y), Math.max(z, vector.z));
    }

    public Vector3 min(Vector3 vector)
    {
        return new Vector3(Math.min(x, vector.x), Math.min(y, vector.y), Math.min(z, vector.z));
    }

    public double distance(Vector3 vector)
    {
        return (double) Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2) + Math.pow(z - vector.z, 2));
    }

    public Vector3 clamp(Vector3 min, Vector3 max)
    {
        return this.min(max).max(min);
    }

    public Vector3 normalize()
    {
        return new Vector3(x / length(), y / length(), z / length());
    }

    public double dot(Vector3 vector)
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

    public Vector3 lerp(Vector3 vector, double amount)
    {
        return vector.subtract(this).multiply(amount).add(this);
    }
}