package util;

public class MyArrayList<Type> {
    private int length;
    private int size = 0;
    private Type[] array;

    @SuppressWarnings("unchecked")
    public MyArrayList(int startSize) {
        length = 2;
        while (length < startSize) {
            length *= 2;
        }
        array = (Type[]) new Object[length];
    }

    public MyArrayList() {
        this(2);
    }

    public Type get(int index) {
        checkIndex(index);
        return array[index];
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
    }

    public void add(int index, Type element) {
        Type temp;
        checkIndex(index);
        if (size == array.length)
            resize();
        if (index < size) {
            for (int i = size - 1; i >= index; i--) {
                temp = array[i];
                array[i + 1] = temp;
            }
        }
        array[index] = element;
        size++;
    }

    public void add(Type element) {
        add(size, element);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Type[] resize = (Type[]) new Object[array.length * 2];
        if (length >= 0) System.arraycopy(array, 0, resize, 0, length);
        this.array = resize;
        this.length = array.length;
    }

    public Type set(int index, Type element) {
        Type previous = array[index];
        checkIndex(index);
        array[index] = element;
        return previous;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index < size()) {
            int i = index;
            array[index] = null;
            while (i < size() - 1) {
                array[i] = array[i + 1];
                array[i + 1] = null;
                i++;
            }
        }
        size--;
    }

    public boolean isEmpty() {
        for (Type a : array) {
            if (a != null)
                return false;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < length; i++)
            array[i] = null;
        size = 0;
    }

    @Override
    public String toString() {
        if (array == null)
            return "null";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) builder.append(array[i]);
            else builder.append(array[i]).append(", ");
        }
        return "[" + builder + "]";
    }
}
