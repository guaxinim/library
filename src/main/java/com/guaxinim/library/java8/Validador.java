package com.guaxinim.library.java8;

@FunctionalInterface
public interface Validador<T> {
    boolean valida(T t);
}
