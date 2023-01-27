package core.gui.core;

import core.gui.components.GUIComponent;
import core.gui.grid.GridRow;
import core.gui.listelements.ListElement;
import core.gui.listelements.Table;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.util.Utils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.util.function.IntConsumer;

public class SimulationMenu extends JFrame implements GUIComponent
{
    private final int width;
    private final int height;
    private final Simulation simulation;

    private JSplitPane mainPanel;

    private JTabbedPane tabbedPane;

    private Table table;
    private JTextField nameField;
    private JTextField positionXField;
    private JTextField positionYField;
    private JTextField positionZField;
    private JTextField dimensionsXField;
    private JTextField dimensionsYField;
    private JTextField dimensionsZField;
    private JTextField massField;
    private JTextField velocityXField;
    private JTextField velocityYField;
    private JTextField velocityZField;
    private JTextField rotationSpeedField;
    private JTextField obliquityField;
    private JTextField orbitalInclinationField;
    private JLabel selectedColorLabel;
    private Color selectedColor;
    private JButton selectColorButton;
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
        this.setTitle("Simulation Menu");
        this.setLayout(new BorderLayout());

        createAndShowGUI();
    }

    public Simulation getSimulation()
    {
        return simulation;
    }

    public void createAndShowGUI()
    {
        mainPanel = new JSplitPane();
        mainPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        tabbedPane = new JTabbedPane();
        tabbedPane.putClientProperty("JTabbedPane.tabClosable", true);
        tabbedPane.putClientProperty("JTabbedPane.tabCloseCallback", (IntConsumer) this::removeTab);
        tabbedPane.putClientProperty("JTabbedPane.scrollButtonsPlacement", "both");
        tabbedPane.putClientProperty("JTabbedPane.tabCloseToolTipText", "Close");
        UIManager.put( "TabbedPane.showTabSeparators", true);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        table = new Table(this);

        for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(i);
            table.addListElement(new ListElement(table, celestialObject));
        }

        table.setPreferredSize(new Dimension(width / 2, height));
        table.setBorder(new TitledBorder("Bodies"));
        //this.add(table, BorderLayout.NORTH);
        mainPanel.setTopComponent(table);
        mainPanel.setBottomComponent(tabbedPane);

        this.add(mainPanel, BorderLayout.CENTER);
        table.requestFocus();
    }

    private JScrollPane initParametersPanel()
    {
        JPanel parameters = new JPanel();
        parameters.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        nameField = new JTextField();
        positionXField = new JTextField();
        positionYField = new JTextField();
        positionZField = new JTextField();
        dimensionsXField = new JTextField();
        dimensionsYField = new JTextField();
        dimensionsZField = new JTextField();
        massField = new JTextField();
        velocityXField = new JTextField();
        velocityYField = new JTextField();
        velocityZField = new JTextField();
        rotationSpeedField = new JTextField();
        obliquityField = new JTextField();
        orbitalInclinationField = new JTextField();
        selectedColorLabel = new JLabel(simulation.getStarSystem().getBodyHandler().get(table.getSelectedIndex()).getColorString());
        selectColorButton = new JButton("Select");

        // Name.
        GridRow nameRow = new GridRow(0, "Name");
        nameRow.createAndShowGUI(gbc, 0, parameters, nameField);

        // Position.
        GridRow positionRow = new GridRow(1, "Position (x, y, z): ", "AU");
        positionRow.createAndShowGUI(gbc, 0, parameters, positionXField, positionYField, positionZField);

        // Dimensions.
        GridRow dimensionsRow = new GridRow(2, "Dimensions (x, y, z): ", "km");
        dimensionsRow.createAndShowGUI(gbc, 0, parameters, dimensionsXField, dimensionsYField, dimensionsZField);

        // Mass.
        GridRow massRow = new GridRow(3, "Mass: ", "kg");
        massRow.createAndShowGUI(gbc, 0, parameters, massField);

        // Velocity.
        GridRow velocityRow = new GridRow(4, "Velocity (x, y, z): ", "km/s");
        velocityRow.createAndShowGUI(gbc, 0, parameters, velocityXField, velocityYField, velocityZField);

        // Rotation speed.
        GridRow rotationSpeedRow = new GridRow(5, "Rotation speed: ", "km/h");
        rotationSpeedRow.createAndShowGUI(gbc, 0, parameters, rotationSpeedField);

        // Obliquity.
        GridRow obliquityRow = new GridRow(6, "Obliquity: ", "degrees");
        obliquityRow.createAndShowGUI(gbc, 0, parameters, obliquityField);

        // Orbital inclination.
        GridRow orbitalInclinationRow = new GridRow(7, "Orbital inclination: ", "degrees");
        orbitalInclinationRow.createAndShowGUI(gbc, 0, parameters, orbitalInclinationField);

        // Color
        GridRow colorRow = new GridRow(8, "Color: ");
        colorRow.createAndShowGUI(gbc, 0, parameters, selectedColorLabel, selectColorButton);

        selectColorButton.addActionListener(e ->
        {
            Color color = ColorChooser.showDialog(this, "Color Chooser", simulation.getStarSystem().getBodyHandler().get(table.getSelectedIndex()).getColor());
            if(color != null)
            {
                selectedColor = color;
                selectedColorLabel.setText(Utils.getColorString(selectedColor));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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
        gbc.gridy = 9;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(buttonPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(parameters);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        //this.add(scrollPane, BorderLayout.CENTER);
        //mainPanel.setBottomComponent(scrollPane);


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

        //this.add(simulationPanel, BorderLayout.SOUTH);

        return scrollPane;
    }

    public void addTab()
    {
        CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(table.getSelectedIndex());

        boolean contains = false;
        for(int i = 0; i < tabbedPane.getTabCount(); i++)
        {
            if(tabbedPane.getTitleAt(i).equals(celestialObject.getName()))
            {
                contains = true;
                break;
            }
        }

        if(!contains)
        {
            tabbedPane.addTab(celestialObject.getName(), initParametersPanel());
            updateData();
            tabbedPane.revalidate();
            tabbedPane.repaint();
        }

        for(int i = 0; i < tabbedPane.getTabCount(); i++)
        {
            if(tabbedPane.getTitleAt(i).equals(celestialObject.getName()))
            {
                tabbedPane.setSelectedIndex(i);
                break;
            }
        }
    }

    public int getIndexOfTab(String title)
    {
        for(int i = 0; i < tabbedPane.getTabCount(); i++)
        {
            if(tabbedPane.getTitleAt(i).equals(title))
            {
                return i;
            }
        }
        return -1;
    }

    public void removeTab(int index)
    {
        if(tabbedPane.getTabCount() > 0)
        {
            tabbedPane.remove(index);
            tabbedPane.revalidate();
            tabbedPane.repaint();
        }
    }

    public void updateData()
    {
        CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(table.getSelectedIndex());

        // Name.
        nameField.setText(celestialObject.getName());

        // Position.
        Vector3 position = celestialObject.getPosition();
        positionXField.setText(String.valueOf(position.getX()));
        positionYField.setText(String.valueOf(position.getY()));
        positionZField.setText(String.valueOf(position.getZ()));

        // Dimensions.
        Vector3 dimensions = celestialObject.getDimensions();
        dimensionsXField.setText(String.valueOf(dimensions.getX()));
        dimensionsYField.setText(String.valueOf(dimensions.getY()));
        dimensionsZField.setText(String.valueOf(dimensions.getZ()));

        // Mass.
        massField.setText(String.valueOf((celestialObject.getMass())));

        // Velocity.
        Vector3 velocity = celestialObject.getVelocity();
        velocityXField.setText(String.valueOf(velocity.getX()));
        velocityYField.setText(String.valueOf(velocity.getY()));
        velocityZField.setText(String.valueOf(velocity.getZ()));

        // Rotation speed.
        rotationSpeedField.setText(String.valueOf(celestialObject.getRotationSpeed()));

        // Obliquity.
        obliquityField.setText(String.valueOf(celestialObject.getObliquity()));

        // Orbital inclination.
        orbitalInclinationField.setText(String.valueOf(celestialObject.getOrbitalInclination()));

        // Color.
        selectedColorLabel.setText(celestialObject.getColorString());
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
        CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(table.getSelectedIndex());
        celestialObject.setName(nameField.getText());

        // Position.
        Vector3 lastPosition = celestialObject.getPosition();
        Vector3 position = new Vector3(Utils.parseDouble(lastPosition.getX(), positionXField.getText()), Utils.parseDouble(lastPosition.getY(), positionYField.getText()),
                Utils.parseDouble(lastPosition.getZ(), positionZField.getText()));
        celestialObject.setPosition(position);

        // Dimensions.
        Vector3 lastDimensions = celestialObject.getDimensions();
        Vector3 dimensions = new Vector3(Utils.parseDouble(lastDimensions.getX(), dimensionsXField.getText()), Utils.parseDouble(lastDimensions.getY(), dimensionsYField.getText()),
                Utils.parseDouble(lastDimensions.getZ(), dimensionsZField.getText()));
        celestialObject.setDimensions(dimensions);

        // Mass.
        celestialObject.setMass(Utils.parseDouble(celestialObject.getMass(), massField.getText()));

        // Velocity.
        Vector3 lastVelocity = celestialObject.getVelocity();
        Vector3 velocity = new Vector3(Utils.parseDouble(lastVelocity.getX(), velocityXField.getText()), Utils.parseDouble(lastVelocity.getY(), velocityYField.getText()),
                Utils.parseDouble(lastVelocity.getZ(), velocityZField.getText()));
        celestialObject.setVelocity(velocity);

        // Rotation speed.
        celestialObject.setRotationSpeed(Utils.parseDouble(celestialObject.getRotationSpeed(), rotationSpeedField.getText()));

        // Obliquity.
        celestialObject.setObliquity(Utils.parseDouble(celestialObject.getObliquity(), obliquityField.getText()));

        // Orbital inclination.
        celestialObject.setOrbitalInclination(Utils.parseDouble(celestialObject.getOrbitalInclination(), orbitalInclinationField.getText()));

        // Color.
        if(selectedColor == null)
        {
            selectedColor = celestialObject.getColor();
        }
        celestialObject.setColor(selectedColor);
        selectedColor = null;
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

    public void componentResized(ComponentEvent e) {

    }

    public void componentMoved(ComponentEvent e) {

    }

    public void componentShown(ComponentEvent e) {

    }

    public void componentHidden(ComponentEvent e)
    {

    }
}