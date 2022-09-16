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
        Scanner inputFile = new Scanner(new File(macPath));
        Random number = new Random();
        int random = number.nextInt(5) + 1;
        ArrayList<String> nodeNames = new ArrayList<>();
        String[] strArray = null;


        // Get and process file contents
        Process t = new Process();
        ArrayList<String> edgeNames = t.myStrings(macPath);

        // Print original list
        System.out.println("Original list:");
        printList(edgeNames);

        // Sort list
        mergeSort(edgeNames);

        // Print sorted list
        System.out.println("Sorted list:");
        printList(edgeNames);

        // Gets node names from edge names
        for (String edgeName : edgeNames)
        {
            strArray = edgeName.split(",");
            for (String s : strArray)
            {
                String temp1 = s.replaceAll("\"", "");
                if (temp1.length() > 1 && !(nodeNames.contains(temp1)))
                {
                    nodeNames.add(temp1);
                } // End for
            } // End for
        } // End for

        System.out.println(nodeNames);

        // Print random numbers
//        System.out.println("Initial: " + random);
//        for (int i = 0; i < 20; i++)
//        {
//            int j = getLcm(random) % 721;
//            System.out.println("Random Number: " + j);
//            random = j;
//        } // End for


        // Close file
        inputFile.close();
    } // End main

    public static int getLcm(int input)
    {
        int A = 5, B = 3, M = 7;
        return (input * A + (B % M));
    } // End getLcm

    public static void bubbleSort(ArrayList<String> edgeNames)
    {
        String temp;
        for (int i = 0; i < edgeNames.size(); i++)
        {
            for (int j = i + 1; j < edgeNames.size(); j++)
            {
                // Compare strings
                if (edgeNames.get(i).compareTo(edgeNames.get(j)) > 0)
                {
                    // Swap
                    temp = edgeNames.get(i);
                    edgeNames.set(i, edgeNames.get(j));
                    edgeNames.set(j, temp);
                } // End if
            } // End for
        } // End for
    }  // End bubbleSort

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

    public static void printList(ArrayList<String> edgeNames)
    {
        for (String s : edgeNames)
        {
            System.out.println(s);
        } // End for
        System.out.println();
    } // End printList
} // End MuddyTown class