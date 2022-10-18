package core.math.vector;

public interface Vector<Vector>
{
    String toString();
    int hashCode();
    boolean equals(Object object);

    float length();
    float distance(Vector vector);
    Vector max(Vector vector);
    Vector min(Vector vector);
    Vector abs();
    Vector clamp(Vector min, Vector max);
    Vector lerp(Vector vector, float amount);
    Vector add(Vector vector);
    Vector subtract(Vector vector);
    Vector multiply(Vector vector);
    Vector multiply(float scalar);
    Vector divide(Vector vector);
    Vector divide(float scalar);
    float dot(Vector vector);
    Vector cross(Vector vector);
    Vector reflect(Vector normal);
    Vector normalize();
    Vector negate();
}