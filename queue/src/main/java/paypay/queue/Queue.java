package paypay.queue;


/**
 * Represents the interface for an Immutable Queue (First-In-First-Out) because of the comment of the method deQueue
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
