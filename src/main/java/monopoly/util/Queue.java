package monopoly.util;

import java.util.LinkedList;

public class Queue<T> {

    private LinkedList<T> dataList;

    public Queue() {
        this.dataList = new LinkedList<>();
    }

    public void enqueue(T data) {
        dataList.addLast(data);
    }

    public T dequeue() {
        return dataList.removeFirst();
    }

    public boolean isEmpty() {
        return dataList.isEmpty();
    }

    public int size() {
        return dataList.size();
    }
}
