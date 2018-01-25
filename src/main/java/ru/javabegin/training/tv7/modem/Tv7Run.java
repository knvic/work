package ru.javabegin.training.tv7.modem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.db.CustomerService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class Tv7Run {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;





    public static volatile Future<String> futureTV7_1;
    public static volatile  Future<String> futureTV7_2;
    public static volatile ExecutorService serviceTV7;

    public void run_tv7(){
        Tv7_DB_Test tv7DbTest=new Tv7_DB_Test();

        Callable task = () -> {
            System.out.println("работает поток "+ Thread.currentThread().getName());

            String log=" Начал работать поток "+ Thread.currentThread().getName()+" \n";

            tv7DbTest.save_tv7(customerService);
            //daily_moth_cron.daily_all_cycle(customerList, customerService, auxiliaryService, data, type);
            return "123";
        };


       serviceTV7 = Executors.newSingleThreadExecutor();
        futureTV7_1 = serviceTV7.submit(task);


        //Future<String> future2 = service.submit(task);

        serviceTV7.shutdown();

        System.out.println("Основная программа работу закончила");



    }

    public void run_tv7_1(){
        Podgotovka podgotovka=new Podgotovka();

        Callable task = () -> {
            System.out.println("работает поток "+ Thread.currentThread().getName());

            String log=" Начал работать поток "+ Thread.currentThread().getName()+" \n";

            podgotovka.pRun(customerService);
            //daily_moth_cron.daily_all_cycle(customerList, customerService, auxiliaryService, data, type);
            return "123";
        };


        serviceTV7 = Executors.newSingleThreadExecutor();
        futureTV7_1 = serviceTV7.submit(task);


        //Future<String> future2 = service.submit(task);

        serviceTV7.shutdown();

        System.out.println("Основная программа работу закончила");



    }



}
