package ru.javabegin.training.vkt7.revisor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 18.11.2017.
 */
public class Vivod {
    public static void main (String[] args) throws InterruptedException {
        for(int i=0; i<2; i++){
            System.out.print(i);
            Thread.sleep(1000);
            System.out.print("\r");
        }
        AtomicInteger aa=new AtomicInteger();

        int dd = aa.get();
        System.out.print("\n"+dd);
    }
}
