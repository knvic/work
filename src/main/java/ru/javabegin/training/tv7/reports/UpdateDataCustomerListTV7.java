package ru.javabegin.training.tv7.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.checkUpdateData.CheckUpdate;
import ru.javabegin.training.vkt7.db.CustomerService;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Николай on 18.09.2017.
 */

  @Component

public class UpdateDataCustomerListTV7 implements Serializable {



     @Qualifier("jpaCustomerService")
    @Autowired
 CustomerService customerService;
    @Autowired
    DataCustomerListTV7 dcsTV7;
    @Autowired
    CheckUpdate checkUpdate;


    public void update(){

        Callable task = () -> {
                System.out.println("работает поток "+ Thread.currentThread().getName());
            System.out.println("Обновляем данные DataCustomerListТВ7!!!");
            checkUpdate.update(customerService, LocalDateTime.now().minusDays(1), dcsTV7 );

                return "123";

        };

       ExecutorService serviceDataCustomerListTV7 = Executors.newSingleThreadExecutor();
        Future<String> dcl= serviceDataCustomerListTV7.submit(task);

        serviceDataCustomerListTV7.shutdown();



    }







}
