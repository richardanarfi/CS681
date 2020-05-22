package edu.umb.cs.cs681.hw12;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {
	private HashMap<Path, Integer> counter;
    private ReentrantReadWriteLock rwLock;
    private static ReentrantLock slock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {
        counter = new HashMap<Path, Integer>();
        rwLock = new ReentrantReadWriteLock();
    }
    
    public static AccessCounter getInstance() {
        slock.lock();
        try {
            if (instance == null) {
                instance = new AccessCounter();
            }
            return instance;
        } finally {
            slock.unlock();
        }
    }

    public int getCount(Path path) {
        rwLock.readLock().lock();
        try {
            if (counter.containsKey(path)) {
                return counter.get(path);
            } else {
                return 0;
            }
        } finally {
            System.out.println(Thread.currentThread().getName()  + " getCount " + path + " count " + counter.get(path));
            rwLock.readLock().unlock();
        }
    }

    public void increment(Path path) {
        rwLock.writeLock().lock();
        try {
            if (counter.containsKey(path)) {
                counter.put(path, counter.get(path) + 1);
            } else {
                counter.put(path, 1);
            }
        } finally {
            System.out.println(Thread.currentThread().getName()  + " increment " + path + " count " + counter.get(path));
            rwLock.writeLock().unlock();
        }
    }
}
