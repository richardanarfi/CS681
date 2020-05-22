package edu.umb.cs.cs681.hw15;

@FunctionalInterface
public interface Observer {
    void update(Observable o, Object obj);
}
