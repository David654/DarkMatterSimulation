package core.settings.graphicspresets;

import core.assets.textures.TextureQuality;

public class CustomPreset extends Preset
{
    protected void setPreset()
    {
        vsync = true;
        fps = 60;
        maxDist = 10000;
        maxSteps = 256;
        fov = 60;
        textureQuality = TextureQuality.HIGH;
    }
}