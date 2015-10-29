package lab3.utility;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * MessageQueue:
 * <brief description of class>
 */
public class MessageQueue<T> extends ArrayBlockingQueue<T> {
    private boolean filter;

    public MessageQueue(int capacity) {
        super(capacity);
        filter = false;
    }

    public MessageQueue(int capacity, boolean filter) {
        super(capacity);
        this.filter = filter;
    }

    /**
     * TODO: Implement Method
     * @param t An object to put at the back of the Queue
     * @throws InterruptedException
     */
    @Override
    public void put(T t) throws InterruptedException {
        if (filter) {
            if (!((Line) t).getContent().contains("distributed systems")) {
                super.put(t);
            }
        } else {
            super.put(t);
        }
    }

    @Override
    public T take() throws InterruptedException {
        return super.take();
    }
}
