package core.settings.graphicspresets;

import core.util.TextureUtils;

public class HighPreset extends Preset
{
    protected void setPreset()
    {
        vsync = true;
        fps = 60;
        maxDist = 2000;
        maxSteps = 256;
        fov = 60;
        textureQuality = TextureUtils.HIGH_QUALITY;
    }
}