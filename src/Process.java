import java.io.*;
import java.util.*;


public class Process
{
    public ArrayList<String> myStrings(String path) throws FileNotFoundException
    {
        ArrayList<String> myList = new ArrayList<>();
        Scanner inputFile = new Scanner(new File(path));
        String inputLine;

        while (inputFile.hasNext())
        {
            inputLine = inputFile.nextLine();

            // If the first char is a size, then add it to the array list
            int test = inputLine.charAt(0) - '0';
            if (test < 10 && test > 0)
            {
                myList.add(inputLine);
            } // End if
        } // End while
        return (myList);
    } // End myStrings
} // End Process class
