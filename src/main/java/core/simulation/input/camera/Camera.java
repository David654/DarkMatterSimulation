package core.simulation.input.camera;

import core.math.matrix.Matrix4;
import core.math.vector.Vector3;

public class Camera
{
    private Vector3 cameraPosition;
    private Vector3 cameraFront;
    private Vector3 cameraUp;
    private double cameraSpeed;

    public Camera()
    {
        this(new Vector3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 1);
    }

    public Camera(Vector3 cameraPosition, Vector3 cameraFront, Vector3 cameraUp, double cameraSpeed)
    {
        this.cameraPosition = cameraPosition;
        this.cameraFront = cameraFront;
        this.cameraUp = cameraUp;
        this.cameraSpeed = cameraSpeed;
    }

    public Vector3 getCameraPosition()
    {
        return cameraPosition;
    }

    public void setCameraPosition(Vector3 cameraPosition)
    {
        this.cameraPosition = cameraPosition;
    }

    public Vector3 getCameraFront()
    {
        return cameraFront;
    }

    public void setCameraFront(Vector3 cameraFront)
    {
        this.cameraFront = cameraFront;
    }

    public void setCameraFront(double yaw, double pitch)
    {
        this.cameraFront = new Vector3(Math.cos(yaw) * Math.cos((pitch)), Math.sin(pitch), Math.sin(yaw) * Math.cos(pitch)).normalize();
    }

    public Vector3 getCameraUp()
    {
        return cameraUp;
    }

    public void setCameraUp(Vector3 cameraUp)
    {
        this.cameraUp = cameraUp;
    }

    public double getCameraSpeed()
    {
        return cameraSpeed;
    }

    public void setCameraSpeed(double cameraSpeed)
    {
        this.cameraSpeed = cameraSpeed;
    }

    public Matrix4 getView()
    {
        Matrix4 view = new Matrix4(new double[][]{
                {cameraPosition.getX(), cameraPosition.getY(), cameraPosition.getZ(), 0},
                {cameraPosition.getX() + cameraFront.getX(), cameraPosition.getY() + cameraFront.getY(), cameraPosition.getZ() + cameraFront.getZ(), 0},
                {cameraUp.getX(), cameraUp.getY(), cameraUp.getZ(), 0},
                {0, 0, 0, 1}
        });

        Matrix4 pos = new Matrix4(new double[][]{
                {1, 0, 0, -cameraPosition.getX()},
                {0, 1, 0, -cameraPosition.getY()},
                {0, 0, 1, -cameraPosition.getZ()},
                {0, 0, 0, 1}
        });
        return view.multiply(pos);
    }
}