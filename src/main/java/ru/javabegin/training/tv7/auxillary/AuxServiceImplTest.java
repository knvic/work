package ru.javabegin.training.tv7.auxillary;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

public class AuxServiceImplTest {



    @Test
    public void localDate_to_tv7() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();
        auxService.localDate_to_tv7(LocalDateTime.now().minusDays(2));

    }

    @Test
    public void date_to_tv7() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();
        Date data=new Date();
        auxService.Date_to_tv7(data);
        //0D0D177E20000
    }

    @Test
    public void l2b() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();
       // String s1="FB2242C0";
       //String s2="42C0FB22";
        String s1="F1C13F61";

        System.out.println("работа : "+auxService.l2b(s1) );

    }


    @Test
    public void hexToBinary() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();

        auxService.hexToBinary("0286", 2);

        String str0286="10 10000110";
        String str0286_дополненное ="0000001010000110"; //верное преобразование
        StringBuilder str=new StringBuilder("0000001010000110");
        System.out.println("реверс :"+str.reverse().toString());

        String str8602="10000110 00000010";

        StringBuilder str1=new StringBuilder( auxService.hexToBinary("80", 1));
        System.out.println("реверс :"+str1.reverse().toString());


    }

}