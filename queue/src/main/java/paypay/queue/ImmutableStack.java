package paypay.queue;

public final class ImmutableStack<T> {
    /*
     * head is the last element put in the queue or first element(if we reverse tthe queue)
     * tail is the the rest of the queue a part of the head, the type is immutable Stack
     * size is the size of the stack
     */

    public T head;
    public ImmutableStack<T> tail;
    public int size;

    /**
     * Default constructor
     * head to null
     * tail to null
     * size to 0
     * */

    public ImmutableStack(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    /**
     * T Object
     * ImmutableStack object tail
     * head is object
     * tail is tail (ImmutableStack object)
     * size = size of the tail +1
     * */

    public ImmutableStack(T obj,ImmutableStack<T> tail){
        this.head = obj;
        this.tail = tail;
        this.size = tail.size+1;
    }

    /**
     * Emptying the stack
     * returning the ImmutableStack
     * */

    public static ImmutableStack emptyStack(){
        return new ImmutableStack();
    }

    /**
     * Checking if stack is empty
     * @return true of false if Stack is empty or not
     * */

    public boolean isEmpty(){
        return this.size ==0;
    }

    /**
     * Adding a new element to the stack.
     * The old tail+head become tail and obj become head
     *
     * @param obj object of type T
     * @return ImmutableStack with object
     * */

    public ImmutableStack<T> push(T obj){
        return new ImmutableStack(obj,this);
    }

    /**
     * Create a new empty stack
     * Take stack object and push the head of (the tail stack)
     * to the new stack created
     * do this until the stack is empty
     *
     * @return reverse stack
     * */

    public ImmutableStack<T> toReverseStack(){
        ImmutableStack<T> stack = new ImmutableStack<T>();
        ImmutableStack<T> tail = this;
        while(!tail.isEmpty()){
            stack = stack.push(tail.head);
            tail = tail.tail;
        }
        return stack;
    }

}
