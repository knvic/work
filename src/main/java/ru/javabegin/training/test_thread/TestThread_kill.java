package ru.javabegin.training.test_thread;

import java.util.concurrent.*;

import static ru.javabegin.training.test_thread.TestThread_12_11_2017_1.future;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.future1;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_kill {




    public void t_kill () throws InterruptedException, ExecutionException {

        if(future!=null) {
        System.out.println("Начинаем закрывать поток ");
        Thread.sleep(10000);
        //System.out.println("Начинаем закрывать поток "+ future.get());
        System.out.println("поток остановлен? "+ future1.isCancelled());
        Thread.sleep(3000);
        System.out.println("поток остановлен? "+ future1.isCancelled());
        Thread.sleep(3000);
        System.out.println("поток остановлен? "+ future1.isCancelled());
            Thread.sleep(3000);
            future1.cancel(true);
        System.out.println("Закрываем поток ");
        Thread.sleep(3000);
        System.out.println("поток остановлен? "+ future1.isCancelled());
        }else {
            System.out.println("не существует");}


        }
    }
