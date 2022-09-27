//*****************************************************************************

/*
 * Students: Adam Prieto, Bishnu Bhusal, Brandon DeRosier
 * Professor: Dr. Dody Paul
 * Class: CS 4050 - Algorithm Analysis
 * Date: 27 September 2022
 *
 * Description: This program is an implementation of Kruskal's algorithm — we
 *              read in information from a file (preferable csv), determine
 *              edge relevancy, sort the edges, get nodes from the edges,
 *              sort the edges, and then determine whether the edges belong
 *              in a final array list before outputting the final result to
 *              the console and an external file for future analysis. This
 *              was a pain to write.
 * */

import java.io.*;
import java.util.*;


public class MuddyTown
{
    public static void main(String[] args) throws FileNotFoundException
    {
        // Variables
        String windowsPath = "C:\\Users\\adamp\\MSU Denver\\CS4050\\Projects" +
                             "\\Muddy Town\\src\\MiniTown copy.txt";
        String macPath = "/Users/adamprieto/Education/MSU Denver/CS 4050/" +
                         "All Programs/Muddy Town/MuddyTown/src/" +
                         "MiniTown copy.txt";
        String newMudville = "/Users/adamprieto/Education/MSU Denver/" +
                             "CS 4050/All Programs/Muddy Town/" +
                             "MuddyTown/src/New Mudville.txt";
        String randomTown = "/Users/adamprieto/Education/MSU Denver/CS 4050" +
                            "/All Programs/Muddy Town/MuddyTown/src/" +
                            "randomTown.txt";
        String coloradoMap = "/Users/adamprieto/Education/MSU Denver" +
                             "/CS 4050/All Programs/Muddy Town/MuddyTown/" +
                             "src/Colorado Plan.txt";
        String wholeUSA = "/Users/adamprieto/Education/MSU Denver/CS 4050/" +
                          "All Programs/Muddy Town/MuddyTown/src/" +
                          "Continental United States.txt";
        Scanner inputFile = new Scanner(new File(newMudville));
        Random number = new Random();
        int random = number.nextInt(5) + 1;
        ArrayList<String> nodeNames;
        ArrayList<String> edgeNames;
        ArrayList<String> finalEdges = new ArrayList<>();


        // Get edge and node names from the file
        Process t = new Process();
        edgeNames = t.getEdgeNames(newMudville);
        nodeNames = t.getNodeNames(edgeNames);


        // Print original data
        System.out.println("Original list:");
        printList(edgeNames);
        System.out.println("Node Names:");
        printList(nodeNames);
        System.out.println("Initial list total weight: " +
                           getTotalWeight(edgeNames) + "\n");


        // Sort edges
        mergeSort(edgeNames);


        // Print sorted edges
        System.out.println("Sorted edges:");
        printList(edgeNames);


        // Scramble and add node names to Binary Tree
        Collections.shuffle(nodeNames);
        BinaryTree theTree = new BinaryTree();
        for (String nodeName : nodeNames)
        {
            theTree.addNode(nodeName);
        } // End for

        // Todod: add edges to a final array list, performing periodic checks
        //  along the way (works for muddy town and new mudville).

        for (String inputLine : edgeNames)
        {
            // Clean up strings
            String[] strArray = inputLine.split(",");
            for (int j = 0; j < strArray.length; j++)
            {
                String temp = strArray[j].replaceAll("\"", "");
                strArray[j] = temp;
            } // End for

            Node n1 = theTree.findNode(strArray[1]);
            Node n2 = theTree.findNode(strArray[2]);

            // If either of the two nodes are unmarked, add the edge.
            if (!(n1.isMarked) || !(n2.isMarked))
            {
                n1.isMarked = true;
                n2.isMarked = true;
                finalEdges.add(inputLine);
                if ((finalEdges.size() == (nodeNames.size()) - 1)) break;
                continue;
            } // End if


            // Todod: If both nodes are marked, see if the edge makes a cyclic
            //  graph. If not, add the edge to the final list; otherwise,
            //  discard.
            else
            {
                // Create variables
                Graph g1 = new Graph(edgeNames.size());
                ArrayList<String> currentEdges = new ArrayList<>(finalEdges);
                ArrayList<String> connections = new ArrayList<>();

                // Initialize locationToInt and fill it w/ relevant data
                HashMap<String, Integer> locationToInt = new HashMap<>();
                for (int i = 0; i < nodeNames.size(); i++)
                {

                    String s4 = nodeNames.get(i);
                    if ((s4.contains(" ")))
                    {
                        s4 = s4.replaceAll(" ", "");
                    } // End if

                    locationToInt.put(s4, i);
                } // End for

                // Get connections "<Node Name1> <Node Name2>"
                for (int i = 0; i < currentEdges.size(); i++)
                {
                    StringBuilder s1 = new StringBuilder(currentEdges.get(i));

                    // Delete unnecessary stuff
                    deleteEdgeWeight(s1);
                    deleteCommas(s1);
                    deleteQuotes(s1);
                    s1.replace(0, 2, "");
                    connections.add(s1.toString());
                } // End for

                for (int h = 0; h < connections.size(); h++)
                {
                    String[] temp = connections.get(h).split(" {2}");

                    // Format each entry from temp array.
                    for (int i = 0; i < temp.length; i++)
                    {
                        StringBuilder s1 = new StringBuilder(temp[i]);

                        // Remove non-letters
                        for (int j = 0; j < s1.length(); j++)
                        {
                            if (!isLetter(s1.charAt(j)) && isLetter(s1.charAt(j)))
                            {
                                s1.replace(j, j + 1, "");
                            } // End if
                        } // End for

                        // Delete everything after the comma
                        if (doesStringContain(String.valueOf(s1), ','))
                        {
                            int g = findInStr(String.valueOf(s1), ',');
                            s1.delete(g, s1.length());
                        } // End if

                        // Formatting
                        String s2 = String.valueOf(s1).replaceAll("]", "");
                        s2 = s2.replace("[", "");
                        temp[i] = s2;
                    } // End for


                    if (temp[0] != null && temp[1] != null)
                    {
                        String s1 = temp[0].replaceAll("\\s", "");
                        String s2 = temp[1].replaceAll("\\s", "");
                        int one = Integer.parseInt(String.valueOf(locationToInt.get(s1)));
                        int two = Integer.parseInt(String.valueOf(locationToInt.get(s2)));

                        g1.addEdge(one, two);
                    } // End if
                } // End outer for

                if (g1.isCyclic())
                {
                    n1.isMarked = true;
                    n2.isMarked = true;
                    continue;
                } // End if
                else
                {
                    finalEdges.add(inputLine);
                } // End else
            } // End else

            if ((finalEdges.size() == (nodeNames.size()) - 1)) break;
        } // End for

        // Verify necessary edges to construct MST
        if (finalEdges.size() != nodeNames.size() - 1)
        {
            // Too few edges
            if (finalEdges.size() < nodeNames.size() - 1)
            {
                System.out.println("Insufficient number of edges to\n" +
                                   "construct a minimum spanning tree.\n" +
                                   "\nCurrent List:");
                printList(finalEdges);
                System.out.println("Total weight: " + getTotalWeight(finalEdges));
                return;
            } // End if

            // Too many edges
            System.out.println("Excessively many edges to construct" +
                               "a minimum spanning tree.\n" +
                               "\nCurrent List:");
            printList(finalEdges);
            System.out.println("Total weight: " + getTotalWeight(finalEdges));
            return;
        } // End if

        // Verify all nodes are marked
        if (!theTree.areAllNodesMarked(theTree.root))
        {
            System.out.println("Insufficient number of nodes to\n" +
                               "construct a minimum spanning tree.\n" +
                               "\nCurrent List:");
            printList(finalEdges);
            System.out.println("Total weight: " + getTotalWeight(finalEdges));
            return;
        } // End if


        // List passes all checks, print MST to console.
        System.out.println("Minimum Spanning Tree list: ");
        printList(finalEdges);
        System.out.println("Total weight: " + getTotalWeight(finalEdges));


        // Close file
        inputFile.close();
    } // End main

