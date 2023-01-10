package core.simulation.input.actions;

import core.graphics.core.Scene;
import core.util.MathUtils;

public class BodySelectionAction implements InputAction
{
    private final Scene scene;
    private int selectedBodyIndex = 0;

    public BodySelectionAction(Scene scene)
    {
        this.scene = scene;
    }

    public int getSelectedBodyIndex()
    {
        return selectedBodyIndex;
    }

    public void setSelectedBodyIndex(int selectedBodyIndex)
    {
        this.selectedBodyIndex = selectedBodyIndex;
    }

    public void perform()
    {
        selectedBodyIndex = MathUtils.clamp(selectedBodyIndex, 0, Scene.simulation.getStarSystem().getBodyHandler().getSize() - 1);
        scene.setSelectedBodyIndex(selectedBodyIndex);
    }
}