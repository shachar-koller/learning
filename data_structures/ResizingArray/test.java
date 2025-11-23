public class test {

    public static void main(String[] args) {

        ResizingArray<String> ra = new ResizingArray<>(10);
        for(int i = 0; i < 22; i++){
            ra.add(String.valueOf(i));
            System.out.println(ra.toStringWithNulls());
        }
        System.out.println(ra);
        ra.delete(String.valueOf(4));
        System.out.println(ra);
        System.out.println(ra.toStringWithNulls());
    }
}
