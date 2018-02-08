package ru.javabegin.training.tv7.revizor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javabegin.training.tv7.modem.Tv7Run.futureTV7_1;

public class TestRevizorThread {

    volatile static int t;
    public static volatile Future<String> futureTV7_1;
    public static volatile ExecutorService serviceTV7;
    public static AtomicInteger atomicInteger;
    public static volatile boolean end_tv7_temp;

   // public static volatile Future<String> future;
    public void RunTestThreadRevizor () throws InterruptedException, ExecutionException {
        end_tv7_temp=false;
        atomicInteger=new AtomicInteger();
        atomicInteger.addAndGet(1);

        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Тесты-%d")
                .setDaemon(false)
                .build();

        serviceTV7 = Executors.newFixedThreadPool(1, threadFactory);
        t=0;


        //final CompletableFuture<BigDecimal> future = CompletableFuture.supplyAsync(this::calculate, executorService);

        try {
            System.out.println("-   -   -   -   isCancelled() future " + futureTV7_1.isCancelled());
            System.out.println("asfgasfgasfgasdfg ");
        } catch (Exception e){
            System.out.println("Нет еще потока "+e);

        }

        futureTV7_1 = serviceTV7.submit(callable(400,"ATH"));

        serviceTV7.shutdown();


    }




    static Callable callable(long Seconds, String name) {
        return () ->
        {

            final Thread currentThread = Thread.currentThread();
            final String oldName = currentThread.getName();
            currentThread.setName("Обработка-" + name);
            System.out.println("работает поток "+ currentThread.getName());
            int j=0;
           try {
               for (int i = 1; i < 40 + 1; i++) {

                   //System.out.print(i);
                   TimeUnit.SECONDS.sleep(1);

                   if (i%4==0){
                       atomicInteger.addAndGet(1);
                       System.out.println("поток работает atomicInteger = "+atomicInteger.get());
                        }



                   System.out.print("\r");
               }

               System.out.println("Тестовое зависание");
                TimeUnit.SECONDS.sleep(80);


               for (int i = 1; i < 40 + 1; i++) {

                   //System.out.print(i);
                   TimeUnit.SECONDS.sleep(1);

                   if (i%4==0){
                       atomicInteger.addAndGet(1);
                       System.out.println("поток продолжил работу atomicIntegerа "+atomicInteger.get());
                   }



                   System.out.print("\r");
               }


               System.out.println("Поток закаочивает работу " + currentThread.getName());
               t = 2;
               end_tv7_temp=true;

           }finally {
               currentThread.setName(oldName);

           }

            return 0;
        };
    }
}
