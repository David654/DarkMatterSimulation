package core.gui.listelements;

import core.gui.core.GUIComponent;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;

public class ListElement extends JButton implements GUIComponent
{
    private CelestialObject celestialObject;

    private JLabel textLabel;
    private JCheckBox visibilityButton;

    public ListElement(CelestialObject celestialObject)
    {
        this.celestialObject = celestialObject;
        createAndShowGUI();
    }

    public CelestialObject getBody()
    {
        return celestialObject;
    }

    public void setBody(CelestialObject celestialObject)
    {
        this.celestialObject = celestialObject;
    }

    public void createAndShowGUI()
    {
        this.setLayout(new BorderLayout());

        textLabel = new JLabel(celestialObject.getName());
        visibilityButton = new JCheckBox();
        visibilityButton.setSelected(celestialObject.isVisible());
        visibilityButton.setFocusable(false);
        visibilityButton.setToolTipText("Visibility");
        visibilityButton.addItemListener(l ->
        {
            celestialObject.setVisible(!celestialObject.isVisible());
        });

        this.add(textLabel, BorderLayout.CENTER);
        this.add(visibilityButton, BorderLayout.EAST);
    }

    public void hierarchyChanged(HierarchyEvent e) {

    }

    public void componentResized(ComponentEvent e) {

    }

    public void componentMoved(ComponentEvent e) {

    }

    public void componentShown(ComponentEvent e) {

    }

    public void componentHidden(ComponentEvent e) {

    }
}