package core.settings.graphicspresets;

import core.assets.textures.TextureQuality;

public abstract class Preset
{
    protected boolean vsync;
    protected int fps;
    protected float maxDist;
    protected int maxSteps;
    protected float fov;
    protected TextureQuality textureQuality;

    public Preset()
    {
        setPreset();
    }

    protected abstract void setPreset();

    public boolean isVSyncEnabled()
    {
        return vsync;
    }

    public int getFPS()
    {
        return fps;
    }

    public float getMaxDist()
    {
        return maxDist;
    }

    public int getMaxSteps()
    {
        return maxSteps;
    }

    public float getFOV()
    {
        return fov;
    }

    public TextureQuality getTextureQuality()
    {
        return textureQuality;
    }

    public void setVSync(boolean vsync)
    {
        this.vsync = vsync;
    }

    public void setFPS(int fps)
    {
        this.fps = fps;
    }

    public void setMaxDist(float maxDist)
    {
        this.maxDist = maxDist;
    }

    public void setMaxSteps(int maxSteps)
    {
        this.maxSteps = maxSteps;
    }

    public void setFOV(float fov)
    {
        this.fov = fov;
    }

    public void setTextureQuality(TextureQuality textureQuality)
    {
        this.textureQuality = textureQuality;
    }

    public boolean equals(Object o)
    {
        Preset preset = (Preset) o;
        return vsync == preset.isVSyncEnabled() && fps == preset.getFPS() && maxDist == preset.getMaxDist() && maxSteps == preset.getMaxSteps() && fov == preset.getFOV() && textureQuality.equals(preset.getTextureQuality());
    }
}