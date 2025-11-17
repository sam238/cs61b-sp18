public class ArrayDeque<T> {

    private T[] item;
    private int nextLeft;
    private int nextRight;
    private int capacity = 8;
    private int size;

    public ArrayDeque() {
        size = 0;
        item = (T[])new Object[capacity];
        nextLeft = 3;
        nextRight = 4;
    }

    //减1操作
    private int minusOne(int index) {
        return (index - 1 + item.length) % item.length;
    }

    private int plusOne(int index) {
        return (index + 1) % item.length;
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int first = plusOne(nextRight);
        for (int i = 0; i < size; i++) {
            a[i] = item[(first + i) % item.length];
        }
        item = a;
        nextLeft = cap - 1;
        nextRight = size;
    }

    public void addFirst(T x) {
        if (size == item.length - 1) {
            resize(item.length * 2);
        }
        item[nextLeft] = x;
        nextLeft = minusOne(nextLeft);
        size += 1;
    }

    public void addLast(T x) {
        if (size == item.length - 1) {
            resize(item.length * 2);
        }
        item[nextRight] = x;
        nextRight = plusOne(nextRight);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = plusOne(nextLeft);
        for (int i = 0; i < size; i++) {
            System.out.print(item[(index + i) % item.length] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        int first = plusOne(nextLeft);
        T val = item[first];
        item[first] = null;
        nextLeft = first;
        size -= 1;
        if (item.length >= 16 && size < item.length / 4) {
            resize(item.length / 2);
        }
        return val;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        int first = plusOne(nextLeft);
        return item[(first + index) % item.length];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(100);
        ad.addFirst(50);
        ad.addFirst(10);
        ad.addLast(200);
        ad.addLast(500);
        ad.printDeque();
        ad.removeFirst();
        ad.printDeque();
    }
}
