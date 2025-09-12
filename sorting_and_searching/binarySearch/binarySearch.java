package sorting_and_searching.BinarySearch;
import java.util.Arrays;

public class BinarySearch {
 
    
    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        // Test arrays
        int[][] testArrays = {
            {},
            {5},
            {5},
            {3, 7},
            {3, 7},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            new int[100]
        };
        // Fill large array
        for (int i = 0; i < 100; i++) testArrays[9][i] = i;

        int[] targets = {1, 5, 2, 3, 4, 1, 5, 3, 6, 50};
        String[] descriptions = {
            "Empty array, target 1",
            "Single element (present), target 5",
            "Single element (absent), target 2",
            "Two elements (present), target 3",
            "Two elements (absent), target 4",
            "Multiple (start), target 1",
            "Multiple (end), target 5",
            "Multiple (middle), target 3",
            "Multiple (absent), target 6",
            "Large array, target 50"
        };
        for (int i = 0; i < testArrays.length; i++) {
            int result = bs.search(testArrays[i], targets[i]);
            System.out.println(descriptions[i] + ": result = " + result);
        }
    }


    public int search(int[] input, int target){
        int left = 0;
        int right = input.length -1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(input[middle] == target){
                return middle;
            }
            else if(input[middle] < target){
                left = middle + 1;
            }
            else{
                right = middle - 1;
            }
        }
        return -1;
    }
    
}