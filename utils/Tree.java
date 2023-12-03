package utils;

public interface Tree<E> {

    void add(E element);
    void remove(E element);
    boolean search (E element);
    int getSize();
    void invert();
    void print();
}
