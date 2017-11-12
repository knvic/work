package ru.javabegin.training.test_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_run {

public static volatile  Future<String> future;

    public void t_run () throws InterruptedException {
        Callable task1 = () -> {
            try {
                for (int i=1; i<30; i++){
                    System.out.println("работает поток "+ Thread.currentThread().getName()+" "+ i);
                    if (i==2){
                        System.out.println("переназываем поток ");
                        Thread.currentThread().setName("alibaba");
                    }
                    Thread.sleep(1000);
                }

                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };


        ExecutorService service = Executors.newSingleThreadExecutor();

        Future<String> future = service.submit(task1);
        //Thread.sleep(3000);
        //future.cancel(true);

        service.shutdown();



    }



}
