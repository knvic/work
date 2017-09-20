package ru.javabegin.training.vkt7.modem;


import org.springframework.stereotype.Component;
import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Connect extends EventListener{
    public static String data;
    public static volatile int m;
    public static volatile boolean stop;




    public  List<Object> connect () throws InterruptedException, TimeoutException, ExecutionException {
        List<Object> connect =new ArrayList<>();
        stop=true;
        int repeat=0;

        System.out.print("\n Ждем установки связи :");


        while (m!=100) {
            System.out.println("m= "+m);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.print("\n поменялось!!!!!!!! M== "+m);



        return connect;
    }



//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                   if(stop=false){return 0;}
                    TimeUnit.SECONDS.sleep(1);
                    if (t == 1) {
                        System.out.println("Ответ получен. Таймер остановлен");
                        return 1;
                    }
                    System.out.print(i + " ");
                }
                System.out.println("timeout error");
            System.out.println("M= "+m);
            t = 2;
            return 0;


        };
    }
///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////




}
