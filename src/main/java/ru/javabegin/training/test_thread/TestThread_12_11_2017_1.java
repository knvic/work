package ru.javabegin.training.test_thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_12_11_2017_1 {

public static volatile  Future<String> future;
public static AtomicInteger atomicInteger;
    public static void main (String[] args) throws InterruptedException, ExecutionException {
        atomicInteger = new AtomicInteger();
        Callable task1 = () -> {
            try {
                for (int i=1; i<30; i++){
                    System.out.println("работает поток "+ i);
                    Thread.sleep(1000);
                    System.out.println("atomicInteger= "+ atomicInteger.get());
                    atomicInteger.addAndGet(1);
                }

                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

TestThread_kill_1 t_kill_1=new TestThread_kill_1();

        ExecutorService service = Executors.newSingleThreadExecutor();

       future = service.submit(task1);
        Thread.sleep(3000);
        //future.cancel(true);
        t_kill_1.t_kill_1();
        service.shutdown();


    }

}
