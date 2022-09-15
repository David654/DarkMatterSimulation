package launcher;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import core.graphics.awt.GraphicSettings;
import core.graphics.Scene;

import javax.swing.*;
import java.awt.*;

public class DesktopLauncher
{
    public static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = d.width / 2;
    public static final int HEIGHT = d.height / 2;
    public static final String TITLE = "Dark Matter Simulation";

    private final JFrame frame;
    private GLJPanel glPanel;

    public DesktopLauncher()
    {
        frame = new JFrame();
        frame.setTitle(TITLE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());

        initGL();
        initGUI();
    }

    private void initGL()
    {
        GLProfile.initSingleton();
        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        glPanel = new GLJPanel(glCapabilities);
        glPanel.addGLEventListener(new Scene());
    }

    private void initGUI()
    {
        frame.add(glPanel);
    }

    public void launch()
    {
        frame.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glPanel, GraphicSettings.FPS);
        animator.start();
    }

    public static void main(String[] args)
    {
        DesktopLauncher launcher = new DesktopLauncher();
        launcher.launch();
    }
}