package ru.javabegin.training.test_thread;

import jssc.SerialPortException;
import ru.javabegin.training.vkt7.modem_run.ModemServiceImpl;

import java.util.concurrent.ExecutionException;

import static ru.javabegin.training.test_thread.TestThread_run.future2;
import static ru.javabegin.training.test_thread.TestThread_run.service2;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.future1;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.service;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.11.2017.
 */
public class TestThread_kill_modem extends ru.javabegin.training.vkt7.modem_cron.EventListener_cron{




    public void t_kill () throws InterruptedException, ExecutionException, SerialPortException {

        if(future1!=null) {
        System.out.println("------>   Начинаем закрывать поток ");
     /*   Thread.sleep(10000);
        //System.out.println("Начинаем закрывать поток "+ future.get());
        System.out.println("------>   поток остановлен? "+ future1.isCancelled());
        Thread.sleep(3000);
        System.out.println("------>   поток остановлен? "+ future1.isCancelled());
        Thread.sleep(3000);*/
        //System.out.println("------>   поток остановлен? "+ future1.isCancelled());


            t=1;
            stop=false;
            Thread.sleep(4000);
            if (serialPort!=null&serialPort.isOpened())
            {System.out.println(" Port открыт. Программа kill разрывает связь и закрывает порт");
               serialPort.writeBytes("+++".getBytes());
                System.out.println("\nпосылаем ATH");
                Thread.sleep(1000);
               serialPort.writeBytes("ATH\r".getBytes());


                System.out.println("\n Закрываем порт ");

                serialPort.closePort();}
            else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}

            //  Thread.sleep(3000);
            future1.cancel(true);
        System.out.println("------>   Закрываем поток ");
        Thread.sleep(3000);
        /*System.out.println("------>  executor  service.isShutdown() ? " + service.isShutdown());
                Thread.sleep(3000);
                System.out.println("------>  executor service.isTerminated() ? "+ service.isTerminated());
                Thread.sleep(3000);*/


            service.shutdownNow();
            System.out.println("------>  executor Закрываем ? " + service.isShutdown());


            Thread.sleep(3000);

            System.out.println("------>  executor  service.isShutdown() ? " + service.isShutdown());
            future1=null;

        }else {
            System.out.println("------>   не существует!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");}


        }
    }
