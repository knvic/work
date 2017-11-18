package ru.javabegin.training.vkt7.revisor;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;

import java.util.concurrent.*;


import static ru.javabegin.training.test_thread.TestThread_run.atomicInteger;
import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by user on 18.11.2017.
 */
public class Revisor {
 public static volatile int t=0;
    Future<String> future;
    ExecutorService service;
    Callable task1;


    public void Revisor() throws InterruptedException {

        if(future2!=null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            System.out.println("Сохраняем atomicInteger----->>" + atomicInteger.get());
            int ai = (int) atomicInteger.get();
            System.out.println("Сохраняем atomicInteger----->>" + ai);
            executorService.submit(callable(20));
            while (t != 2) {
               // System.out.println("Ждем таймер ");
               // System.out.println("atomicInteger=== " + atomicInteger.get());
                Thread.sleep(3000);
                System.out.print("\r");


            }
            System.out.println("Время вышло");
            if (ai+100 > atomicInteger.get()) {
                System.out.println("Поток завис. Требуется прерывание");
                future2.cancel(true);
                } else {
                System.out.println("поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get());
            }
            System.out.println("atomicInteger=== " + atomicInteger.get());

            executorService.shutdown();
        }else
        {
            System.out.println("поток не запущен");
        }


    }


    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                if(stop==false){
                    System.out.println("Ответ из вспомогательного потока. Поступила команда STOP "+ stop);
                    return 1;}



                if (t == 1) {
                    System.out.println("Ответ получен. Таймер остановлен");
                    return 1;
                }
                //
                System.out.print(i);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\r");
              // System.out.print("\n  ***atomicInteger = "+ atomicInteger.get());
            }
            System.out.println("timeout error");
            t = 2;
            return 0;


        };
    }



}
