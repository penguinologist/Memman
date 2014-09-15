import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author1 Phuong Le(Ldp91)
 * @version 2014.09.14
 */
public class Memman {
	private static int initialHashSize;
	private static int blockSize;
	private static String commandFile;
	private static Hash<String, Integer> artists;
	private static Hash<String, Integer> songs;
	private static MemoryManager memManager;

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			return;
		}

		initialHashSize = Integer.parseInt(args[0]);
		blockSize = Integer.parseInt(args[1]);
		commandFile = args[2];
		artists = new Hash<String, Integer>(initialHashSize);
		songs = new Hash<String, Integer>(initialHashSize);
		memManager = new MemoryManager(blockSize, initialHashSize); // TODO
																	// should
																	// we link
		readCommandFile(commandFile);

	}

	// ----------------------------------------------------------
	/**
	 * Place a description of your method here.
	 * 
	 * @param fileName
	 */
	public static void readCommandFile(String fileName) {
		Scanner inStream = null;

		try {
			inStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (inStream.hasNext()) {
			String cmdLine = inStream.nextLine();
			parseLine(cmdLine);
		}
	}

	private static void parseLine(String cmdLine) {
		cmdLine = cmdLine.trim();
		String command = cmdLine.substring(0, cmdLine.indexOf(' '));
		cmdLine = cmdLine.substring(cmdLine.indexOf(' ') + 1).trim();
		String[] args = new String[3];
		if (command.equalsIgnoreCase("insert"))// insert
												// {artist-name}<SEP>{song-name}
		{
			args[0] = cmdLine.substring(0, cmdLine.indexOf("<SEP>"));
			args[1] = cmdLine.substring(cmdLine.indexOf(">") + 1);
			insert(args);
		} else if (command.equalsIgnoreCase("remove"))// remove {artist|song}
														// {name}
		{
			args[0] = cmdLine.substring(0, cmdLine.indexOf(' '));
			args[1] = cmdLine.substring(cmdLine.indexOf(' ') + 1);
			remove(args);
		} else if (command.equalsIgnoreCase("print"))// print
														// {artist|song|blocks}
		{
			args[0] = cmdLine;
			print(args);
		} else {
			// Invalid Command
		}
	}

	private static void insert(String[] args) {
		boolean success = false;

		if (args[0] != null && args[1] != null) {
			success = true;

			// artist <SEP> song

			if (!artists.containsKey(args[0])) {

				artists.put(args[0], memManager.insert(args[0].getBytes()));
			}
			if (!songs.containsKey(args[1])) {
				songs.put(args[1], memManager.insert(args[1].getBytes()));
			}

		}

		if (success) {
			System.out.println("Successful execution.");
		} else {
			System.out.println("Failed execution.");
		}

	}

	private static void remove(String[] args) {
		boolean actionExecuted = false;
		if (artists.containsKey(args[0])) {
			artists.remove(args[0]);
			actionExecuted = true;
		}
		if (songs.containsKey(args[0])) {
			songs.remove(args[0]);
			actionExecuted = true;
		}

		if (actionExecuted) {
			System.out.println("Successful execution.");
		} else {
			System.out.println("Failed execution.");
		}

	}

	private static void print(String[] args) {
		if (args[0].equals("artist")) {
			//print out values
			//iterate this stuff...{
			
			memManager.getData(artists.get(args[0]));
			
			//}
			
		} else if (args[0].equals("song")) {
			memManager.getData(songs.get(args[0]));
		} else if (args[0].equals("blocks")) {

		}
	}
}
