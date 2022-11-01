package core.simulation.input;

import core.gui.core.Window;
import core.simulation.components.Simulation;

import java.awt.event.*;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener
{
    public static final float mouseSensitivityX = 1f;
    public static final float mouseSensitivityY = 1f;
    public static float scaleStep = 0.08f;
    private static final float deltaStep = 0.8f;

    private final Simulation simulation;

    private int mouseMoveX;
    private int mouseMoveY;
    private int lastMouseX;
    private int lastMouseY;

    public MouseInput(Simulation simulation)
    {
        this.simulation = simulation;
    }

    public void mouseDragged(MouseEvent e)
    {
        int mouseX = e.getX();
        int mouseY = e.getY();
        simulation.translate(lastMouseX - mouseX, lastMouseY - mouseY);
        lastMouseX = mouseX;
        lastMouseY = mouseY;
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseMoveX = e.getX();
        mouseMoveY = e.getY();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e)
    {
        lastMouseX = e.getX();
        lastMouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseWheelEvent e)
    {
        simulation.setScale(Math.max(scaleStep / 2, simulation.getScale() + scaleStep * -e.getWheelRotation()));
        //simulation.translate(- mouseMoveX, - mouseMoveY);
    }
}