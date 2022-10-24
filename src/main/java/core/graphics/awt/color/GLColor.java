package core.graphics.awt.color;

import java.util.Objects;

public final class GLColor
{
    public static final GLColor BLACK = new GLColor(0, 0, 0);
    public static final GLColor WHITE = new GLColor(1, 1, 1);
    public static final GLColor RED = new GLColor(1, 0, 0);
    public static final GLColor GREEN = new GLColor(0, 1, 0);
    public static final GLColor BLUE = new GLColor(0, 0, 1);
    public static final GLColor YELLOW = new GLColor(1, 1, 0);
    public static final GLColor MAGENTA = new GLColor(1, 0, 1);
    public static final GLColor CYAN = new GLColor(0, 1, 1);
    public static final GLColor GRAY = new GLColor(0.5f, 0.5f, 0.5f);

    private float red;
    private float green;
    private float blue;

    public GLColor(float red, float green, float blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed()
    {
        return red;
    }

    public void setRed(float red)
    {
        this.red = red;
    }

    public float getGreen()
    {
        return green;
    }

    public void setGreen(float green)
    {
        this.green = green;
    }

    public float getBlue()
    {
        return blue;
    }

    public void setBlue(float blue)
    {
        this.blue = blue;
    }

    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        GLColor glColor = (GLColor) o;
        return Float.compare(glColor.red, red) == 0 && Float.compare(glColor.green, green) == 0 && Float.compare(glColor.blue, blue) == 0;
    }

    public int hashCode()
    {
        return Objects.hash(red, green, blue);
    }

    public String toString()
    {
        return (int) red * 255 + ", " + (int) green * 255 + ", " + (int) blue * 255;
        //return "GLColor: red = " + red + ", green = " + green + ", blue = " + blue;
    }
}