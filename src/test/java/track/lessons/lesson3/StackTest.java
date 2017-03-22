package track.lessons.lesson3;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class StackTest {

    @Test(expected = NoSuchElementException.class)
    public void emptyStack() throws Exception {
        Stack stack = new MyLinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assert.assertTrue(stack.pop() == 3);
        Assert.assertTrue(stack.pop() == 2);
        Assert.assertTrue(stack.pop() == 1);

        stack.pop();
    }

    @Test
    public void stackPushPop() throws Exception {
        Stack stack = new MyLinkedList();
        for (int i = 0; i < 1000; ++i) {
            stack.push(i);
        }
        for (int i = 999; i <= 0; --i) {
            Assert.assertTrue(stack.pop() == i);
        }
    }
}
