package net.fabricmc.example;


public class Value<T> {
    T value;
    public Value(T def) {
        this.value = def;
    }
    public T get() {
        return this.value;
    }
}
