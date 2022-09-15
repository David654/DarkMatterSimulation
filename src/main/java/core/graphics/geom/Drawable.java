package core.graphics.geom;

import com.jogamp.opengl.GL2;

public interface Drawable
{
    void draw(GL2 gl);
    void fill(GL2 gl);
}