package edu.umb.cs.cs681.hw17;

@FunctionalInterface
public interface Observer {
    void update(Observable o, Object obj);
}
