package core.gui.listelements;

import core.gui.core.GUIElement;
import core.simulation.components.Simulation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Table extends JPanel implements GUIElement
{
    private final Simulation simulation;
    private JPanel table;
    private JLabel bodiesTotalLabel;
    private final ArrayList<ListElement> listElements;
    private int selectedIndex = 0;
    private boolean isListElementClicked = false;

    public Table(Simulation simulation)
    {
        this.simulation = simulation;
        listElements = new ArrayList<>();
        initGUI();
    }

    public void addListElement(ListElement element)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        listElements.add(element);
        element.addActionListener(e ->
        {
            selectedIndex = listElements.indexOf(element);
            isListElementClicked = true;
        });
        bodiesTotalLabel.setText("Total: " + simulation.getBodyHandler().getSize());

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

    public void removeListElement(int index)
    {
        ListElement element = listElements.get(index);
        GridBagConstraints gbc = new GridBagConstraints();
        listElements.remove(element);
        bodiesTotalLabel.setText("Total: " + simulation.getBodyHandler().getSize());

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
            table.add(listElements.get(i), gbc);
        }
        table.repaint();
    }

    public ListElement getListElement(int index)
    {
        return listElements.get(index);
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

    public boolean isListElementClicked()
    {
        return isListElementClicked;
    }

    public void setListElementClicked(boolean listElementClicked)
    {
        isListElementClicked = listElementClicked;
    }

    public void initGUI()
    {
        table = new JPanel();
        table.setLayout(new GridBagLayout());

        this.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        this.add(scrollPane, BorderLayout.CENTER);

        bodiesTotalLabel = new JLabel("Total: " + simulation.getBodyHandler().getSize());
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(bodiesTotalLabel);
        this.add(panel, BorderLayout.SOUTH);
    }
}