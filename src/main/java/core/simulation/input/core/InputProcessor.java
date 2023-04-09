package core.simulation.input.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import core.graphics.core.Scene;
import core.math.vector.Vector2;
import core.settings.core.InputSettings;
import core.simulation.input.actions.*;
import core.util.MathUtils;

public class InputProcessor implements com.badlogic.gdx.InputProcessor
{
    private final Scene scene;
    private Vector2 mousePosition = new Vector2();
    private int keyCode;
    private double mouseDragX;
    private double mouseDragY;
    private boolean clicked = false;

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
        simulationMenuAction = new SimulationMenuAction((int) (Gdx.graphics.getWidth() / 2.5), (int) (Gdx.graphics.getHeight() / 2.5), scene);
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

        if(!clicked)
        {
            mouseDragX += (mousePosition.getX() - mouseDragX) / 10;
            mouseDragY += (mousePosition.getY() - mouseDragY) / 10;
        }
    }

    public double getMouseDragX()
    {
        return mouseDragX;
    }

    public double getMouseDragY()
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
        clicked = true;
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        mousePosition = new Vector2(mouseDragX, mouseDragY);
        clicked = false;
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        //System.out.println(mouseDragX + ", " + mouseDragY);
        //System.out.println(mousePosition.toString());
        //System.out.println("\n\n");

        int dx = (int) (screenX - mousePosition.getX());
        int dy = (int) (screenY - mousePosition.getY());

        double zoom = Math.max(Math.abs(scene.getZoom()), 100);
        dx *= Math.pow(zoom, 1d / 3) * InputSettings.MOUSE_SENSITIVITY_X;
        dy *= Math.pow(zoom, 1d / 3) * InputSettings.MOUSE_SENSITIVITY_Y;

        mouseDragX += dx; // yaw
        mouseDragY += dy; // pitch

        double factor = Math.min(0.4, 0.4 / Math.abs(scene.getCamera().getCameraPosition().getZ()) * 700);
        mouseDragX = MathUtils.lerp(mouseDragX - dx, mouseDragX, factor);
        mouseDragY = MathUtils.lerp(mouseDragY - dy, mouseDragY, factor);

        if(mouseDragX > Gdx.graphics.getWidth())
        {
            mouseDragX -= Gdx.graphics.getWidth();
        }

        if(mouseDragX < 0)
        {
            mouseDragX += Gdx.graphics.getWidth();
        }

        mouseDragY = MathUtils.clamp(mouseDragY, -Gdx.graphics.getHeight() * 0.1, Gdx.graphics.getHeight() * 1.8);
        mousePosition = new Vector2(screenX, screenY);

        Vector2 direction = new Vector2(mouseDragX, -mouseDragY);
        scene.getCamera().setCameraFront(Math.toRadians(direction.getX()), Math.toRadians(direction.getY()));

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