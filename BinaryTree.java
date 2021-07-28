//BinaryTreeWithDuplicates.java
package Project_ds;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

class BinaryTreeWithDuplicates {

    static class Node {

        int key;
        int count, d;
        boolean totalDublicate;
        Stack<String> stack = new Stack<String>();
        Node left, right;
        int data;
    };

    static Node createNode(int item, Stack stack) {
        Node temp = new Node();
        temp.key = item;
        temp.stack = stack;
        temp.left = null;
        temp.right = null;
        temp.count = 1;
        temp.totalDublicate = false;

        return temp;
    }

    static void levelorder(Node root) {
        System.out.print("Print Level Order ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            System.out.print(temp.key + "(" + temp.count + ") " + temp.stack + "\n");
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println();
    }

    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + "(" + root.count + ") " + root.stack + "\n");
            inorder(root.right);
        }
    }

    static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.key + "(" + root.count + ") " + root.stack + "\n");
            preorder(root.left);
            preorder(root.right);
        }
    }

    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.key + "(" + root.count + ") " + root.stack + "\n");
        }
    }

    static void deleteAll(Node root) {
        if (root != null) {
            if (root.count > 1) {
                while (root.count > 1) {
                    deleteNode(root, root.key);
                }

            }
            deleteAll(root.left);
            //System.out.print(root.key + "(" + root.count + ") "+ root.stack +"\n");
            deleteAll(root.right);

        }
    }

    static void deleteElement(Node root, int d) {
        if (root != null) {
            if (root.key == d && root.count > 1) {
                //while(root.count > 1){
                deleteNode(root, root.key);

                // }
            } else if (root.key == d && root.count == 1) {
                System.out.println("Not a dublicate");
            } else {
                System.out.println("Bad input or wrong tree :P");
            }
            deleteElement(root.left, d);
            //System.out.print(root.key + "(" + root.count + ") "+ root.stack +"\n");
            deleteElement(root.right, d);

        }
    }

    static void returnElementDB(Node root, int d) {
        if (root != null) {
            if (root.key == d) {
                System.out.println("Dublicated: " + root.count + " times");
            }
            returnElementDB(root.left, d);
            returnElementDB(root.right, d);
        }
    }

    static void deleteAllElementDB(Node root, int d) {
        if (root != null) {
            if (root.key == d && root.count > 1) {
                while (root.count > 1) {
                    deleteNode(root, root.key);
                }
            }
            deleteAllElementDB(root.left, d);
            deleteAllElementDB(root.right, d);

        }
    }

    static void onlyDB(Node root) {
        if (root != null) {
            if (root.count > 1) {
                System.out.print(root.key + "(" + root.count + ") " + root.stack + "\n");
            }
            onlyDB(root.left);
            onlyDB(root.right);
        }
    }

    static void noDB(Node root) {
        if (root != null) {
            if (root.count == 1) {
                System.out.print(root.key + "(" + root.count + ") " + root.stack + "\n");
            }
            noDB(root.left);
            noDB(root.right);
        }
    }

    static Node insert(Node node, int key, Stack stack) {

        if (node == null) {
            return createNode(key, stack);
        }

        if (key == node.key) {
            (node.count)++;
            (node.totalDublicate) = true;

            return node;
        }

        if (key < node.key) {
            node.left = insert(node.left, key, stack);
        } else {
            node.right = insert(node.right, key, stack);
        }

        return node;
    }

    static Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    static Node deleteNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.count > 1) {
                (root.count)--;
                return root;
            }
            if (root.left == null) {
                Node temp = root.right;
                root = null;
                return temp;
            } else if (root.right == null) {
                Node temp = root.left;
                root = null;
                return temp;
            }
            Node temp = minValueNode(root.right);
            root.key = temp.key;
            root.right = deleteNode(root.right, temp.key);
        }
        return root;
    }

    static void ReplaceNode(Node root, int d, Node node, int key, Stack stack) {
        if (root != null) {
            if (root.key == d && root.count > 1) {
                while (root.count > 1) {
                    deleteNode(root, root.key);
                    insert(node, key, stack);
                }
            }
            ReplaceNode(root.left, d, node, key, stack);
            ReplaceNode(root.right, d, node, key, stack);

        }
    }

    public static void main(String[] args) {

        System.out.println("Binary Search Tree, version 1.0");

        Node root = null;
        int c = 0;
        int[] a = {1, 2, 3, 2, 2, 3, 4, 4, 9, 9, 11, 15, 11, 16, 10, 15};

        for (int i = 0; i < a.length; i++) {
            int temp = 0;
            for (int j = 0; j <= i; j++) {
                if (a[i] == a[j]) {
                    temp++;
                }
            }
            if (temp > 1) {
                c++;
            }
        }

        root = insert(root, a[0], stack1());
        root = insert(root, a[1], stack2());
        root = insert(root, a[2], stack3());
        root = insert(root, a[3], stack4());
        root = insert(root, a[4], stack5());
        root = insert(root, a[5], stack6());
        root = insert(root, a[6], stack7());
        root = insert(root, a[7], stack8());
        root = insert(root, a[8], stack9());
        root = insert(root, a[9], stack10());
        root = insert(root, a[10], stack11());
        root = insert(root, a[11], stack12());
        root = insert(root, a[12], stack13());
        root = insert(root, a[13], stack14());
        root = insert(root, a[14], stack15());
        root = insert(root, a[15], stack16());

        int choice;
        Scanner sc = new Scanner(System.in);

        boolean B = true;
        while (B) {
            Menu();
            choice = sc.nextInt();
            Suic(choice, root, c);
        }//while

    }//main

    public static Stack<String> elementbS() {
        Stack<String> stack = new Stack<>();
        stack.push("ea");
        stack.push("ea1");
        stack.push("ea2");
        return stack;
    }

    public static Stack<String> stack1() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("a1");
        stack.push("a2");
        return stack;
    }

    public static Stack<String> stack2() {
        Stack<String> stack = new Stack<>();
        stack.push("b");
        stack.push("b1");
        stack.push("b2");
        return stack;
    }

    public static Stack<String> stack3() {
        Stack<String> stack = new Stack<>();
        stack.push("c");
        stack.push("c1");
        stack.push("c2");
        return stack;
    }

    public static Stack<String> stack4() {
        Stack<String> stack = new Stack<>();
        stack.push("d");
        stack.push("d1");
        stack.push("d2");
        return stack;
    }

    public static Stack<String> stack5() {
        Stack<String> stack = new Stack<>();
        stack.push("e");
        stack.push("e1");
        stack.push("e2");
        return stack;
    }

    public static Stack<String> stack6() {
        Stack<String> stack = new Stack<>();
        stack.push("ë");
        stack.push("ë1");
        stack.push("ë2");
        return stack;
    }

    public static Stack<String> stack7() {
        Stack<String> stack = new Stack<>();
        stack.push("f");
        stack.push("f1");
        stack.push("f2");
        return stack;
    }

    public static Stack<String> stack8() {
        Stack<String> stack = new Stack<>();
        stack.push("g");
        stack.push("g1");
        stack.push("g2");
        return stack;
    }

    public static Stack<String> stack9() {
        Stack<String> stack = new Stack<>();
        stack.push("gj");
        stack.push("gj1");
        stack.push("gj2");
        return stack;
    }

    public static Stack<String> stack10() {
        Stack<String> stack = new Stack<>();
        stack.push("h");
        stack.push("h1");
        stack.push("h2");
        return stack;
    }

    public static Stack<String> stack11() {
        Stack<String> stack = new Stack<>();
        stack.push("i");
        stack.push("i1");
        stack.push("i2");
        return stack;
    }

    public static Stack<String> stack12() {
        Stack<String> stack = new Stack<>();
        stack.push("j");
        stack.push("j1");
        stack.push("j2");
        return stack;
    }

    public static Stack<String> stack13() {
        Stack<String> stack = new Stack<>();
        stack.push("k");
        stack.push("k1");
        stack.push("k2");
        return stack;
    }

    public static Stack<String> stack14() {
        Stack<String> stack = new Stack<>();
        stack.push("l");
        stack.push("l1");
        stack.push("l2");
        return stack;
    }

    public static Stack<String> stack15() {
        Stack<String> stack = new Stack<>();
        stack.push("m");
        stack.push("m1");
        stack.push("m2");
        return stack;
    }

    public static Stack<String> stack16() {
        Stack<String> stack = new Stack<>();
        stack.push("n");
        stack.push("n1");
        stack.push("n2");
        return stack;
    }

    public static void Menu() {
        System.out.println("\nBST Menu\n\n"
                + "\t1: In-order\n"
                + "\t2: Pre-order\n"
                + "\t3: Post-order\n"
                + "\t4: Level-order\n"
                + "\t5: Remove all dublicates\n"
                + "\t6: Remove dublicate of an element\n"
                + "\t7: Return number of dublicates of an element\n"
                + "\t8: Remove all dublicates of an element\n"
                + "\t9: Show only the nodes that have dublicates\n"
                + "\t10: Show only the nodes that do not have dublicates\n"
                + "\t11: Show how many dublicates does the tree have: \n"
                + "\t12: Replace all duplicates of an element A with element B\n"
                + "\t0: Exit\n");
    }

    public static void Suic(int choice, Node root, int c) {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case 1:
                System.out.println("\nYou chose In-order");
                inorder(root);
                break;
            case 2:
                System.out.println("\nYou chose Pre-order");
                preorder(root);
                break;
            case 3:
                System.out.println("\nYou chose Post-order");
                postorder(root);
                break;
            case 4:
                System.out.println("\nYou chose Level-order");
                levelorder(root);
                break;
            case 5:
                System.out.println("\nYou chose delete all dublicates");
                deleteAll(root);
                break;
            case 6:
                System.out.println("Enter dublicated element key");
                int dub = sc.nextInt();
                deleteElement(root, dub);
                break;
            case 7:
                System.out.println("Enter element key");
                int dub7 = sc.nextInt();
                returnElementDB(root, dub7);
                break;
            case 8:
                System.out.println("Enter element key");
                int dub8 = sc.nextInt();
                deleteAllElementDB(root, dub8);
                break;
            case 9:
                onlyDB(root);
                break;
            case 10:
                noDB(root);
                break;
            case 11:
                System.out.println("Total number of dublicates is: " + c);
                break;
            case 12:
                int a1,
                 b;
                System.out.println("Enter element A:");
                a1 = sc.nextInt();
                System.out.println("Enter element B:");
                b = sc.nextInt();
                ReplaceNode(root, a1, root, b, elementbS());
                break;
            case 0:
                System.out.println("\nYou chose to exit program");
                System.exit(0);
        }//switch
    }
}
