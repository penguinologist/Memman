
// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author1 Phuong Le(Ldp91)
 * @version 2014.09.14
 * @param <T>
 */
public class DoubleLinkedList<T>
{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // ----------------------------------------------------------
    /**
     * Create a new DoubleLinkedList object.
     */
    public DoubleLinkedList()
    {
        head = new Node<T>(null);
        tail = new Node<T>(null);
        head.join(tail);
        size = 0;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param newData
     */
    public void append(T newData, int position)
    {
        if (position > size)
        {
            return;
        }
        Node<T> current = head;
        for (int i=0; i< position; i++)
        {
            current = current.next();
        }
        Node<T> temp = current.split();
        Node<T> newNode = new Node<T>(newData);
        newNode.join(temp);
        current.join(newNode);
        size++;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @param position
     */
    public void remove(int position)
    {
        if (position >= size)
        {
            return;
        }
        Node<T> current = head;
        for (int i=0; i<= position; i++)
        {
            current = current.next();
        }
        Node<T> temp = current.previous();
        temp.split();
        temp.join(current.split());
        size--;
    }

    // ----------------------------------------------------------
    /**
     * Get element at a position. Index starts with 0
     * @param index position need to get data
     * @return Data at index
     */
    public T getElement(int index)
    {
        Node<T> current = head;
        for (int i=0; i<= index; i++)
        {
            current = current.next();
        }
        return current.data();
    }
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public int getSize()
    {
        return this.size;
    }

}
