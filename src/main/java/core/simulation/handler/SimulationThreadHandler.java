package core.simulation.handler;

import core.simulation.thread.SimulationThread;

public class SimulationThreadHandler extends Handler<SimulationThread>
{
    public void update()
    {
        for(int i = 0; i < list.size(); i++)
        {
            SimulationThread thread = list.get(i);

        }
    }
}