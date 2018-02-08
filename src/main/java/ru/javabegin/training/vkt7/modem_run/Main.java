/*
package ru.javabegin.training.vkt7.modem_run;

import jssc.SerialPort;
import jssc.SerialPortException;
import ru.javabegin.training.vkt7.modem.*;
import ru.javabegin.training.vkt7.modem.EventListener;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.util.ArrayList;
//import java.util.EventListener;
import java.util.List;
import java.util.concurrent.*;

*/
/**
 * Created by Николай on 18.08.2017.
 *//*

public class Main {
    public static volatile List<Object> connec;
    public static SerialPort serialPort;

    public static void main_rezerv (String[] args) throws InterruptedException, ExecutionException, TimeoutException, SerialPortException {
        Connect c =new Connect();
        List<Object> connect=new ArrayList<>();

        Callable task = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                connec =c.connect();
                //return Thread.currentThread().getName();
                return connec;

            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor1 = Executors.newFixedThreadPool(2);
        Future<Object> future = executor1.submit(task);

        // System.out.println(new Date()+ "::" + future.get());


        //if(future.isDone()){connec.forEach(p->System.out.print(p+" "));}
        List<Object> conn= (List<Object>)future.get();
        List<String> service_information=(List<String>)conn.get(1);
        List<Properts> prop_completed=(List<Properts>)conn.get(3);
        List<SerialPort> serialPortList = ( List<SerialPort>)conn.get(5);
        List<ru.javabegin.training.vkt7.modem.EventListener> eventListeners=(List<ru.javabegin.training.vkt7.modem.EventListener> )conn.get(6);
        System.out.print("Выводим полученный массив!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        service_information.forEach(p->System.out.print(p+" "));

        executor1.shutdown();

        System.out.println("Запускаем вторую задачу!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        System.out.println("Инициализируем класс!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");


        Current curre =new Current();


        serialPort=serialPortList.get(0);
        EventListener eventListener=eventListeners.get(0);
        curre.setEventListener(eventListener);
        curre.setSerialPort(serialPort);


        Callable task1 = () -> {
            try {
                //TimeUnit.SECONDS.sleep(1);
                curre.cur(prop_completed);
                return Thread.currentThread().getName();


            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor2 = Executors.newFixedThreadPool(2);
        Future<String> future1 = executor2.submit(task1);
        boolean a=true;
        while (a!=false) {
            try {
                if( future1.isDone()){
                    System.out.println("Done");

                    // заканчиваем работу executor service
                    executor2.shutdown();
                    a=false;

                }



                System.out.println("Ждем, пока не получены текущие значения");
                String s = future1.get(20L, TimeUnit.SECONDS);
                if(s !=null){
                    System.out.println("Результат  = " + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //оставим пустым
            }
        }


        System.out.println("Закрываем подключение !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

        Close_connect cl = new Close_connect();
        cl.setEventListener(eventListener);
        cl.setSerialPort(serialPort);

        Callable task3 = () -> {
            try {
                cl.close_port();
                return Thread.currentThread().getName();


            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor3 = Executors.newFixedThreadPool(2);
        Future<String> future3 = executor3.submit(task3);
         a=true;
        while (a!=false) {
            try {
                if( future3.isDone()){
                    System.out.println("Done");
                    // заканчиваем работу executor service
                    executor3.shutdown();
                    a=false;
                }



                System.out.println("Ждем, пока закрывается подключение");
                String s = future3.get(20L, TimeUnit.SECONDS);
                if(s !=null){
                    System.out.println("Результат  = " + s);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }catch(TimeoutException e){
                //оставим пустым
            }
        }



















    }
}
*/
