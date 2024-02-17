package stack;

import org.example.TqsStack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest {

    TqsStack<Integer> stack, boundstack;


    @BeforeEach
    void setup() {
        stack = new TqsStack<>(); boundstack = new TqsStack<>(1);
    }

    @DisplayName("\n=========== Size test :  ===========\n")
    @Test
    public void stackSizeContructor() {

        Assertions.assertTrue(stack.size() == 0, "Stack size test: failed | " + stack.size() + " < 0|");
    }


    @DisplayName("\n=========== Size after n pushes test :  ===========\n")
    @Test
    public void sizeAfterNPushes() {

        stack.push(1);
        stack.push(2);
        stack.push(3);
        //System.out.println("\n\n ||| Actuall size: "+ stack.size());
        Assertions.assertTrue(stack.size() > 0, "Stack size after n pushes test: failed | ");

    }


    @DisplayName("\n=========== Push and Pop test :  ===========\n")
    @Test
    public void PushAndPop() {
        stack.push(1);
        stack.push(2);

        Assertions.assertEquals(stack.pop(), 2);

    }


    @DisplayName("\n=========== Peek and size test :  ===========\n")
    @Test
    public void PeekTest() {
        stack.push(1);
        stack.push(2);
        final int size = 1;


        Assertions.assertAll(
                "If one pushes x then peeks, the value returned is x, but the size stays the same",
                (Executable) () -> assertEquals(stack.peek(), 2),
                (Executable) () -> assertEquals(stack.size(), 2)
        );


    }

    @DisplayName("\n=========== Multiply pops and size test :  ===========\n")
    @Test
    public void MultiplyPops() {
        final int expectedSize = 4;

        for (int i = 1; i < expectedSize; i++) {
            stack.push(i);
        }

        for (int i = 1; i < expectedSize; i++) {
            System.out.println(stack.pop());
        }

        Assertions.assertEquals(stack.size(), 0);


    }

    @DisplayName("\n=========== Empty stack pop test :  ===========\n")
    @Test
    public void EmptyStackPop() {
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.pop(), "A 'NoSuchElementException' should appear when pop is used on an empty stack ");
    }


    @DisplayName("\n=========== Empty stack peek test :  ===========\n")
    @Test
    public void EmptyStackPeek() {
        Assertions.assertThrows(NoSuchElementException.class, () -> stack.peek(), "A 'NoSuchElementException' exception was expected ");
    }

    @DisplayName("\n=========== Bound stack peek test :  ===========\n")
    @Test
    void BoundStack() {
        boundstack.push(1);
        Assertions.assertThrows(IllegalStateException.class, () -> boundstack.push(2), "A 'IllegalStateException' exception was expected");

    }
}
