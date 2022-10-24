package core.gui.core;

import core.graphics.awt.color.GLColor;
import core.gui.listelements.ListElement;
import core.gui.listelements.Table;
import core.math.vector.Vector2;
import core.simulation.components.Simulation;
import core.simulation.physics.Body;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class ControlPanel extends JPanel implements GUIElement
{
    private final int width;
    private final int height;
    private Simulation simulation;
    private Table table;
    private JTextField nameField;
    private JTextField massField;
    private JTextField velocityXField;
    private JTextField velocityYField;
    private JTextField colorField;
    private JButton addButton;
    private JButton removeButton;
    private JButton applyButton;

    public ControlPanel(int width, int height, Simulation simulation)
    {
        this.width = width;
        this.height = height;
        this.simulation = simulation;
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());

        initGUI();
    }

    public void initGUI()
    {
        table = new Table(simulation);

        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            table.addListElement(new ListElement(body));
        }

        table.setPreferredSize(new Dimension(width, height / 2));
        this.add(table, BorderLayout.NORTH);

        initParametersPanel();
    }

    private void initParametersPanel()
    {
        JPanel parameters = new JPanel();
        parameters.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // name
        JLabel nameLabel = new JLabel("Name: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(nameLabel, gbc);

        nameField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(nameField, gbc);

        // mass
        JLabel massLabel = new JLabel("Mass: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(massLabel, gbc);

        massField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(massField, gbc);

        // velocity x
        JLabel velocityXLabel = new JLabel("Velocity x: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(velocityXLabel, gbc);

        velocityXField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityXField, gbc);

        // velocity y
        JLabel velocityYLabel = new JLabel("Velocity y: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(velocityYLabel, gbc);

        velocityYField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityYField, gbc);

        // color
        JLabel colorLabel = new JLabel("Color: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(colorLabel, gbc);

        colorField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(colorField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // add
        addButton = new JButton("Add");
        addButton.setFocusable(false);
        addButton.addActionListener(e -> add());
        buttonPanel.add(addButton);

        // remove
        removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.addActionListener(e -> remove());
        buttonPanel.add(removeButton);

        // apply
        applyButton = new JButton("Apply");
        applyButton.setBackground(new Color(54, 88, 128));
        applyButton.setFocusable(false);
        buttonPanel.add(applyButton);

        this.add(parameters, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(1, l ->
        {
            if(table.isListElementClicked())
            {
                Body body = simulation.getBodyHandler().get(table.getSelectedIndex());
                nameField.setText(body.getName());
                massField.setText(body.getMass().toEngineeringString());
                velocityXField.setText(String.valueOf(body.getVelocity().getX()));
                velocityYField.setText(String.valueOf(body.getVelocity().getY()));
                colorField.setText(body.getColor().toString());
                table.setListElementClicked(false);
            }
        });
        timer.start();
    }

    private void add()
    {
        Body body = new Body(new Vector2(0, 0), 10, new BigDecimal("1e24"), new Vector2(10f * 1000, 0), GLColor.GRAY, "New Body");
        simulation.getBodyHandler().add(body);
        table.addListElement(new ListElement(body));
        table.setSelectedIndex(simulation.getBodyHandler().getSize() - 1);
        removeButton.setEnabled(true);
    }

    private void remove()
    {
        if(simulation.getBodyHandler().getSize() > 1)
        {
            simulation.getBodyHandler().remove(simulation.getBodyHandler().get(table.getSelectedIndex()));
            table.removeListElement(table.getSelectedIndex());
        }
        removeButton.setEnabled(simulation.getBodyHandler().getSize() > 1);
    }

    private void apply()
    {

    }
}