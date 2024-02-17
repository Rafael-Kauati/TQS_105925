package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


public class TqsStack <T>{

    private LinkedList<T> ll ;

    private  int boundery = -1;

    public TqsStack(){
        ll = new LinkedList<>();
    }
    public TqsStack(int bound) {
        this.ll = new LinkedList<>();
        this.boundery = bound;
    }

    public T pop(){
        if (this.ll.isEmpty())throw  new NoSuchElementException();
        return this.ll.pop();
    }

    public void push(T t){
        if (boundery > 0 && boundery == this.ll.size()) throw new IllegalStateException();
        this.ll.push(t);
    }

    public T peek(){
        if (this.ll.isEmpty()) throw new NoSuchElementException();
        return this.ll.peek();
    }

    public int size(){
        return this.ll.size();
    }

    public boolean isEmpty(){
        return this.ll.isEmpty() ;
    }

}
