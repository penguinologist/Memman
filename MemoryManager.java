// -------------------------------------------------------------------------
/**
 * Manager of the memory pool
 * @author Jeroen
 * @author Phuong Le(Ldp91)
 * @version 2014.09.14
 */
public class MemoryManager {
	// Fields
	private int poolSize;
	private int blockSize;
	private byte[] pool;
	private FreeBlock availableMem;

	// Constructor

	// ----------------------------------------------------------
	/**
	 * Create a new MemoryManager object.
	 * 
	 * @param blockSize
	 */
	public MemoryManager(int blockSize) {
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
	Handle insert(byte[] space) {
		int size = space.length;
		byte[] newSpace = new byte[size + 2];
		for (int i = 0; i < size; i++) {
			newSpace[2 + i] = space[i];
		}
		size = size + 2;
		newSpace[0] = (byte) (size / 256);
		newSpace[1] = (byte) (size % 256);

		while (availableMem.getBlock(size) == null) {
			this.expandMemoryPool();
		}

		Handle insertPos = availableMem.getBlock(size);
		// Copy data to memory pool
		for (int i = insertPos.getStartPosition(); i < insertPos
				.getStartPosition() + size; i++) {
			pool[i] = newSpace[i - insertPos.getStartPosition()];
		}
		// Delete freeblock
		Handle result = new Handle(insertPos.getStartPosition(), size);
		availableMem.remove(result);
		return result;
	}

	public String getData(int index) {
		if (index >= poolSize)
			return null;
		int size = (int) pool[index] * 256 + (int) pool[index + 1];
		char[] temp = new char[size - 2];
		for (int i = 0; i < size - 2; i++) {
			temp[i] = (char) pool[index + 2 + i];
		}

		return String.valueOf(temp);
	}

	private void expandMemoryPool() {
		byte[] temp = new byte[poolSize + blockSize];
		for (int i = 0; i < poolSize; i++) {
			temp[i] = pool[i];
		}
		availableMem.add(new Handle(poolSize, blockSize));
		poolSize = poolSize + blockSize;
		pool = temp;
		System.out
				.println("Memory pool expanded to be " + poolSize + " bytes.");
	}

	public void removeAt(int index) {
		int size = (int) pool[index] * 256 + (int) pool[index + 1];
		Handle newFreeBlock = new Handle(index, size);
		availableMem.add(newFreeBlock);
	}

	public String getFreeMemory() {
		return availableMem.toString();
	}
}
