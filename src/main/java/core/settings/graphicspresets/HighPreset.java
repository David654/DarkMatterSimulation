package core.settings.graphicspresets;

import core.assets.textures.TextureQuality;

public class HighPreset extends Preset
{
    protected void setPreset()
    {
        vsync = false;
        fps = 60;
        maxDist = 8192;
        maxSteps = 256;
        fov = 60;
        antialiasing = true;
        textureQuality = TextureQuality.HIGH;
    }
}