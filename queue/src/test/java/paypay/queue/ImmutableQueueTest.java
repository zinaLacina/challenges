package paypay.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableQueueTest {
    public static void main(String[] args) {
        Queue<Integer> q = new ImmutableQueue<>();
        q = q.enQueue(3);
        q = q.enQueue(30);
        q = q.enQueue(90);
        q = q.enQueue(5);
        System.out.println("HEAD: "+q.head());

        int i=1;
        while (!q.isEmpty()){
            q = q.deQueue();
            System.out.println("After "+i+" deQueue:");
            System.out.println("Head equal to "+q.head());

            i++;
        }
    }
}