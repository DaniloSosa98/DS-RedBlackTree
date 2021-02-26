
public class Node {

    Node leftNode, rightNode;
    int value;
    int color;

    public Node(int val) {

        this(val, null, null);

    }

    public Node(int val, Node left, Node right) {

        value = val;
        leftNode = left;
        rightNode = right;
        color = 1;

    }

    @Override
    public String toString() {

        if (value != Integer.MIN_VALUE) {

            return String.valueOf(value);

        }

        return "Sentinel";

    }

}
