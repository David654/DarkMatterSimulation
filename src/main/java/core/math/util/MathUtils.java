package core.math.util;

public class MathUtils
{
    public static short min(short a, short b)
    {
        return (short) Math.min(a, b);
    }
    
    public static int min(int a, int b)
    {
        return Math.min(a, b);
    }

    public static long min(long a, long b)
    {
        return Math.min(a, b);
    }

    public static float min(float a, float b)
    {
        return Math.min(a, b);
    }

    public static double min(double a, double b)
    {
        return Math.min(a, b);
    }

    public static short max(short a, short b)
    {
        return (short) Math.max(a, b);
    }

    public static int max(int a, int b)
    {
        return Math.max(a, b);
    }
    
    public static long max(long a, long b)
    {
        return Math.max(a, b);
    }

    public static float max(float a, float b)
    {
        return Math.max(a, b);
    }

    public static double max(double a, double b)
    {
        return Math.max(a, b);
    }

    public static short abs(short a)
    {
        return (short) Math.abs(a);
    }

    public static int abs(int a)
    {
        return Math.abs(a);
    }

    public static long abs(long a)
    {
        return Math.abs(a);
    }

    public static float abs(float a)
    {
        return Math.abs(a);
    }

    public static double abs(double a)
    {
        return Math.abs(a);
    }

    public static short pow(short a, short b)
    {
        return (short) Math.pow(a, b);
    }

    public static int pow(int a, int b)
    {
        return (int) Math.pow(a, b);
    }

    public static long pow(long a, long b)
    {
        return (long) Math.pow(a, b);
    }

    public static float pow(float a, float b)
    {
        return (float) Math.pow(a, b);
    }

    public static double pow(double a, double b)
    {
        return Math.pow(a, b);
    }

    public static short floor(short a)
    {
        return (short) Math.floor(a);
    }

    public static int floor(int a)
    {
        return (int) Math.floor(a);
    }

    public static long floor(long a)
    {
        return (long) Math.floor(a);
    }

    public static float floor(float a)
    {
        return (float) Math.floor(a);
    }

    public static double floor(double a)
    {
        return Math.floor(a);
    }

    public static short ceil(short a)
    {
        return (short) Math.ceil(a);
    }

    public static int ceil(int a)
    {
        return (int) Math.ceil(a);
    }

    public static long ceil(long a)
    {
        return (long) Math.ceil(a);
    }

    public static float ceil(float a)
    {
        return (float) Math.ceil(a);
    }

    public static double ceil(double a)
    {
        return Math.ceil(a);
    }

    public static short clamp(short value, short min, short max)
    {
        return MathUtils.max(min, MathUtils.min(value, max));
    }

    public static int clamp(int value, int min, int max)
    {
        return MathUtils.max(min, MathUtils.min(value, max));
    }

    public static long clamp(long value, long min, long max)
    {
        return MathUtils.max(min, MathUtils.min(value, max));
    }

    public static float clamp(float value, float min, float max)
    {
        return MathUtils.max(min, MathUtils.min(value, max));
    }

    public static double clamp(double value, double min, double max)
    {
        return MathUtils.max(min, MathUtils.min(value, max));
    }

    public static float sqrt(float a)
    {
        return (float) Math.sqrt(a);
    }

    public static double sqrt(double a)
    {
        return Math.sqrt(a);
    }

    public static float sin(float a)
    {
        return (float) Math.sin(a);
    }

    public static double sin(double a)
    {
        return Math.sin(a);
    }

    public static float cos(float a)
    {
        return (float) Math.cos(a);
    }

    public static double cos(double a)
    {
        return Math.cos(a);
    }

    public static float tan(float a)
    {
        return (float) Math.tan(a);
    }

    public static double tan(double a)
    {
        return Math.tan(a);
    }

    public static float asin(float a)
    {
        return (float) Math.asin(a);
    }

    public static double asin(double a)
    {
        return Math.asin(a);
    }

    public static float acos(float a)
    {
        return (float) Math.acos(a);
    }

    public static double acos(double a)
    {
        return Math.acos(a);
    }

    public static float atan(float a)
    {
        return (float) Math.atan(a);
    }

    public static double atan(double a)
    {
        return Math.atan(a);
    }

    public static float atan2(float y, float x)
    {
        return (float) Math.atan2(y, x);
    }

    public static double atan2(double y, double x)
    {
        return Math.atan2(y, x);
    }
}