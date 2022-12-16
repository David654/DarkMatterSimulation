package core.graphics.geom;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable
{
    void draw(ShapeRenderer shapeRenderer);
    void fill(ShapeRenderer shapeRenderer);
}