package core.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public final class TextureUtils
{
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight)
    {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public static BufferedImage readImage(String imagePath)
    {
        try
        {
            return ImageIO.read(new File(imagePath));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Color getMostCommonColor(BufferedImage image)
    {
        HashMap<Color, Integer> colorMap = new HashMap<>();
        //image = resizeImage(image, 10, 10);

        for (int i = 0; i < image.getWidth(); i++)
        {
            for (int j = 0; j < image.getHeight(); j++)
            {
                Color color = new Color(image.getRGB(i, j));
                if(!isGray(color))
                {
                    if(!colorMap.containsKey(color))
                    {
                        colorMap.put(color, 1);
                    }
                    else
                    {
                        colorMap.replace(color, colorMap.get(color) + 1);
                    }
                }
            }
        }

        ArrayList<Integer> values = new ArrayList<>(colorMap.values());
        if(values.size() == 0)
        {
            return new Color(image.getRGB(0, 0));
        }
        Collections.sort(values);

        int maxValue = values.get(values.size() - 1);
        Color mostCommonColor = null;

        for(Color color : colorMap.keySet())
        {
            if(colorMap.get(color) == maxValue)
            {
                mostCommonColor = color;
            }
        }

        return mostCommonColor;
    }

    private static boolean isGray(Color color)
    {
        int rgDiff = color.getRed() - color.getGreen();
        int rbDiff = color.getRed() - color.getBlue();

        int tolerance = 20;
        if(rgDiff > tolerance || rgDiff < -tolerance)
        {
            return rbDiff <= tolerance && rbDiff >= -tolerance;
        }
        return true;
    }
}