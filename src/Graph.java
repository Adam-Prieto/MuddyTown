import java.util.*;
import java.io.*;

public class Graph
{

    // No. of vertices
    public int V;

    // Adjacency List Representation
    public LinkedList<Integer>[] adj;

    public Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
        {
            adj[i] = new LinkedList<>();
        } // End for
    } // End Constructor

    void addEdge(int v, int w)
    {
        adj[v].add(w);
        adj[w].add(v);
    } // End addEdge


    Boolean isCyclic()
    {

        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean[] visited = new Boolean[V];
        for (int i = 0; i < V; i++)
        {
            visited[i] = false;
        }

        // Call the recursive helper
        // function to detect cycle in
        // different DFS trees
        for (int u = 0; u < V; u++)
        {

            // Don't recur for u if already visited
            if (!visited[u])
            {
                if (isCyclicUtil(u, visited, -1))
                {
                    return true;
                }
            }
        }

        return false;
    } // End isCyclic

    Boolean isCyclicUtil(int v, Boolean[] visited, int parent)
    {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices
        // adjacent to this vertex
        for (Integer integer : adj[v])
        {
            i = integer;

            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            if (!visited[i])
            {
                if (isCyclicUtil(i, visited, v))
                {
                    return true;
                }
            }

            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
            {
                return true;
            }
        }
        return false;
    } // End isCyclicUtil

    public int getCorrespondingNumericValue(String s1)
    {
        int total = 0;
        for (int i = 0; i < s1.length(); i++)
        {
            total += numericValue(s1.charAt(i));
        }
        return total;
    } // End getCorrespondingNumericValue

    public int numericValue(char input)
    {
        switch (input)
        {
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case '0':
                return 10;
            case 'a':
                return 11;
            case 'b':
                return 12;
            case 'c':
                return 13;
            case 'd':
                return 14;
            case 'e':
                return 15;
            case 'f':
                return 16;
            case 'g':
                return 17;
            case 'h':
                return 18;
            case 'i':
                return 19;
            case 'j':
                return 20;
            case 'k':
                return 21;
            case 'l':
                return 22;
            case 'm':
                return 23;
            case 'n':
                return 24;
            case 'o':
                return 25;
            case 'p':
                return 26;
            case 'q':
                return 27;
            case 'r':
                return 28;
            case 's':
                return 29;
            case 't':
                return 30;
            case 'u':
                return 31;
            case 'v':
                return 32;
            case 'w':
                return 33;
            case 'x':
                return 34;
            case 'y':
                return 35;
            case 'z':
                return 36;
            case 'A':
                return 37;
            case 'B':
                return 38;
            case 'C':
                return 39;
            case 'D':
                return 40;
            case 'E':
                return 41;
            case 'F':
                return 42;
            case 'G':
                return 43;
            case 'H':
                return 44;
            case 'I':
                return 45;
            case 'J':
                return 46;
            case 'K':
                return 47;
            case 'L':
                return 48;
            case 'M':
                return 49;
            case 'N':
                return 50;
            case 'O':
                return 51;
            case 'P':
                return 52;
            case 'Q':
                return 53;
            case 'R':
                return 54;
            case 'S':
                return 55;
            case 'T':
                return 56;
            case 'U':
                return 57;
            case 'V':
                return 58;
            case 'W':
                return 59;
            case 'X':
                return 60;
            case 'Y':
                return 61;
            case 'Z':
                return 62;
            default:
                return 0;
        } // End switch
    } // End numericValue

    void DFSUtil(int v, boolean[] visited)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this vertex
        for (int n : adj[v])
        {
            if (!visited[n])
            {
                DFSUtil(n, visited);
            } // End if
        } // End while
    } // End DFSUtil

    // The function to do DFS traversal. It uses recursive DFSUtil()
    void depthFirstSearch()
    {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];

        // Call recursive helper function to print DFS traversal
        for (int i = 0; i < V; ++i)
        {
            if (!visited[i])
            {
                DFSUtil(i, visited);
            } // End if
        } // End for
    } // End depthFirstSearch
}// End Graph class