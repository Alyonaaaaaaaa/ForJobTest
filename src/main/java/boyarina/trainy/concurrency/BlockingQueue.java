package boyarina.trainy.concurrency;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;

@Data
@AllArgsConstructor
public class BlockingQueue<T> {
    private final int limit;
    private LinkedList<T> queue;

    public void enqueue(T item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() == 0) {
            this.queue.add(item);
            notifyAll();
        }
    }

    public void dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.limit) {
            this.queue.remove();
            notifyAll();
        }
    }
}