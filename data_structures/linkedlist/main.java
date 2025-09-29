public class main {

    public static void main(String[] args) {
        LinkedListImpl<String> mylist = new LinkedListImpl<>();

        mylist.add("Hello");
        mylist.add("World");
        mylist.add("Of");
        mylist.add("Data");
        mylist.add("Structures");
        mylist.add("And");
        mylist.add("Algorithms");

        mylist.printList();

        mylist.remove(0);
        mylist.printList();
        mylist.remove(3);
        mylist.remove(4);
        mylist.printList();
        mylist.remove(3);
        mylist.printList();
        mylist.remove(0);
        mylist.remove(0);
        mylist.printList();
    }
}
