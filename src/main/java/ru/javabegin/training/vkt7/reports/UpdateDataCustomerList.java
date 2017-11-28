package ru.javabegin.training.vkt7.reports;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.cleaner.Cleaner;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.ResultService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.TestService;
import ru.javabegin.training.vkt7.modem.*;
import ru.javabegin.training.vkt7.modem_cron.Daily_Moth_cron;
import ru.javabegin.training.vkt7.modem_run.ModemService;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Николай on 18.09.2017.
 */

  @Component

public class UpdateDataCustomerList implements Serializable {
    @Qualifier("jpaCustomerService")
    @Autowired
 CustomerService customerService;


    public void update(){

        Callable task = () -> {
                System.out.println("работает поток "+ Thread.currentThread().getName());
            System.out.println("Обновляем данные DataCustomerList!!!");
                    customerService.customerOperationStatus();

               /* File file = new File("C:\\Work\\Java\\work\\DataCustomerList.txt");
                FileWriter writer = new FileWriter(file, true);
                LocalDateTime ldt1=LocalDateTime.now();
                String log=ldt1+ "DataCustomerList обновлен "+ Thread.currentThread().getName()+" \n";
                writer.write( log);
                writer.flush();
                writer.close();*/

                return "123";

        };



       ExecutorService serviceDataCustomerList = Executors.newSingleThreadExecutor();
        Future<String> dcl= serviceDataCustomerList.submit(task);


        serviceDataCustomerList.shutdown();

        System.out.println("Основная программа работу закончила");


    }


}
