

public class Main {
    public static void main(String[] args) {
        Integer[] my_array = {1, 1, 9, 54, 2, 38, 99, 5};
        MergeSort ms = new MergeSort();
        ms.sort(my_array);
        
        printArray(my_array);
        BinarySearch bs = new BinarySearch();
        System.out.println(bs.search(my_array, 9));
    }

    private static void printArray(Integer[] my_array) {
        System.out.print("{");
        int i;
        for(i = 0; i < my_array.length-1; i++){
            System.out.print(my_array[i]);
            System.out.print(",");
        }
        System.out.print(my_array[i]);
        System.out.println("} ");
    }
}
