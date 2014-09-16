public class FreeBlock
{
    private DoubleLinkedList<Handle> data;

    //------------------------------------------------
    public FreeBlock ()
    {
        data = new DoubleLinkedList<Handle>();
    }

    public void add(Handle newBlock)
    {
        if (data.getSize() == 0)
        {
            data.append(newBlock, 0);
            return;
        }
        for (int i = 0; i < data.getSize(); i++)
        {
            if (data.getElement(i).isConsecutiveTo(newBlock))
            {
                data.getElement(i).expand(newBlock);
                // keep checking if the next block is consecutive to the block
                if (data.getElement(i).isConsecutiveTo(data.getElement(i + 1)))
                {
                    data.getElement(i).expand(data.getElement(i + 1));
                }
                return;
            }
            if (data.getElement(i).getStartPosition()
                + data.getElement(i).getLength() < newBlock.getStartPosition())
            {
                data.append(newBlock, i);
                if (data.getElement(i).isConsecutiveTo(data.getElement(i + 1)))
                {
                    data.getElement(i).expand(data.getElement(i + 1));
                }
                return;
            }
        }

    }


    public void remove(Handle usedBlock)
    {
        for (int i = 0; i < data.getSize(); i++)
        {
            if (data.getElement(i).getStartPosition() == usedBlock
                .getStartPosition())
            {
                if (data.getElement(i).getLength() == usedBlock.getLength())
                {
                    data.remove(i);
                }
                else
                {
                    data.getElement(i).setLength(
                        data.getElement(i).getLength() - usedBlock.getLength());
                }
                return;
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Get the position for the best fit block
     *
     * @param size
     *            of needed block
     * @return null if there is not sufficient free block
     */
    public Handle getBlock(int size)
    {
        Handle result = null;
        int bestFit = 100000000;
        for (int i = 0; i < data.getSize(); i++)
        {
            if (size <= data.getElement(i).getLength()
                && data.getElement(i).getLength() - size < bestFit)
            {
                result = data.getElement(i);
                bestFit = data.getElement(i).getLength() - size;
            }
        }
        return result;
    }
}
