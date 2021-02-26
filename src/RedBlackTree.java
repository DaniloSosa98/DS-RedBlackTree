
public class RedBlackTree {

    private Node current;
    private Node parent;
    private Node grand;
    private Node great;
    Node root;
    private static Node nullNode;

    static {

        nullNode = new Node(0);
        nullNode.leftNode = nullNode;
        nullNode.rightNode = nullNode;

    }

    static final int BLACK = 1;
    static final int RED = 0;

    //Constructor With Sentinel
    public RedBlackTree(int negInf) {
        root = new Node(negInf);
        root.leftNode = nullNode;
        root.rightNode = nullNode;
    }

    public void insert(int item) {

        System.out.println("\nInserting: " + item);

        grand = root;
        parent = grand;
        current = parent;

        nullNode.value = item;

        while (current.value != item) {

            great = grand;
            grand = parent;
            parent = current;

            if (item < current.value) {

                System.out.println(item + " is less than " + current.toString());
                current = current.leftNode;

            } else {

                System.out.println(item + " is greater than " + current.toString());
                current = current.rightNode;

            }

            // Check if two red children and fix if so            
            if (current.leftNode.color == RED && current.rightNode.color == RED) {
                System.out.println("Check if rules are broken.");
                handleReorient(item);
            }
        }

        // Insertion fails if already present
        if (current != nullNode) {
            return;
        }

        current = new Node(item, nullNode, nullNode);

        //Attach to parent
        if (item < parent.value) {
            System.out.println(current.toString() + " is the left child of " + parent.toString());
            parent.leftNode = current;
        } else {
            System.out.println(current.toString() + " is the right child of " + parent.toString());
            parent.rightNode = current;
        }

        handleReorient(item);
    }

    private void handleReorient(int item) {

        //Do the color flip
        current.color = RED;
        current.leftNode.color = BLACK;
        current.rightNode.color = BLACK;

        if (parent.color == RED) {
            System.out.println(parent.toString() + " and " + current.toString() + " are both Red");
            //Have to rotate
            grand.color = RED;
            System.out.println("Grandparent: " + grand.toString() + " color is now Red");

            if (item < grand.value != item < parent.value) {

                parent = rotate(item, grand);

            }

            current = rotate(item, great);
            current.color = BLACK;

        }

        //Make root black
        root.rightNode.color = BLACK;
        System.out.println("Root: " + root.rightNode.toString() + " is now Black.");
    }

    private Node rotate(int item, Node parent) {

        System.out.println("*Rotation*");
        if (item < parent.value) {
            System.out.println(item + " is less than " + parent.toString());
            if (item < parent.leftNode.value) {
                System.out.println(item + " is less than " + parent.leftNode.toString());
                return parent.leftNode = rotateWithLeftChild(parent.leftNode);

            } else {
                System.out.println(item + " is greater than " + parent.leftNode.toString());
                return parent.leftNode = rotateWithRightChild(parent.leftNode);

            }

        } else {
            System.out.println(item + " is greater than " + parent.toString());
            if (item < parent.rightNode.value) {
                System.out.println(item + " is less than " + parent.rightNode.toString());
                return parent.rightNode = rotateWithLeftChild(parent.rightNode);

            } else {
                System.out.println(item + " is greater than " + parent.rightNode.toString());
                return parent.rightNode = rotateWithRightChild(parent.rightNode);

            }

        }
    }

    //Rotate with left child
    private Node rotateWithLeftChild(Node n2) {
        System.out.println("Rotating " + n2.toString() + " with left child");
        Node n1 = n2.leftNode;
        n2.leftNode = n1.rightNode;
        n1.rightNode = n2;
        return n1;
    }

    //Rotate with right child
    private Node rotateWithRightChild(Node n1) {
        System.out.println("Rotating " + n1.toString() + " with right child");
        Node n2 = n1.rightNode;
        n1.rightNode = n2.leftNode;
        n2.leftNode = n1;
        return n2;
    }

    //Search
    public boolean search(int val) {
        if (search(root.rightNode, val)) {
            System.out.println("The value was found");
            return true;
        }else{
            System.out.println("The value was not found");
            return false;
        }
        
    }

    private boolean search(Node node, int val) {
        boolean found = false;
        while ((node != nullNode) && !found) {
            int nval = node.value;
            if (val < nval) {
                System.out.println(val + " is less than " + node.toString());
                System.out.println("Go to the left");
                node = node.leftNode;
            } else if (val > nval) {
                System.out.println(val + " is greater than " + node.toString());
                System.out.println("Go to the right");
                node = node.rightNode;
            } else {
                found = true;
                break;
            }
            found = search(node, val);
        }
        return found;
    }

    //Inorder traversal
    public void inorder() {
        System.out.println("*Inorder*");
        inorder(root.rightNode);
    }

    private void inorder(Node node) {
        if (node != nullNode) {
            inorder(node.leftNode);
            char c = 'B';
            if (node.color == 0) {
                c = 'R';
            }
            System.out.print(node.value + "" + c + " ");
            inorder(node.rightNode);
        }
    }

    //Preorder traversal
    public void preorder() {
        System.out.println("*Preorder*");
        preorder(root.rightNode);
    }

    private void preorder(Node node) {
        if (node != nullNode) {
            char c = 'B';
            if (node.color == 0) {
                c = 'R';
            }
            System.out.print(node.value + "" + c + " ");
            preorder(node.leftNode);
            preorder(node.rightNode);
        }
    }

    //Postorder traversal
    public void postorder() {
        System.out.println("*Postorder*");
        postorder(root.rightNode);
    }

    private void postorder(Node node) {
        if (node != nullNode) {
            postorder(node.leftNode);
            postorder(node.rightNode);
            char c = 'B';
            if (node.color == 0) {
                c = 'R';
            }
            System.out.print(node.value + "" + c + " ");
        }
    }

    String level = "";

    public void printTree(Node curr) {

        if (curr == nullNode) {
            return;
        }

        char c = 'B';
        if (curr.color == 0) {
            c = 'R';
        }

        System.out.println(level + curr.value + c);

        //Increment level marker
        level += "- ";
        //Recursive PreOrder
        printTree(curr.leftNode);
        printTree(curr.rightNode);
        //Decrement level marker
        level = level.substring(0, level.length() - 2);
    }
}
