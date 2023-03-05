package core.gui.grid;

import javax.swing.*;
import java.awt.*;

public class GridRow
{
    private final int gridY;
    private final String title;
    private final String units;
    private final String tootipText;

    public GridRow(int gridY, String title, String units, String tootipText)
    {
        this.gridY = gridY;
        this.title = title;
        this.units = units;
        this.tootipText = tootipText;
    }

    public GridRow(int gridY, String title)
    {
        this(gridY, title, "", "");
    }


    public void createAndShowGUI(GridBagConstraints gbc, int weightY, JComponent parent, JComponent... components)
    {
        for(int i = 0; i < components.length + 2; i++)
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = i;
            gbc.gridy = gridY;
            gbc.weightx = 1;
            gbc.weighty = weightY;

            if(i == 0)
            {
                gbc.gridwidth = 1;
                gbc.insets = new Insets(0, 10, 0, 0);
                JLabel titleLabel = new JLabel(title);

                Font f = titleLabel.getFont();
                titleLabel.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
                titleLabel.setToolTipText(tootipText);

                parent.add(titleLabel, gbc);
            }
            else if(i < components.length + 1)
            {
                gbc.gridwidth = 3 / components.length;
                gbc.insets = new Insets(5, 0, 0, 10);
                JComponent component = components[i - 1];
                parent.add(component, gbc);
            }
            else
            {
                gbc.gridx = components.length + gbc.gridwidth;
                gbc.insets = new Insets(5, 0, 0, 10);
                JLabel unitsLabel = new JLabel(units);
                parent.add(unitsLabel, gbc);
            }
        }
    }
}