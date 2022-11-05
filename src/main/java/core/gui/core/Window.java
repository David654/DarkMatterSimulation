package core.gui.core;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import core.graphics.Scene;
import core.graphics.awt.GraphicSettings;
import core.simulation.components.Simulation;
import core.simulation.input.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements GUIComponent, ComponentListener
{
    public static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH = d.width / 2;
    public static int HEIGHT = d.height / 2;
    public static int SCENE_WIDTH = WIDTH * 3 / 4;
    public static final String TITLE = "Dark Matter Simulation";

    private GLJPanel glPanel;
    private final Simulation simulation;
    private final MouseInput mouseInput;

    public Window()
    {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addComponentListener(this);

        simulation = new Simulation();
        mouseInput = new MouseInput(simulation);

        initGL();
        createAndShowGUI();
    }

    private void initGL()
    {
        GLProfile.initSingleton();
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glPanel = new GLJPanel(glCapabilities);
        glPanel.addGLEventListener(new Scene(simulation));
    }

    public void createAndShowGUI()
    {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        glPanel.setPreferredSize(new Dimension(SCENE_WIDTH, Window.HEIGHT));
        glPanel.addMouseListener(mouseInput);
        glPanel.addMouseMotionListener(mouseInput);
        glPanel.addMouseWheelListener(mouseInput);

        ControlPanel controlPanel = new ControlPanel(WIDTH / 4, HEIGHT, simulation);

        splitPane.setLeftComponent(glPanel);
        splitPane.setRightComponent(controlPanel);
        splitPane.addComponentListener(new ComponentListener()
        {
            public void componentResized(ComponentEvent e)
            {
                if(e.getComponent().equals(glPanel))
                {
                    SCENE_WIDTH = e.getComponent().getWidth();
                }
            }

            public void componentMoved(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentHidden(ComponentEvent e) {}
        });

        this.add(splitPane, BorderLayout.CENTER);
        this.pack();
    }

    public void launch()
    {
        this.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glPanel, GraphicSettings.FPS);
        animator.start();
    }

    public void componentResized(ComponentEvent e)
    {
        WIDTH = e.getComponent().getWidth();
        HEIGHT = e.getComponent().getHeight();
        SCENE_WIDTH = WIDTH * 3 / 4;
    }

    public void componentMoved(ComponentEvent e)
    {

    }

    public void componentShown(ComponentEvent e)
    {

    }

    public void componentHidden(ComponentEvent e)
    {

    }
}