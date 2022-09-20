import java.io.*;
import java.util.*;


public class Process
{
    public ArrayList<String> getEdgeNames(String path) throws FileNotFoundException
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
    } // End getEdgeNames

    public ArrayList<String> getNodeNames(ArrayList<String> edgeNames)
    {
        String[] strArray;
        ArrayList<String> nodeNames = new ArrayList<>();

        for (String edgeName : edgeNames)
        {
            strArray = edgeName.split(",");
            for (String s : strArray)
            {
                String temp1 = s.replaceAll("\"", "");
                if (temp1.length() > 1 && !(nodeNames.contains(temp1)))
                {
                    nodeNames.add(temp1);
                } // End if
            } // End for
        } // End for

        return (nodeNames);
    } // End getNodeNames
} // End Process class
