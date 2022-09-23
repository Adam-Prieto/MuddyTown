import java.util.Objects;

public class BinaryTree
{
    Node root;

    public void addNode(String address)
    {
        Node newNode = new Node(address);

        if (root == null)
        {
            root = newNode;
        } // End if

        else
        {
            Node focusPointNode = root;
            Node parentNode;

            while (true)
            {
                parentNode = focusPointNode;

                // Add to left if smaller
                if (address.compareTo(focusPointNode.address) < 0)
                {
                    focusPointNode = focusPointNode.leftChild;

                    if (focusPointNode == null)
                    {
                        parentNode.leftChild = newNode;
                        return; 
                    } // End if
                } // End if

                // Add to right if larger
                else
                {
                    focusPointNode = focusPointNode.rightChild;

                    if (focusPointNode == null)
                    {
                        parentNode.rightChild = newNode;
                        return;
                    } // End if
                } // End else
            } // End while
        } // End else
    } // End addNode

    public void inOrderTraversal(Node focusPointNode)
    {
        if (focusPointNode != null)
        {
            inOrderTraversal(focusPointNode.leftChild);
            System.out.println(focusPointNode.address);

            inOrderTraversal(focusPointNode.rightChild);

        } // End if
    } // End inOrderTraversal

    public boolean areAllNodesMarked(Node focusPointNode)
    {
        if (focusPointNode != null)
        {
            areAllNodesMarked(focusPointNode.leftChild);

            if(!focusPointNode.isMarked)
            {
                return false;
            } // End if

            areAllNodesMarked(focusPointNode.rightChild);

        } // End if
        return true;
    } // End areAllNodesMarked

    public Node findNode(String address)
    {
        Node focusPointNode = root;

        while (!Objects.equals(focusPointNode.address, address))
        {
            if (address.compareTo(focusPointNode.address) < 0)
            {
                focusPointNode = focusPointNode.leftChild;
            } // End if
            else
            {
                focusPointNode = focusPointNode.rightChild;
            } // End else

            if (focusPointNode == null) return null;
        } // End while

        return focusPointNode;
    } // End findNode
} // End BinaryTree class

class Node
{
    String address;
    boolean isMarked = false;

    Node leftChild;
    Node rightChild;

    Node(String address)
    {
        this.address = address;
    } // End constructor

    public String getMarkedStatus()
    {
        if (isMarked) return (address + " is marked.");

        return (address + " is not marked.");
    } // End getMarkedStatus
} // End Node class