    //***********************************************************************

    /**
     * @param s1 - a string builder w/ quotes.
     * @description - this method deletes the aforementioned quotes from a
     * string builder object.
     */
    public static void deleteQuotes(StringBuilder s1)
    {
        for (int j = 0; j < s1.length(); j++)
        {
            if (s1.charAt(j) == '\"')
            {
                s1.replace(j, j + 1, " ");
                j--;
            } // End if
        } // End for
    } // End deleteQuotes

    //***********************************************************************

    /**
     * @param s1 - a string builder w/ commas.
     * @description - this method deletes the aforementioned commas from a
     * string builder object.
     */
    public static void deleteCommas(StringBuilder s1)
    {
        for (int j = 0; j < s1.length(); j++)
        {
            if (s1.charAt(j) == ',')
            {
                s1.replace(j, j + 1, " ");
                j--;
            } // End if
        } // End for
    } // End deleteCommas

    //***********************************************************************

    /**
     * @param s1 - a string that represents an edge between two nodes.
     * @description - this method deletes the numeric part (weight) of an
     * edge.
     */
    public static void deleteEdgeWeight(StringBuilder s1)
    {
        for (int j = 0; j < findInStr(String.valueOf(s1), ','); j++)
        {
            if (hasInt(s1.charAt(j)))
            {
                s1.delete(j, j + 1);
                j--;
            } // End if
        } // End for
    } // End deleteEdgeWeight

