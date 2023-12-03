import utils.*;

public class Main {
    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(5);
        tree.add(20);
        tree.add(9);
        tree.add(12);
        tree.add(97);
        tree.add(35);
        tree.add(16);
        tree.add(87);

        tree.print();

        System.out.println("Removes 12");
        tree.remove(12);
        System.out.println("Inverted");
        tree.invert();
        tree.print();

        System.out.println("adds 21 to inveted tree");
        tree.add(21);
        tree.print();


        System.out.println("removing 35");
        tree.remove(35);
        tree.print();
    }
}