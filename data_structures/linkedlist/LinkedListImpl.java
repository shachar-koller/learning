import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListImpl<Data> implements Iterable<Data> {

    private static class ListNode<Data> {

        private Data data;
        private ListNode<Data> next;

        public Data getData() {
            return this.data;
        }

        public void setData(Data d) {
            this.data = d;
        }

        public ListNode<Data> getNext() {
            return this.next;
        }

        public void setNext(ListNode<Data> n) {
            this.next = n;
        }
    }

    private ListNode<Data> head;
    private ListNode<Data> tail;
    private int size;

    public LinkedListImpl() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean add(Data data) {
        ListNode<Data> node = new ListNode<>();
        node.setData(data);

        if (this.size == 0) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.size++;
        return true;
    }

    public Data remove(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }

        ListNode<Data> current = this.head;
        int position = 0;
        Data returnValue = null;

        if (index == 0) {
            returnValue = this.head.getData();
            this.head = this.head.next;
            this.size--;
            return returnValue;
        }

        while (current != null) {
            if (position + 1 == index) {
                returnValue = current.next.getData();
                current.next = current.next.next;
                if (index == this.size - 1) {
                    this.tail = current;
                }
                this.size--;
                return returnValue;
            }
            current = current.next;
            position++;
        }
        return returnValue;
    }

    public Data remove(Data value) {
        return null;
    }

    public int getSize() {
        return this.size;
    }

    public void printList() {
        if (head == null) {
            System.out.println("Empty List");
            return;
        }
        ListNode<Data> current = this.head;
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext() != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public Iterator<Data> iterator() {
        return new Iterator<Data>() {
            private ListNode<Data> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Data next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                Data data = current.getData();
                current = current.getNext();
                return data;
            }
        };
    }
}
