package com.nickmcconnell.p0;

public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
