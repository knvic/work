package ru.javabegin.training.test_thread;

import java.util.concurrent.ExecutionException;

import static ru.javabegin.training.test_thread.TestThread_12_11_2017_1.future;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_kill_1 {




    public  void  t_kill_1() throws InterruptedException, ExecutionException {

if(future!=null) {
    System.out.println("Начинаем закрывать поток ");
    Thread.sleep(1000);
    //System.out.println("Начинаем закрывать поток "+ future.get());
    System.out.println("поток работает? " + future.isCancelled());
    Thread.sleep(3000);
    future.cancel(true);
}else {
    System.out.println("не существует");}

        }
    }
