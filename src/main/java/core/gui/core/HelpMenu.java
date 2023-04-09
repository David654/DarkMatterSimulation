package core.gui.core;

import core.assets.icons.Icons;
import core.gui.components.core.GUIComponent;
import core.util.TextureUtils;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelpMenu extends JDialog implements GUIComponent
{
    private final String textPath = "src\\main\\resources\\text.html";

    public HelpMenu(Component component, int width, int height)
    {
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(component);
        this.setTitle("Help");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setIconImage(TextureUtils.readImage(Icons.APPLICATION_ICON_PATH));

        this.setVisible(true);
        createAndShowGUI();

        this.setModal(true);
    }

    public void createAndShowGUI()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);

        String text = readFile(textPath);
        textPane.setText(text);

        StyledDocument doc = textPane.getStyledDocument();

        SimpleAttributeSet justified = new SimpleAttributeSet();
        StyleConstants.setAlignment(justified, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), justified, false);

        textPane.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(null);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private String readFile(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            while(true)
            {
                final String line = br.readLine();
                if(line == null)
                {
                    break;
                }
                sb.append(line).append("\n");
            }
            br.close();
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return sb.toString();
    }
}