package gui;

import core.Tester;

import javax.swing.*;

public abstract class GUITester implements Tester
{
    protected int windowWidth = 800;
    protected int windowHeight = 600;
    protected String title;
    protected JFrame window;

    public GUITester(String title)
    {
        this.title = title;
    }

    public void test()
    {
        window = new JFrame(title);
        window.setSize(windowWidth, windowHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        initWindow();
        initComponents();
        window.setVisible(true);
    }

    protected abstract void initWindow();

    protected abstract void initComponents();
}