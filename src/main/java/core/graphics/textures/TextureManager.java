package core.graphics.textures;

import core.settings.graphicspresets.Preset;

public class TextureManager
{
    public static final int HIGH_QUALITY = 1;
    public static final int LOW_QUALITY = 0;

    /**
     * Directories.
     */
    public static final String PLANET_TEXTURE_DIRECTORY = "src\\main\\resources\\planets\\";

    /**
     * Planets.
     */
    public static final String DEFAULT_PLANET_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "default\\planet.jpg";
    public static String SUN_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "sun\\sun";
    public static String MERCURY_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mercury\\mercury";
    public static String VENUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "venus\\venus";
    public static String EARTH_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth\\earth";
    public static String MOON_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "earth\\moon";
    public static String MARS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "mars\\mars";
    public static String JUPITER_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "jupiter\\jupiter";
    public static String SATURN_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn\\saturn";
    public static String SATURN_RING_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "saturn\\saturn_ring";
    public static String URANUS_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "uranus\\uranus.jpg";
    public static String NEPTUNE_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "neptune\\neptune.jpg";

    /**
     * Backgrounds.
     */
    public static String MILKY_WAY_TEXTURE_PATH = PLANET_TEXTURE_DIRECTORY + "background\\milky_way";

    public TextureManager(Preset preset)
    {
        switch(preset.getTextureQuality())
        {
            case HIGH_QUALITY ->
            {
                SUN_TEXTURE_PATH += "_high.jpg";
                MERCURY_TEXTURE_PATH += "_high.jpg";
                VENUS_TEXTURE_PATH += "_high.jpg";
                EARTH_TEXTURE_PATH += "_high.jpg";
                MOON_TEXTURE_PATH += "_high.jpg";
                MARS_TEXTURE_PATH += "_high.jpg";
                JUPITER_TEXTURE_PATH += "_high.jpg";
                SATURN_TEXTURE_PATH += "_high.jpg";
                SATURN_RING_TEXTURE_PATH += "_high.png";
                MILKY_WAY_TEXTURE_PATH += "_high.jpg";
            }

            case LOW_QUALITY ->
            {
                SUN_TEXTURE_PATH += "_low.jpg";
                MERCURY_TEXTURE_PATH += "_low.jpg";
                VENUS_TEXTURE_PATH += "_low.jpg";
                EARTH_TEXTURE_PATH += "_low.jpg";
                MOON_TEXTURE_PATH += "_low.jpg";
                MARS_TEXTURE_PATH += "_low.jpg";
                JUPITER_TEXTURE_PATH += "_low.jpg";
                SATURN_TEXTURE_PATH += "_low.jpg";
                SATURN_RING_TEXTURE_PATH += "_low.png";
                MILKY_WAY_TEXTURE_PATH += "_low.jpg";
            }
        }
    }
}