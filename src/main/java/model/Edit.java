package model;

public interface Edit<T> {
    void add(T object);
    void remove(T object);
    T get();
    String toString();
}
