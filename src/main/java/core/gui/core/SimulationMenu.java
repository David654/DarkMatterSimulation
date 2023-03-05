package core.gui.core;

import core.graphics.core.Scene;
import core.gui.components.GUIComponent;
import core.gui.components.PrimaryButton;
import core.gui.grid.GridRow;
import core.gui.listelements.ListElement;
import core.gui.listelements.Table;
import core.gui.tabbedpane.Tab;
import core.gui.tabbedpane.TabbedPane;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.PhysicsConstants;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.util.Utils;

import javax.swing.*;
import java.awt.*;

public class SimulationMenu extends JFrame implements GUIComponent
{
    private final Simulation simulation;

    private JSplitPane mainPanel;

    private TabbedPane tabbedPane;

    private Table table;

    private JButton addButton;
    private JButton removeButton;
    private JButton applyButton;
    private JButton resetButton;
    private JButton pauseButton;
    private JSlider simulationSpeedSlider;
    private JSpinner simulationSpeedSpinner;
    private JComboBox<String> celestialObjectsCombobox;

    public SimulationMenu(int width, int height, Simulation simulation)
    {
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

    public Table getTable()
    {
        return table;
    }

    public TabbedPane getTabbedPane()
    {
        return tabbedPane;
    }

    public void createAndShowGUI()
    {
        mainPanel = new JSplitPane();
        mainPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        tabbedPane = new TabbedPane(this);

        table = new Table(this);

        for(int i = 0; i < simulation.getStarSystem().getBodyHandler().getSize(); i++)
        {
            CelestialObject celestialObject = simulation.getStarSystem().getBodyHandler().get(i);
            table.addListElement(new ListElement(table, celestialObject));
        }

        //table.setBorder(new TitledBorder("Bodies"));
        mainPanel.setLeftComponent(table);
        mainPanel.setRightComponent(tabbedPane);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(initSimulationPanel(), BorderLayout.SOUTH);
        table.requestFocus();
    }

    private JComponent initSimulationPanel()
    {
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //simulationPanel.setBorder(new TitledBorder("Simulation controls"));

        simulationSpeedSpinner = new JSpinner(new SpinnerNumberModel(PhysicsConstants.DAYS, -365, 365, 0.1));
        simulationSpeedSlider = new JSlider(-3650, 3650, (int) (PhysicsConstants.DAYS * 10));

        simulationSpeedSlider.addChangeListener(e ->
        {
            PhysicsConstants.DAYS = simulationSpeedSlider.getValue() / 10.0;
            simulationSpeedSpinner.setValue(PhysicsConstants.DAYS);
        });

        simulationSpeedSpinner.addChangeListener(e ->
        {
            PhysicsConstants.DAYS = (Double) simulationSpeedSpinner.getValue();
            simulationSpeedSlider.setValue((int) (PhysicsConstants.DAYS * 10));
        });

        GridRow simulationSpeedRow = new GridRow(0, "Simulation speed: ", " days", "The speed of the simulation.");
        simulationSpeedRow.createAndShowGUI(gbc, 0, simulationPanel, simulationSpeedSlider, simulationSpeedSpinner);

        String[] celestialObjectsNames = simulation.getStarSystem().getBodyHandler().getCelestialObjectsNames();
        celestialObjectsCombobox = new JComboBox<>(celestialObjectsNames);
        GridRow celestialObjectsComboboxRow = new GridRow(1, "Camera position: ", "", "Camera position.");
        celestialObjectsComboboxRow.createAndShowGUI(gbc, 0, simulationPanel, celestialObjectsCombobox);

        celestialObjectsCombobox.setSelectedIndex(Scene.SELECTED_BODY_INDEX);
        celestialObjectsCombobox.addActionListener(e -> Scene.SELECTED_BODY_INDEX = celestialObjectsCombobox.getSelectedIndex());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> reset());
        buttonPanel.add(resetButton);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pause());
        buttonPanel.add(pauseButton);

        applyButton = new PrimaryButton("Apply");
        applyButton.addActionListener(e -> apply());
        buttonPanel.add(applyButton);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        simulationPanel.add(buttonPanel, gbc);

        return simulationPanel;
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
        Tab tab = tabbedPane.getSelectedTab();
        CelestialObject celestialObject = tab.getCelestialObject();
        celestialObject.setName(tab.getNameField().getText());

        // Position.
        Vector3 lastPosition = celestialObject.getPositionAU();
        Vector3 position = new Vector3(Utils.parseDouble(lastPosition.getX(), tab.getPositionXField().getText()), Utils.parseDouble(lastPosition.getY(), tab.getPositionYField().getText()),
                Utils.parseDouble(lastPosition.getZ(), tab.getPositionZField().getText()));
        celestialObject.setPosition(position);

        // Dimensions.
        Vector3 lastDimensions = celestialObject.getDimensions();
        Vector3 dimensions = new Vector3(Utils.parseDouble(lastDimensions.getX(), tab.getDimensionsXField().getText()), Utils.parseDouble(lastDimensions.getY(), tab.getDimensionsYField().getText()),
                Utils.parseDouble(lastDimensions.getZ(), tab.getDimensionsZField().getText()));
        celestialObject.setDimensions(dimensions.multiply(1000));

        // Mass.
        celestialObject.setMass(Utils.parseDouble(celestialObject.getMass(), tab.getMassField().getText()));

        // Velocity.
        Vector3 lastVelocity = celestialObject.getVelocity();
        Vector3 velocity = new Vector3(Utils.parseDouble(lastVelocity.getX(), tab.getVelocityXField().getText()), Utils.parseDouble(lastVelocity.getY(), tab.getVelocityYField().getText()),
                Utils.parseDouble(lastVelocity.getZ(), tab.getVelocityZField().getText()));
        celestialObject.setVelocity(velocity.multiply(1000));

        // Rotation speed.
        celestialObject.setRotationSpeed(Utils.parseDouble(celestialObject.getRotationSpeed(), tab.getRotationSpeedField().getText()));

        // Obliquity.
        celestialObject.setObliquity(Utils.parseDouble(celestialObject.getObliquity(), tab.getObliquityField().getText()));

        // Orbital inclination.
        celestialObject.setOrbitalInclination(Utils.parseDouble(celestialObject.getOrbitalInclination(), tab.getOrbitalInclinationField().getText()));

        // Color.
        celestialObject.setColor(tab.getSelectedColor());

        // Texture.
        celestialObject.setTexture(tab.getSelectedTexture());
    }
}