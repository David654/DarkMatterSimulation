package core.gui.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import core.assets.icons.Icons;
import core.assets.textures.TextureQuality;
import core.graphics.core.Scene;
import core.gui.components.core.GUIComponent;
import core.gui.components.grid.GridRow;
import core.settings.graphicspresets.HighPreset;
import core.settings.graphicspresets.LowPreset;
import core.settings.graphicspresets.Preset;
import core.util.TextureUtils;

import javax.swing.*;
import java.awt.*;

public class SettingsMenu extends JDialog implements GUIComponent
{
    private final Scene scene;

    private JComboBox<String> presetCombobox;
    private JComboBox<Integer> maxDistanceCombobox;
    private JComboBox<Integer> maxStepsCombobox;
    private JSlider fovSlider;
    private JLabel fovLabel;
    private JComboBox<String> antialiasingCombobox;
    private JComboBox<String> textureQualityCombobox;

    private Preset preset;

    public SettingsMenu(Scene scene, Component component, int width, int height)
    {
        this.scene = scene;
        preset = scene.getPreset();

        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.setLocationRelativeTo(component);
        this.setTitle("Graphics Settings");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setIconImage(Icons.createIcon(Icons.APPLICATION_ICON_PATH).getImage());

        this.setVisible(true);
        createAndShowGUI();

        this.setModal(true);
    }

    public void createAndShowGUI()
    {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] presetValues = new String[] {"Low", "High", "Custom"};
        presetCombobox = new JComboBox<>(presetValues);
        //presetCombobox.addActionListener(e -> setPreset(presetCombobox.getSelectedItem()));

        Integer[] maxDistanceValues = new Integer[] {1024, 2048, 4096, 8192};
        maxDistanceCombobox = new JComboBox<>(maxDistanceValues);
        maxDistanceCombobox.addActionListener(e -> setRenderDistance());

        Integer[] maxStepsValues = new Integer[] {32, 64, 128, 256, 512, 1024};
        maxStepsCombobox = new JComboBox<>(maxStepsValues);
        maxDistanceCombobox.addActionListener(e -> setSteps());

        fovSlider = new JSlider(30, 100, (int) preset.getFOV());
        fovLabel = new JLabel(String.valueOf(fovSlider.getValue()));
        fovSlider.addChangeListener(e -> setFOV());

        String[] antiAliasingValues = new String[] {"On", "Off"};
        antialiasingCombobox = new JComboBox<>(antiAliasingValues);
        antialiasingCombobox.addActionListener(e -> setAntialiasing());

        String[] textureQualityValues = new String[] {"Low", "High"};
        textureQualityCombobox = new JComboBox<>(textureQualityValues);
        textureQualityCombobox.addActionListener(e -> setTextureQuality());

        GridRow presetRow = new GridRow(0, "Graphics preset: ", "Graphics preset.");
        presetRow.createAndShowGUI(gbc, 0, settingsPanel, presetCombobox);

        GridRow vsyncRow = new GridRow(1, "VSync: ", "VSync.");
        vsyncRow.createAndShowGUI(gbc, 0, settingsPanel, antialiasingCombobox);

        GridRow maxDistanceRow = new GridRow(2, "Render distance: ", "Render distance.");
        maxDistanceRow.createAndShowGUI(gbc, 0, settingsPanel, maxDistanceCombobox);

        GridRow maxStepsRow = new GridRow(3, "Steps: ", "Steps.");
        maxStepsRow.createAndShowGUI(gbc, 0, settingsPanel, maxStepsCombobox);

        GridRow fovRow = new GridRow(4, "FOV: ", "degrees",  "Field of view.");
        fovRow.createAndShowGUI(gbc, 0, settingsPanel, fovSlider, fovLabel);

        GridRow antialiasingRow = new GridRow(5, "Antialiasing: ", "Antialiasing.");
        antialiasingRow.createAndShowGUI(gbc, 0, settingsPanel, antialiasingCombobox);

        GridRow textureQualityRow = new GridRow(6, "Texture quality: ", "Texture quality.");
        textureQualityRow.createAndShowGUI(gbc, 0, settingsPanel, textureQualityCombobox);

        //setPreset(scene.getPreset().equals(new LowPreset()) ? "Low" : "High");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        settingsPanel.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(settingsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void comparePresets()
    {
        if(!preset.equals(new LowPreset()) || !preset.equals(new HighPreset()))
        {
            presetCombobox.setSelectedIndex(2);
            scene.setPreset(preset);
        }
    }

    public void setPreset(Object p)
    {
        String preset = (String) p;
        assert preset != null;

        switch(preset)
        {
            case "Low" -> this.preset = new LowPreset();
            case "High" -> this.preset = new HighPreset();
        }

        boolean vsync = this.preset.isVSyncEnabled();
        int maxDistance = (int) this.preset.getMaxDist();
        int maxSteps = this.preset.getMaxSteps();
        int fov = (int) this.preset.getFOV();
        TextureQuality textureQuality = this.preset.getTextureQuality();

        antialiasingCombobox.setSelectedItem(vsync ? "On" : "Off");
        maxDistanceCombobox.setSelectedItem(maxDistance);
        maxStepsCombobox.setSelectedItem(maxSteps);
        fovSlider.setValue(fov);
        textureQualityCombobox.setSelectedItem(textureQuality == TextureQuality.LOW ? "Low" : "High");
    }

    private void setVSync()
    {
        String vsync = (String) antialiasingCombobox.getSelectedItem();
        assert vsync != null;

        switch(vsync)
        {
            case "On" ->
            {
                Gdx.graphics.setVSync(true);
                preset.setVSync(true);
            }
            case "Off" ->
            {
                Gdx.graphics.setVSync(false);
                preset.setVSync(false);
            }
            case "50%" ->
            {
                Graphics.Monitor monitor = Lwjgl3ApplicationConfiguration.getPrimaryMonitor();
                Graphics.DisplayMode display = Lwjgl3ApplicationConfiguration.getDisplayMode(monitor);
                Gdx.graphics.setVSync(false);
                preset.setVSync(false);
                Gdx.graphics.setForegroundFPS(display.refreshRate / 2);
            }
        }

        comparePresets();
    }

    private void setAntialiasing()
    {
        String antialiasing = (String) antialiasingCombobox.getSelectedItem();
        assert antialiasing != null;

        switch(antialiasing)
        {
            case "On" -> preset.setAntialiasing(true);
            case "Off" -> preset.setAntialiasing(false);
        }

        comparePresets();
    }

    private void setRenderDistance()
    {
        Integer distance = (Integer) maxDistanceCombobox.getSelectedItem();
        assert distance != null;
        preset.setMaxDist(distance);
        comparePresets();
    }

    private void setSteps()
    {
        Integer steps = (Integer) maxStepsCombobox.getSelectedItem();
        assert steps != null;
        preset.setMaxSteps(steps);
        comparePresets();
    }

    private void setFOV()
    {
        int fov = fovSlider.getValue();
        fovLabel.setText(String.valueOf(fov));
        preset.setFOV(fov);
        comparePresets();
    }

    private void setTextureQuality()
    {
        String quality = (String) textureQualityCombobox.getSelectedItem();
        assert quality != null;

        switch(quality)
        {
            case "Low" -> preset.setTextureQuality(TextureQuality.LOW);
            case "High" -> preset.setTextureQuality(TextureQuality.HIGH);
        }

        comparePresets();
    }
}