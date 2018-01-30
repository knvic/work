package ru.javabegin.training.test_thread;

import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

public class Thread_2018 {

    volatile static int t;
    public static volatile Future<String> future;
    public static void main (String[] args) throws InterruptedException, ExecutionException {

        Callable callable;
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Тесты-%d")
                .setDaemon(false)
                .build();

        final ExecutorService service = Executors.newFixedThreadPool(1, threadFactory);
        t=0;


        //final CompletableFuture<BigDecimal> future = CompletableFuture.supplyAsync(this::calculate, executorService);

        try {
            System.out.println("-   -   -   -   isCancelled() future " + future.isCancelled());
            System.out.println("asfgasfgasfgasdfg ");
        } catch (Exception e){
            System.out.println("Нет еще потока "+e);

        }
        future = service.submit(callable(30,"ATH"));

        service.shutdown();


    }




    static Callable callable(long Seconds, String name) {
        return () ->
        {
            final Thread currentThread = Thread.currentThread();
            final String oldName = currentThread.getName();
            currentThread.setName("Обработка-" + name);
            System.out.println("работает поток "+ currentThread.getName());
           try {
               for (int i = 1; i < Seconds + 1; i++) {

                   System.out.print(i);
                   TimeUnit.SECONDS.sleep(1);
                   if (t == 1) {
                       System.out.println("Ответ " + currentThread.getName() + " получен. Таймер остановлен");
                       return 1;
                   }
                   System.out.print("\r");
               }
               System.out.println("timeout error " + currentThread.getName());
               t = 2;
               return 0;
           }finally {
               currentThread.setName(oldName);
           }

        };
    }
}
