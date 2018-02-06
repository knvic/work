package ru.javabegin.training.thread;

import jssc.SerialPortException;

import java.util.concurrent.ExecutionException;

import static ru.javabegin.training.tv7.modem.Tv7Run.futureTV7_1;
import static ru.javabegin.training.tv7.modem.Tv7Run.serviceTV7;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.*;

/**
 * Created by Николай on 12.11.2017.
 */
public class ThreadKillTv7 extends ru.javabegin.training.tv7.modem.Modem_cron{




    public void tv7_kill () throws InterruptedException, ExecutionException, SerialPortException {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
        logger.info(" Начала работать программа kill_tv7_Thread");
        if(futureTV7_1!=null) {
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
            futureTV7_1.cancel(true);
        System.out.println("------>   Закрываем поток ");
            logger.info("------>   Закрываем поток ");
        Thread.sleep(3000);
            futureTV7_1.cancel(true);
            System.out.println("------> Проверяем снята ли задача (futureTV7_1.isCancelled())=="+ futureTV7_1.isCancelled());



            serviceTV7.shutdownNow();
            System.out.println("------>  executor Закрываем serviceTV7.isShutdown() " + serviceTV7.isShutdown());
            System.out.println("------>  executor Закрываем serviceTV7.isTerminated() " + serviceTV7.isTerminated());

            Thread.sleep(3000);
            serviceTV7.shutdownNow();

            System.out.println("------>  executor  service.isShutdown() ? " + serviceTV7.isShutdown());
            try {
                futureTV7_1=null;
            }
            catch (Exception e){
                System.out.println("Поток уже звакрыт и не существует =>"+e);
            }

        }else {
            System.out.println("------> поток не запущен");}


        }
    }
