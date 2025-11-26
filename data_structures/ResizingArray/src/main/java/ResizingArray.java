import java.util.Iterator;

public class ResizingArray<Type> implements Iterable<Type> {

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
        this.base = (Type[]) new Object[16];
        this.length = 16;
        this.numElements = 0;
    }

    public void add(Type element) {
        if (this.numElements == this.length) doubleSize();
        this.base[numElements] = element;
        this.numElements++;
    }

    public void delete(Type toDelete) {
        int idx = this.indexOf(toDelete);
        if (idx == -1) return;
        this.base[idx] = null;
        for (int i = idx; i < this.length - 1; i++) {
            this.base[i] = this.base[i + 1];
        }
        this.numElements--;
    }

    public Type deleteByIndex() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public void doubleSize() {
        Type[] newArr = (Type[]) new Object[this.length * 2];
        if (this.length >= 0) System.arraycopy(this.base, 0, newArr, 0, this.length);
        this.base = newArr;
        this.length = newArr.length;
    }

    public Type get(int index) {
        return this.base[index];
    }

    public int indexOf(Type toFind) {
        for (int i = 0; i < this.length; i++) {
            if (this.base[i].equals(toFind)) {
                return i;
            }
        }
        return -1;
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

    public String toStringWithNulls() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.base[0]);
        for (int i = 1; i < this.length; i++) {
            sb.append(",").append(this.base[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    public int sum() {
        int sum = 0;
        for (int i = 0; i < this.numElements; i++) {
            Type elem = this.base[i];
            if (elem == null) continue;
            if (elem instanceof Number) {
                sum += ((Number) elem).intValue();
            } else if (elem instanceof String) {
                try {
                    sum += Integer.parseInt((String) elem);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Element at index " + i + " is not a parsable integer: " + elem, e);
                }
            } else {
                throw new IllegalArgumentException("Unsupported element type at index " + i + ": " + elem.getClass());
            }
        }
        return sum;
    }

    public int size() {
        return this.numElements;
    }

    public Iterator<Type> iterator() {
        return new Iterator<Type>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < numElements;
            }

            @Override
            public Type next() {
                return base[i++];
            }
        };
    }
}
