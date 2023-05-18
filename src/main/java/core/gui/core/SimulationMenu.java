package core.gui.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import core.assets.icons.Icons;
import core.graphics.core.Scene;
import core.gui.components.buttons.IconButton;
import core.gui.components.core.GUIComponent;
import core.gui.components.buttons.PrimaryButton;
import core.gui.components.grid.GridRow;
import core.gui.components.listelements.Table;
import core.gui.components.tabbedpane.Tab;
import core.gui.components.tabbedpane.TabbedPane;
import core.math.vector.Vector3;
import core.simulation.core.Simulation;
import core.simulation.physics.PhysicsConstants;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.starsystems.StarSystem;
import core.util.Utils;

import javax.swing.*;
import java.awt.*;

public class SimulationMenu extends JFrame implements GUIComponent
{
    private final Scene scene;
    private Simulation simulation;

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
    private JComboBox<String> cameraPositionCombobox;
    private JComboBox<String> starSystemCombobox;

    public SimulationMenu(int width, int height, Scene scene)
    {
        this.scene = scene;
        this.simulation = scene.getSimulation();
        this.setSize(new Dimension(width, height));
        this.setTitle("Simulation Menu");
        this.setLayout(new BorderLayout());
        this.setIconImage(Icons.createIcon(Icons.APPLICATION_ICON_PATH).getImage());

        Timer timer = new Timer(1, e -> this.setTitle("Simulation Menu | FPS: " + Gdx.graphics.getFramesPerSecond()));
        timer.start();

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
        table.initTable();

        //table.setBorder(new TitledBorder("Bodies"));
        mainPanel.setLeftComponent(table);
        mainPanel.setRightComponent(tabbedPane);

        this.add(initButtonPanel(), BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(initSimulationPanel(), BorderLayout.SOUTH);
        table.requestFocus();
    }

    private JComponent initButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton settingsButton = new IconButton(Icons.SETTINGS_ICON_PATH);
        settingsButton.setBackground(null);
        settingsButton.setToolTipText("Settings");
        settingsButton.addActionListener(e -> new SettingsMenu(scene, this, this.getWidth() / 2, this.getHeight() / 2));

        JButton helpButton = new IconButton(Icons.HELP_ICON_PATH);
        helpButton.setBackground(null);
        helpButton.setToolTipText("Help");
        helpButton.addActionListener(e -> new HelpMenu(this, this.getWidth(), this.getHeight()));

        JButton exitButton = new IconButton(Icons.SWITCH_ICON_PATH);
        exitButton.setBackground(null);
        exitButton.setToolTipText("Exit");
        exitButton.addActionListener(e ->
        {
            Gdx.app.exit();
            System.exit(0);
        });

        buttonPanel.add(settingsButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);

        return buttonPanel;
    }

    private JComponent initSimulationPanel()
    {
        JPanel simulationPanel = new JPanel();
        simulationPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //simulationPanel.setBorder(new TitledBorder("Simulation controls"));

        String[] starSystemNames = StarSystem.getStarSystemNames();
        starSystemCombobox = new JComboBox<>(starSystemNames);
        GridRow starSystemComboboxRow = new GridRow(0, "Star system: ", "", "Star system.");
        starSystemComboboxRow.createAndShowGUI(gbc, 0, simulationPanel, starSystemCombobox);

        starSystemCombobox.setSelectedItem(simulation.getStarSystem().getName());
        starSystemCombobox.addActionListener(e ->
        {
            simulation.setPaused(true);
            scene.setZoom(0);
            StarSystem starSystem = StarSystem.STAR_SYSTEMS.get(starSystemCombobox.getSelectedIndex());
            simulation.setStarSystem(starSystem);
            table.initTable();
            tabbedPane.removeAllTabs();
            simulation.setPaused(false);
        });

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

        GridRow simulationSpeedRow = new GridRow(1, "Simulation speed: ", " days", "The speed of the simulation.");
        simulationSpeedRow.createAndShowGUI(gbc, 0, simulationPanel, simulationSpeedSlider, simulationSpeedSpinner);

        String[] celestialObjectsNames = simulation.getStarSystem().getBodyHandler().getCelestialObjectsNames();
        cameraPositionCombobox = new JComboBox<>(celestialObjectsNames);
        GridRow celestialObjectsComboboxRow = new GridRow(2, "Camera position: ", "", "Camera position.");
        celestialObjectsComboboxRow.createAndShowGUI(gbc, 0, simulationPanel, cameraPositionCombobox);

        cameraPositionCombobox.setSelectedIndex(Scene.SELECTED_BODY_INDEX);
        cameraPositionCombobox.addActionListener(e -> Scene.SELECTED_BODY_INDEX = cameraPositionCombobox.getSelectedIndex());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resetButton = new IconButton(Icons.RESET_ICON_PATH);
        resetButton.setBackground(null);
        resetButton.setToolTipText("Reset");
        resetButton.addActionListener(e -> reset());
        buttonPanel.add(resetButton);

        pauseButton = new IconButton(Icons.PAUSE_ICON_PATH);
        pauseButton.setBackground(null);
        pauseButton.addActionListener(e -> pause());
        buttonPanel.add(pauseButton);

        applyButton = new PrimaryButton("Apply");
        applyButton.addActionListener(e ->
        {
            if(tabbedPane.getTabCount() > 0)
            {
                apply();
            }
        });
        buttonPanel.add(applyButton);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 4;
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
        pauseButton.setToolTipText(simulation.isPaused() ? "Resume" : "Pause");
        pauseButton.setIcon(simulation.isPaused() ? Icons.createIcon(Icons.PLAY_ICON_PATH) : Icons.createIcon(Icons.PAUSE_ICON_PATH));
    }

    private synchronized void apply()
    {
        simulation.setPaused(true);
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
        celestialObject.setRotationSpeed(Utils.parseDouble(celestialObject.getRotationSpeed(), tab.getRotationSpeedField().getText()) / 3.6);

        // Obliquity.
        celestialObject.setObliquity(Utils.parseDouble(celestialObject.getObliquity(), tab.getObliquityField().getText()));

        // Orbital inclination.
        celestialObject.setOrbitalInclination(Utils.parseDouble(celestialObject.getOrbitalInclination(), tab.getOrbitalInclinationField().getText()));

        // Color.
        celestialObject.setColor(tab.getSelectedColor());

        // Texture.
        String texturePath = tab.getSelectedTexturePath();
       // scene.getAssetManager().load(texturePath, Texture.class);
        Texture texture = scene.getAssetManager().get(texturePath, Texture.class);
       // texture.bind(2 + simulation.getStarSystem().getBodyHandler().indexOf(celestialObject));
        celestialObject.setTexture(texture);

        // Dark matter.
        celestialObject.getDarkMatter().setDensity((Double) tab.getDarkMatterDensitySpinner().getValue());
        simulation.setPaused(false);
    }
}