package ru.javabegin.training.vkt7.modem;



import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

import java.util.concurrent.TimeUnit;

import static ru.javabegin.training.vkt7.modem.Connect_test.m;


/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Close_connect_test extends EventListener{
  /*  @Autowired
    Connect connect;
    @Autowired Close_connect close_connect;*/

  //public volatile int m;

 public void close_port()  {

//connect.m=100;
     m=m+2;

     System.out.println("меняем м :: M= "+m);


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
