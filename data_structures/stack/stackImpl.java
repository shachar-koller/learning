public class stackImpl {

    private int[] stack;
    private int head;
    private int size;

    public stackImpl(int stackSize) {
        this.stack = new int[stackSize];
        this.size = 0;
        this.head = 0;
    }

    public void push(int n) {
        stack[head++] = n;
    }

    public int pop() {
        return stack[--head];
    }
}
