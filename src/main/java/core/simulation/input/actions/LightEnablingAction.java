package core.simulation.input.actions;

import core.graphics.core.Scene;

public class LightEnablingAction implements InputAction
{
    private final Scene scene;

    public LightEnablingAction(Scene scene)
    {
        this.scene = scene;
    }

    public void perform()
    {
        scene.setLightingEnabled(!scene.isLightingEnabled());
    }
}