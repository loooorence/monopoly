package monopoly.util;

import java.util.LinkedList;

public class Stack<T> {

    private LinkedList<T> dataList;

    public Stack() {
        dataList = new LinkedList<>();
    }

    public T peek() {
        return dataList.peek();
    }

    public T pop() {
        return dataList.remove();
    }

    public void push(T data) {
        dataList.addFirst(data);
    }

    public boolean isEmpty() {
        return dataList.isEmpty();
    }

    public int size() {
        return dataList.size();
    }
}
