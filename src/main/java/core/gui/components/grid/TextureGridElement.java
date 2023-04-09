package core.gui.components.grid;

import core.gui.components.core.GUIComponent;
import core.gui.core.TextureChooser;
import core.simulation.core.BasicCelestialObjects;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class TextureGridElement extends JButton implements GUIComponent, MouseListener
{
    private final TextureChooser textureChooser;
    private final BufferedImage image;
    private final CelestialObject celestialObject;

    public TextureGridElement(TextureChooser textureChooser, BufferedImage image, CelestialObject celestialObject)
    {
        this.textureChooser = textureChooser;
        this.image = image;
        this.celestialObject = celestialObject;

        this.setLayout(new BorderLayout());
        this.addMouseListener(this);

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        JPanel imagePanel = new ImagePanel(image);

        JLabel textLabel = new JLabel(celestialObject.getName(), SwingConstants.CENTER);

        this.add(imagePanel, BorderLayout.CENTER);
        this.add(textLabel, BorderLayout.SOUTH);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        textureChooser.setSelectedIndex(BasicCelestialObjects.BASIC_CELESTIAL_OBJECTS.indexOf(celestialObject));
        if(e.getClickCount() == 2)
        {
            textureChooser.select();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private static class ImagePanel extends JPanel
    {
        private final BufferedImage image;

        public ImagePanel(BufferedImage image)
        {
            this.image = image;
            this.setPreferredSize(new Dimension(200, 100));
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            int height = this.getHeight() * this.getHeight() / 50;
            int width = height * 2;
            g2d.drawImage(image, (this.getWidth() - width) / 2, (this.getHeight() - height) / 2, width, height, null);
        }
    }
}