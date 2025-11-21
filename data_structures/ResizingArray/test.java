public class test {

    public static void main(String[] args) {
        ResizingArray<Integer> ra = new ResizingArray<>();
        ra.add(1);
        ra.add(2);
        ra.add(3);
        System.out.println(ra);
        ra.add(4);
        System.out.println(ra);
        ra.add(5);
        System.out.println(ra);
        ra.add(6);
        ra.add(7);
        ra.add(8);
        ra.add(9);
        ra.add(10);

        System.out.println(ra.get(0));
        System.out.println(ra.get(9));

        ResizingArray<String> ra2 = new ResizingArray<>(10);
        for(int i = 0; i < 22; i++){
            ra2.add(String.valueOf(i));
        }
        System.out.println(ra2);
    }
}
