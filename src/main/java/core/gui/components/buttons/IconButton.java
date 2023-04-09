package core.gui.components.buttons;

import core.assets.icons.Icons;

import javax.swing.*;

public class IconButton extends JButton
{
    public IconButton(String fileName)
    {
        super(Icons.createIcon(fileName));
        this.setOpaque(false);
        this.setBorderPainted(false);
       // this.setBackground(null);
    }
}