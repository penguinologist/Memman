import java.math.BigInteger;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author1 Phuong Le(Ldp91)
 * @version 2014.09.14
 */
public class MemoryManager
{
    // Fields
    private int       poolSize;
    private int       blockSize;
    private byte[]    pool;
    private FreeBlock availableMem;


    // Constructor

    // ----------------------------------------------------------
    /**
     * Create a new MemoryManager object.
     *
     * @param blockSize
     */
    public MemoryManager(int blockSize)
    {
        this.poolSize = blockSize;
        this.blockSize = blockSize;
        pool = new byte[blockSize];
        availableMem = new FreeBlock();
        availableMem.add(new Handle(0, blockSize));
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param space
     * @param size
     * @return 0: successful 1: increase size of pool -1: fail
     */
    Handle insert(byte[] space)
    {
        int size = space.length;
        byte[] newSpace = new byte[size + 2];
        for (int i = 0; i < size; i++)
        {
            newSpace[2 + i] = space[i];
        }
        size = size + 2;
        BigInteger temp = BigInteger.valueOf(size);
        byte[] sizeByte = temp.toByteArray();
        newSpace[0] = sizeByte[sizeByte.length - 2];
        newSpace[1] = sizeByte[sizeByte.length - 1];

        while (availableMem.getBlock(size) == null)
        {
            this.expandMemoryPool();
        }

        Handle insertPos = availableMem.getBlock(size);
        // Copy data to memory pool
        for (int i = insertPos.getStartPosition(); i < insertPos
            .getStartPosition() + size; i++)
        {
            pool[i] = newSpace[i - insertPos.getStartPosition()];
        }
        // Delete freeblocl
        Handle result = new Handle(insertPos.getStartPosition(), size);
        availableMem.remove(result);
        return result;
    }


    public String getData(int index)
    {
        int size = pool[index] * 0xff00 + pool[index + 1] * 0x00ff;
        char[] temp = new char[size];
        for (int i = 0; i < size; i++)
        {
            temp[i] =(char) pool[index + 2 + i];
        }

        return String.valueOf(temp);
    }


    private void expandMemoryPool()
    {
        byte[] temp = new byte[poolSize + blockSize];
        for (int i = 0; i < poolSize; i++)
        {
            temp[i] = pool[i];
        }
        poolSize = poolSize + blockSize;
        pool = temp;
        availableMem.add(new Handle(poolSize, blockSize));
    }

}
