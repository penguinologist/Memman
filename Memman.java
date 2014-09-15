import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Memman {
	private static int initialHashSize;
	private static int blockSize;
	private static String commandFile;

	public static void main(String[] args) {
		if (args.length != 3) {
			return;
		}

		initialHashSize = Integer.parseInt(args[0]);
		blockSize = Integer.parseInt(args[1]);
		commandFile = args[2];

		MemoryManager memManager = new MemoryManager();
		readCommandFile(commandFile);
	}

	public static void readCommandFile(String fileName) {
		Scanner inStream = null;

		try {
			inStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (inStream.hasNext()) {
			String cmdLine = inStream.nextLine();
			eval(cmdLine);
		}
	}

	private static void eval(String cmdLine) {
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

	}

	private static void remove(String[] args) {

	}

	private static void print(String[] args) {

	}
}
