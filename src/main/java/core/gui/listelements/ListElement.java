package core.gui.listelements;

import core.gui.core.GUIComponent;
import core.simulation.physics.body.Body;

import javax.swing.*;
import java.awt.*;

public class ListElement extends JButton implements GUIComponent
{
    private Body body;

    private JLabel textLabel;
    private JCheckBox visibilityButton;

    public ListElement(Body body)
    {
        this.body = body;
        createAndShowGUI();
    }

    public Body getBody()
    {
        return body;
    }

    public void setBody(Body body)
    {
        this.body = body;
    }

    public void createAndShowGUI()
    {
        this.setLayout(new BorderLayout());

        textLabel = new JLabel(body.getName());
        visibilityButton = new JCheckBox();
        visibilityButton.setSelected(body.isVisible());
        visibilityButton.setFocusable(false);
        visibilityButton.setToolTipText("Visibility");
        visibilityButton.addItemListener(l ->
        {
            body.setVisible(!body.isVisible());
        });

        this.add(textLabel, BorderLayout.CENTER);
        this.add(visibilityButton, BorderLayout.EAST);
    }
}