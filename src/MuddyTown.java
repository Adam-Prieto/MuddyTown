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
                "All Programs/Muddy Town/MuddyTown/src/" + "MiniTown copy.txt";
        Scanner inputFile = new Scanner(new File(windowsPath));
        Random number = new Random();
        int random = number.nextInt(5) + 1;
        ArrayList<String> nodeNames;
        ArrayList<String> edgeNames;
        ArrayList<String> finalEdges = new ArrayList<>();


        // Get edge and node names from the file
        Process t = new Process();
        edgeNames = t.getEdgeNames(windowsPath);
        nodeNames = t.getNodeNames(edgeNames);


        // Print original data
        System.out.println("Original list:");
        printList(edgeNames);
        System.out.println("Node Names:");
        printList(nodeNames);
        System.out.println("Initial list total weight: " +
                            getTotalWeight(edgeNames) + "\n");


        // Sort list
        mergeSort(edgeNames);


        // Print sorted edges
        System.out.println("Sorted edges:");
        printList(edgeNames);

        // Scramble nodes
        Collections.shuffle(nodeNames);
        System.out.println("Shuffled nodes: ");
        printList(nodeNames);


        // Print random numbers
//        System.out.println("Initial: " + random);
//        getMultipleRandomNumbers(random, 8);

        // Add node names to Binary Tree
        BinaryTree theTree = new BinaryTree();
        for (String nodeName : nodeNames)
        {
            theTree.addNode(nodeName);
        } // End for


        // Todo: add edges to a file, checking to make sure that the
        //  corresponding nodes aren't both already marked.

        for (String inputLine : edgeNames)
        {
            String[] strArray = inputLine.split(",");

            for (int j = 0; j < strArray.length; j++)
            {
                String temp = strArray[j].replaceAll("\"", "");
                strArray[j] = temp;
            } // End for

            // Check to make sure both nodes aren't already marked.
            if (!(theTree.findNode(strArray[1]).isMarked) || !(theTree.findNode(
                    strArray[2]).isMarked))
            {
                theTree.findNode(strArray[1]).isMarked = true;
                theTree.findNode(strArray[2]).isMarked = true;
                finalEdges.add(inputLine);
            } // End if

            if ((finalEdges.size() == (nodeNames.size()) - 1)) break;
        } // End for

        System.out.println("Final list: ");
        printList(finalEdges);
        System.out.println("Final list total weight: " +
                getTotalWeight(finalEdges));


        // Close file
        inputFile.close();
    } // End main

    //***********************************************************************

    /**
     * @description - enter an initial guess and a loop parameter to get the
     *                specified number of random numbers using LCM
     *
     * @param random - a random number to begin the calculations
     * @param totalRuns - the total number of runs or integers to be printed
     *
     * @return - void
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
     * @description  - enter an array list of edges to get the total cost from
     *                 those edges.
     * @param edgeNames - the initial list of edges.
     * @return - the total weight of the list
     */
    public static int getTotalWeight(ArrayList<String> edgeNames)
    {
        int totalWeight = 0;
        for (String edgeName : edgeNames)
        {
            totalWeight += edgeName.charAt(0) - '0';
        } // End for

        return totalWeight;
    } // End getTotalWeight

    //***********************************************************************

    /**
     * @description - enter a number and get the LCM value back.
     *
     * @param input - an initial guess.
     * @return - the corresponding LCM value post-calculation.
     */
    public static int getLcm(int input)
    {
        int A = 5, B = 3, M = 7;
        return (input * A + (B % M));
    } // End getLcm

    //***********************************************************************

    /**
     * @description - perform merge sort on array list of edges (first part
     *                of Kruskal's algorithm and part 1 of 2 for whole of
     *                merge sort).
     * @param edgeNames - list of edges to be sorted.
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
        for (int i = midIndex; i <= inputLength; i++)
        {
            if (!leftHalf.contains(edgeNames.get(i - midIndex)))
            {
                rightHalf.add(edgeNames.get(i - midIndex));
            } // End if
        } // End for
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(edgeNames, leftHalf, rightHalf);
    } // End mergeSort

    //***********************************************************************

    /**
     * @param inputArray - the initial array to be merged.
     * @param leftHalf - left half of the "to be sorted" array.
     * @param rightHalf - right "".
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
            String s1 = leftHalf.get(i);
            String s2 = rightHalf.get(j);

            int test1 = s1.charAt(0) - '0';
            int test2 = s2.charAt(0) - '0';


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
     * @description - this method takes in an array list of strings and outputs
     *                the respective entries from that list on a new line.
     * @param edgeNames - the array list to be printed
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