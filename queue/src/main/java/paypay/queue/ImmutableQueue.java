package paypay.queue;

import java.util.NoSuchElementException;

/*
 * If we try to compare the different List performance of 100 runs for a list with size of: 10000 and performing 10000 operations.
 * We end up ArrayList is more performance with ADD-->9.873, GET-->0.051, ITERATE-->0.037 and SIZE-->0.013; then coming
 * Stack with ADD-->9.843, GET-->0.105, ITERATE-->0.129 and SIZE-->0.060; and LinkedList is worst then the two precedent with
 * ADD-->9.913, GET-->172.824, ITERATE-->0.538, SIZE-->0.030.
 * If the purpose is to use as FIFO so Stack is the most convenient, ArrayList does not normalStack the normalStack of element added.
 * In our case we will use Stack to track elements in the queue.
 * To stay in our Logic of immutable we create a Immutable Stack that will handle the our elements
 */

public final class ImmutableQueue<T> implements Queue<T> {

    /**
     * Two stack for enqueue and dequeue the element from the queue
     * normalStack for enqueueing the Stack
     * dequeueStack for dequeueing the Stack
     */

    private ImmutableStack<T> normalStack;
    private ImmutableStack<T> dequeueStack;

    /**
     * Default constructor ImmutableQueue
     * two empty stacks that will initialize head to null, tail tto null and size to null
     */

    public ImmutableQueue() {
        this.normalStack = ImmutableStack.emptyStack();
        this.dequeueStack = ImmutableStack.emptyStack();
    }

    /**
     * Constructor overloading
     * Using two immutable stack normalStack and dequeueStack
     * This is helpful  when we want for the dequeueing and enqueueing
     */

    public ImmutableQueue(ImmutableStack<T> normalStack, ImmutableStack<T> dequeueStack) {
        this.normalStack = normalStack;
        this.dequeueStack = dequeueStack;
    }


    /**
     * Add one element of type T in the Queue.
     *
     * @param t of type T
     *          The old tail+head become tail and obj become head
     * @throws IllegalArgumentException
     */
    @Override
    public final Queue<T> enQueue(T t) {
        if (t == null)
            throw new IllegalArgumentException();
        return new ImmutableQueue<T>(this.normalStack.push(t), this.dequeueStack);
    }

    /**
     * Remove one element of type T in the Queue.
     * The old head will be remove. The old tail will be decompose into two tail and
     * head(the next element after removed the old head ).
     *
     * @return
     * @throws IllegalArgumentException
     */

    @Override
    public final Queue<T> deQueue() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        if (!this.dequeueStack.isEmpty()) {
            return new ImmutableQueue<T>(this.normalStack, this.dequeueStack.tail);
        } else {
            return new ImmutableQueue<T>(ImmutableStack.emptyStack(), this.normalStack.toReverseStack().tail);
        }
    }

    /**
     * Return the head the Stack
     *
     * @return
     * @throws IllegalArgumentException
     */

    @Override
    public final T head() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return this.dequeueStack.head;
    }

    /**
     * If the size is different of 0 the return will be falsee. That mean empty.
     * @return
     */
    @Override
    public final boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the size of the current normalStack + the size of dequeueStack
     * if the dequeue methods was not call, so dequeueStack will be empty. it will return only the size of normalStack
     * @return
     */
    public int size() {
        return this.normalStack.size + this.dequeueStack.size;
    }
}
