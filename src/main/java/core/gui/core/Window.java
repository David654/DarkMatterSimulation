package core.gui.core;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import core.graphics.Scene;
import core.graphics.awt.GraphicSettings;
import core.simulation.components.Simulation;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements GUIElement
{
    public static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = d.width / 2;
    public static final int HEIGHT = d.height / 2;
    public static final String TITLE = "Dark Matter Simulation";

    private GLJPanel glPanel;
    private final Simulation simulation;

    public Window()
    {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        simulation = new Simulation();

        initGL();
        initGUI();
    }

    private void initGL()
    {
        GLProfile.initSingleton();
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glPanel = new GLJPanel(glCapabilities);
        glPanel.addGLEventListener(new Scene(simulation));
    }

    public void initGUI()
    {
        glPanel.setPreferredSize(new Dimension(Window.WIDTH * 3 / 4, Window.HEIGHT));
        this.add(glPanel, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel(WIDTH / 4, HEIGHT, simulation);
        this.add(controlPanel, BorderLayout.EAST);
    }

    public void launch()
    {
        this.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glPanel, GraphicSettings.FPS);
        animator.start();
    }
}