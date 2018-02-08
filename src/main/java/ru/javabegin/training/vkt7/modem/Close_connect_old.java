package ru.javabegin.training.vkt7.modem;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.m;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Close_connect_old extends EventListener{
        public static String data;
    //SerialPort serialPort;


    @Autowired Connect connect;


    @Autowired EventListener eventListener;



       //public void setStop(boolean stop) {this.stop = stop; }
   /* public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }*/
   /* public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }*/


    public void close_port() throws InterruptedException, TimeoutException, ExecutionException, SerialPortException {
/*
        System.out.println("\n t======= "+t);

        t=1;*/

        System.out.println("\n m======= "+m);
        System.out.println("\n Stop=== "+stop);
        System.out.println("\n Step=== "+step);
        System.out.println("\n m======= "+serialPort);
        System.out.println("\n t======= "+t);

        System.out.println("\n Начинает работать задача закрытия COM пора");



        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }


       // stop=true;

       // m=m+1;

        try {
            System.out.println("\n Останавливаем таймер ");
            t=1;
            Thread.sleep(2000);
            System.out.println("\n Выводим event listener ");

            step=2000;

            Thread.sleep(5000);
            System.out.println("\n Переходим в (+++) из программы закрытия соединения (+++)");

            serialPort.writeBytes("+++".getBytes());
            Thread.sleep(2000);
            System.out.println("\n Закрываем порт "+data);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            Thread.sleep(500);
            System.out.println("\n Разрываем связь из программы закрытия соединения");
            serialPort.writeBytes("ATH\r".getBytes());
            Thread.sleep(5000);
            System.out.println("\n После разрыва связи "+data);
            System.out.println("\n Закрываем порт из программы закрытия соединения");
            serialPort.closePort();
        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        }

    }


//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () -> {
            for (int i=1;i< Seconds+1;i++)
            {
                TimeUnit.SECONDS.sleep(1);
                if (t==1){
                    System.out.println("Ответ получен. Таймер остановлен");
                    return 1;
                }
                System.out.print(i+ " ");
            }
            System.out.println("timeout error");
            t=2;
            return 0;
        };
    }
///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////




}
