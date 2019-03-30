package ru.javabegin.training.tv7.modem;



import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;

@Component
@PropertySource("classpath:allProperties.properties")
public class Tv7Run {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;






    public static volatile Future<String> futureTV7_1;
    public static volatile  Future<String> futureTV7_2;
    public static volatile ExecutorService serviceTV7;


    @Value("${year}")
    private  int year;
    @Value("${month}")
    private  int month;
    @Value("${dayOfMonth}")
    private  int dayOfMonth;
    public void tv7RunCron() throws InterruptedException, ExecutionException, TimeoutException, SerialPortException, IOException {
        Modem_cron modem_cron=new Modem_cron();

        java.util.concurrent.Callable task = () -> {
            System.out.println("работает поток "+ Thread.currentThread().getName());

            String log=" Начал работать поток "+ Thread.currentThread().getName()+" \n";
            LocalDateTime ldt= LocalDateTime.now().minusDays(1);

            modem_cron.tv7_cron(customerService, ldt);
            //daily_moth_cron.daily_all_cycle(customerList, customerService, auxiliaryService, data, type);
            return "123";
        };

        System.out.println("==>>> ТВ7 МЕСТО ГДЕ НАДО ПРОВЕРЯТЬ ДАТУ <<<======");
        LocalDateTime now= LocalDateTime.now();
       // LocalDateTime deadLine = LocalDateTime.of(2018, 8, 28, 0, 0, 0);
       // LocalDateTime deadLine = LocalDateTime.of(2019, 3, 14, 0, 0, 0);
        LocalDateTime deadLine = LocalDateTime.of(year, month, dayOfMonth, 0, 0, 0);
        if (!now.isAfter(deadLine)){

            System.out.println("==>>> ТВ7 The DemoVersion so far in work!!!!!!!!!!! <<<======");



            serviceTV7 = Executors.newSingleThreadExecutor();
            futureTV7_1 = serviceTV7.submit(task);
            serviceTV7.shutdown();





        }
        else{
            System.out.println("==>>>ТВ7  The demo visrion expires "+deadLine +" !!!!!!!!!!! <<<======");
        }




      /*  serviceTV7 = Executors.newSingleThreadExecutor();
        futureTV7_1 = serviceTV7.submit(task);


        //Future<String> future2 = service.submit(task);

        serviceTV7.shutdown();*/

        System.out.println("Основная программа работу закончила");



    }


    public void tv7RunCron_modem(Customer customer) throws InterruptedException, ExecutionException, TimeoutException, SerialPortException, IOException {
        Modem_cron1 modem_cron=new Modem_cron1();

        java.util.concurrent.Callable task = () -> {
            System.out.println("работает поток "+ Thread.currentThread().getName());

            String log=" Начал работать поток "+ Thread.currentThread().getName()+" \n";
            LocalDateTime ldt= LocalDateTime.now().minusDays(1);


            modem_cron.tv7_cron(customerService, ldt, customer);
            //daily_moth_cron.daily_all_cycle(customerList, customerService, auxiliaryService, data, type);
            return "123";
        };


        serviceTV7 = Executors.newSingleThreadExecutor();
        futureTV7_1 = serviceTV7.submit(task);
        serviceTV7.shutdown();



        System.out.println("Основная программа работу закончила");



    }


    public void run_tv7(){
        Tv7_DB_Test tv7DbTest=new Tv7_DB_Test();

        java.util.concurrent.Callable task = () -> {
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

        java.util.concurrent.Callable task = () -> {
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
