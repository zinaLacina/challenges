# Immutable Queue

Queue has the property of O(1) enqueue and dequeue operations. Using an array to represent a Queue results in O(N) enqueue and dequeue operations. A correct implementation of a queue uses a DoublyNode and two pointers for head and tail of the Queue. That lead us to use Stack to manage our eelements. 
In the Queue implementation we created a private inner class to represente a Immutable Stack.
```java
private  static  class  ImmutableStack<T>{
.......
}
 ```
 This class is used to implement the different action of enqueue and dequeue.