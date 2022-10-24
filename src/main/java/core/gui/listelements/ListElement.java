package core.gui.listelements;

import core.gui.core.GUIElement;
import core.simulation.physics.Body;

import javax.swing.*;
import java.awt.*;

public class ListElement extends JButton implements GUIElement
{
    private Body body;

    private JLabel textLabel;
    private JCheckBox visibilityButton;

    public ListElement(Body body)
    {
        this.body = body;
        initGUI();
    }

    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }

    public boolean isVisible()
    {
        return body.isVisible();
    }

    public void setVisible(boolean visible)
    {
        body.setVisible(true);
        visibilityButton.addActionListener(e -> visibilityButton.setSelected(isVisible()));
        this.repaint();
    }

    public void initGUI()
    {
        this.setLayout(new BorderLayout());

        textLabel = new JLabel(body.getName());
        visibilityButton = new JCheckBox();
        visibilityButton.setSelected(isVisible());
        visibilityButton.setFocusable(false);

        this.add(textLabel, BorderLayout.CENTER);
        this.add(visibilityButton, BorderLayout.EAST);
    }
}