package track.lessons.lesson3;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class QueueTest {

    @Test(expected = NoSuchElementException.class)
    public void emptyQueue() throws Exception {
        Queue queue = new MyLinkedList();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Assert.assertTrue(queue.dequeu() == 1);
        Assert.assertTrue(queue.dequeu() == 2);
        Assert.assertTrue(queue.dequeu() == 3);

        queue.dequeu();
    }

    @Test
    public void queueInOut() throws Exception {
        Queue queue = new MyLinkedList();
        for (int i = 0; i < 1000; ++i) {
            queue.enqueue(i);
        }
        for (int i = 999; i <= 0; --i) {
            Assert.assertTrue(queue.dequeu() == i);
        }
    }
}