    //***********************************************************************

    /**
     * @param random    - a random number to begin the calculations
     * @param totalRuns - the total number of runs or integers to be printed
     * @description - enter an initial guess and a loop parameter to get the
     * specified number of random numbers using LCM
     */
    public static void getMultipleRandomNumbers(int random, int totalRuns)
    {
        for (int i = 0; i < totalRuns; i++)
        {
            int j = getLcm(random) % 721;
            System.out.println(j);
            random = j;
        } // End for
    } // End getMultipleRandomNumbers

    //***********************************************************************

    /**
     * @param allEdgeNames - the initial list of edges.
     * @return - the total weight of the list
     * @description - enter an array list of edges to get the total cost from
     * those edges.
     */
    public static int getTotalWeight(ArrayList<String> allEdgeNames)
    {
        // Variable initialization
        int totalWeight = 0;
        int firstOccurrence;
        StringBuilder str;

        // Add current edge weight to total weight
        for (String currentEdge : allEdgeNames)
        {
            str = new StringBuilder(currentEdge);
            firstOccurrence = currentEdge.indexOf(',');
            str.delete(firstOccurrence, currentEdge.length());
            totalWeight += Integer.parseInt(str.toString());
        } // End for

        return totalWeight;
    } // End getTotalWeight

    //***********************************************************************

    /**
     * @param input - an initial guess.
     * @return - the corresponding LCM value post-calculation.
     * @description - enter a number and get the LCM value back.
     */
    public static int getLcm(int input)
    {
        int A = 5, B = 3, M = 7;
        return (input * A + (B % M));
    } // End getLcm

    //***********************************************************************

    /**
     * @param edgeNames - list of edges to be sorted.
     * @description - perform merge sort on array list of edges (first part
     * of Kruskal's algorithm and part 1 of 2 for whole of
     * merge sort).
     */
    public static void mergeSort(ArrayList<String> edgeNames)
    {
        int inputLength = edgeNames.size();

        if (inputLength < 2)
        {
            return;
        } // End if

        int midIndex = inputLength / 2;
        ArrayList<String> leftHalf = new ArrayList<>(midIndex);
        ArrayList<String> rightHalf = new ArrayList<>(inputLength - midIndex);

        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(edgeNames.get(i));
        } // End for

