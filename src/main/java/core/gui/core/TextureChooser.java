package core.gui.core;

import core.assets.icons.Icons;
import core.gui.components.core.GUIComponent;
import core.gui.components.buttons.PrimaryButton;
import core.gui.components.grid.TextureGridElement;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.celestialobjects.CelestialObject;
import core.util.TextureUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TextureChooser extends JDialog implements GUIComponent
{
    private final int columns;
    private final BufferedImage[] images;

    private static boolean isSelectButtonPressed = false;
    private static int selectedIndex = 0;

    private TextureChooser(Component component, int width, int height, int columns, BufferedImage[] images)
    {
        this.columns = columns;
        this.images = images;

        this.setSize(new Dimension(width, height));
        this.setResizable(false);
        this.setLocationRelativeTo(component);
        this.setTitle("Texture Chooser");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setIconImage(TextureUtils.readImage(Icons.APPLICATION_ICON_PATH));

        createAndShowGUI();

        this.setModal(true);
        this.setVisible(true);
    }

    public void setSelectedIndex(int selectedIndex)
    {
        TextureChooser.selectedIndex = selectedIndex;
    }

    public void createAndShowGUI()
    {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(images.length / columns + (images.length % columns == 0 ? 0 : 1), columns));

        for(int i = 0; i < images.length; i++)
        {
            CelestialObject celestialObject = BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.get(i);
            TextureGridElement element = new TextureGridElement(this, images[i], celestialObject);
            gridPanel.add(element);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton selectButton = new PrimaryButton("Select");
        selectButton.addActionListener(e -> select());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> this.dispose());

        buttonPanel.add(selectButton);
        buttonPanel.add(cancelButton);

        this.add(scrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void select()
    {
        isSelectButtonPressed = true;
        this.dispose();
    }

    public static String showDialog(Component component, int width, int height, int columns, BufferedImage[] images)
    {
        new TextureChooser(component, width, height, columns, images);

        if(isSelectButtonPressed)
        {
            return BasicCelestialObjects.TEXTURE_PATHS.get(selectedIndex);
        }

        return null;
    }
}