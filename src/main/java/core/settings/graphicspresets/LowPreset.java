package core.settings.graphicspresets;

import core.util.TextureUtils;

public class LowPreset extends Preset
{
    protected void setPreset()
    {
        vsync = true;
        fps = 60;
        maxDist = 1000;
        maxSteps = 256;
        fov = 60;
        textureQuality = TextureUtils.LOW_QUALITY;
    }
}