package ru.javabegin.training.test_thread;

import java.util.concurrent.*;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_12_11_2017_1 {

public static volatile  Future<String> future;

    public static void main (String[] args) throws InterruptedException, ExecutionException {
        Callable task1 = () -> {
            try {
                for (int i=1; i<30; i++){
                    System.out.println("работает поток "+ i);
                    Thread.sleep(1000);
                }

                return "123";

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

TestThread_kill t_kill=new TestThread_kill();

        ExecutorService service = Executors.newSingleThreadExecutor();

       future = service.submit(task1);
        //Thread.sleep(3000);
        //future.cancel(true);
        //t_kill.t_kill();
        service.shutdown();



    }



}
