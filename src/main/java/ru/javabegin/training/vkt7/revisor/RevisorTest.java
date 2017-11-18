package ru.javabegin.training.vkt7.revisor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javabegin.training.test_thread.TestThread_kill;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Created by user on 18.11.2017.
 */
public class RevisorTest {
    Future<String>  future;
    ExecutorService service;
    Callable task1;
    public static AtomicInteger atomicInteger;

    @Before
    public void setUp() throws Exception {
        atomicInteger = new AtomicInteger();

        task1 = () -> {
            try {
                for (int i=1; i<5; i++){
                   // System.out.println("работает поток "+ i);
                    //System.out.println("atomicInteger= "+ atomicInteger.get());
                    atomicInteger.addAndGet(1);
                    Thread.sleep(1000);

                }

                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        service = Executors.newSingleThreadExecutor();

        future = service.submit(task1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void revisor() throws Exception {

for(int i=0; i<10; i++){
    System.out.println("atomicInteger= "+ atomicInteger.get());
    Thread.sleep(1000);
}
        service.shutdown();
    }

}