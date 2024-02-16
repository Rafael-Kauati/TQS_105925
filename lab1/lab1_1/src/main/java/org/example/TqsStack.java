package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TqsStack <T>{

    private List<T> linkedList ;

    private int boundery = -1;

    public TqsStack(){
        linkedList = new LinkedList<>();
    }
    public TqsStack(int bound) {
        this.linkedList = new LinkedList<>();
        this.boundery = bound;
    }

    public T pop(){
        return null;
    }

    public void push(T t){

    }

    public T peek(){
        return null;
    }

    public int size(){
        return 1;
    }

    public boolean isEmpty(){
        return  true;
    }

}
