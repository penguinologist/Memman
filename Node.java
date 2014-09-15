

public class Node<T>
{
    //~ Fields ................................................................
    private T data;
    private Node<T> next;
    private Node<T> previous;

    // ----------------------------------------------------------
    /**
     * Creates a new Node with a given data.
     *
     * @param data the data for the node
     */
    public Node(T newData)
    {
        this.data = newData;
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Gets the data.
     *
     * @return the data value
     */
    public T data()
    {
        return data;
    }


    // ----------------------------------------------------------
    /**
     * Sets the data.
     *
     * @param newData the new data.
     */
    public void setData(T newData)
    {
        data = newData;
    }


    // ----------------------------------------------------------
    /**
     * Gets the next Node.
     *
     * @return the Next node
     */
    public Node<T> next()
    {
        return next;
    }


    // ----------------------------------------------------------
    /**
     * Gets the previous node.
     *
     * @return the previous node.
     */
    public Node<T> previous()
    {
        return previous;
    }


    // ----------------------------------------------------------
    /**
     * Joins this node to the specified node so that the one passed as a
     * parameter follows this node. In other words, writing {@code A.join(B)}
     * would create the linkage A <-> B.
     *
     * An exception will be thrown if this node already has another node
     * following it, or if {@code newNext} already has another node preceding
     * it. In this case, you must {@link #split()} the nodes before you can
     * join it to something else.
     *
     * @param newNext the node that should follow this one
     * @return this node, so that you can chain methods like
     *     {@code A.join(B.join(C))}
     *
     * @throws IllegalStateException if there is already a node following this
     *     node or if there is already a node preceding {@code newNext}
     */
    public Node<T> join(Node<T> newNext)
    {
        if (this.next != null)
        {
            throw new IllegalStateException("A node is already following "
                + "this one.");
        }
        else if (newNext != null && newNext.previous != null)
        {
            throw new IllegalStateException("A node is already preceding "
                + "the one passed to this method.");
        }
        else
        {
            this.next = newNext;

            if (newNext != null)
            {
                newNext.previous = this;
            }

            return this;
        }
    }


    // ----------------------------------------------------------
    /**
     * Splits this node from its follower and then returns the follower.
     *
     * @return the node that used to follow this node before they were split
     */
    public Node<T> split()
    {
        Node<T> oldNext = this.next;

        if (next != null)
        {
            next.previous = null;
        }

        this.next = null;
        return oldNext;
    }
}