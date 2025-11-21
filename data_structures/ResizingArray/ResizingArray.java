//import java.util.Iterator;

public class ResizingArray<Type> /*implements Iterable<Type>*/{

    private Type[] base;
    private int numElements;
    private int length;

    @SuppressWarnings("unchecked")
    public ResizingArray(int size) {
        this.base = (Type[]) new Object[size];
        this.length = size;
        this.numElements = 0;
    }

    @SuppressWarnings("unchecked")
    public ResizingArray() {
        this.base = (Type[]) new Object[4];
        this.length = 4;
        this.numElements = 0;
    }

    public void add(Type element) {
        if (this.numElements == this.length) doubleSize();
        for (int i = 0; i < this.length; i++) {
            if (this.base[i] == null) {
                this.base[i] = element;
                this.numElements++;
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void doubleSize() {
        Type[] newArr = (Type[]) new Object[this.length * 2];
        if (this.length >= 0) System.arraycopy(this.base, 0, newArr, 0, this.length);
        this.base = newArr;
        this.length = newArr.length;
    }

    public Type get(int index){
        return this.base[index];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.base[0]);
        for (int i = 1; i < this.length; i++) {
            if (this.base[i] != null) sb.append(",").append(this.base[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public int size(){
        return this.numElements;
    }

/*
    @Override
    public Iterator<Type> iterator() {

    }
*/
}
