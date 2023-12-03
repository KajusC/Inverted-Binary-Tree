package utils;

import java.util.Comparator;

public class BinaryTree<E extends Comparable<E>> implements Tree<E> {

    BinaryNode<E> root = null;
    int size = 0;
    Comparator<? super E> c;

    boolean inverted = false;

    public BinaryTree() {
        c = Comparator.naturalOrder();
    }


    protected static class BinaryNode<E> {
        E element;
        BinaryNode<E> left;
        BinaryNode<E> right;

        public BinaryNode() {

        }

        public BinaryNode(E element) {
            this.element = element;
            left = null;
            right = null;
        }

    }

    @Override
    public void add(E element) {
        if (element != null) {
            root = addRec(element, root);
        }
    }

    private BinaryNode<E> addRec(E element, BinaryNode<E> node) {
        //end reached = leaf
        if (node == null) {
            size++;
            return new BinaryNode<>(element);
        }
        int cmp = c.compare(element, node.element);
        if (inverted)
            cmp = c.compare(node.element, element);

        if (cmp < 0) {
            node.left = addRec(element, node.left);
        } else if (cmp > 0) {
            node.right = addRec(element, node.right);
        }

        return node;
    }

    @Override
    public void remove(E element) {
        if (element != null) {
            root = removeRec(element, root);
        }
    }

    private BinaryNode<E> removeRec(E element, BinaryNode<E> node) {
        if (node == null)
            return node;

        int cmp = c.compare(element, node.element);
        if (inverted)
            cmp = c.compare(node.element, element);

        if (cmp < 0)
            node.left = removeRec(element, node.left);
        else if (cmp > 0)
            node.right = removeRec(element, node.right);
        else {
            if ((node.left == null) || (node.right == null)) {
                BinaryNode<E> temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;


                node = temp;
            } else {
                node.element = node.right.element;
                if (!inverted) {

                    node.right = removeMin(node.right);
                } else {
                    node.right = removeMax(node.right);
                }

            }
            size--;
        }
        return node;
    }

    private BinaryNode<E> removeMax(BinaryNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node.right = removeMax(node.right);
            return node;
        } else {
            return node.left;
        }
    }

    private BinaryNode<E> removeMin(BinaryNode<E> node) {
        if (node == null) {
            return null;
        } else if (node.left != null) {
            node.left = removeMin(node.left);
            return node;
        } else {
            return node.right;
        }
    }

    @Override
    public boolean search(E element) {
        return get(element) != null;

    }

    private E get(E element) {
        if (element != null) {
            throw new IllegalArgumentException("Element is null in get(E element)");
        }

        BinaryNode<E> node = root;
        while (node != null) {
            int cmp = c.compare(element, node.element);

            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.element;
            }
        }

        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void invert() {
        root = recursiveInv(root);
        inverted = !inverted;

    }

    private BinaryNode<E> recursiveInv(BinaryNode<E> node) {
        if (node == null)
            return null;
        recursiveInv(node.left);
        recursiveInv(node.right);
        BinaryNode<E> temp = node.right;
        node.right = node.left;
        node.left = temp;

        return node;
    }

    @Override
    public void print() {
        System.out.println("Current elements in Tree: ");
        root = traverse(root);
        System.out.println(" ");
    }

    private BinaryNode<E> traverse(BinaryNode<E> node) {
        if (node == null)
            return null;
        traverse(node.left);
        System.out.print(node.element+ " ");
        traverse(node.right);

        return node;
    }
}
