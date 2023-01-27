package core.graphics.hud.renderpositions;

import core.math.vector.Vector2;

public class RenderPosition
{
    private final float x;
    private final float y;

    public RenderPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 getRenderPosition()
    {
        return new Vector2(x, y);
    }
}