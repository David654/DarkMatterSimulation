package core.gui.core;

import core.assets.icons.Icons;
import core.assets.textures.Textures;
import core.gui.components.core.GUIComponent;
import core.gui.components.buttons.PrimaryButton;
import core.util.TextureUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ColorChooser extends JDialog implements GUIComponent
{
    private final int width = 800;
    private final int height = 400;
    private Color currColor;
    private int wheelCursorX;
    private int wheelCursorY;
    private int sliderCursorX;
    private int sliderCursorY;
    private final int cursorSize = 10;

    private ColorWheelPanel colorWheelPanel;
    private ColorSliderPanel colorSliderPanel;
    private static JPanel currColorPanel;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private JSpinner redSpinner;
    private JSpinner greenSpinner;
    private JSpinner blueSpinner;
    private JButton selectButton;
    private JButton cancelButton;

    private static boolean isSelectButtonPressed = false;

    private ColorChooser(Component component, String title, Color initialColor)
    {
        this.currColor = initialColor;

        this.setTitle(title);
        this.setSize(width, height);
        this.setMinimumSize(new Dimension(width, height));
        this.setResizable(false);
        this.setLocationRelativeTo(component);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setIconImage(Icons.createIcon(Icons.APPLICATION_ICON_PATH).getImage());

        createAndShowGUI();

        this.setModal(true);
        this.setVisible(true);
    }

    public void createAndShowGUI()
    {
        colorWheelPanel = new ColorWheelPanel(width / 2, height);


        wheelCursorX = height / 2 - cursorSize / 2;
        wheelCursorY = height / 2 - cursorSize / 2;
        sliderCursorX = 0;
        sliderCursorY = 20;

        colorSliderPanel = new ColorSliderPanel(width / 20, height);

        this.add(colorWheelPanel);
        this.add(colorSliderPanel);
        createColorControlPanelGUI();

        setCursorPosition(currColor);

        this.pack();

        selectButton.requestFocus();
    }

    private void setCursorPosition(Color originalColor)
    {
        int maxValue = Math.max(Math.max(originalColor.getRed(), originalColor.getGreen()), originalColor.getBlue());
        int red = 255 - (maxValue - originalColor.getRed());
        int green = 255 - (maxValue - originalColor.getGreen());
        int blue = 255 - (maxValue - originalColor.getBlue());

        /*for(int x = 0; x < colorWheelPanel.getColorWheel().getWidth(); x++)
        {
            for(int y = 0; y < colorWheelPanel.getColorWheel().getHeight(); y++)
            {
                BufferedImage colorSlider = colorSliderPanel.createColorSlider(new Color(colorWheelPanel.getColorWheel().getRGB(x, y)));
                for(int k = 0; k < colorSlider.getHeight(); k++)
                {
                    Color c = new Color(colorSlider.getRGB(0, k));
                    if(c.equals(originalColor))
                    {
                        System.out.println("x: " + x + ", y:" + y + ", k: "+ k);
                        break;
                    }
                }
            }
        }**/


        Color color = new Color(red, green, blue);
        //System.out.println(color);

        int[] pos = getPos(colorWheelPanel.getColorWheel(), color);
        if(pos[0] != -1 && pos[1] != -1)
        {
            wheelCursorX = pos[0];
            wheelCursorY = pos[1];
        }
        //System.out.println(Arrays.toString(pos));
    }

    private void createColorControlPanelGUI()
    {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 0, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width / 2, height));

        // curr color
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        currColorPanel = new JPanel();
        currColorPanel.setBackground(currColor);
        panel1.add(currColorPanel, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // red
        JLabel redLabel = new JLabel("Red: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        panel2.add(redLabel, gbc);

        redSlider = new JSlider(0, 255, currColor.getRed());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        panel2.add(redSlider, gbc);

        redSpinner = new JSpinner(new SpinnerNumberModel(currColor.getRed(), 0, 255, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        panel2.add(redSpinner, gbc);
        
        // green
        JLabel greenLabel = new JLabel("Green: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        panel2.add(greenLabel, gbc);

        greenSlider = new JSlider(0, 255, currColor.getGreen());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        panel2.add(greenSlider, gbc);

        greenSpinner = new JSpinner(new SpinnerNumberModel(currColor.getGreen(), 0, 255, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        panel2.add(greenSpinner, gbc);
        
        // blue
        JLabel blueLabel = new JLabel("Blue: ");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 0, 0, 0);
        panel2.add(blueLabel, gbc);

        blueSlider = new JSlider(0, 255, currColor.getBlue());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel2.add(blueSlider, gbc);

        blueSpinner = new JSpinner(new SpinnerNumberModel(currColor.getBlue(), 0, 255, 1));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        panel2.add(blueSpinner, gbc);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        selectButton = new PrimaryButton("Select");
        cancelButton = new JButton("Cancel");
        panel3.add(selectButton);
        panel3.add(cancelButton);

        setButtonActions();

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        createColorAdjustment();

        this.add(panel);
    }

    public static Color showDialog(Component component, String title, Color initialColor)
    {
        new ColorChooser(component, title, initialColor);

        if(isSelectButtonPressed)
        {
            return currColorPanel.getBackground();
        }

        return null;
    }

    private void setButtonActions()
    {
        selectButton.addActionListener(e ->
        {
            isSelectButtonPressed = true;
            this.dispose();
        });
        cancelButton.addActionListener(e -> this.dispose());
    }

    private void createColorAdjustment()
    {
        redSlider.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
            redSpinner.setValue(redSlider.getValue());
        });

        greenSlider.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
            greenSpinner.setValue(greenSlider.getValue());
        });

        blueSlider.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
            blueSpinner.setValue(blueSlider.getValue());
        });

        redSpinner.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color((Integer) redSpinner.getValue(), (Integer) greenSpinner.getValue(), (Integer) blueSpinner.getValue()));
            redSlider.setValue((Integer) redSpinner.getValue());
        });

        greenSpinner.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color((Integer) redSpinner.getValue(), (Integer) greenSpinner.getValue(), (Integer) blueSpinner.getValue()));
            greenSlider.setValue((Integer) greenSpinner.getValue());
        });

        blueSpinner.addChangeListener(e ->
        {
            currColorPanel.setBackground(new Color((Integer) redSpinner.getValue(), (Integer) greenSpinner.getValue(), (Integer) blueSpinner.getValue()));
            blueSlider.setValue((Integer) blueSpinner.getValue());
        });
    }

    private int[] getPos(BufferedImage image, Color color)
    {
        for(int x = 0; x < image.getWidth(); x++)
        {
            for(int y = 0; y < image.getHeight(); y++)
            {
                Color c = new Color(image.getRGB(x, y));
                int threshold = 5;
                if(Math.abs(c.getRed() - color.getRed()) <= threshold && Math.abs(c.getGreen() - color.getGreen()) <= threshold && Math.abs(c.getBlue() - color.getBlue()) <= threshold)
                {
                    return new int[] {x, y};
                }
            }
        }
        return new int[] {-1, -1};
    }

    private class ColorWheelPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        private final int width;
        private final int height;
        private final int margin = 10;
        private final int radius;
        private BufferedImage colorWheel;

        private ColorWheelPanel(int width, int height)
        {
            this.width = width;
            this.height = height;

            this.setPreferredSize(new Dimension(width, height));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);

            radius = (Math.min(width, height) - 2 * margin) / 2;
            //colorWheel = createColorWheel();
            colorWheel = TextureUtils.readImage(Textures.COLOR_WHEEL_IMAGE_PATH);
            colorWheel = TextureUtils.resizeImage(colorWheel, width, height);

            Timer timer = new Timer(1, e -> this.repaint());
            timer.start();
        }

        private BufferedImage createColorWheel()
        {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) image.getGraphics();

            int w = width;
            int h = height;
            int cx = w / 2;
            int cy = h / 2;
            float[] dist = {0F, 1F};

            g2d.setColor(this.getBackground());
            g2d.fillRect(0, 0, w, h);
            for(int angle = 0; angle < 360; angle++)
            {
                Color color = hsvToRgb(angle, 1.0, 1.0);
                Color[] colors = {Color.WHITE, color};
                RadialGradientPaint paint = new RadialGradientPaint(cx, cy, radius, dist, colors);
                g2d.setPaint(paint);
                g2d.fillArc(cx - radius, cy - radius, radius * 2, radius * 2, angle, 1);
            }

            return image;
        }

        public BufferedImage getColorWheel()
        {
            return colorWheel;
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.drawImage(colorWheel, 0, 0, null);
            //g2d.setColor((currColor.getRed() + currColor.getGreen() + currColor.getBlue()) / 3 > 128 ? Color.BLACK : Color.WHITE);
            g2d.setColor(this.getBackground());
            g2d.drawOval(wheelCursorX, wheelCursorY, cursorSize, cursorSize);
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}

        public void mousePressed(MouseEvent e)
        {
            this.requestFocus();
            int x = e.getX() - cursorSize / 2;
            int y = e.getY() - cursorSize / 2;
            double dist = Math.sqrt(Math.pow(x - width / 2d, 2) + Math.pow(y - height / 2d, 2));
            if(dist < radius)
            {
                wheelCursorX = x;
                wheelCursorY = y;
                currColor = new Color(colorWheel.getRGB(wheelCursorX, wheelCursorY));
                currColorPanel.setBackground(new Color(colorSliderPanel.getColorSlider().getRGB(sliderCursorX, sliderCursorY)));

                redSlider.setValue(currColorPanel.getBackground().getRed());
                greenSlider.setValue(currColorPanel.getBackground().getGreen());
                blueSlider.setValue(currColorPanel.getBackground().getBlue());

                redSpinner.setValue(currColorPanel.getBackground().getRed());
                greenSpinner.setValue(currColorPanel.getBackground().getGreen());
                blueSpinner.setValue(currColorPanel.getBackground().getBlue());
            }
        }

        public void mouseDragged(MouseEvent e)
        {
            this.requestFocus();
            int x = e.getX() - cursorSize / 2;
            int y = e.getY() - cursorSize / 2;
            double dist = Math.sqrt(Math.pow(x - width / 2d + cursorSize / 2d, 2) + Math.pow(y - height / 2d + cursorSize / 2d, 2));
            if(dist < radius)
            {
                wheelCursorX = x;
                wheelCursorY = y;
                currColor = new Color(colorWheel.getRGB(wheelCursorX, wheelCursorY));
                currColorPanel.setBackground(new Color(colorSliderPanel.getColorSlider().getRGB(sliderCursorX, sliderCursorY)));

                redSlider.setValue(currColorPanel.getBackground().getRed());
                greenSlider.setValue(currColorPanel.getBackground().getGreen());
                blueSlider.setValue(currColorPanel.getBackground().getBlue());

                redSpinner.setValue(currColorPanel.getBackground().getRed());
                greenSpinner.setValue(currColorPanel.getBackground().getGreen());
                blueSpinner.setValue(currColorPanel.getBackground().getBlue());
            }
        }
    }

    private class ColorSliderPanel extends JPanel implements MouseListener, MouseMotionListener
    {
        private final int width;
        private final int height;
        private final int margin = 20;
        private BufferedImage colorSlider;

        private ColorSliderPanel(int width, int height)
        {
            this.width = width;
            this.height = height;

            this.setPreferredSize(new Dimension(width, height));
            this.addMouseListener(this);
            this.addMouseMotionListener(this);

            colorSlider = createColorSlider(currColor);

            Timer timer = new Timer(1, e -> this.repaint());
            timer.start();
        }

        private BufferedImage createColorSlider(Color color)
        {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = (Graphics2D) image.getGraphics();

            int w = width;
            int h = height;

            g2d.setColor(this.getBackground());
            g2d.fillRect(0, 0, w, h);
            GradientPaint paint = new GradientPaint(0, 0, color, 0, h, Color.BLACK);
            g2d.setPaint(paint);
            g2d.fillRect(0, margin, w, h - 2 * margin);

            return image;
        }

        public BufferedImage getColorSlider()
        {
            return colorSlider;
        }

        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            colorSlider = createColorSlider(currColor);
            g2d.drawImage(colorSlider, 0, 0, null);
            g2d.setColor(this.getBackground());
            g2d.drawRect(0, sliderCursorY, width, cursorSize / 4);
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}

        public void mousePressed(MouseEvent e)
        {
            this.requestFocus();
            if(e.getY() >= margin && e.getY() <= 2 * margin + height)
            {
                sliderCursorX = Math.max(0, Math.min(width - 1, e.getX()));
                sliderCursorY = Math.max(margin, Math.min(height - margin - 1, e.getY()));

                Color color = new Color(colorSlider.getRGB(sliderCursorX, sliderCursorY));
                currColorPanel.setBackground(color);

                redSlider.setValue(color.getRed());
                greenSlider.setValue(color.getGreen());
                blueSlider.setValue(color.getBlue());

                redSpinner.setValue(color.getRed());
                greenSpinner.setValue(color.getGreen());
                blueSpinner.setValue(color.getBlue());

                currColor = new Color(colorWheelPanel.getColorWheel().getRGB(wheelCursorX, wheelCursorY));
            }
        }

        public void mouseDragged(MouseEvent e)
        {
            this.requestFocus();
            if(e.getY() >= margin && e.getY() <= 2 * margin + height)
            {
                sliderCursorX = Math.max(0, Math.min(width - 1, e.getX()));
                sliderCursorY = Math.max(margin, Math.min(height - margin - 1, e.getY()));

                Color color = new Color(colorSlider.getRGB(sliderCursorX, sliderCursorY));
                currColorPanel.setBackground(color);

                redSlider.setValue(color.getRed());
                greenSlider.setValue(color.getGreen());
                blueSlider.setValue(color.getBlue());

                redSpinner.setValue(color.getRed());
                greenSpinner.setValue(color.getGreen());
                blueSpinner.setValue(color.getBlue());

                currColor = new Color(colorWheelPanel.getColorWheel().getRGB(wheelCursorX, wheelCursorY));
            }
        }
    }

    private static Color hsvToRgb(int h, double s, double v)
    {
        double hp = h / 60.0;
        double c = s * v;
        double x = c * (1 - Math.abs(hp % 2.0 - 1));
        double m = v - c;
        double r = 0, g = 0, b = 0;

        if(hp <= 1)
        {
            r = c;
            g = x;
        }
        else if(hp <= 2)
        {
            r = x;
            g = c;
        }
        else if(hp <= 3)
        {
            g = c;
            b = x;
        }
        else if(hp <= 4)
        {
            g = x;
            b = c;
        }
        else if(hp <= 5)
        {
            r = x;
            b = c;
        }
        else
        {
            r = c;
            b = x;
        }
        r += m;
        g += m;
        b += m;

        return new Color((int) (r * 255), (int) (g * 255), (int) (b * 255));
    }
}