package core.simulation.input.actions;

import com.badlogic.gdx.Gdx;

public class ExitAction implements InputAction
{
    public void perform()
    {
        Gdx.app.exit();
        System.exit(0);
    }
}