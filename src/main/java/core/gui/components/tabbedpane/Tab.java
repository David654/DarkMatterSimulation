package core.gui.components.tabbedpane;

import core.gui.components.core.GUIComponent;
import core.gui.core.ColorChooser;
import core.gui.core.TextureChooser;
import core.gui.components.grid.GridRow;
import core.math.vector.Vector3;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.simulation.physics.celestialobjects.Star;
import core.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public final class Tab extends JPanel implements GUIComponent
{
    private CelestialObject celestialObject;

    private JComboBox<String> celestialObjectCombobox;
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
    private JSlider absoluteMagnitudeSlider;
    private JSpinner absoluteMagnitudeSpinner;
    private JLabel selectedColorLabel;
    private Color selectedColor;
    private JLabel selectedTextureLabel;
    private String selectedTexturePath;
    private JCheckBox ringCheckBox;
    private JTextField innerRingRadiusField;
    private JTextField outerRingRadiusField;
    private JSlider darkMatterDensitySlider;
    private JSpinner darkMatterDensitySpinner;
    private JLabel totalMassLabel;

    public Tab(CelestialObject celestialObject)
    {
        this.celestialObject = celestialObject;
        this.setLayout(new BorderLayout());

        createAndShowGUI();
        setData(celestialObject);
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

    public String getSelectedTexturePath()
    {
        return selectedTexturePath;
    }

    public JTextField getInnerRingRadiusField()
    {
        return innerRingRadiusField;
    }

    public JTextField getOuterRingRadiusField()
    {
        return outerRingRadiusField;
    }

    public JCheckBox getRingCheckBox()
    {
        return ringCheckBox;
    }

    public JSpinner getDarkMatterDensitySpinner()
    {
        return darkMatterDensitySpinner;
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
        ringCheckBox = new JCheckBox();
        innerRingRadiusField = new JTextField();
        outerRingRadiusField = new JTextField();

        // Celestial object.
        String[] celestialObjectsNames = new String[BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.size()];
        for(int i = 0; i < celestialObjectsNames.length; i++)
        {
            celestialObjectsNames[i] = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i).getName();
        }
        celestialObjectCombobox = new JComboBox<>(celestialObjectsNames);
        GridRow celestialObjectsComboboxRow = new GridRow(0, "Celestial object: ", "", "Choose from predefined celestial objects.");
        celestialObjectsComboboxRow.createAndShowGUI(gbc, 0, parameters, celestialObjectCombobox);

        celestialObjectCombobox.setSelectedIndex(BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.indexOf(celestialObject));
        celestialObjectCombobox.addActionListener(e ->
        {
            Vector3 position = celestialObject.getPositionAU();
            setData(BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(celestialObjectCombobox.getSelectedIndex()));
            celestialObject.setPosition(position);
        });

        // Info.
        GridRow infoRow = new GridRow(1, "", "", "");
        infoRow.createAndShowGUI(gbc, 0, parameters, new JLabel(), new JLabel("Or set your own parameters."));

        // Name.
        GridRow nameRow = new GridRow(2, "Name", "", "The name of the celestial object.");
        nameRow.createAndShowGUI(gbc, 0, parameters, nameField);

        // Position.
        GridRow positionRow = new GridRow(3, "Position (x, y, z): ", "AU", "The current position of the celestial object in astronomical units (AU). 1 AU = 1 495 978 700 m");
        positionRow.createAndShowGUI(gbc, 0, parameters, positionXField, positionYField, positionZField);

        // Dimensions.
        GridRow dimensionsRow = new GridRow(4, "Dimensions (x, y, z): ", "km", "The dimension of the celestial objects in kilometres.");
        dimensionsRow.createAndShowGUI(gbc, 0, parameters, dimensionsXField, dimensionsYField, dimensionsZField);

        // Mass.
        GridRow massRow = new GridRow(5, "Mass: ", "kg", "The mass of the celestial object in kilograms.");
        massRow.createAndShowGUI(gbc, 0, parameters, massField);

        // Velocity.
        GridRow velocityRow = new GridRow(6, "Velocity (x, y, z): ", "km/s", "The velocity of the celestial object in kilometers per second.");
        velocityRow.createAndShowGUI(gbc, 0, parameters, velocityXField, velocityYField, velocityZField);

        // Rotation speed.
        GridRow rotationSpeedRow = new GridRow(7, "Rotation speed: ", "km/h", "The speed of the body rotation around its own axis.");
        rotationSpeedRow.createAndShowGUI(gbc, 0, parameters, rotationSpeedField);

        // Obliquity.
        GridRow obliquityRow = new GridRow(8, "Obliquity: ", "degrees", "The angle between the body's rotational axis and its orbital axis.");
        obliquityRow.createAndShowGUI(gbc, 0, parameters, obliquityField);

        // Orbital inclination.
        GridRow orbitalInclinationRow = new GridRow(9, "Orbital inclination: ", "degrees", "The angle at which the body orbit around the Sun is tilted relative to the ecliptic plane.");
        orbitalInclinationRow.createAndShowGUI(gbc, 0, parameters, orbitalInclinationField);

        int gridYIncrement = 0;

        if(celestialObject instanceof Star star)
        {
            gridYIncrement = 1;
            absoluteMagnitudeSlider = new JSlider(0, 2000, (int) (star.getApparentMagnitude() * 100));
            absoluteMagnitudeSpinner = new JSpinner(new SpinnerNumberModel(star.getApparentMagnitude(), 0, 20, 0.01));

            absoluteMagnitudeSlider.addChangeListener(e ->
            {
                double absoluteMagnitude = absoluteMagnitudeSlider.getValue() / 100.0;
                star.setApparentMagnitude(absoluteMagnitude);
                absoluteMagnitudeSpinner.setValue(absoluteMagnitude);
            });

            absoluteMagnitudeSpinner.addChangeListener(e ->
            {
                double absoluteMagnitude = (double) absoluteMagnitudeSpinner.getValue();
                star.setApparentMagnitude(absoluteMagnitude);
                absoluteMagnitudeSlider.setValue((int) (absoluteMagnitude * 100));
            });

            // Apparent magnitude.
            GridRow absoluteMagnitudeRow = new GridRow(10, "Absolute magnitude: ", "A measure of the luminosity of a celestial object on an inverse logarithmic astronomical magnitude scale");
            absoluteMagnitudeRow.createAndShowGUI(gbc, 0, parameters, absoluteMagnitudeSlider, absoluteMagnitudeSpinner, new JLabel());
        }

        // Color.
        GridRow colorRow = new GridRow(10 + gridYIncrement, "Accent color: ", "", "The color of the celestial object.");
        colorRow.createAndShowGUI(gbc, 0, parameters, selectedColorLabel, selectColorButton);

        // Texture.
        GridRow textureRow = new GridRow(11 + gridYIncrement, "Texture: ", "", "The texture of the celestial object.");
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
            String texturePath = TextureChooser.showDialog(this, 1000, 600, 4, BasicCelestialObjects.THUMBNAILS.toArray(new BufferedImage[0]));
            if(texturePath != null)
            {
                selectedTexturePath = texturePath;
                selectedTextureLabel.setText("");
            }
        });

        // Rings.
        GridRow ringRow = new GridRow(12, "Rings: ", "", "The rings of the celestial object.");
        ringRow.createAndShowGUI(gbc, 0, parameters, ringCheckBox, innerRingRadiusField, outerRingRadiusField);

        // Dark matter.
        darkMatterDensitySpinner = new JSpinner(new SpinnerNumberModel(celestialObject.getDarkMatter().getDensity(), 0, 1000, 0.001));
        darkMatterDensitySlider = new JSlider(0, 1000000, (int) (celestialObject.getDarkMatter().getDensity() * 1000));

        darkMatterDensitySlider.addChangeListener(e ->
        {
            double density = darkMatterDensitySlider.getValue() / 1000.0;
            celestialObject.getDarkMatter().setDensity(density);
            darkMatterDensitySpinner.setValue(density);
            totalMassLabel.setText(String.valueOf(celestialObject.getTotalMass()));
        });

        darkMatterDensitySpinner.addChangeListener(e ->
        {
            double density = (double) darkMatterDensitySpinner.getValue();
            celestialObject.getDarkMatter().setDensity(density);
            darkMatterDensitySlider.setValue((int) (density * 1000));
            totalMassLabel.setText(String.valueOf(celestialObject.getTotalMass()));
        });

        GridRow darkMatterDensityRow = new GridRow(13, "Dark matter density: ", "GeV / cm³", "Dark matter density of energy.");
        darkMatterDensityRow.createAndShowGUI(gbc, 0, parameters, darkMatterDensitySlider, darkMatterDensitySpinner, new JLabel());

        // Total mass.
        totalMassLabel = new JLabel("                  ");
        GridRow totalMassRow = new GridRow(14, "Total mass: ", "kg", "Total mass of the celestial object which includes its own mass and dark matter mass.");
        totalMassRow.createAndShowGUI(gbc, 0, parameters, totalMassLabel);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 5;
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;
        parameters.add(new JPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(parameters);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void setData(CelestialObject celestialObject)
    {
        DecimalFormat df = new DecimalFormat("#.###");

        // Name.
        nameField.setText(celestialObject.getName());

        // Position.
        Vector3 position = celestialObject.getPositionAU();
        positionXField.setText(df.format(position.getX()));
        positionYField.setText(df.format(position.getY()));
        positionZField.setText(df.format(position.getZ()));

        // Dimensions.
        Vector3 dimensions = celestialObject.getDimensions().multiply(0.001);
        dimensionsXField.setText(df.format(dimensions.getX()));
        dimensionsYField.setText(df.format(dimensions.getY()));
        dimensionsZField.setText(df.format(dimensions.getZ()));

        // Mass.
        massField.setText(String.valueOf((celestialObject.getMass())));

        // Velocity.
        Vector3 velocity = celestialObject.getVelocity().multiply(0.001);
        velocityXField.setText(df.format(velocity.getX()));
        velocityYField.setText(df.format(velocity.getY()));
        velocityZField.setText(df.format(velocity.getZ()));

        // Rotation speed.
        rotationSpeedField.setText(df.format(celestialObject.getRotationSpeed() * 3.6));

        // Obliquity.
        obliquityField.setText(df.format(celestialObject.getObliquity()));

        // Orbital inclination.
        orbitalInclinationField.setText(df.format(celestialObject.getOrbitalInclination()));

        // Color.
        selectedColorLabel.setText(celestialObject.getColorString());
        selectedColor = celestialObject.getColor();

        // Texture.
        selectedTextureLabel.setText(celestialObject.getName());
        selectedTexturePath = celestialObject.getTexturePath();

        // Dark matter.
        double density = celestialObject.getDarkMatter() == null ? this.celestialObject.getDarkMatter().getDensity() : celestialObject.getDarkMatter().getDensity();
        darkMatterDensitySlider.setValue((int) density * 1000);
        darkMatterDensitySpinner.setValue(density);

        // Total mass.
        totalMassLabel.setText(df.format(celestialObject.getDarkMatter() == null ? this.celestialObject.getTotalMass() : celestialObject.getTotalMass()));
    }
}