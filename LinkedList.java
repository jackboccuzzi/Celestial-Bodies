public class LinkedList<T> implements List<T> {

    public class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public T get(int pos) {
        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        return current.data;
    }

    public boolean add(T item) {
        Node<T> newNode = new Node<T>(item);
        if (head == null) {
            head = newNode;
        }
        else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
        return true;
    }

    public void add(int pos, T item) {
        if (pos < 0 || pos > count) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> newNode = new Node<T>(item);
            if (pos == 0) {
                newNode.next = head;
                head = newNode;
            }
            else {
                Node<T> current = head;
                for (int i = 0; i < pos - 1; i++) {
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }
        count++;
    }

    public T remove(int pos) {
        T removeData = null;
        if (pos < 0 || pos >= count) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> current = head;
            if (pos == 0) {
                head = current.next;
            }
            else {
                for (int i = 0; i < pos - 1; i++) {
                    current = current.next;
                }
                removeData = current.next.data;
                current.next = current.next.next;
            }
        }
        count--;
        return removeData;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
        }
        return sb.toString();
    }

}
