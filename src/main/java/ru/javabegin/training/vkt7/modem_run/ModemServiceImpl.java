package ru.javabegin.training.vkt7.modem_run;

import jssc.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.modem.*;

import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Николай on 18.09.2017.
 */

    @Component

public class ModemServiceImpl implements ModemService {
    public static volatile List<Object> connec;
  public static volatile SerialPort serialPort;
    public static volatile boolean stop=true;


  @Autowired
  Connect_test connect;
  @Autowired
  Close_connect_test close_connect;


    public static int getM() {
        return m;
    }
    public void increment() {
        m++;
    }

    public static volatile int m=50;

    @Autowired EventListener eventListener;
    @Override
    public void connect() throws ExecutionException, InterruptedException {

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
        executor1.shutdown();
    }


    @Override
    public void close_connect(){
        System.out.println("Закрываем подключение !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");

        //Close_connect cl = new Close_connect();
        Close_connect_test cl = new Close_connect_test();


        Callable task3 = () -> {

            cl.close_port();
            return Thread.currentThread().getName();

            /*try {



            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }*/
        };

        ExecutorService executor3 = Executors.newFixedThreadPool(2);
        Future<String> future3 = executor3.submit(task3);
      /*   boolean a=true;
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
        }*/

        executor3.shutdown();

    }


}
