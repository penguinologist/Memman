public class FreeMemoryManager
{
    private DoubleLinkedList<FreeBlock> availableMem;


    public FreeMemoryManager()
    {
        availableMem = new DoubleLinkedList<FreeBlock>();

    }


    public void append(FreeBlock newBlock, int position)
    {
        availableMem.append(newBlock, position);
    }


    public FreeBlock getBlock(int size)
    {
        FreeBlock result = null;
        int bestFit = Integer.MAX_VALUE;
        for (int i = 0; i < availableMem.getSize(); i++)
        {
            if (size <= availableMem.getElement(i).getLength()
                && availableMem.getElement(i).getLength() - size < bestFit)
            {
                result = availableMem.getElement(i);
                bestFit = availableMem.getElement(i).getLength() - size;
            }
        }
        return result;
    }


    public void remove(FreeBlock usedBlock)
    {
        for (int i = 0; i < availableMem.getSize(); i++)
        {
            if (availableMem.getElement(i).getStartPosition() == usedBlock
                .getStartPosition())
            {
                if (availableMem.getElement(i).getLength() == usedBlock
                    .getLength())
                {
                    availableMem.remove(i);
                }
                else
                {
                    availableMem.getElement(i).setStartPosition(
                        usedBlock.getLength() + usedBlock.getStartPosition());
                    availableMem.getElement(i).setLength(
                        availableMem.getElement(i).getLength()
                            - usedBlock.getLength());
                }
                return;
            }
        }
    }


    public void add(FreeBlock newBlock)
    {
        if (availableMem.getSize() == 0)
        {
            availableMem.append(newBlock, 0);
            return;
        }
        if (newBlock.getStartPosition() + newBlock.getLength() < availableMem
            .getElement(0).getStartPosition())
        {
            availableMem.append(newBlock, 0);
            return;
        }
        if (newBlock.isConsecutiveTo(availableMem.getElement(0)))
        {
            availableMem.getElement(0).expand(newBlock);
            if (availableMem.getSize() > 1)
            {
                if (availableMem.getElement(0).isConsecutiveTo(
                    availableMem.getElement(1)))
                {
                    availableMem.getElement(0).expand(
                        availableMem.getElement(1));
                }
            }
            return;
        }
        if (newBlock.getStartPosition() + newBlock.getLength() < availableMem
            .getElement(0).getStartPosition())
        {
            availableMem.append(newBlock, 0);
            return;
        }
        for (int i = 0; i < availableMem.getSize() - 1; i++)
        {
            if (availableMem.getElement(i).isConsecutiveTo(newBlock))
            {
                availableMem.getElement(i).expand(newBlock);
                // keep checking if the next block is consecutive to the block
                if (availableMem.getElement(i).isConsecutiveTo(
                    availableMem.getElement(i + 1)))
                {
                    availableMem.getElement(i).expand(
                        availableMem.getElement(i + 1));
                }
                return;
            }
            if (newBlock.isConsecutiveTo(availableMem.getElement(i + 1)))
            {
                availableMem.getElement(i + 1).setStartPosition(
                    newBlock.getStartPosition());
                availableMem.getElement(i + 1).setLength(
                    newBlock.getLength()
                        + availableMem.getElement(i + 1).getLength());
                return;
            }
            if (availableMem.getElement(i).getStartPosition()
                + availableMem.getElement(i).getLength() < newBlock
                    .getStartPosition()
                && availableMem.getElement(i + 1).getStartPosition()
                    + availableMem.getElement(i + 1).getLength() > newBlock
                        .getStartPosition())
            {
                availableMem.append(newBlock, i + 1);
                return;
            }
        }
        if (availableMem.getElement(availableMem.getSize() - 1)
            .isConsecutiveTo(newBlock))
        {
            availableMem.getElement(availableMem.getSize() - 1)
                .expand(newBlock);
        }
        else
        {
            availableMem.append(newBlock, availableMem.getSize());
        }
    }


    public String toString()
    {
        String result = "";
        for (int i = 0; i < availableMem.getSize(); i++)
        {
            result += availableMem.getElement(i).toString();
            if (i + 1 < availableMem.getSize())
            {
                result += " -> ";
            }
        }
        return result;
    }
}
