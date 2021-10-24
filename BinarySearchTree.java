
import java.util.*;

// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */


public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( null);
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( null);
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }

    
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program

    public void printPreOrder(BinaryNode<AnyType>currentNode)
    {
        if(currentNode == null)
            return;

        System.out.print(currentNode.element + " ");
        printPreOrder(currentNode.left);
        printPreOrder(currentNode.right);

    }

    public void printInorder(BinaryNode<AnyType>currentNode)
    {
        if(currentNode == null)
            return;

        printInorder(currentNode.left);
        System.out.print(currentNode.element+" ");
        printInorder(currentNode.right);
    }

    public void printPostorder(BinaryNode<AnyType>currentNode)
    {
        if(currentNode == null)
            return;

        printPostorder(currentNode.left);
        printPostorder(currentNode.right);
        System.out.print(currentNode.element+" ");
    }

    public void printPreOrder()
    {
        System.out.println("Pre order traversal of tree");
        printPreOrder(root);
        System.out.println();
    }
    public void printPostorder()
    {
        System.out.println("Post order traversal of tree");
        printPostorder(root);
        System.out.println();
    }
    public void printInorder()
    {
        System.out.println("Inorder traversal of tree");
        printInorder(root);
        System.out.println();
    }

    // #################################### Node count function ###########################
    public int nodeCount(BinaryNode<AnyType>currentNode){

        if(currentNode == null)
            return 0;

        return 1 + this.nodeCount(currentNode.left) + this.nodeCount(currentNode.right);


    }

    // #################################### Function to compare structure of trees ###########################
    public boolean compareStructure(BinarySearchTree<AnyType>argumentTree)
    {
        return compareStructure(root, argumentTree.root);
    }
    public boolean compareStructure(BinaryNode<AnyType>currentNodeTree1, BinaryNode<AnyType>currentNodeTree2)
    {
        if(currentNodeTree1 == null && currentNodeTree2 == null)
            return true;

        if(currentNodeTree1 == null || currentNodeTree2 == null)
            return false;

        return compareStructure(currentNodeTree1.left, currentNodeTree2.left) && compareStructure(currentNodeTree1.right, currentNodeTree2.right);
    }

    // #################################### function to check if a tree is Full ###########################
    public boolean isFull(BinaryNode<AnyType>currentNode){

        if(currentNode == null)
            return true;

        if(currentNode.left == null && currentNode.right == null)
            return true;

        if(currentNode.left == null || currentNode.right == null)
            return false;

        return isFull(currentNode.left) && isFull(currentNode.right);
    }

    public boolean isFull()
    {
        return isFull(root);
    }

