package core.gui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame
{
    public static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = d.width / 2;
    public static final int HEIGHT = d.height / 2;
    public static final String TITLE = "Dark Matter Simulation";

    public Window()
    {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        initGUI();
    }

    private void initGUI()
    {

    }
}