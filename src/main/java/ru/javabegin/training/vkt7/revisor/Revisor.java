package ru.javabegin.training.vkt7.revisor;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;


import static ru.javabegin.training.test_thread.TestThread_run.atomicInteger;
import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by user on 18.11.2017.
 */
public class Revisor {
 public static volatile int tt=0;
    Future<String> future;
    ExecutorService service;
    Callable task1;


    public void Revisor() throws InterruptedException, IOException {

        File file = new File("D:\\Work\\work\\logRevizor.txt");
        FileWriter writer = new FileWriter(file, true);
        LocalDateTime ldt;
        String log;

        if(future2!=null) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            int ai = (int) atomicInteger.get();
            System.out.println("Сохраняем atomicInteger----->>" + ai);
            executorService.submit(callable(20));
            while (tt != 2) {
                Thread.sleep(14000);
                if(ai!= atomicInteger.get()){
                    tt=1;
                    break;
                }

            }
            System.out.println("Таймер отработал");
            if (ai== atomicInteger.get()) {
                System.out.println("Поток завис. Требуется прерывание и перезапуск");
                ldt=LocalDateTime.now();
                log=ldt+ " Поток завис. Требуется прерывание  \n";
                writer.write( log);
                writer.flush();
                writer.close();


                future2.cancel(true);



                } else {
                System.out.println("поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get());
                ldt=LocalDateTime.now();
                log=ldt+ " поток в рабочем состоянии ai = "+ai+ "atomicInteger = "+atomicInteger.get()+"\n";
                writer.write( log);
                writer.flush();
                writer.close();
            }
            System.out.println("atomicInteger=== " + atomicInteger.get());

            executorService.shutdown();
        }else
        {
            System.out.println("поток не запущен");
            ldt=LocalDateTime.now();
            log=ldt+ " поток не запущен \n";
            writer.write( log);
            writer.flush();
            writer.close();
        }


    }


    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                if(stop==false){
                    System.out.println("Ответ из вспомогательного потока. Поступила команда STOP "+ stop);
                    return 1;}



                if (tt == 1) {
                    System.out.println("Поток в порядке. Таймер остановлен");
                    return 1;
                }
                //
                System.out.print(i);
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\r");

            }
            System.out.println("timeout error");
            tt = 2;
            return 0;


        };
    }



}
