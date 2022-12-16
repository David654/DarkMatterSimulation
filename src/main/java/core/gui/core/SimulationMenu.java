package core.gui.core;

import core.graphics.textures.TextureManager;
import core.gui.listelements.ListElement;
import core.gui.listelements.Table;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.body.Body;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SimulationMenu extends JFrame implements GUIComponent
{
    private final int width;
    private final int height;
    private final Simulation simulation;

    private JSplitPane mainPanel;

    private Table table;
    private JTextField nameField;
    private JTextField massField;
    private JTextField velocityXField;
    private JTextField velocityYField;
    private JTextField velocityZField;
    private JLabel selectedColorLabel;
    private JButton addButton;
    private JButton removeButton;
    private JButton applyButton;
    private JButton resetButton;
    private JButton pauseButton;

    public SimulationMenu(int width, int height, Simulation simulation)
    {
        this.width = width;
        this.height = height;
        this.simulation = simulation;
        this.setSize(new Dimension(width, height));
        this.setTitle("Control Panel");
        this.setLayout(new BorderLayout());

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        mainPanel = new JSplitPane();
        mainPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        table = new Table(simulation);

        for(int i = 0; i < simulation.getBodyHandler().getSize(); i++)
        {
            Body body = simulation.getBodyHandler().get(i);
            table.addListElement(new ListElement(body));
        }

        table.setPreferredSize(new Dimension(width / 2, height));
        table.setBorder(new TitledBorder("Bodies"));
        //this.add(table, BorderLayout.NORTH);
        mainPanel.setTopComponent(table);

        initParametersPanel();

        this.add(mainPanel, BorderLayout.CENTER);
        table.requestFocus();
    }

    private void initParametersPanel()
    {
        JPanel parameters = new JPanel();
        parameters.setLayout(new GridBagLayout());
        parameters.setBorder(new TitledBorder("Body parameters"));
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

        JLabel massLabel2 = new JLabel("kg");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(massLabel2, gbc);

        // velocity x
        JLabel velocityLabel = new JLabel("Velocity (x, y, z): ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(velocityLabel, gbc);

        velocityXField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityXField, gbc);

        velocityYField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityYField, gbc);

        velocityZField = new JTextField();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityZField, gbc);

        JLabel velocityLabel2 = new JLabel("m/s");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(velocityLabel2, gbc);

        // color
        JLabel colorLabel = new JLabel("Color: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(colorLabel, gbc);

        selectedColorLabel = new JLabel(simulation.getBodyHandler().get(table.getSelectedIndex()).getColorString());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 10, 0, 0);
        gbc.weightx = 1;
        parameters.add(selectedColorLabel, gbc);

        JButton selectColorButton = new JButton("Select");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 0, 10);
        gbc.weightx = 1;
        parameters.add(selectColorButton, gbc);

        selectColorButton.addActionListener(e ->
        {
            Color color = ColorChooser.showDialog(this, "Color Chooser", simulation.getBodyHandler().get(table.getSelectedIndex()).getColor());
            if(color != null)
            {
                Body body = simulation.getBodyHandler().get(table.getSelectedIndex());
                body.setColor(color);
                selectedColorLabel.setText(body.getColorString());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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
        applyButton.setBackground(new Color(55, 90, 129));
        applyButton.setFocusable(false);
        applyButton.addActionListener(e -> apply());
        buttonPanel.add(applyButton);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(buttonPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(parameters);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        //this.add(scrollPane, BorderLayout.CENTER);
        mainPanel.setBottomComponent(scrollPane);

        Timer timer = new Timer(1, l ->
        {
            if(table.isListElementClicked())
            {
                Body body = simulation.getBodyHandler().get(table.getSelectedIndex());
                nameField.setText(body.getName());
                massField.setText(String.valueOf((body.getMass())));
                velocityXField.setText(String.valueOf(body.getVelocity().getX()));
                velocityYField.setText(String.valueOf(body.getVelocity().getY()));
                velocityZField.setText(String.valueOf(body.getVelocity().getZ()));
                selectedColorLabel.setText(body.getColorString());
                table.setListElementClicked(false);
            }
        });
        timer.start();


        // simulation controls
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        simulationPanel.setBorder(new TitledBorder("Simulation controls"));

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> reset());
        simulationPanel.add(resetButton);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pause());
        simulationPanel.add(pauseButton);

        this.add(simulationPanel, BorderLayout.SOUTH);
    }

    private void add()
    {
        Body body = new Body(new Vector3(0, 0, 0), 10, 1e24, new Vector3(10f * 1000, 0, 0), 0, 0, "New Body", simulation);
        body.setColor(Color.GRAY);
        body.setTexture(TextureManager.DEFAULT_PLANET_TEXTURE_PATH);
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

    private void reset()
    {

    }

    private void pause()
    {
        simulation.setPaused(!simulation.isPaused());
        pauseButton.setText(simulation.isPaused() ? "Resume" : "Pause");
    }

    private void apply()
    {
        Body body = simulation.getBodyHandler().get(table.getSelectedIndex());
        body.setName(nameField.getText());
        if(parseDouble(massField))
        {
            body.setMass(Double.parseDouble(massField.getText()));
        }

        if(parseDouble(velocityXField))
        {
            Vector3 velocity = body.getVelocity();
            body.setVelocity(new Vector3(Double.parseDouble(velocityXField.getText()), velocity.getY(), velocity.getZ()));
        }

        if(parseDouble(velocityYField))
        {
            Vector3 velocity = body.getVelocity();
            body.setVelocity(new Vector3(velocity.getX(), Double.parseDouble(velocityYField.getText()), velocity.getZ()));
        }
    }

    private boolean parseDouble(JTextField textField)
    {
        try
        {
            double number = Double.parseDouble(textField.getText());
            textField.setForeground(new JTextField().getForeground());
            return true;
        }
        catch(NumberFormatException e)
        {
            textField.setForeground(new Color(199, 84, 80));
        }
        return false;
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

    }
}