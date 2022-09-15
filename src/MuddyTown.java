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
        String path = "C:\\Users\\adamp\\MSU Denver\\CS4050\\Projects" +
                "\\Muddy Town\\src\\MiniTown copy.txt";
        Scanner inputFile = new Scanner(new File(path));
        Random number = new Random();
        int random = number.nextInt(5) + 1;


        // Get and process file contents
        Process t = new Process();
        ArrayList<String>myList = t.myStrings(path);

        // Print original list
        System.out.println("Original list:");
        printList(myList);

        // Sort list
        mergeSort(myList);

        // Print sorted list
        System.out.println("Sorted list:");
        printList(myList);

        // Print random numbers
        System.out.println("Initial: " + random);
        for (int i = 0; i < 10; i++)
        {
            int j = getLcm(random) % 721;
            System.out.println("Random Number: " + j);
            random = j;
        } // End for


        // Close file
        inputFile.close();
    } // End main

    public static int getLcm(int input)
    {
        int A = 5, B = 3, M = 7;
        return (input * A + (B % M));
    } // End getLcm

    public static void bubbleSort(ArrayList<String> myList)
    {
        String temp;
        for (int i = 0; i < myList.size(); i++)
        {
            for (int j = i + 1; j < myList.size(); j++)
            {
                // Compare strings
                if (myList.get(i).compareTo(myList.get(j)) > 0)
                {
                    // Swap
                    temp = myList.get(i);
                    myList.set(i, myList.get(j));
                    myList.set(j, temp);
                } // End if
            } // End for
        } // End for
    }  // End bubbleSort

    public static void mergeSort(ArrayList<String> myList)
    {
        int inputLength = myList.size();

        if (inputLength < 2)
        {
            return;
        } // End if

        int midIndex = inputLength / 2;
        ArrayList<String> leftHalf = new ArrayList<>(midIndex);
        ArrayList<String> rightHalf = new ArrayList<>(inputLength - midIndex);

        for (int i = 0; i < midIndex; i++)
        {
            leftHalf.add(myList.get(i));
        } // End for
        for (int i = midIndex; i <= inputLength; i++)
        {
            if (!leftHalf.contains(myList.get(i - midIndex)))
            {
                rightHalf.add(myList.get(i - midIndex));
            } // End if
        } // End for
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(myList, leftHalf, rightHalf);
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

    public static void printList(ArrayList<String> myList)
    {
        for (String s : myList)
        {
            System.out.println(s);
        } // End for
        System.out.println();
    } // End printList
} // End MuddyTown class