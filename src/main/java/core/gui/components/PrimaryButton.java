package core.gui.components;

import javax.swing.*;
import java.awt.*;

public class PrimaryButton extends JButton
{
    public PrimaryButton(String text)
    {
        super(text);
        this.setBackground(new Color(54, 88, 128));
        Font f = this.getFont();
        this.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        this.setFocusable(true);
    }
}