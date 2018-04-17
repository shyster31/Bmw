package bean;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger counter = new AtomicInteger(0);

    public Counter() {
    }

    public int get(){
        return counter.get();
    }
    
    public void increment(){
        counter.incrementAndGet();
    }
    
    public void decrement(){
        counter.decrementAndGet();
    }
}