        for (int i = midIndex; i < inputLength; i++)
        {
            String t1 = edgeNames.get(i);
            if (!leftHalf.contains(t1))
            {
                String temp1 = edgeNames.get(i);
                rightHalf.add(temp1);
            } // End if
        } // End for

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(edgeNames, leftHalf, rightHalf);
    } // End mergeSort

    //***********************************************************************

    /**
     * @param inputArray - the initial array to be merged.
     * @param leftHalf   - left half of the "to be sorted" array.
     * @param rightHalf  - right "".
     */
    public static void merge(ArrayList<String> inputArray,
                             ArrayList<String> leftHalf,
                             ArrayList<String> rightHalf)
    {
        int leftSize = leftHalf.size();
        int rightSize = rightHalf.size();

        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize)
        {
            // Create new string builder variables with the left and right
            // halves.
            StringBuilder s1 = new StringBuilder(leftHalf.get(i));
            StringBuilder s2 = new StringBuilder(rightHalf.get(j));

            // Remove everything from the string builders after the first
            // comma, leaving a nice integer.
            s1.delete(s1.indexOf(","), s1.length());
            s2.delete(s2.indexOf(","), s2.length());

            // Convert the string builders to ints and initialize new
            // variables with appropriate values.
            int test1 = Integer.parseInt(String.valueOf(s1));
            int test2 = Integer.parseInt(String.valueOf(s2));

            if (test1 <= test2)
            {
                inputArray.set(k, leftHalf.get(i));
                i++;
            } // End if
            else
            {
                inputArray.set(k, rightHalf.get(j));
                j++;
            } // End else
            k++;
        } // End while

        while (i < leftSize)
        {
            inputArray.set(k, leftHalf.get(i));
            i++;
            k++;
        } // End while

        while (j < rightSize)
        {
            inputArray.set(k, rightHalf.get(j));
            j++;
            k++;
        } // End while
    } // End merge

    //***********************************************************************

    /**
     * @param edgeNames - the array list to be printed
     * @description - this method takes in an array list of strings and outputs
     * the respective entries from that list on a new line.
     */
    public static void printList(ArrayList<String> edgeNames)
    {
        for (String s : edgeNames)
        {
            System.out.println(s);
        } // End for
        System.out.println();
    } // End printList

    //***********************************************************************

    /**
     * @param input - "Does this particular char match one of the
     *              corresponding switch cases?"
     * @return - true if char is an int, false otherwise.
     * @description - See above statement for input
     */
    public static boolean hasInt(char input)
    {

        switch (input)
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        } // End switch

        return false;
    } //

    //***********************************************************************

    /**
     * @param s1 - the string to be searched
     * @param c  - the character we want to search s1 for.
     * @return - the value where the char first appears.
     * @description - this method searches an input string for a particular
     * char and returns the corresponding value where the char
     * first appears.
     */
    public static int findInStr(String s1, char c)
    {
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) == c)
            {
                return i;
            }
        }
        return -1;
    } // End findInStr

    //***********************************************************************

    /**
     * @param s1 - the string to be searched
     * @return - true/ false based on whether c was/ wasn't found in s1.
     * @description - this method searches an input string for a particular
     * char and returns true if the value is found, false
     * otherwise.
     */
    public static boolean doesStringContain(String s1, char c)
    {

        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.charAt(i) == c)
            {
                return true;
            }
        }
        return false;
    } // End doesStringContain

    //***********************************************************************

    /**
     * @param input - a string to be studied.
     * @return - true/ false based on whether input is an int (0-9).
     * @description - this method determines if the inputted string matches
     * one of the switch cases.
     */
    public static boolean isNumber(String input)
    {
        switch (input)
        {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return true;
        } // End switch


        switch (input.charAt(0))
        {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        } // End switch

        return false;
    } // End isNumber

    //***********************************************************************

    /**
     * @param input - a char to be studied.
     * @return - true if letter (a-z), false otherwise.
     * @description - this method takes a char and returns true/ false on
     * whether it's a letter (a-z).
     */
    public static boolean isLetter(char input)
    {

        String s1 = Character.toString(input).toLowerCase();
        switch (s1)
        {
            case "a":
            case "b":
            case "c":
            case "d":
            case "e":
            case "f":
            case "g":
            case "h":
            case "i":
            case "j":
            case "k":
            case "l":
            case "m":
            case "n":
            case "o":
            case "p":
            case "q":
            case "r":
            case "s":
            case "t":
            case "u":
            case "v":
            case "w":
            case "x":
            case "y":
            case "z":
                return true;
        } // End switch
        return false;
    } // End isLetter
} // End MuddyTown class