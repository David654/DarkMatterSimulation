package core.settings.graphicspresets;

import core.graphics.textures.TextureManager;

public class LowPreset extends Preset
{
    protected void setPreset()
    {
        vsync = true;
        fps = 60;
        maxDist = 1000;
        maxSteps = 256;
        fov = 60;
        textureQuality = TextureManager.LOW_QUALITY;
    }
}