package core.simulation.input.actions;

import core.graphics.core.Scene;
import core.settings.InputSettings;

public class ScrollAction implements InputAction
{
    private final Scene scene;
    private float amountX;
    private float amountY;

    public ScrollAction(Scene scene)
    {
        this.scene = scene;
    }

    public float getAmountX()
    {
        return amountX;
    }

    public void setAmountX(float amountX)
    {
        this.amountX = amountX;
    }

    public float getAmountY()
    {
        return amountY;
    }

    public void setAmountY(float amountY)
    {
        this.amountY = amountY;
    }

    public void perform()
    {
        //InputSettings.MOUSE_SENSITIVITY_X -= amountX / 400;
      //  InputSettings.MOUSE_SENSITIVITY_Y -= amountY / 400;
        scene.setZoom(scene.getZoom() - amountY);
        //scene.setZoom(scene.getZoom() - amountY);
    }
}