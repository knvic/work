package ru.javabegin.training.test_thread;

import jssc.SerialPortException;
import org.slf4j.Logger;
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
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
        logger.info(" Начала работать программа kill_modem");
        if(future1!=null) {
        System.out.println("------>   Начинаем закрывать поток ");


            t=1;
            stop=false;
            Thread.sleep(4000);
            if (serialPort!=null&serialPort.isOpened())
            {logger.info(" Port открыт. Программа kill разрывает связь и закрывает порт");
                System.out.println(" Port открыт. Программа kill разрывает связь и закрывает порт");
               serialPort.writeBytes("+++".getBytes());
                System.out.println("\nпосылаем ATH");
                Thread.sleep(1000);
               serialPort.writeBytes("ATH\r".getBytes());


                System.out.println("\n Закрываем порт ");

                serialPort.closePort();}
            else {logger.info(" Port УЖЕ ЗАКРЫТ ");
                System.out.println(" Port УЖЕ ЗАКРЫТ ");}


            if (serialPort.isOpened())
            {logger.info("Прерываем программу но порт ОТКРЫТ!!! ");
                logger.info("Прерываем программу но порт ОТКРЫТ!!! ");
                System.out.println(" Port открыт ");}
            else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}




            //  Thread.sleep(3000);
            future1.cancel(true);
        System.out.println("------>   Закрываем поток ");
            logger.info("------>   Закрываем поток ");
        Thread.sleep(3000);
            future1.cancel(true);
        /*System.out.println("------>  executor  service.isShutdown() ? " + service.isShutdown());
                Thread.sleep(3000);
                System.out.println("------>  executor service.isTerminated() ? "+ service.isTerminated());
                Thread.sleep(3000);*/


            service.shutdownNow();
            System.out.println("------>  executor Закрываем ? " + service.isShutdown());


            Thread.sleep(3000);
            service.shutdownNow();

            System.out.println("------>  executor  service.isShutdown() ? " + service.isShutdown());
            future1=null;

        }else {
            System.out.println("------>   не существует!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");}


        }
    }
