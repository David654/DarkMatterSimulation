package core.simulation.input.actions;

import core.graphics.core.Scene;

public class GridShowingAction implements InputAction
{
    private final Scene scene;

    public GridShowingAction(Scene scene)
    {
        this.scene = scene;
    }

    public void perform()
    {
        scene.setShowGrid(!scene.isGridShown());
    }
}
