import java.util.*;

public class HashTableImpl <Key, Value> {
    /**
     * create an array of linked-lists. each linked-list contains entries, which themselves contain a key-value pair
     */
    private myLinkedList<Key, Value>[] table;

    public HashTableImpl() {
        this.table = new myLinkedList[5];
    }



    /**
     * EntrySubclass. an entry is just an object with a Key var, a Value var, and a next var (which points to the next entry in the list)
     * @param <Key>
     * @param <Value>
     */
    private class Entry <Key, Value> {
        Key key;
        Value value;
        Entry<Key, Value> next;

        public Entry(Key k, Value v) {
            if (k == null || v == null) {
                throw new IllegalArgumentException();
            }
            this.key = k;
            this.value = v;
            this.next = null;
        }
    }

    private class myLinkedList <Key, Value> {
        Entry<Key, Value> head;
        Entry<Key, Value> tail;
        int size;

        public myLinkedList() {
            this.head = tail = null;
            this.size = 0;
        }

        public void add(Entry<Key, Value> e) {
            if (this.head == null || this.tail == null) { //if the current add is the first add
                this.head = this.tail = e;
                this.size++;
            } else if (head == tail) { //if the current add is the second add
                this.head.next = e;
                this.tail = e;
                this.tail.next = null;
                this.size++;
            } else { //if the current add is 2+
                this.tail.next = e;
                this.tail = e;
                this.tail.next = null;
                this.size++;
            }
        }

        public void remove(Key k) {
            if(this.head == null){
                return;
            }

            if (this.head.key.equals(k)) {
                this.head = this.head.next;
                this.size--;
                if(this.head == null){
                    this.tail = null;
                }
                return;
            }

            Entry<Key, Value> iteratorEntry = this.head;
            while (iteratorEntry != null && iteratorEntry.next != null) {
                if (iteratorEntry.next.key.equals(k)) {
                    iteratorEntry.next = iteratorEntry.next.next;
                    if (iteratorEntry.next == null) {
                        this.tail = iteratorEntry;
                    }
                    this.size--;
                    return;
                }
                iteratorEntry = iteratorEntry.next;
            }
        }
    }
    /**
     * @param k the key whose value should be returned
     * @return the value that is stored in the HashTable for k, or null if there is no such key in the table
     */
    @Override
    public Value get(Key k) {
        int index = this.hashFunction(k);

        if (this.table[index] == null) {
            return null;
        }

        if (this.table[index].head == null) {
            return null;
        }

        Entry<Key, Value> iteratorEntry = this.table[index].head;
        while (iteratorEntry != null) {
            if (iteratorEntry.key.equals(k)) {
                return iteratorEntry.value;
            }
            iteratorEntry = iteratorEntry.next;
        }

        return null;
    }

    /**
     * @param k the key at which to store the value
     * @param v the value to store
     *          To delete an entry, put a null value.
     * @return if the key was already present in the HashTable, return the previous value stored for the key. If the key was not already present, return null.
     */
    @Override
    public Value put(Key k, Value v) {
        if(k == null) return null;
        int index = this.hashFunction(k);
        if (v == null) return deleteCall(k, index);

        if (this.table[index] == null) {
            this.table[index] = new myLinkedList < > ();
        }

        if (this.table[index].head == null) {
            Entry<Key, Value> e = new Entry<>(k, v);
            this.table[index].add(e);
            return null;
        }

        Entry <Key, Value> newEntry = new Entry <> (k, v);
        Entry<Key, Value> iteratorEntry = this.table[index].head;
        while (iteratorEntry != null) {
            if (iteratorEntry.key.equals(k)) {
                Value oldValue = iteratorEntry.value;
                iteratorEntry.value = v;
                return oldValue;
            }
            iteratorEntry = iteratorEntry.next;
        }
        this.table[index].add(newEntry);
        return null;
    }

    /**
     * Method which gets called when a user inputs a null value. this signifies that this is a "delete call"
     * @param k, the key which was given a null value
     * @param index which hashcode method returned for this key
     * @return null if no value was at the location of the given key, old value if there was a value which has been deleted
     */
    private Value deleteCall(Key k, int index) {
        if (this.table[index] == null) {
            return null;
        }
        Value oldValue = get(k);
        if (oldValue != null) {
            this.table[index].remove(k);
        }
        return oldValue;
    }


    /**
     * @param key the key whose presence in the hashtable we are inquiring about
     * @return true if the given key is present in the hashtable as a key, false if not
     * @throws NullPointerException if the specified key is null
     */
    @Override
    public boolean containsKey(Key key) {

        if(key == null) {
            throw new NullPointerException();
        }

        int index = this.hashFunction(key);
        if (this.table[index] == null) {
            return false;
        }

        Entry<Key, Value> iteratorEntry = this.table[index].head;
        while (iteratorEntry != null) {
            if (iteratorEntry.key.equals(key)) {
                return true;
            }
            iteratorEntry = iteratorEntry.next;
        }
        return false;
    }

    /**
     * @return an unmodifiable set of all the keys in this HashTable
     * @see Collections#unmodifiableSet(Set)
     */
    @Override
    public Set <Key> keySet() {

        Set <Key> returnKeySet = new HashSet<>();

        for(int i = 0; i < this.table.length; i++) {
            if(this.table[i] != null) {
                Entry<Key, Value> iteratorEntry = this.table[i].head;
                while (iteratorEntry != null) {
                    returnKeySet.add(iteratorEntry.key);
                    iteratorEntry = iteratorEntry.next;

                }
            }
        }
        return Collections.unmodifiableSet(returnKeySet);
    }

    /**
     * @return an unmodifiable collection of all the values in this HashTable
     * @see Collections#unmodifiableCollection(Collection)
     */
    @Override
    public Collection <Value> values() {

        Collection<Value> returnValueCollection = new HashSet<>();

        for(int i = 0; i < this.table.length; i++) {
            if(this.table[i] != null) {
                Entry<Key, Value> iteratorEntry = this.table[i].head;
                while (iteratorEntry != null) {
                    returnValueCollection.add(iteratorEntry.value);
                    iteratorEntry = iteratorEntry.next;

                }
            }
        }
        return Collections.unmodifiableCollection(returnValueCollection);
    }

    /**
     * @return how entries there currently are in the HashTable
     */
    @Override
    public int size() {
        int size = 0;

        for(int i = 0; i < this.table.length; i++) {
            if(this.table[i] != null) {
                Entry<Key, Value> iteratorEntry = this.table[i].head;
                while (iteratorEntry != null) {
                    iteratorEntry = iteratorEntry.next;
                    size++;
                }
            }
        }
        return size;
    }

    /**
     * Function which returns the index at which an object's hash will point to.
     * @param key the key
     * @return index in the hashtable
     */
    private int hashFunction(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.table.length;
    }
}
