package ru.javabegin.training.vkt7.scheduler;

import java.util.Date;

/**
 * Created by Николай on 11.10.2017.
 */
public class Test_sch_13_10_2 {

    public void test() throws InterruptedException {


        String str = "";
        System.out.println("Executor 2 запущена =" + new Date());


        Thread.sleep(5000);
        System.out.println("Executor 2 остановлена =" + new Date());


    }


}
