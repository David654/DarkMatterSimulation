package core.simulation.input.actions;

import core.graphics.core.Scene;

public class PauseAction implements InputAction
{
    private final Scene scene;

    public PauseAction(Scene scene)
    {
        this.scene = scene;
    }

    public void perform()
    {
        scene.getSimulation().setPaused(!scene.getSimulation().isPaused());
    }
}