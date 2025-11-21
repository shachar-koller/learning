public class Main {

    public static void main(String[] args) {
        stackImpl stacky = new stackImpl(10);
        stacky.push(10);
        stacky.push(0);
        stacky.push(1);
        stacky.push(5);

        System.out.println(stacky.pop());
        System.out.println(stacky.pop());
        System.out.println(stacky.pop());
        System.out.println(stacky.pop());
    }
}
