package core.util;

import java.awt.*;

public class Utils
{
    public static String getColorString(Color color)
    {
        return "rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

    public static boolean isDouble(String s)
    {
        boolean isDouble;
        try
        {
            double d = Double.parseDouble(s);
            isDouble = true;
        }
        catch(NumberFormatException e)
        {
            isDouble = false;
        }
        return isDouble;
    }

    public static double parseDouble(double initialValue, String s)
    {
        double d;
        try
        {
            d = Double.parseDouble(s);
        }
        catch(NumberFormatException e)
        {
            d = initialValue;
        }
        return d;
    }
}