package core.settings.graphicspresets;

import core.assets.textures.TextureQuality;

public class LowPreset extends Preset
{
    protected void setPreset()
    {
        vsync = true;
        fps = 60;
        maxDist = 1024;
        maxSteps = 256;
        fov = 60;
        textureQuality = TextureQuality.LOW;
    }
}