package ru.javabegin.training.thread;

import jssc.SerialPortException;

import java.util.concurrent.ExecutionException;


import static ru.javabegin.training.tv7.revizor.TestRevizorThread.futureTV7_1;
import static ru.javabegin.training.tv7.revizor.TestRevizorThread.serviceTV7;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.11.2017.
 */
public class ThreadKillTv7_test extends ru.javabegin.training.tv7.modem.Modem_cron{




    public void tv7_kill () throws InterruptedException, ExecutionException, SerialPortException {
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
        logger.info(" Начала работать программа kill_tv7_Thread");
        if(futureTV7_1!=null) {
        System.out.println("------>   Начинаем закрывать поток ");


            t=1;
            stop=false;
            Thread.sleep(4000);

            System.out.println("Проверяем статус задачи futureTV7_1 (futureTV7_1.isCancelled()) : "+futureTV7_1.isCancelled());

            //  Thread.sleep(3000);
            futureTV7_1.cancel(true);
        System.out.println("------>   Закрываем поток ");

        Thread.sleep(3000);
            futureTV7_1.cancel(true);

            System.out.println("Проверяем статус задачи futureTV7_1 (futureTV7_1.isCancelled())  после прерывания потока: "+futureTV7_1.isCancelled());

            serviceTV7.shutdownNow();
            System.out.println("------>  executor Закрываем  serviceTV7.isShutdown() -- " + serviceTV7.isShutdown());
            System.out.println("------>  executor Закрываем  serviceTV7.isTerminated() == " + serviceTV7.isTerminated());


            Thread.sleep(3000);
            serviceTV7.shutdownNow();
            System.out.println("------>  executor Закрываем  serviceTV7.isShutdown() -- " + serviceTV7.isShutdown());
            System.out.println("------>  executor Закрываем  serviceTV7.isTerminated() == " + serviceTV7.isTerminated());

           try {
               futureTV7_1=null;
           }
            catch (Exception e){
                System.out.println(e);
            }


        }else {
            System.out.println("------>   не существует!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");}


        }
    }
