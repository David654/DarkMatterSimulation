package core.simulation.input.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import core.graphics.core.Scene;
import core.math.vector.Vector2;
import core.settings.InputSettings;
import core.simulation.input.actions.*;
import core.util.MathUtils;

public class InputProcessor implements com.badlogic.gdx.InputProcessor
{
    private final Scene scene;
    private Vector2 mousePosition;
    private int keyCode;
    private int mouseDragX;
    private int mouseDragY;

    private final InputAction exitAction;
    private final InputAction lightEnablingAction;
    private final InputAction gridShowingAction;
    private final InputAction pauseAction;
    private final InputAction scrollAction;
    private final InputAction simulationMenuAction;
    private final InputAction timeStepAction;
    private final InputAction movementAction;
    private final InputAction bodySelectionAction;

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
        movementAction = new MovementAction(scene);
        bodySelectionAction = new BodySelectionAction(scene);
    }

    public void update()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.D))
        {
            MovementAction ma = (MovementAction) movementAction;
            ma.setKeyCode(keyCode);
            ma.setMousePos(mousePosition);
            ma.perform();
        }
    }

    public int getMouseDragX()
    {
        return mouseDragX;
    }

    public int getMouseDragY()
    {
        return mouseDragY;
    }

    public boolean keyDown(int keycode)
    {
        this.keyCode = keycode;
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
            tsa.setDays(tsa.getDays() + 1);
            tsa.perform();
        }

        if(keycode == Input.Keys.DOWN)
        {
            TimeStepAction tsa = (TimeStepAction) timeStepAction;
            tsa.setDays(tsa.getDays() - 1);
            tsa.perform();
        }

        if(keycode == Input.Keys.LEFT)
        {
            BodySelectionAction bsa = (BodySelectionAction) bodySelectionAction;
            bsa.setSelectedBodyIndex(bsa.getSelectedBodyIndex() - 1);
            bsa.perform();
        }

        if(keycode == Input.Keys.RIGHT)
        {
            BodySelectionAction bsa = (BodySelectionAction) bodySelectionAction;
            bsa.setSelectedBodyIndex(bsa.getSelectedBodyIndex() + 1);
            bsa.perform();
        }
        return false;
    }

    public boolean keyTyped(char character)
    {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        mousePosition = new Vector2(screenX, screenY);
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        int dx = (int) (screenX - mousePosition.getX());
        int dy = (int) (screenY - mousePosition.getY());

        dx *= Math.signum(-scene.getZoom()) * Math.pow(Math.abs(scene.getZoom()), 1d / 3) * InputSettings.MOUSE_SENSITIVITY_X;
        dy *= Math.signum(-scene.getZoom()) * Math.pow(Math.abs(scene.getZoom()), 1d / 3) * InputSettings.MOUSE_SENSITIVITY_Y;

        mouseDragX += dx; // yaw
        mouseDragY += dy; // pitch
        //mouseDragY = MathUtils.clamp(mouseDragY, -1500, 0);
        mousePosition = new Vector2(screenX, screenY);

        Vector2 direction = new Vector2(mouseDragX, -mouseDragY);
        direction.clamp(new Vector2(direction.getY(), -89), new Vector2(direction.getY(), 89));

        scene.getCamera().setCameraFront(Math.toRadians(direction.getX()), Math.toRadians(direction.getY()));

        //rotation = rotation.multiply(Matrix3.rotateX(direction.getY())).multiply(Matrix3.rotateY(direction.getX())).multiply(Matrix3.rotateZ(0));
        //Matrix3 rotation = scene.getCameraRotation().multiply(Matrix3.rotateY(direction.getX())).multiply(Matrix3.rotateX(direction.getY()));
       // scene.setCameraRotation(rotation);

        //scene.setDirection(new Vector3(Math.cos(direction.getX()) * Math.cos(direction.getY()), Math.sin(direction.getY()), Math.sin(direction.getX()) * Math.cos(direction.getY())).normalize());

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