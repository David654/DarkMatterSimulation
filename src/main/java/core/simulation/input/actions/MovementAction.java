package core.simulation.input.actions;

import com.badlogic.gdx.Input;
import core.graphics.core.Scene;
import core.math.matrix.Matrix3;
import core.math.vector.Vector2;
import core.math.vector.Vector3;
import core.simulation.input.camera.Camera;

public class MovementAction implements InputAction
{
    private final Scene scene;
    private int keyCode;
    private Vector2 mousePos;

    public MovementAction(Scene scene)
    {
        this.scene = scene;
    }

    public int getKeyCode()
    {
        return keyCode;
    }

    public void setKeyCode(int keyCode)
    {
        this.keyCode = keyCode;
    }

    public Vector2 getMousePos()
    {
        return mousePos;
    }

    public void setMousePos(Vector2 mousePos)
    {
        this.mousePos = mousePos;
    }

    public void perform()
    {
        double angle = Math.atan2(mousePos.getY(), mousePos.getX());
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);

        Camera camera = scene.getCamera();

        if(keyCode == Input.Keys.W)
        {
            Vector3 position = camera.getCameraFront().multiply(camera.getCameraSpeed());
            camera.setCameraPosition(camera.getCameraPosition().add(position)); // .multiply(new Vector3(0, 0, 1))
            //rotation = rotation.lerp(Vector3.zero(), cameraSpeed);
            //scene.setPosition(scene.getPosition().add(new Vector3(cameraSpeed * cos, 0, cameraSpeed * sin)));
            //scene.setPosition(scene.getPosition().add(new Vector3(cameraSpeed * cos, 0, cameraSpeed * sin)));
        }

        if(keyCode == Input.Keys.A)
        {
            Vector3 position = camera.getCameraFront().cross(camera.getCameraUp()).normalize().multiply(-camera.getCameraSpeed());
            camera.setCameraPosition(camera.getCameraPosition().add(position)); // .multiply(new Vector3(-1, 0, 0))
            //scene.setPosition(scene.getPosition().add(new Vector3(-cameraSpeed * sin, 0, cameraSpeed * cos)));
            //scene.setPosition(scene.getPosition().add(new Vector3(-cameraSpeed * sin, 0, cameraSpeed * cos)));
        }

        if(keyCode == Input.Keys.S)
        {
            Vector3 position = camera.getCameraFront().multiply(-camera.getCameraSpeed());
            camera.setCameraPosition(camera.getCameraPosition().add(position)); // .multiply(new Vector3(0, 0, -1))
            //scene.setPosition(scene.getPosition().add(new Vector3(-cameraSpeed * cos, 0, -cameraSpeed * sin)));
            //scene.setPosition(scene.getPosition().add(new Vector3(-cameraSpeed * cos, 0, -cameraSpeed * sin)));
        }

        if(keyCode == Input.Keys.D)
        {
            Vector3 position = camera.getCameraFront().cross(camera.getCameraUp()).normalize().multiply(camera.getCameraSpeed());
            camera.setCameraPosition(camera.getCameraPosition().add(position)); // .multiply(new Vector3(1, 0, 0))
            //scene.setPosition(scene.getPosition().add(new Vector3(cameraSpeed * sin, 0, -cameraSpeed * cos)));
            //scene.setPosition(scene.getPosition().add(new Vector3(cameraSpeed * sin, 0, -cameraSpeed * cos)));
        }
    }
}