import java.util.*;

public class Graph
{
    public HashMap<String, List<String>> adjVertices = new HashMap<>();
    public ArrayList<String> nodeNames;
    public ArrayList<String> edges;

    public Graph()
    {

    }

    public Graph(ArrayList<String> nodeNames)
    {
        this.nodeNames = nodeNames;
    }

    public Graph(ArrayList<String> nodeNames, ArrayList<String> edges)
    {
        this.nodeNames = nodeNames;
        this.edges = edges;


        for (int i = 0; i < edges.size(); i++)
        {
            // Parse and clean up strings
            String[] strArray = edges.get(i).split(",");
            for (int j = 0; j < strArray.length; j++)
            {
                String temp = strArray[j].replaceAll("\"", "");
                strArray[j] = temp;
            } // End for


            if (adjVertices.containsKey(strArray[1]))
            {
                String homeNode = strArray[1];
                List<String> tempList = adjVertices.get(strArray[1]);
                tempList.add(strArray[2]);
                adjVertices.put(homeNode, tempList);
            } // End if

            else
            {
                String homeNode = strArray[1];
                List<String> tempList = new ArrayList<>();
                tempList.add(strArray[2]);
                adjVertices.put(homeNode, tempList);
            } // End else
        } // End for

    } // End Constructor


}// End Graph class