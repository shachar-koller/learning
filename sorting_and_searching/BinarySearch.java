class BinarySearch<T extends  Comparable<T>> {
    public int search(T[] nums, T target) {
        return search(nums, 0, nums.length-1, target);
    }

    private int search(T[] nums, int low, int high, T target){
        if(low > high) return -1;
        int mid = low + (high-low)/2;
        
        int compare = nums[mid].compareTo(target);

        if(compare == 0) return mid;
        if(compare < 0) return search(nums, mid+1, high, target);
        else return search(nums, low, mid-1, target);

    }
}

