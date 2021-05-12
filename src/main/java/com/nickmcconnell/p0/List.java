package com.nickmcconnell.p0.util;

public interface List<T> extends Collection<T> {
    void add(T data);
    T remove(T data);
    T get(int index);
}
