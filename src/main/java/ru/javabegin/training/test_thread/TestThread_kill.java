package ru.javabegin.training.test_thread;

import jssc.SerialPortException;
import ru.javabegin.training.vkt7.modem_run.ModemServiceImpl;

import java.util.concurrent.*;

import static ru.javabegin.training.test_thread.TestThread_12_11_2017_1.future;
import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.test_thread.TestThread_run.service2;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.future1;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.service;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_kill extends ru.javabegin.training.vkt7.modem_cron.EventListener_cron{




    public void t_kill () throws InterruptedException, ExecutionException, SerialPortException {

        if(future2!=null) {
        System.out.println("------>   Начинаем закрывать поток ");
        //Thread.sleep(10000);
     /*   //System.out.println("Начинаем закрывать поток "+ future.get());
        System.out.println("------>   поток остановлен? "+ future2.isCancelled());
        Thread.sleep(3000);
        System.out.println("------>   поток остановлен? "+ future2.isCancelled());
        Thread.sleep(3000);
        System.out.println("------>   поток остановлен? "+ future2.isCancelled());*/

            System.out.println("------>   Посылаем команду стоп ");
            t=1;
     stop=false;
            Thread.sleep(4000);
            if ( ModemServiceImpl.serialPort!=null& ModemServiceImpl.serialPort.isOpened())
            {System.out.println(" Port открыт ");
                ModemServiceImpl.serialPort.writeBytes("+++".getBytes());
                System.out.println("\nпосылаем ATH");
                Thread.sleep(1000);
                ModemServiceImpl.serialPort.writeBytes("ATH\r".getBytes());


                System.out.println("\n Закрываем порт ");

                ModemServiceImpl.serialPort.closePort();}
            else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}






            Thread.sleep(3000);
            future2.cancel(true);
        System.out.println("------>   Закрываем поток ");
        Thread.sleep(3000);
        System.out.println("------>  executor  service.isShutdown() ? " + service2.isShutdown());
                Thread.sleep(3000);
                System.out.println("------>  executor service.isTerminated() ? "+ service2.isTerminated());
                Thread.sleep(3000);


            service2.shutdownNow();
            System.out.println("------>  executor Закрываем ? " + service2.isShutdown());


            Thread.sleep(3000);

            System.out.println("------>  executor  service.isShutdown() ? " + service2.isShutdown());
            future2=null;



        }else {
            System.out.println("------>   не существует!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");}


        }
    }
