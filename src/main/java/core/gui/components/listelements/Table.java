package core.gui.components.listelements;

import core.assets.textures.Textures;
import core.gui.components.core.GUIComponent;
import core.gui.components.buttons.PrimaryButton;
import core.gui.core.SimulationMenu;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.util.ArrayList;

public class Table extends JPanel implements GUIComponent
{
    private final SimulationMenu simulationMenu;

    private JPanel table;
    private JLabel bodiesTotalLabel;
    private JButton addButton;
    private JButton removeButton;
    private JCheckBox showDarkMatterCheckBox;

    private final ArrayList<ListElement> listElements;
    private int selectedIndex = 0;

    public Table(SimulationMenu simulationMenu)
    {
        this.simulationMenu = simulationMenu;
        listElements = new ArrayList<>();
        createAndShowGUI();
    }

    public Simulation getSimulation()
    {
        return simulationMenu.getSimulation();
    }

    public void initTable()
    {
        this.removeAll();
        for(int i = 0; i < simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulationMenu.getSimulation().getStarSystem().getBodyHandler().get(i);
            this.addListElement(new ListElement(this, celestialObject));
        }
        this.setSelectedIndex(0);
        this.revalidate();
        this.repaint();
    }

    public void add()
    {
        CelestialObject celestialObject = new CelestialObject(new Vector3(0, 0, 0), new Vector3(5000), 1e24, new Vector3(10f * 1000, 0, 0), 0, 0, 0, "New Body");
        celestialObject.setColor(Color.GRAY);
        celestialObject.setTexturePath(Textures.DEFAULT_PLANET_TEXTURE_PATH);
        simulationMenu.getSimulation().getStarSystem().getBodyHandler().add(celestialObject);
        addListElement(new ListElement(this, celestialObject));
        selectedIndex = simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize() - 1;
        removeButton.setEnabled(true);
    }

    public void addListElement(ListElement element)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        listElements.add(element);
        bodiesTotalLabel.setText("Total: " + simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize());

        table.removeAll();
        for(int i = 0; i < listElements.size(); i++)
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 1;
            gbc.weighty = 0;
            if(i + 1 == listElements.size())
            {
                gbc.weighty = 1;
            }
            table.add(listElements.get(i), gbc);
        }
        table.repaint();
    }

    public void swapListElements(ListElement listElement, int index)
    {
        ListElement listElement2 = listElements.get(index);
        int index2 = listElements.indexOf(listElement);
        listElements.set(index, listElement);
        listElements.set(index2, listElement2);
    }

    public void updateListElement(ListElement element)
    {
        selectedIndex = listElements.indexOf(element);
        simulationMenu.getTabbedPane().addTab();
    }

    public void remove()
    {
        /*if(simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize() > 1)
        {
            simulationMenu.getSimulation().getStarSystem().getBodyHandler().remove(simulationMenu.getSimulation().getStarSystem().getBodyHandler().get(selectedIndex));
            removeListElement(selectedIndex);
        }
        removeButton.setEnabled(simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize() > 1);**/

        if(simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize() > 0)
        {
            removeButton.setEnabled(true);
            simulationMenu.getSimulation().getStarSystem().getBodyHandler().remove(simulationMenu.getSimulation().getStarSystem().getBodyHandler().get(selectedIndex));
            removeListElement(selectedIndex);

            if(selectedIndex > listElements.size() - 1)
            {
                selectedIndex = listElements.size() - 1;
            }

            for(int i = 0; i < listElements.size(); i++)
            {
                ListElement listElement = listElements.get(i);
                if(i == selectedIndex)
                {
                    listElement.setFocusable(true);
                    break;
                }
            }
        }
        else
        {
            removeButton.setEnabled(false);
        }
    }

    private void removeListElement(int index)
    {
        ListElement element = listElements.get(index);
        GridBagConstraints gbc = new GridBagConstraints();

        int tabIndex = simulationMenu.getTabbedPane().getSelectedIndex();
        if(tabIndex != -1)
        {
            simulationMenu.getTabbedPane().removeTab(tabIndex);
        }

        listElements.remove(element);
        bodiesTotalLabel.setText("Total: " + simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize());

        table.remove(element);
        for(int i = 0; i < listElements.size(); i++)
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 1;
            gbc.weighty = 0;
            if(i + 1 == listElements.size())
            {
                gbc.weighty = 1;
            }

            ListElement listElement = listElements.get(i);
            table.add(listElement, gbc);
        }
        table.repaint();
    }

    public void removeAll()
    {
        listElements.clear();
        table.removeAll();
        table.repaint();
    }

    public ListElement getListElement(int index)
    {
        return listElements.get(index);
    }

    public int geIndexOfListElement(ListElement listElement)
    {
        return listElements.indexOf(listElement);
    }

    public int getSelectedIndex()
    {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex)
    {
        this.selectedIndex = selectedIndex;
        listElements.get(selectedIndex).requestFocus();
    }

    public void createAndShowGUI()
    {
        table = new JPanel();
        table.setLayout(new GridBagLayout());
        selectedIndex = 0;

        this.setLayout(new BorderLayout());
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.trackArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.trackInsets", new Insets(999, 999, 999, 999));
        UIManager.put("ScrollPane.smoothScrolling", true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane, BorderLayout.CENTER);

        bodiesTotalLabel = new JLabel("Total: " + simulationMenu.getSimulation().getStarSystem().getBodyHandler().getSize());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(bodiesTotalLabel);

        addButton = new PrimaryButton("Add");
        addButton.addActionListener(e -> add());
        buttonPanel.add(addButton);

        removeButton = new JButton("Delete");
        removeButton.setFocusable(false);
        removeButton.addActionListener(e -> remove());
        buttonPanel.add(removeButton);

        JPanel darkMatterPanel = new JPanel();
        darkMatterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        darkMatterPanel.setToolTipText("Dark matter visualisation. May cause a significant FPS loss especially when used with large amount of objects.");
        showDarkMatterCheckBox = new JCheckBox();
        showDarkMatterCheckBox.setSelected(simulationMenu.getSimulation().isDarkMatterVisible());
        showDarkMatterCheckBox.addItemListener(l -> simulationMenu.getSimulation().setDarkMatterVisible(!simulationMenu.getSimulation().isDarkMatterVisible()));
        darkMatterPanel.add(new JLabel("Show dark matter: "));
        darkMatterPanel.add(showDarkMatterCheckBox);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(darkMatterPanel, BorderLayout.SOUTH);

        this.add(panel, BorderLayout.SOUTH);
    }
}