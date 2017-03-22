package track.lessons.lesson3;

import java.util.NoSuchElementException;

/**
 * Должен наследовать List
 * Односвязный список
 */
public class MyLinkedList extends List implements Stack, Queue {

    Node lastNode;

    /**
     * private - используется для сокрытия этого класса от других.
     * Класс доступен только изнутри того, где он объявлен
     * <p>
     * static - позволяет использовать Node без создания экземпляра внешнего класса
     */
    private static class Node {
        Node prev;
        Node next;
        int val;

        Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    @Override
    void add(int item) {
        if (size == 0) {
            lastNode = new Node(null, null, item);
        } else {
            Node newNode = new Node(lastNode, null, item);
            lastNode.next = newNode;
            lastNode = newNode;
        }
        ++size;
    }

    @Override
    int remove(int idx) throws NoSuchElementException {
        checkIndex(idx);
        Node currNode = lastNode;
        for (int i = size - 1; i > idx; --i) {
            currNode = currNode.prev;
        }
        if (currNode.prev != null) {
            currNode.prev.next = currNode.next;
        }
        if (currNode.next != null) {
            currNode.next.prev = currNode.prev;
        } else {
            lastNode = lastNode.prev;
        }
        --size;
        return currNode.val;
    }

    @Override
    int get(int idx) throws NoSuchElementException {
        checkIndex(idx);
        Node currNode = lastNode;
        for (int i = size - 1; i > idx; --i) {
            currNode = currNode.prev;
        }
        return currNode.val;
    }

    @Override
    public void push(int value) {
        add(value);
    }

    @Override
    public int pop() {
        return remove(size - 1);
    }

    @Override
    public void enqueue(int value) {
        add(value);
    }

    @Override
    public int dequeu() {
        return remove(0);
    }
}
