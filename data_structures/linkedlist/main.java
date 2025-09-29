public class main{
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

        LinkedListImpl<Integer> ml2 = new LinkedListImpl<>();
        for(int i = 0; i < 10; i++){
            ml2.add(i);
        }
        ml2.printList();

    }
}