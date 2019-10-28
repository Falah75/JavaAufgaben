package ch.ffhs.dua.list;
/**
 * Basis Implementierung des List Interfaces;
 * Alle Methoden werfen eine Exception.
 * Kann verwendet werden als Basisklasse einer 
 * teilweisen Implementierung des List-Interfaces.
 * Die Methoden, die in dieser Klasse implementiert sind,
 * (indem sie eine Exception werfen,) müssen bei der Abgabe der Aufgabe
 * nicht überschrieben werden.
 */

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class ListBasic<E> implements List<E>
{

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int indexOf(Object o)
    {
        throw new UnsupportedOperationException();
    }

}