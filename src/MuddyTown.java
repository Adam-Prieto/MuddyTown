//*****************************************************************************

/*
 * File notes:
 * File is able to read information from a file and add selected data to an
 * Array List variable.
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

        // Print random numbers
//        System.out.println("Initial: " + random);
//        getMultipleRandomNumbers(random, 8);

        // Scramble and add node names to Binary Tree
        Collections.shuffle(nodeNames);
        BinaryTree theTree = new BinaryTree();
        for (String nodeName : nodeNames)
        {
            theTree.addNode(nodeName);
        } // End for

        // Create and initialize a graph variable with all node names
        Graph g1 = new Graph(nodeNames, edgeNames);
        System.out.println(g1.adjVertices);



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

            // If either of the two nodes are unmarked, add the edge.
            if (!(theTree.findNode(strArray[1]).isMarked) ||
                !(theTree.findNode(strArray[2]).isMarked))
            {
                theTree.findNode(strArray[1]).isMarked = true;
                theTree.findNode(strArray[2]).isMarked = true;
                finalEdges.add(inputLine);
            } // End if

            // Todo: If both edges are marked, see if the edge makes a cycle.
            //  If not, add the edge to the final list; otherwise, discard.
            if(theTree.findNode(strArray[1]).isMarked &&
               theTree.findNode(strArray[2]).isMarked)
            {

            } // End if

            if ((finalEdges.size() == (nodeNames.size()) - 1)) break;
        } // End for

        // Verify necessary edges to construct MST
        if(finalEdges.size() != nodeNames.size()-1)
        {
            // Too few edges
            if(finalEdges.size() < nodeNames.size()-1)
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
     * @param random    - a random number to begin the calculations
     * @param totalRuns - the total number of runs or integers to be printed
     * @description - enter an initial guess and a loop parameter to get the
     * specified number of random numbers using LCM
     */
    private static void getMultipleRandomNumbers(int random, int totalRuns)
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
    private static void merge(ArrayList<String> inputArray,
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
} // End MuddyTown class