// #################################### function to check if two trees are equal ###########################
    public boolean equals(BinarySearchTree<AnyType>argumentTree)
    {
        return equals(root, argumentTree.root);
    }
    public boolean equals(BinaryNode<AnyType>currentNodeTree1, BinaryNode<AnyType>currentNodeTree2)
    {
        if(currentNodeTree1 == null && currentNodeTree2 == null)
            return true;

        if(currentNodeTree1 == null || currentNodeTree2 == null)
            return false;

        if(currentNodeTree1.element != currentNodeTree2.element)
            return false;

        return equals(currentNodeTree1.left, currentNodeTree2.left) && equals(currentNodeTree1.right, currentNodeTree2.right);
    }
    
    // #################################### function to create a copy of a tree ###########################
    public BinarySearchTree<AnyType>copy()
    {
        BinarySearchTree<AnyType>newTree = new BinarySearchTree<>();
        newTree.root = copy(root);
        return newTree;
    }

    public BinaryNode<AnyType>copy(BinaryNode<AnyType>currentNodeOrignalTree)
    {
        if(currentNodeOrignalTree == null)
            return null;

        BinaryNode<AnyType>newNode = new BinaryNode<>(currentNodeOrignalTree.element);

        BinaryNode<AnyType>leftCopy = copy(currentNodeOrignalTree.left);
        BinaryNode<AnyType>rightCopy = copy(currentNodeOrignalTree.right);

        newNode.left = leftCopy;
        newNode.right = rightCopy;

        return newNode;
        
    }

    // #################################### function to create a mirror image of a tree ###########################
    public BinarySearchTree<AnyType>mirror()
    {
        BinarySearchTree<AnyType>mirrorTree = new BinarySearchTree<>();
        mirrorTree.root = mirror(root);
        return mirrorTree;
    }

    public BinaryNode<AnyType>mirror(BinaryNode<AnyType>currentNode)
    {
        if(currentNode == null)
            return currentNode;

        BinaryNode<AnyType>leftInverted = mirror(currentNode.left);
        BinaryNode<AnyType>rightInverted = mirror(currentNode.right);

        BinaryNode<AnyType>newNode = new BinaryNode<>(currentNode.element);

        newNode.left = rightInverted;
        newNode.right = leftInverted;
        return newNode;
    }

    // #################################### Function to check if two trees are mirrir images of each other ###########################
    public boolean isMirror(BinarySearchTree<AnyType>treeToBeCompared)
    {
        return isMirror(root, treeToBeCompared.root);
    }

    public boolean isMirror(BinaryNode<AnyType>root1, BinaryNode<AnyType>root2)
    {
        if(root1 == null && root2 == null)
            return true;
        
        if(root1 == null || root2 == null || root1.element != root2.element )
            return false;
        
        
        
            return isMirror(root1.right, root2.left) && isMirror(root1.left, root2.right);
    }

    // #################################### function to rotate left ###########################
    public void rotateLeft(AnyType element)
    {
        rotateLeft(root, element);
    }

    public void rotateLeft(BinaryNode<AnyType>currentNode, AnyType element)
    {
        if(currentNode == null)
            return;

        if(element.compareTo(currentNode.element) < 0)
        {
            if(currentNode.left != null && element.compareTo(currentNode.left.element) == 0)
            {
                BinaryNode<AnyType>formerChild = currentNode.left;
                BinaryNode<AnyType>newChild = currentNode.left.right;
                if(newChild != null)
                {
                    formerChild.right = newChild.left;
                    newChild.left = formerChild;
                    currentNode.left = newChild;
                }
            }
            else
                rotateLeft(currentNode.left,  element);
           
        }
        else if(element.compareTo(currentNode.element) > 0)
        {
            if(currentNode.right != null && element.compareTo(currentNode.right.element) == 0)
            {
                BinaryNode<AnyType>formerChild = currentNode.right;
                BinaryNode<AnyType>newChild = currentNode.right.right;
                if(newChild != null)
                {
                    formerChild.right = newChild.left;
                    newChild.left = formerChild;
                    currentNode.right = newChild;
                }
            }
            else
                rotateLeft(currentNode.right, element);
        }
        else{

            // current node is root itself
            BinaryNode<AnyType>newRoot = currentNode.right;
            currentNode.right = newRoot.left;
            newRoot.left = currentNode;

            root = newRoot;

        }
    }

    // #################################### Function to rotate right ###########################
    public void rotateRight(AnyType element)
    {
        rotateRight(root, element);
    }

    public void rotateRight(BinaryNode<AnyType>currentNode, AnyType element)
    {
        if(currentNode == null)
            return;

        if(element.compareTo(currentNode.element) < 0)
        {
            if(currentNode.left != null && element.compareTo(currentNode.left.element) == 0)
            {
                BinaryNode<AnyType>formerChild = currentNode.left;
                BinaryNode<AnyType>newChild = currentNode.left.left;
                if(newChild != null)
                {
                    formerChild.left = newChild.right;
                    newChild.right = formerChild;
                    currentNode.left = newChild;
                }
            }
            else
                rotateRight(currentNode.left,  element);
           
        }
        else if(element.compareTo(currentNode.element) > 0)
        {
            if(currentNode.right != null && element.compareTo(currentNode.right.element) == 0)
            {
                BinaryNode<AnyType>formerChild = currentNode.right;
                BinaryNode<AnyType>newChild = currentNode.right.left;
                if(newChild != null)
                {
                    formerChild.left = newChild.right;
                    newChild.right = formerChild;
                    currentNode.right = newChild;
                }
            }
            else
                rotateRight(currentNode.right, element);
        }
        else{

            // current node is root itself
            BinaryNode<AnyType>newRoot = currentNode.left;
            currentNode.left = newRoot.right;
            newRoot.right = currentNode;

            root = newRoot;

        }
    }
    
   

    // #################################### function to print a tree level order ###########################

    public void printLevelorder(){

        if(root == null)
            return;

        Queue<BinaryNode<AnyType>>q1 = new LinkedList<>();
        Queue<BinaryNode<AnyType>>q2 = new LinkedList<>();

        q1.add(root);

        while(!q1.isEmpty() || !q2.isEmpty())
        {
            while(!q1.isEmpty())
            {
                BinaryNode<AnyType>currentNode = q1.remove();
                System.out.print(currentNode.element + " ");

                if(currentNode.left != null)
                    q2.add(currentNode.left);

                if(currentNode.right != null)
                    q2.add(currentNode.right);


            }

            while(!q2.isEmpty())
            {
                q1.add(q2.remove());
            }

            System.out.println();
        }

    }
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer>t = new BinarySearchTree<>();
        
        
        /*
        final int NUMS = 400;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
        {
            t.insert(i);
            
        }
        */

        t.insert(100);
        t.insert(50);
        t.insert(150);
        t.insert(40);
        t.insert(45);

        BinarySearchTree<Integer>copyTree = t.copy();
        BinarySearchTree<Integer>mirrorTree = t.mirror();

        int nodeCount = t.nodeCount(t.root);
        System.out.println("Node Count in orignalTree: "+nodeCount);

        //t.printInorder();
        //t.printPreOrder();
        //t.printPostorder();

        //System.out.println("Orignal tree");
        //t.printTree();
        //System.out.println("Printing the mirror tree");
        //mirrorTree.printTree();

        //System.out.println("first tree t, mirror of the passed tree: ");
        //System.out.println(t.isMirror(t.mirror()));
        //System.out.println("t and mirrorTree are same in structure: "+t.compareStructure(t.root, copyTree.root));

        
        //System.out.println("Rotating right around root");
        //t.rotateRight(50);

        //t.printInorder();
        //t.printPreOrder();
        //t.printPostorder();

        
        System.out.println("Level order printing of orignal tree");
        t.printLevelorder();

       

        System.out.println("Level order traversal of mirror tree");
        mirrorTree.printLevelorder();

        System.out.println("Copy tree and orignal tree are equal: "+t.equals(copyTree));

        System.out.println("Orignal tree and mirror tree have same structure: "+ t.compareStructure(mirrorTree));
        
        System.out.println("Orignal tree and copy tree have same structure: "+ t.compareStructure(copyTree));
        
        System.out.println("Orignal tree and mirror tree are mirror of each other: "+t.isMirror(mirrorTree));

        System.out.println("Orignal tree and copy tree are mirror of each other: "+t.isMirror(copyTree));
    }
}