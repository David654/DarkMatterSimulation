package launcher;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import core.graphics.awt.GraphicSettings;
import core.graphics.Scene;
import core.gui.Window;

public class DesktopLauncher
{
    private Window window;
    private GLJPanel glPanel;

    public DesktopLauncher()
    {
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
        window = new Window();
        window.add(glPanel);
    }

    public void launch()
    {
        window.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glPanel, GraphicSettings.FPS);
        animator.start();
    }

    public static void main(String[] args)
    {
        FlatDarculaLaf.setup();
        DesktopLauncher launcher = new DesktopLauncher();
        launcher.launch();
    }
}