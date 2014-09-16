public class FreeBlock
{
    private DoubleLinkedList<Handle> data;


    // ------------------------------------------------
    public FreeBlock()
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
        if (newBlock.getStartPosition() + newBlock.getLength() == data
            .getElement(0).getStartPosition())
        {
            data.getElement(0).expand(newBlock);
            if (data.getSize() > 1)
            {
                if (data.getElement(0).isConsecutiveTo(data.getElement(1)))
                {
                    data.getElement(0).expand(data.getElement(1));
                }
            }
            return;
        }
        if (newBlock.getStartPosition() + newBlock.getLength() < data
            .getElement(0).getStartPosition())
        {
            data.append(newBlock, 0);
            return;
        }
        for (int i = 0; i < data.getSize() - 1; i++)
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
                + data.getElement(i).getLength() < newBlock.getStartPosition()
                && data.getElement(i + 1).getStartPosition()
                    + data.getElement(i + 1).getLength() > newBlock
                        .getStartPosition())
            {
                data.append(newBlock, i + 1);
                return;
            }
        }
        if (data.getElement(data.getSize() - 1).isConsecutiveTo(newBlock))
        {
            data.getElement(data.getSize() - 1).expand(newBlock);
        }
        else
        {
            data.append(newBlock, data.getSize());
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
                    data.getElement(i).setStartPosition(
                        usedBlock.getLength() + usedBlock.getStartPosition());
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


    public String toString()
    {
        String result = "";
        for (int i = 0; i < data.getSize(); i++)
        {
            result += data.getElement(i).toString();
            if (i + 1 < data.getSize())
            {
                result += " -> ";
            }
        }
        return result;
    }
}
