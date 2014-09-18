// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// -------------------------------------------------------------------------
/**
 * Manager of the memory pool
 *
 * @author Jeroen
 * @author Phuong Le(Ldp91)
 * @version 2014.09.14
 */
public class MemoryManager
{
    // Fields
    private int               poolSize;
    private int               blockSize;
    private byte[]            pool;
    private FreeMemoryManager availableMem;


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
        availableMem = new FreeMemoryManager();
        availableMem.append(new FreeBlock(0, blockSize), 0);
    }


    // ----------------------------------------------------------
    /**
     * inserts space
     *
     * @param space
     *            the array of bytes need to be insert to pool
     * @return Handle with start insert position
     */
    public Handle insert(byte[] space)
    {
        int size = space.length;
        byte[] newSpace = new byte[size + 2];
        for (int i = 0; i < size; i++)
        {
            newSpace[2 + i] = space[i];
        }
        size = size + 2;
        newSpace[0] = (byte)(size / 256);
        newSpace[1] = (byte)(size % 256);

        while (availableMem.getBlock(size) == null)
        {
            this.expandMemoryPool();
        }

        FreeBlock insertPos = availableMem.getBlock(size);
        // Copy data to memory pool
        for (int i = insertPos.getStartPosition(); i < insertPos
            .getStartPosition() + size; i++)
        {
            pool[i] = newSpace[i - insertPos.getStartPosition()];
        }
        // Delete freeblock
        Handle result = new Handle(insertPos.getStartPosition());
        availableMem.add(new FreeBlock(result.getStartPosition(), size));
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Insert from space array to pool memory.
     * @param space source of data
     * @param size size of dat
     * @return Handle with start insert position
     */
    public Handle insert(byte[] space, int size)
    {
        byte[] newSpace = new byte[size + 2];
        for (int i = 0; i < size; i++)
        {
            newSpace[2 + i] = space[i];
        }
        int newSize = size + 2;
        newSpace[0] = (byte)(newSize / 256);
        newSpace[1] = (byte)(newSize % 256);

        while (availableMem.getBlock(newSize) == null)
        {
            this.expandMemoryPool();
        }

        FreeBlock insertPos = availableMem.getBlock(newSize);
        // Copy data to memory pool
        for (int i = insertPos.getStartPosition(); i < insertPos
            .getStartPosition() + newSize; i++)
        {
            pool[i] = newSpace[i - insertPos.getStartPosition()];
        }
        // Delete freeblock
        Handle result = new Handle(insertPos.getStartPosition());
        availableMem.add(new FreeBlock(result.getStartPosition(), newSize));
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Get the String from memory pool with Start position.
     * @param index start position
     * @return String of data
     */
    public String getData(int index)
    {
        if (index >= poolSize)
            return null;
        int size = pool[index] * 256 + pool[index + 1];
        char[] temp = new char[size - 2];
        for (int i = 0; i < size - 2; i++)
        {
            temp[i] = (char)pool[index + 2 + i];
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
        availableMem.remove(new FreeBlock(poolSize, blockSize));
        poolSize = poolSize + blockSize;
        pool = temp;
        System.out
            .println("Memory pool expanded to be " + poolSize + " bytes.");
    }


    // ----------------------------------------------------------
    /**
     * Remove data from Pool memory from a position.
     * @param index position to start remove
     */
    public void removeAt(int index)
    {
        int size = pool[index] * 256 + pool[index + 1];
        FreeBlock newFreeBlock = new FreeBlock(index, size);
        availableMem.remove(newFreeBlock);
    }

    // ----------------------------------------------------------
    /**
     * Remove data from Pool memory from a Handle position.
     * @param theHandle start position in the pool
     */
    public void remove(Handle theHandle)
    {
        this.removeAt(theHandle.getStartPosition());
    }


    // ----------------------------------------------------------
    /**
     * Get the String of free memory.
     * @return String free block of memory
     */
    public String getFreeMemory()
    {
        return availableMem.toString();
    }


    // ----------------------------------------------------------
    /**
     * Print out the String of free memory.
     */
    public void dump()
    {
        System.out.println(availableMem.toString());
    }


    // ----------------------------------------------------------
    /**
     * Get number of bytes copied to the pool memory.
     * @param space source of data
     * @param theHandle start copy position
     * @param size size of data
     * @return number of actual copied bytes
     */
    public int get(byte[] space, Handle theHandle, int size)
    {
        int result = 0;
        for (int i = theHandle.getStartPosition(); i < size; i++)
        {
            if (i >= poolSize)
            {
                return result;
            }
            space[result] = pool[i];
            result++;
        }
        return result;
    }
}
