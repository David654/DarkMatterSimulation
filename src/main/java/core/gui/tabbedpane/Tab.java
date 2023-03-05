package core.gui.tabbedpane;

import com.badlogic.gdx.graphics.Texture;
import core.gui.components.GUIComponent;
import core.gui.core.ColorChooser;
import core.gui.core.TextureChooser;
import core.gui.grid.GridRow;
import core.math.vector.Vector3;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class Tab extends JPanel implements GUIComponent
{
    private final CelestialObject celestialObject;

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
    private JLabel selectedTextureLabel;
    private Texture selectedTexture;

    public Tab(CelestialObject celestialObject)
    {
        this.celestialObject = celestialObject;
        this.setLayout(new BorderLayout());

        createAndShowGUI();
        setData();
    }

    public CelestialObject getCelestialObject()
    {
        return celestialObject;
    }

    public JTextField getNameField()
    {
        return nameField;
    }

    public JTextField getPositionXField()
    {
        return positionXField;
    }

    public JTextField getPositionYField()
    {
        return positionYField;
    }

    public JTextField getPositionZField()
    {
        return positionZField;
    }

    public JTextField getDimensionsXField()
    {
        return dimensionsXField;
    }

    public JTextField getDimensionsYField()
    {
        return dimensionsYField;
    }

    public JTextField getDimensionsZField()
    {
        return dimensionsZField;
    }

    public JTextField getMassField()
    {
        return massField;
    }

    public JTextField getVelocityXField()
    {
        return velocityXField;
    }

    public JTextField getVelocityYField()
    {
        return velocityYField;
    }

    public JTextField getVelocityZField()
    {
        return velocityZField;
    }

    public JTextField getRotationSpeedField()
    {
        return rotationSpeedField;
    }

    public JTextField getObliquityField()
    {
        return obliquityField;
    }

    public JTextField getOrbitalInclinationField()
    {
        return orbitalInclinationField;
    }

    public Color getSelectedColor()
    {
        return selectedColor;
    }

    public Texture getSelectedTexture()
    {
        return selectedTexture;
    }

    public void createAndShowGUI()
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
        selectedColorLabel = new JLabel(celestialObject.getColorString());
        JButton selectColorButton = new JButton("Select Color");
        selectedTextureLabel = new JLabel(celestialObject.getName());
        JButton selectTextureButton = new JButton("Select Texture");

        // Name.
        GridRow nameRow = new GridRow(0, "Name", "", "The name of the celestial object.");
        nameRow.createAndShowGUI(gbc, 0, parameters, nameField);

        // Position.
        GridRow positionRow = new GridRow(1, "Position (x, y, z): ", "AU", "The current position of the celestial object in astronomical units (AU). 1 AU = 1 495 978 700 m");
        positionRow.createAndShowGUI(gbc, 0, parameters, positionXField, positionYField, positionZField);

        // Dimensions.
        GridRow dimensionsRow = new GridRow(2, "Dimensions (x, y, z): ", "km", "The dimension of the celestial objects in kilometres.");
        dimensionsRow.createAndShowGUI(gbc, 0, parameters, dimensionsXField, dimensionsYField, dimensionsZField);

        // Mass.
        GridRow massRow = new GridRow(3, "Mass: ", "kg", "The mass of the celestial object in kilograms.");
        massRow.createAndShowGUI(gbc, 0, parameters, massField);

        // Velocity.
        GridRow velocityRow = new GridRow(4, "Velocity (x, y, z): ", "km/s", "The velocity of the celestial object in kilometers per second.");
        velocityRow.createAndShowGUI(gbc, 0, parameters, velocityXField, velocityYField, velocityZField);

        // Rotation speed.
        GridRow rotationSpeedRow = new GridRow(5, "Rotation speed: ", "km/h", "The speed of the body rotation around its own axis.");
        rotationSpeedRow.createAndShowGUI(gbc, 0, parameters, rotationSpeedField);

        // Obliquity.
        GridRow obliquityRow = new GridRow(6, "Obliquity: ", "degrees", "The angle between the body's rotational axis and its orbital axis.");
        obliquityRow.createAndShowGUI(gbc, 0, parameters, obliquityField);

        // Orbital inclination.
        GridRow orbitalInclinationRow = new GridRow(7, "Orbital inclination: ", "degrees", "The angle at which the body orbit around the Sun is tilted relative to the ecliptic plane.");
        orbitalInclinationRow.createAndShowGUI(gbc, 0, parameters, orbitalInclinationField);

        // Color
        GridRow colorRow = new GridRow(8, "Accent color: ", "", "The color of the celestial object.");
        colorRow.createAndShowGUI(gbc, 0, parameters, selectedColorLabel, selectColorButton);

        GridRow textureRow = new GridRow(9, "Texture: ", "", "The texture of the celestial object.");
        textureRow.createAndShowGUI(gbc, 0, parameters, selectedTextureLabel, selectTextureButton);

        selectColorButton.addActionListener(e ->
        {
            Color color = ColorChooser.showDialog(this, "Color Chooser", celestialObject.getColor());
            if(color != null)
            {
                selectedColor = color;
                selectedColorLabel.setText(Utils.getColorString(selectedColor));
            }
        });

        selectTextureButton.addActionListener(e ->
        {
            Texture texture = TextureChooser.showDialog(this, 1000, 600, 4, BasicCelestialObjects.THUMBNAILS.toArray(new BufferedImage[0]));
            if(texture != null)
            {
                selectedTexture = texture;
                selectedTextureLabel.setText("");
            }
        });

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(parameters);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void setData()
    {
        // Name.
        nameField.setText(celestialObject.getName());

        // Position.
        Vector3 position = celestialObject.getPositionAU();
        positionXField.setText(String.valueOf(position.getX()));
        positionYField.setText(String.valueOf(position.getY()));
        positionZField.setText(String.valueOf(position.getZ()));

        // Dimensions.
        Vector3 dimensions = celestialObject.getDimensions().multiply(0.001);
        dimensionsXField.setText(String.valueOf(dimensions.getX()));
        dimensionsYField.setText(String.valueOf(dimensions.getY()));
        dimensionsZField.setText(String.valueOf(dimensions.getZ()));

        // Mass.
        massField.setText(String.valueOf((celestialObject.getMass())));

        // Velocity.
        Vector3 velocity = celestialObject.getVelocity().multiply(0.001);
        velocityXField.setText(String.valueOf(velocity.getX()));
        velocityYField.setText(String.valueOf(velocity.getY()));
        velocityZField.setText(String.valueOf(velocity.getZ()));

        // Rotation speed.
        rotationSpeedField.setText(String.valueOf(celestialObject.getRotationSpeed() * 3.6));

        // Obliquity.
        obliquityField.setText(String.valueOf(celestialObject.getObliquity()));

        // Orbital inclination.
        orbitalInclinationField.setText(String.valueOf(celestialObject.getOrbitalInclination()));

        // Color.
        selectedColorLabel.setText(celestialObject.getColorString());
        selectedColor = celestialObject.getColor();

        // Texture.
        selectedTextureLabel.setText(celestialObject.getName());
        selectedTexture = celestialObject.getTexture();
    }
}