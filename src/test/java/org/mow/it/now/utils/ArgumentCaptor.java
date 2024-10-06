package org.mow.it.now.utils;

public class ArgumentCaptor<T> {
    private T argument;

    public T capte(T argument) {
        this.argument = argument;
        return argument;
    }

    public T argument() {
        return argument;
    }
}
