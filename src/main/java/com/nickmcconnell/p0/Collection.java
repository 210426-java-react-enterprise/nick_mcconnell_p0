package com.nickmcconnell.p0;

public interface Collection<T> {
    int size();
    boolean contains(T data);
    void add(T data);
    T remove(T data);
}
