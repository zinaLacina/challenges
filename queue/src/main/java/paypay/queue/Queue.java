package paypay.queue;


/**
 * Represents the interface for an Immutable Queue (First-In-First-Out)
 * @author zina lacina
 *
 * @param <T> generic type for the elements of the queue
 */
public interface Queue<T> {
    public Queue<T> enQueue(T t);
//    Removes the element at the beginning of the immutable queue, and returns the new queue.
    public Queue<T> deQueue();
    public T head();
    public boolean isEmpty();
}
