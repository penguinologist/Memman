import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
// -------------------------------------------------------------------------
/**
 * The memman class in all its glory
 * @author Phuong Le(Ldp91)
 * @author Jeroen
 * @version 2014.09.14
 */
public class Memman
{
    private static int                  initialHashSize;
    private static int                  blockSize;
    private static String               commandFile;
    private static Hash<String, Handle> artists;
    private static Hash<String, Handle> songs;
    private static MemoryManager        memManager;


    // ----------------------------------------------------------
    /**
     * main executable command
     *
     * @param args to be passed along
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
        artists = new Hash<String, Handle>(initialHashSize);
        songs = new Hash<String, Handle>(initialHashSize);
        memManager = new MemoryManager(blockSize); // TODO
                                                   // should
                                                   // we link
        readCommandFile(commandFile);
    }


    // ----------------------------------------------------------
    /**
     * reads a command file
     *
     * @param fileName of the file
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
        String command = cmdLine.substring(0, cmdLine.indexOf(' ')).trim();
        cmdLine = cmdLine.substring(cmdLine.indexOf(' ') + 1).trim();
        String[] args = new String[3];
        if (command.equalsIgnoreCase("insert"))// insert
                                               // {artist-name}<SEP>{song-name}
        {
            args[0] = cmdLine.substring(0, cmdLine.indexOf("<SEP>")).trim();
            args[1] = cmdLine.substring(cmdLine.indexOf(">") + 1).trim();
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
            args[0] = cmdLine.trim();
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
            if (!artists.containsKey(args[0], memManager))
            {
                if (artists.getItems() + 1 > artists.size() / 2)
                {
                    DoubleLinkedList<Handle> listOfArtists =
                        artists.getValues();
                    artists = new Hash<String, Handle>(artists.size() * 2);
                    for (int i = 0; i < listOfArtists.getSize(); i++)
                    {
                        artists.put(memManager.getData(listOfArtists
                            .getElement(i).getStartPosition()), listOfArtists
                            .getElement(i),memManager);
                    }

                    System.out.println("Artist hash table size doubled.");
                }
                artists.put(
                    args[0],
                    new Handle(memManager.insert(args[0].getBytes())
                        .getStartPosition()),memManager);
                System.out.println("|" + args[0]
                    + "| is added to the artist database.");
            }
            else
            {
                System.out.println("|" + args[0]
                    + "| duplicates a record already in the artist database.");
            }

            if (!songs.containsKey(args[1], memManager))
            {
                if (songs.getItems() + 1 > songs.size() / 2)
                {
                    DoubleLinkedList<Handle> listOfSongs = songs.getValues();
                    songs = new Hash<String, Handle>(songs.size() * 2);
                    for (int i = 0; i < listOfSongs.getSize(); i++)
                    {
                        songs.put(memManager.getData(listOfSongs.getElement(i)
                            .getStartPosition()), listOfSongs.getElement(i),memManager);
                    }

                    System.out.println("Song hash table size doubled.");
                }
                songs.put(
                    args[1],
                    new Handle(memManager.insert(args[1].getBytes())
                        .getStartPosition()),memManager);
                System.out.println("|" + args[1]
                    + "| is added to the song database.");
            }
            else
            {
                System.out.println("|" + args[0]
                    + "| duplicates a record already in the song database.");
            }
        }
    }


    private static void remove(String[] args)
    {
        if (args[0].compareToIgnoreCase("artist") == 0)
        {
            if (artists.containsKey(args[1], memManager))
            {
                int index = artists.get(args[1], memManager).getStartPosition();
                memManager.removeAt(index);
                artists.remove(args[1], memManager);
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
            if (songs.containsKey(args[1], memManager))
            {
                int index = songs.get(args[1], memManager).getStartPosition();
                memManager.removeAt(index);
                songs.remove(args[1], memManager);
                System.out.println("|" + args[1]
                    + "| is removed from the song database.");
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
            DoubleLinkedList<Handle> listOfArtists = artists.getValues();
            for (int i = 0; i < listOfArtists.getSize(); i++)
            {
                System.out.println("|"
                    + memManager.getData(listOfArtists.getElement(i)
                        .getStartPosition()) + "| "
                    + artists.indexOfValue(listOfArtists.getElement(i)));
            }
            System.out.println("total artists: " + listOfArtists.getSize());
        }
        else if (args[0].equals("song"))
        {
            DoubleLinkedList<Handle> listOfSongs = songs.getValues();
            for (int i = 0; i < listOfSongs.getSize(); i++)
            {
                System.out.println("|"
                    + memManager.getData(listOfSongs.getElement(i)
                        .getStartPosition()) + "| "
                    + songs.indexOfValue(listOfSongs.getElement(i)));
            }
            System.out.println("total songs: " + listOfSongs.getSize());
        }
        else if (args[0].equals("blocks"))
        {
            System.out.println(memManager.getFreeMemory());
        }
    }
}
