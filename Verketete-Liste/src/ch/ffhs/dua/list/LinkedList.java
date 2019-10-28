package ch.ffhs.dua.list;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<E> extends ListBasic<E> implements List<E>
{
    private Node m_HeadNode;
    private Node m_TailNode;
    private int m_iNumberOfElements;


    LinkedList()
    {
        m_HeadNode = null;
        m_TailNode = null;
        m_iNumberOfElements = 0;
    }


    /**
     * Appends the given element to the end of this list
     * @param p_Element the element to be added
     * @return          true if this collection changed as a result of the call
     */
    @Override
    public boolean add(E p_Element)
    {
        Node newElement = new Node();
        newElement.m_Element = p_Element;

        if (m_iNumberOfElements == 0)
        {
            // add the first element
            m_HeadNode = newElement;
            m_TailNode = newElement;
        }
        else
        {
            // add it to the last element in the list
            newElement.m_PrevNode = m_TailNode; // new element knows its previous
            m_TailNode.m_NextNode = newElement; // previous knows new element as next
            m_TailNode = newElement;        // tail points to last element
        }

        m_iNumberOfElements++;

        return true; // this type of list has no limitations for adding elements like duplicates etc.
    }

    /**
     * Inserts the given element at the specified position in this list. Shifts the
     * element currently at that position (if any) and any subsequent elements to the
     * right (adds one to their indices).
     *
     * @param p_iIndex  the index position where the new element is inserted
     * @param p_Element the element to be added
     */
    @Override
    public void add(int p_iIndex, E p_Element)
    {
        Node newElement = new Node();
        newElement.m_Element = p_Element;

        Node targetElement = getNode(p_iIndex);
        if (targetElement != null)
        {
            // insert the new element before the target element
            newElement.m_NextNode = targetElement;        // new element knows its next
            newElement.m_PrevNode = targetElement.m_PrevNode; // new element knows its previous
            newElement.m_NextNode.m_PrevNode = newElement;    // next of new element knows its previous

            if (newElement.m_PrevNode == null)
            {
                // head element must be replaced by new element, modify pointer
                m_HeadNode = newElement;
            }
            else
            {
                newElement.m_PrevNode.m_NextNode = newElement; // previous of new element knows its next
            }

            m_iNumberOfElements++;
        }
        else
        {
            // index points outside the current list, add it to the tail
            this.add(p_Element);
        }
    }


    /**
     * Removes the element at the given position in this list. Shifts any subsequent
     * elements to the left (subtracts one from their indices).
     *
     * @param p_iIndex  the index position of the element to be removed
     * @return          the element previously at the specified position
     */
    @Override
    public E remove(int p_iIndex)
    {
        E removedValue = null;
        Node removedElement = getNode(p_iIndex);
        if (removedElement != null)
        {
            //noinspection unchecked
            removedValue = (E) removedElement.m_Element;
            this.removeNode(removedElement);
        }
        return removedValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if any is
     * present.
     *
     * @param p_Value   the value of the element to be removed
     * @return          true if this list contained the specified element i.e the list as changed
     */
    @Override
    public boolean remove(Object p_Value)
    {
        Node removedElement = getNode(p_Value);
        boolean bElementRemoved = (removedElement != null);
        if (bElementRemoved)
        {
            this.removeNode(removedElement);
        }
        return bElementRemoved;
    }

    /**
     * Removes all elements from this list. The list will be empty as newly created.
     */
    @Override
    public void clear()
    {
        while (m_iNumberOfElements > 0)
        {
            this.remove(0);
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    @Override
    public int size()
    {
        return m_iNumberOfElements;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty()
    {
        return (m_iNumberOfElements == 0);
    }

    /**
     * Returns the element at the specified position in this list. The position is
     * given by the parameter which is zero-based i.e. first element is index 0
     * Request of an element which is not in the list i.e. index behind last element,
     * returns null
     *
     * @param p_iIndex  the index position of the requested element
     * @return          the element at the specified position in this list
     */
    @Override
    public E get(int p_iIndex)
    {
        E returnValue = null;

        Node requestedElement = getNode(p_iIndex);
        if (requestedElement != null)
        {
            //noinspection unchecked
            returnValue = (E) requestedElement.m_Element;
        }

        return returnValue;
    }

    /**
     * Replaces the element at the specified position in this list with the given
     * element. The position is given by the index-parameter which is zero-based
     * i.e. the first element is index 0.
     * If the element does not exist the new value will be added to the end of the list
     */
    @Override
    public E set(int p_iIndex, E p_Element)
    {
        @SuppressWarnings("unchecked")
        E returnValue = null;

        Node requestedElement = getNode(p_iIndex);
        if (requestedElement != null)
        {
            //noinspection unchecked
            returnValue = (E) requestedElement.m_Element;
            requestedElement.m_Element = p_Element;
        }
        else
        {
            // target position not valid, add it to the tail
            this.add(p_Element);
        }

        return returnValue;
    }

    /**
     * Returns true if this list contains at least one of the specified element
     * (t.b.d.what to do if the specified element is null and the list is empty?)
     *
     * @param p_Value   the value of element to be found
     * @return          true if this list contains the specified element
     */
    public boolean contains(Object p_Value)
    {
        return (getNode(p_Value) != null);
    }

    /**
     * Returns a specific iterator object for this class
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<E> iterator()
    {
        return new LinkedListIterator();
    }

    /**
     * Returns the node at the given position of the list returns null if the node is
     * not in the list
     *
     * @param p_iIndex  the index of the node to be returned
     * @return          null or a reference to the requested node
     */
    private Node getNode(int p_iIndex)
    {
        Node target = null;
        boolean bIsInTheList = (p_iIndex < m_iNumberOfElements) && (p_iIndex >= 0);
        if (bIsInTheList)
        {
            target = m_HeadNode;
            for (int iNodeIndex = 0; iNodeIndex < p_iIndex; iNodeIndex++)
            {
                target = target.m_NextNode;
            }
        }
        return target;
    }

    /**
     * Returns the first node with the given value from the list.
     * Returns null if the node is not in the list
     *
     * @param p_Value   the value of the node to be returned
     * @return          null or a reference to the requested node
     */
    private Node getNode(Object p_Value)
    {
        //noinspection unchecked
        E searchValue = (E) p_Value;

        Node foundElement = null;
        Node checkElement = m_HeadNode;
        while ((foundElement == null) && (checkElement != null))
        {
            //noinspection unchecked
            E currentValue = (E) checkElement.m_Element;

            if (currentValue == null)
            {
                // there is a null element stored in the node, cannot
                // use null.equals(), avoid null pointer exception
                // but check if it is the one to return
                if (searchValue == null)
                {
                    foundElement = checkElement;
                }
            }
            else
            {
                if (currentValue.equals(searchValue))
                {
                    foundElement = checkElement;
                }
            }

            if (foundElement == null)
            {
                // not yet found, check the next node
                checkElement = checkElement.m_NextNode;
            }
        }
        return foundElement;
    }

    /**
     * Removes the given node from the list.
     *
     * @param p_Node    the node to be removed
     */
    private void removeNode(Node p_Node)
    {
        if (p_Node != null)
        {
            // remove the element by linking its previous and next nodes together
            Node previousNode = p_Node.m_PrevNode;
            Node nextNode     = p_Node.m_NextNode;

            p_Node.m_NextNode = null; // set the pointers to null to let the GC remove the memory
            p_Node.m_PrevNode = null;

            if (previousNode == null)
            {
                // removed node must have been head, modify it
                m_HeadNode = nextNode;
            }
            else
            {
                previousNode.m_NextNode = nextNode;
            }

            if (nextNode == null)
            {
                // removed node must have been tail, modify it
                m_TailNode = previousNode;
            }
            else
            {
                nextNode.m_PrevNode = previousNode;
            }

            m_iNumberOfElements--;
        }
    }

    /**
     * nested class node
     *
     *
     */
    private static class Node<E>
    {
        E m_Element;
        Node<E> m_NextNode;
        Node<E> m_PrevNode;

        Node()
        {
            m_NextNode = null;
            m_PrevNode = null;
            m_Element = null;
        }
    }

    /**
     * nested class iterator
     *
     *
     */
    private class LinkedListIterator implements Iterator<E>
    {
        Node m_CurrentNode;
        Node m_LastNode;


       
        LinkedListIterator()
        {
            m_CurrentNode = LinkedList.this.m_HeadNode;
            m_LastNode = LinkedList.this.m_HeadNode;
        }

        /**
         * Returns true if the iteration has more elements. (In other words, returns true if calling
         * the next() method would return an element rather than throwing an exception.)
         * @return  true if the iteration has more elements
         */
        @Override
        public boolean hasNext()
        {
            return m_CurrentNode != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @throws NoSuchElementException if the iteration has no more elements, i.e. call next at the tail
         *
         * @return   the value of the next element
         */
        @Override
        public E next()
        {
            E nextElement = null;
            if (this.hasNext())
            {
                //noinspection unchecked
                nextElement   = (E) m_CurrentNode.m_Element;
                m_LastNode = m_CurrentNode;
                m_CurrentNode = m_CurrentNode.m_NextNode;
            }
            else
            {
                throw new NoSuchElementException();
            }
            return nextElement;
        }

        /**
         * Removes the last element returned by this iterator from the linked list.
         * The method can be called only once per call to next(). The behavior of an iterator is unspecified
         * if the list is modified while the iteration is in progress in any way other than by calling this method.
         * @throws IllegalStateException  if the next method has not yet been called, or the remove method has
         */
        @Override
        public void remove()
        {
            if (m_CurrentNode != m_LastNode)
            {
                LinkedList.this.removeNode(m_LastNode);
                m_LastNode = m_CurrentNode;
            }
            else
            {
                throw new IllegalStateException();
            }
        }
    }


}
