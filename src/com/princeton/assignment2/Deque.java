
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int N;

    private class Node {
        private Node next;
        private Node prev;
        private Item item;
    }

    public Deque() {
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("item should not be null");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if (size() == 0)
            last = first;
        else {
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        N++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("item should not be null");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        if (size() == 0)
            first = last;
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        N++;
    }

    public Item removeFirst() {
        if (size() == 0)
            throw new NoSuchElementException();
        Node oldfirst = first;
        Item item = oldfirst.item;
        first = first.next;
        oldfirst = null;
        N--;
        return item;
    }

    public Item removeLast() {
        if (size() == 0)
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        last.next = null;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = first;
        }

        public boolean hasNext() { return current != null;                      }
        public void remove()     { throw new UnsupportedOperationException();   }

        public Item next() {
            if (!this.hasNext()) { throw new NoSuchElementException();          }
            else {
                Node node = current;
                current = current.next;
                return node.item;
            }
    }
    }

    public static void main(String[] args) {
        Deque<Integer> deq = new Deque<Integer>();
        deq.addFirst(3);
        deq.addFirst(2);
        deq.addFirst(1);
        deq.addFirst(0);
        deq.addLast(4);
        Iterator<Integer> it = deq.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}
