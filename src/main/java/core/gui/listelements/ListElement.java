package core.gui.listelements;

import com.formdev.flatlaf.FlatClientProperties;
import core.gui.components.GUIComponent;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListElement extends JButton implements GUIComponent, MouseListener
{
    private Table table;
    private CelestialObject celestialObject;

    private JLabel textLabel;
    private JCheckBox visibilityButton;
    private JButton upButton;
    private JButton downButton;

    public ListElement(Table table, CelestialObject celestialObject)
    {
        this.table = table;
        this.celestialObject = celestialObject;
        createAndShowGUI();
        this.addMouseListener(this);
    }

    public CelestialObject getCelestialObject()
    {
        return celestialObject;
    }

    public void setCelestialObject(CelestialObject celestialObject)
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
        visibilityButton.addItemListener(l -> celestialObject.setVisible(!celestialObject.isVisible()));
        upButton = new JButton("↑");
        downButton = new JButton("↓");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(null);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      //  buttonPanel.add(upButton);
       // buttonPanel.add(downButton);
        buttonPanel.add(visibilityButton);

        this.add(textLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.EAST);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        table.setSelectedIndex(table.geIndexOfListElement(this));
        if(e.getClickCount() == 2)
        {
            table.updateListElement(this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}