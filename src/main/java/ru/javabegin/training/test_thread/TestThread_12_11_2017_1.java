package ru.javabegin.training.test_thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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

                    final Thread currentThread = Thread.currentThread();
                   // final String oldName = currentThread.getName();
                    System.out.println("работает поток "+ i+ " "+currentThread.getName());
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

        //ExecutorService service = Executors.newSingleThreadExecutor();

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Тесты-%d")
                .setDaemon(false)
                .build();
        final ExecutorService service = Executors.newFixedThreadPool(1, threadFactory);

        System.out.println("-   -   -   -   проверяем future "+ future);
        try {
            System.out.println("-   -   -   -   isCancelled() future " + future.isCancelled());
            System.out.println("asfgasfgasfgasdfg ");
        } catch (Exception e){
            System.out.println("Нет еще потока "+e);

        }

       future = service.submit(task1);
        Thread.sleep(3000);
        System.out.println("-   -   -   -   проверяем future "+ future);
        try {
            System.out.println("-   -   -   -   isCancelled() future " + future.isCancelled());
            System.out.println("------- -----  Поток есть и не прерван ");

        } catch (Exception e){
            System.out.println("Нет еще потока "+e);

        }
        //future.cancel(true);
        t_kill_1.t_kill_1();
        Thread.sleep(3000);
        System.out.println("-   -   -   -   проверяем future "+ future);
        System.out.println("-   -   -   -   isCancelled() future "+ future.isCancelled());

        service.shutdown();







    }

}
