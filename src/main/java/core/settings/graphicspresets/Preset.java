package core.settings.graphicspresets;

public abstract class Preset
{
    protected boolean vsync;
    protected int fps;
    protected float maxDist;
    protected int maxSteps;
    protected float fov;
    protected int textureQuality;

    public Preset()
    {
        setPreset();
    }

    protected abstract void setPreset();

    public boolean isVSYNCEnabled()
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

    public int getTextureQuality()
    {
        return textureQuality;
    }
}