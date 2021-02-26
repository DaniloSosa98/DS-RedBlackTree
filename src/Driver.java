
import java.util.Random;
import java.util.Scanner;

public class Driver {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {

        char resp;
        int size = 0;
        int[] Array;
        RedBlackTree rbt = new RedBlackTree(Integer.MIN_VALUE);

        do {
            System.out.println("*****MENU*****");
            System.out.println("1.Insert Random Array ");
            System.out.println("2.Insert Ascending Array");
            System.out.println("3.Search");

            int op = sc.nextInt();
            switch (op) {

                case 1: {
                    System.out.println("Insert size of the array: ");
                    size = sc.nextInt();
                    Array = generateArray(size, 'R');
                    for (int i = 0; i < size; i++) {
                        rbt.insert(Array[i]);
                    }
                    System.out.println();
                    rbt.inorder();
                    System.out.println("\n");
                    rbt.printTree(rbt.root.rightNode);
                    break;
                }

                case 2: {
                    System.out.println("Insert size of the array: ");
                    size = sc.nextInt();
                    Array = generateArray(size, 'A');
                    for (int i = 0; i < size; i++) {
                        rbt.insert(Array[i]);
                    }
                    rbt.inorder();
                    System.out.println("\n");
                    rbt.printTree(rbt.root.rightNode);
                    break;
                }

                case 3: {
                    System.out.println("Which value do you want to search?:");
                    int val = sc.nextInt();

                    rbt.search(val);
                    break;
                }

            }
            System.out.println("\nTo fill a new tree restart the program");
            System.out.println("Do you want to perform another operation? (y/n):");
            resp = sc.next().charAt(0);
        } while (resp == 'y');
    }

    public static int[] generateArray(int size, char t) {
        int[] array = new int[size];

        switch (t) {
            case 'R': {
                for (int i = 0; i < array.length; i++) {
                    array[i] = rand.nextInt(1000) + 1;
                }
                break;
            }
            case 'A': {
                int temp = 0;
                for (int i = 0; i < array.length; i++) {
                    array[i] = rand.nextInt(1000) + 1;
                }

                for (int i = 0; i < array.length; i++) {
                    for (int j = i + 1; j < array.length; j++) {
                        if (array[i] > array[j]) {
                            temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                    }
                }

                break;
            }
        }

        System.out.println();
        System.out.println("Array");
        print(array);
        return array;
    }

    public static void print(int[] a) {
        for (int l = 0; l < a.length; l++) {
            if (l == 0) {
                System.out.print(a[l]);
            } else {
                System.out.print(", " + a[l]);
            }
        }
        System.out.println("");
    }

}
