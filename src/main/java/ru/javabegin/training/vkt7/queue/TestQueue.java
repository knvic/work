package ru.javabegin.training.vkt7.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Николай on 22.10.2017.
 */
public class TestQueue {



        public static void main (String[] args) {
            ExecutorService service = Executors.newSingleThreadExecutor();

            for (int i = 0; i < 10; i++){
                final int num = i;
                System.out.println("Send task " + num);

                service.submit(()->{
                    try {
                        System.out.println("Start thread " + num);
                        Thread.sleep(1000);
                        System.out.println("Finish thread " + num);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });



            }
            Callable task1 = () -> {
                try {
                    System.out.println("запуск " + 01);
                    Thread.sleep(5000);
                    System.out.println("выполнено " + 01);
                    return "123";

                }
                catch (InterruptedException e) {
                    throw new IllegalStateException("task interrupted", e);
                }
            };
            Callable task2 = () -> {
                try {
                    System.out.println("запуск " + 02);
                    Thread.sleep(2000);
                    System.out.println("выполнено  " + 02);
                    return "123";

                }
                catch (InterruptedException e) {
                    throw new IllegalStateException("task interrupted", e);
                }
            };

            Future<String> future = service.submit(task1);
            Future<String> future1 = service.submit(task2);
            service.shutdown();
 /*           ExecutorService executor = Executors.newSingleThreadExecutor();

            Callable task1 = () -> {
                try {
                    System.out.println("запуск " + 01);
                    Thread.sleep(5000);
                    System.out.println("запуск " + 01);
                    return "123";

                }
                catch (InterruptedException e) {
                    throw new IllegalStateException("task interrupted", e);
                }
            };
            Callable task2 = () -> {
                try {
                    System.out.println("запуск " + 02);
                    Thread.sleep(2000);
                    System.out.println("запуск " + 02);
                    return "123";

                }
                catch (InterruptedException e) {
                    throw new IllegalStateException("task interrupted", e);
                }
            };
            Future<String> future = executor.submit(task1);
            Future<String> future1 = executor.submit(task1);
            executor.shutdown();
*/

        }
    }

