package ru.javabegin.training.vkt7.revisor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 18.11.2017.
 */
public class Vivod {
    public static void main (String[] args) throws InterruptedException, IOException {
        for(int i=0; i<2; i++){
            System.out.print(i);
            Thread.sleep(1000);
            System.out.print("\r");
        }
        AtomicInteger aa=new AtomicInteger();

        int dd = aa.get();
        System.out.print("\n"+dd);
        File file = new File("D:\\Work\\work\\logRevizor.txt");
        FileWriter writer = new FileWriter(file, true);
        LocalDateTime ldt= LocalDateTime.now();
        String log=ldt+ " Поток завис. Требуется прерывание \n";
        writer.write( log);
       // writer.append('\n');
        writer.flush();
        writer.close();
    }
}
