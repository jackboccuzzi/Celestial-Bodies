public class ArrayList<T> implements List<T> {
    private T[] arr;
    private int size;

    public ArrayList() {
        arr = (T[]) new Object[10];
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[pos];
    }

    private void growArray() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean add(T item) {
        if (size == arr.length) {
            growArray();
        }
        arr[size] = item;
        size++;
        return true;
    }

    public void add(int pos, T item) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == arr.length) {
            growArray();
        }
        // shift items to the right (start at size to shift over to create space)
        for (int i = size; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = item;
        size++;
    }

    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        T returnVal = arr[pos];
        // shift items to the left
        for (int i = pos; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return returnVal;
    }

}