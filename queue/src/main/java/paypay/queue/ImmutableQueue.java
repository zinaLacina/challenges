package paypay.queue;

import java.util.NoSuchElementException;

/**
 * If we try to compare the different JAVA List methods performance of 100 runs for a list with size of: 10000 and performing 10000 operations.
 * We end up ArrayList is more performance with ADD-->9.873, GET-->0.051, ITERATE-->0.037 and SIZE-->0.013; then coming
 * Stack with ADD-->9.843, GET-->0.105, ITERATE-->0.129 and SIZE-->0.060; and LinkedList is worst then the two precedent with
 * ADD-->9.913, GET-->172.824, ITERATE-->0.538, SIZE-->0.030.
 * If the purpose of our Q is to use as FIFO so Stack is the most convenient because of the similarity with Queue. N.B Stack is LIFO
 * , ArrayList does not normalStack the normalStack of element added.
 * In our case we will use Stack to track elements in the queue.
 * To stay in our Logic of immutable we create a Immutable Stack that will handle our elements of type T(T can be any Object Type of Java)
 */

public final class ImmutableQueue<T> implements Queue<T> {


    private static class ImmutableStack<T> {

        /**
         * head is the first element T put inside the Queue.
         * tail is the the rest of the queue a part of the head of type ImmutableStack
         */
        private final T head;
        private final ImmutableStack<T> tail;

        /**
         * Default constructor without fields
         * head to null
         * tail to null
         * */
        private ImmutableStack() {
            this.head = null;
            this.tail = null;
        }

        /**
         * constructor with fields
         * head is object T
         * tail is ImmutableStack
         * Both are initialize by the params in the constructor
         * */
        private ImmutableStack(T head, ImmutableStack<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        /**
         * Adding a new element to the ImmutableStack.
         * The old tail+head become tail and obj become head
         *
         * @param t object of type T
         * @return ImmutableStack element
         * */
        public ImmutableStack<T> push(T t) {
            return new ImmutableStack<>(t, this);
        }

        /**
         * This is acting like removing an element. Because he dismisses the head from the rest of the queue.
         * Then return the tail. And the tail will be decompose as tail and head.
         * @return ImmutableStack element
         * */
        public ImmutableStack<T> pop() {
            return this.tail;
        }

        /**
         * @Return the head only
        * */
        public T peek() {
            return this.head;
        }

        /**
         * returning a boolean if the tail and the head are null
         * */

        public boolean isEmpty() {
            return this.head == null && this.tail == null;
        }

        /**
         * Create a new empty stack
         * Take stack object and push the head of (the tail stack)
         * to the new stack created
         * do this until the stack is empty
         *
         * @return reverse stack
         * */
        public ImmutableStack<T> reverse() {
            ImmutableStack<T> result = new ImmutableStack<>();
            ImmutableStack<T> tmp = this;
            while (!tmp.isEmpty()) {
                result = result.push(tmp.peek());
                tmp = tmp.pop();
            }
            return result;
        }
    }





    /**
     * Two stack for enqueue and dequeue the element from the queue
     * backward for enqueueing the Stack
     * forward for dequeueing the Stack
     */

    private ImmutableStack<T> backward;
    private ImmutableStack<T> forward;

    /**
     * Default constructor ImmutableQueue
     * two empty stacks that will initialize head to null,
     * tail to null
     */

    public ImmutableQueue() {
        this.backward = new ImmutableStack<>();
        this.forward = new ImmutableStack<>();
    }

    /**
     * Constructor overloading
     * Using two immutable stack backward and forward
     * This is helpful  when we want for the dequeueing and enqueueing
     */

    private ImmutableQueue(ImmutableStack<T> forward, ImmutableStack<T> backward) {
        this.forward = forward;
        this.backward = backward;
    }


    /**
     * Add one element of type T in the Queue.
     * As we are using two stack to manage our elements. we push our t of Type T in the forward Stack.
     * Verify first if it is not empty. If it is, so reverse the backward stack, before adding the element t
     * @param t of type T
     *          The old tail+head become tail and obj become head
     * @throws IllegalArgumentException
     */
    @Override
    public final Queue<T> enQueue(T t) {
        if (t == null)
            throw new IllegalArgumentException();
        if (this.forward.isEmpty()) {
            return new ImmutableQueue<T>(this.backward.reverse().push(t), new ImmutableStack<>());
        }
        return new ImmutableQueue<T>(this.forward, this.backward.push(t));
    }



    /**
     * Remove one element of type T in the Queue.
     * The old head will be remove. The old tail will be decompose into two tail and
     * head(the next element after removed the old head ).
     * 4 cases here:
     * - check if forward is empty. If empty reverse backward before removing the head
     * - If forward is not empty so remove is top. But check the status after removing.
     * - If after removing forward is empty so reverse backward.
     * - If both forward and backward are empty throw and IllegalArgumentException
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public final Queue<T> deQueue() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        if (this.forward.isEmpty()) {
            return new ImmutableQueue<>(this.backward.reverse().pop(), new ImmutableStack<>());
        } else {
            ImmutableStack<T> newForward = this.forward.pop();
            if (newForward.isEmpty()) {
                return new ImmutableQueue<>(this.backward.reverse(), new ImmutableStack<>());
            }
            return new ImmutableQueue<>(newForward, this.backward);
        }
    }


    /**
     * Return the head the Stack forward
     *
     * @return
     *
     */

    @Override
    public final T head() {
        if (this.isEmpty()) return null;
        return this.forward.peek();
    }


    /**
     * if both stack forward and backward are empty return true.
     * @return
     */
    @Override
    public final boolean isEmpty() {
        return this.forward.isEmpty() && this.backward.isEmpty();
    }

}