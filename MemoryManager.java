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
    private Hash<String, Integer> artists;
    private Hash<String, Integer> songs;


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
    Handle insert(byte[] space, int size)
    {
        while (availableMem.getBlock(size) == null)
        {
            this.expandMemoryPool();
        }

        Handle insertPos = availableMem.getBlock(size);
        //Copy data to memory pool
        for (int i = insertPos.getStartPosition(); i < insertPos
            .getStartPosition() + size; i++)
        {
            pool[i] = space[i-insertPos.getStartPosition()];
        }
        // Delete freeblocl
        Handle result = new Handle(insertPos.getStartPosition(), size);
        availableMem.remove(result);
        return result;
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
