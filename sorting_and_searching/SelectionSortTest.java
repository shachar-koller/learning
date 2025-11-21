public class SelectionSortTest {

    public static void main(String[] args) {
        Integer[] my_array = { 1, 1, 9, 54, 2, 38, 99, 5 };
        Selection ss = new Selection();
        printArray(my_array);
        ss.sort(my_array);
        printArray(my_array);
    }

    private static void printArray(Integer[] my_array) {
        System.out.print("{");
        int i;
        for (i = 0; i < my_array.length - 1; i++) {
            System.out.print(my_array[i]);
            System.out.print(",");
        }
        System.out.print(my_array[i]);
        System.out.println("} ");
    }
}
