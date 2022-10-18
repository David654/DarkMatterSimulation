package core.simulation.components.handler;

import java.util.ArrayList;

public abstract class Handler<T>
{
    private final ArrayList<T> list;

    public Handler()
    {
        list = new ArrayList<>();
    }

    public void add(T t)
    {
        list.add(t);
    }

    public void remove(T t)
    {
        list.remove(t);
    }

    public void remove(int index)
    {
        list.remove(index);
    }

    public T get(int index)
    {
        return list.get(index);
    }

    public int indexOf(T t)
    {
        return list.indexOf(t);
    }
}