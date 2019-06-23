
# Software Engineer Challenge
The challenge contains two tasks of coding and design. We have two folder corresponding to each task.
## Coding task
Implementation of an immutable queue based on the following API.
```Java
public interface Queue<T> {
    public Queue<T> enQueue(T t);
    // Removes the element at the beginning of the immutable queue, and returns the new queue.
    public Queue<T> deQueue();
    public T head();
    public boolean isEmpty();
}
```
The **queue** folder is promised for this part of the challenge. The folder has three pachages, the main package(`paypay.queue)` where is living the right code.  And then old package(`paypay.queue.old`) where we can find the old code. And we have the testing folder where I implemented a test base on the right code.

## Design Question
The design question is to design a google analytic like a backend for customers which can handle billions of write request, handle large read/query volumes, provide metrics to customers with most one hour delay, with minimum downtime and have the ability to reprocess historical data.
For this question, the promise folder is  **design** folder. Well, in this folder we can find at the root the image for the system design. I explained more in detail each component inside the file readme.md. Then the folder **infrastructure** where I tried to design the infrastructure which can support our system based on the AWS cloud. More, I wrote the infrastructure code source, so with just a few clicks, we have our infrastructure. Inside the final folder **codeSource**, I tried to write a sample code with Spring framework that implements the google analytics for our customers.