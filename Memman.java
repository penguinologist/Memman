import java.util.Collection;
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
public class Memman
{
    private static int                   initialHashSize;
    private static int                   blockSize;
    private static String                commandFile;
    private static Hash<String, Integer> artists;
    private static Hash<String, Integer> songs;
    private static MemoryManager         memManager;


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            return;
        }

        initialHashSize = Integer.parseInt(args[0]);
        blockSize = Integer.parseInt(args[1]);
        commandFile = args[2];
        artists = new Hash<String, Integer>(initialHashSize);
        songs = new Hash<String, Integer>(initialHashSize);
        memManager = new MemoryManager(blockSize); // TODO
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
    public static void readCommandFile(String fileName)
    {
        Scanner inStream = null;

        try
        {
            inStream = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        while (inStream.hasNext())
        {
            String cmdLine = inStream.nextLine();
            parseLine(cmdLine);
        }

        inStream.close();
    }


    private static void parseLine(String rawCommand)
    {
        String cmdLine = rawCommand.toString();
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
        }
        else if (command.equalsIgnoreCase("remove"))// remove {artist|song}
                                                    // {name}
        {
            args[0] = cmdLine.substring(0, cmdLine.indexOf(' ')).trim();
            args[1] = cmdLine.substring(cmdLine.indexOf(' ') + 1).trim();
            remove(args);
        }
        else if (command.equalsIgnoreCase("print"))// print
                                                   // {artist|song|blocks}
        {
            args[0] = cmdLine;
            print(args);
        }
        else
        {
            // Invalid Command
        }
    }


    private static void insert(String[] args)
    {
        if (args[0] != null && args[1] != null)
        {
            if (!artists.containsKey(args[0]))
            {
                if (artists.getItems() + 1 > artists.getCapacity() / 2)
                {
                    artists.extendHash();
                    System.out.println("Artist hash table size doubled.");
                }
                artists.put(args[0], memManager.insert(args[0].getBytes())
                    .getStartPosition());
                System.out.println("|" + args[0]
                    + "| is added to the artist database.");
            }
            if (!songs.containsKey(args[1]))
            {
                if (songs.getItems() + 1 > songs.getCapacity() / 2)
                {
                    songs.extendHash();
                    System.out.println("Song hash table size doubled.");
                }
                songs.put(args[1], memManager.insert(args[1].getBytes())
                    .getStartPosition());
                System.out.println("|" + args[1]
                    + "| is added to the song database.");
            }
        }
    }


    private static void remove(String[] args)
    {
        if (args[0].compareToIgnoreCase("artist") == 0)
        {
            if (artists.containsKey(args[1]))
            {
                int index = artists.get(args[1]);
                memManager.removeAt(index);
                artists.remove(args[1]);
                System.out.println("|" + args[1]
                    + "| is removed from the artist database.");
            }
            else
            {
                System.out.println("|" + args[1]
                    + "| does not exist in the artist database.");
            }
        }
        if (args[0].compareToIgnoreCase("song") == 0)
        {
            if (songs.containsKey(args[1]))
            {
                int index = songs.get(args[1]);
                memManager.removeAt(index);
                songs.remove(args[1]);
                System.out.println("|" + args[1]
                    + "| is removed from the songs database.");
            }
            else
            {
                System.out.println("|" + args[1]
                    + "| does not exist in the song database.");
            }
        }
    }


    private static void print(String[] args)
    {
        if (args[0].equals("artist"))
        {
            DoubleLinkedList<Integer> listOfArtists = artists.getValues();
            for (int i = 0; i < listOfArtists.getSize(); i++)
            {
                System.out.println("|"
                    + memManager.getData(listOfArtists.getElement(i))
                    + "|" + listOfArtists.getElement(i));
            }
            System.out.println("total artists: " + listOfArtists.getSize());
        }
        else if (args[0].equals("song"))
        {
            DoubleLinkedList<Integer> listOfSongs = songs.getValues();
            for (int i = 0; i < listOfSongs.getSize(); i++)
            {
                System.out.println("|"
                    + memManager.getData(listOfSongs.getElement(i))
                    + "|" + listOfSongs.getElement(i));
            }
            System.out.println("total songs: " + listOfSongs.getSize());
        }
        else if (args[0].equals("blocks"))
        {
            System.out.println("Didn't do LOL");
        }
    }
}
