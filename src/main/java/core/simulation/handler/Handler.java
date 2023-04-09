package core.simulation.handler;

import java.util.ArrayList;

public abstract class Handler<T>
{
    protected final ArrayList<T> list;

    public Handler()
    {
        list = new ArrayList<>();
    }

    public int getSize()
    {
        return list.size();
    }

    public void add(T t)
    {
        list.add(t);
    }

    public void remove(T t)
    {
        list.remove(t);
    }

    public void set(int index, T t)
    {
        list.set(index, t);
    }

    public void remove(int index)
    {
        list.remove(index);
    }

    public void clear()
    {
        list.clear();
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