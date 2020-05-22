package edu.umb.cs.cs681.hw16;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private ConcurrentHashMap<Path, Integer> counter;
    private static ReentrantLock slock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {
        counter = new ConcurrentHashMap<Path, Integer>();
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
        try {
            if (counter.containsKey(path)) {
                return counter.get(path);
            } else {
                return 0;
            }
        } finally {
            System.out.println(Thread.currentThread().getName()  + " getCount " + path + " count " + counter.get(path));
        }
    }

    public void increment(Path path) {
        try {
            if (counter.containsKey(path)) {
                counter.put(path, counter.get(path) + 1);
            } else {
                counter.put(path, 1);
            }
        } finally {
            System.out.println(Thread.currentThread().getName()  + " increment " + path + " count " + counter.get(path));
        } 
    }
}
