package core.gui.components.listelements;

import core.assets.icons.Icons;
import core.gui.components.buttons.IconButton;
import core.gui.components.core.GUIComponent;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ListElement extends JButton implements GUIComponent, MouseListener
{
    private Table table;
    private CelestialObject celestialObject;

    private JLabel textLabel;
    private JButton visibilityButton;
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
        visibilityButton = new IconButton(Icons.EYE_ICON_PATH);
        visibilityButton.setFocusable(false);
        visibilityButton.setToolTipText("Visibility");
        visibilityButton.setIcon(celestialObject.isVisible() ? Icons.createIcon(Icons.EYE_ICON_PATH) : Icons.createEmptyIcon());
        visibilityButton.addActionListener(l ->
        {
            celestialObject.setVisible(!celestialObject.isVisible());
            visibilityButton.setIcon(celestialObject.isVisible() ? Icons.createIcon(Icons.EYE_ICON_PATH) : Icons.createEmptyIcon());
        });
        upButton = new IconButton(Icons.UP_ICON_PATH);
        upButton.addActionListener(e ->
        {
            int index = table.getSimulation().getStarSystem().getBodyHandler().indexOf(celestialObject) - 1;
            if(index >= 0)
            {
                table.getSimulation().getStarSystem().getBodyHandler().swapCelestialObjects(celestialObject, table.getSimulation().getStarSystem().getBodyHandler().get(index));
                //table.swapListElements(this, index);
                table.initTable();
            }
        });

        downButton = new IconButton(Icons.DOWN_ICON_PATH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(upButton);
        buttonPanel.add(downButton);
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