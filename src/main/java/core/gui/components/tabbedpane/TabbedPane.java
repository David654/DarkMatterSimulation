package core.gui.components.tabbedpane;

import core.gui.components.core.GUIComponent;
import core.gui.core.SimulationMenu;
import core.simulation.physics.celestialobjects.CelestialObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.function.IntConsumer;

public class TabbedPane extends JTabbedPane implements GUIComponent
{
    private final SimulationMenu simulationMenu;
    private final ArrayList<Tab> tabs;
    private int selectedTabIndex;

    public TabbedPane(SimulationMenu simulationMenu)
    {
        this.simulationMenu = simulationMenu;
        tabs = new ArrayList<>();

        createAndShowGUI();
    }

    public void createAndShowGUI()
    {
        this.putClientProperty("JTabbedPane.tabClosable", true);
        this.putClientProperty("JTabbedPane.tabCloseCallback", (IntConsumer) this::removeTab);
        this.putClientProperty("JTabbedPane.scrollButtonsPlacement", "both");
        this.putClientProperty("JTabbedPane.tabCloseToolTipText", "Close");
        UIManager.put("TabbedPane.showTabSeparators", true);
        this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.addChangeListener(e -> selectedTabIndex = this.getSelectedIndex());
    }

    public void addTab()
    {
        CelestialObject celestialObject = simulationMenu.getSimulation().getStarSystem().getBodyHandler().get(simulationMenu.getTable().getSelectedIndex());

        boolean contains = false;
        for(int i = 0; i < this.getTabCount(); i++)
        {
            if(this.getTitleAt(i).equals(celestialObject.getName()))
            {
                contains = true;
                break;
            }
        }

        if(!contains)
        {
            Tab tab = new Tab(celestialObject);
            tabs.add(tab);
            this.addTab(celestialObject.getName(), tab);
            this.revalidate();
            this.repaint();
        }

        for(int i = 0; i < this.getTabCount(); i++)
        {
            if(this.getTitleAt(i).equals(celestialObject.getName()))
            {
                this.setSelectedIndex(i);
                break;
            }
        }
    }

    public Tab getSelectedTab()
    {
        return tabs.get(selectedTabIndex);
    }

    public void removeTab(int index)
    {
        if(this.getTabCount() > 0)
        {
            tabs.remove(index);
            this.remove(index);
            this.revalidate();
            this.repaint();
        }
    }

    public void removeAllTabs()
    {
        tabs.clear();
        this.removeAll();
    }
}