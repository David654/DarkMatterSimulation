package core.simulation.input.core;

import com.badlogic.gdx.Input;
import core.graphics.core.Scene;
import core.simulation.input.actions.*;

public class InputProcessor implements com.badlogic.gdx.InputProcessor
{
    private final Scene scene;

    private final InputAction exitAction;
    private final InputAction lightEnablingAction;
    private final InputAction gridShowingAction;
    private final InputAction pauseAction;
    private final InputAction scrollAction;
    private final InputAction simulationMenuAction;
    private final InputAction timeStepAction;

    public InputProcessor(Scene scene)
    {
        this.scene = scene;

        exitAction = new ExitAction();
        lightEnablingAction = new LightEnablingAction(scene);
        gridShowingAction = new GridShowingAction(scene);
        pauseAction = new PauseAction(scene);
        scrollAction = new ScrollAction(scene);
        simulationMenuAction = new SimulationMenuAction(800, 600, scene.getSimulation());
        timeStepAction = new TimeStepAction();
    }

    public boolean keyDown(int keycode)
    {
        return false;
    }

    public boolean keyUp(int keycode)
    {
        if(keycode == Input.Keys.ESCAPE)
        {
            exitAction.perform();
        }

        if(keycode == Input.Keys.L)
        {
            lightEnablingAction.perform();
        }

        if(keycode == Input.Keys.G)
        {
            gridShowingAction.perform();
        }

        if(keycode == Input.Keys.TAB)
        {
            simulationMenuAction.perform();
        }

        if(keycode == Input.Keys.SPACE)
        {
            pauseAction.perform();
        }

        if(keycode == Input.Keys.UP)
        {
            TimeStepAction tsa = (TimeStepAction) timeStepAction;
            tsa.setDays(tsa.getDays() + 0.1);
            tsa.perform();
        }

        if(keycode == Input.Keys.DOWN)
        {
            TimeStepAction tsa = (TimeStepAction) timeStepAction;
            tsa.setDays(tsa.getDays() - 0.1);
            tsa.perform();
        }
        return false;
    }

    public boolean keyTyped(char character)
    {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    public boolean scrolled(float amountX, float amountY)
    {
        ScrollAction sa = (ScrollAction) scrollAction;
        sa.setAmountX(amountX);
        sa.setAmountY(amountY);
        sa.perform();
        return false;
    }
}