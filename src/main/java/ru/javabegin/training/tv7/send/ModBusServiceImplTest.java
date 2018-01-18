package ru.javabegin.training.tv7.send;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class ModBusServiceImplTest {



    @Test
    public void typeUnit() throws Exception {
        ModBusServiceImpl modBusService=new ModBusServiceImpl();
        AscServiceImpl ascService=new AscServiceImpl();

        List<String> commamd=modBusService.typeUnit(0);
        commamd.forEach(p->System.out.print(p+" "));

        List<String> commamdAsc=ascService.enctypt(commamd);
        commamdAsc.forEach(p->System.out.print(p+" "));
    }
    @Test
    public void day() throws Exception {
        int number=5;
        int addr=8550;
        System.out.println("123="+format("%04X", number));
        ModBusServiceImpl modBusService=new ModBusServiceImpl();

        StringBuilder dataSend= new StringBuilder();



        dataSend.append(format("%02X", 1));
        dataSend.append(format("%02X", 12));
        dataSend.append(format("%02X", 23));
        dataSend.append(format("%02X", 18));
        dataSend.append(format("%02X", 0));
        dataSend.append(format("%02X", 0));
        dataSend.append(format("%04X", 1));
        dataSend.append(format("%04X", 0));
        dataSend.append(format("%04X", 0));
        List<String> data = new ArrayList<>(Arrays.asList( dataSend.toString().replace(" ","").split("(?<=\\G.{2})")));


        /*List<String> data= new ArrayList<>();
        data.add(format("%02X", 12));
        data.add(format("%02X", 1));
        data.add(format("%02X", 18));
        data.add(format("%02X", 23));
        data.add(format("%02X", 0));
        data.add(format("%02X", 0));
        data.add(format("%04X", 1));*/
        List<String> list=modBusService.day(0,2740,109,99,6,12, 1, data);
        list.forEach(p-> System.out.print(p+" "));

        String dayRequest="00 48 0A B4 00 6D 00 63 00 06 00 0C 00 27 01 0C 17 12 00 00 00 01 00 00 00 00";

        System.out.print("\n"+dayRequest);
        String dayRequest1="01 48 0A B4 00 69 00 63 00 06 00 0C 00 01 01 0C 17 12 00 00 00 01 00 00 00 00";
        System.out.print("\n"+dayRequest1);

        String input_day ="01 48 00 26 00 00 17 02 01 00 03 00 D5 2E 00 00 40 F7 00 E5 36 20 21 32 18 31 04 00 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5C";
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> input = new ArrayList<>(Arrays.asList( input_day.replace(" ","").split("(?<=\\G.{2})")));
        lrcService.lrcCheck(input);

/*
//List<String> list= new ArrayList<>();
        // Arrays.asList(strHEX.replace(" ","").split("(?<=\\G.{2})"))
        //list.add(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        //list.add("48");
        StringBuilder command_temp=new  StringBuilder();

      */
/*  command_temp.delete(0, command_temp.length());
        command_temp.append(format("%04X", Arrd));
        System.out.println("command_temp = "+command_temp);
        list.add(command_temp.substring(0,2).toString());
        list.add(command_temp.substring(2).toString());
        list.forEach(p-> System.out.print(p+" "));
        command_temp.delete(0, command_temp.length());
        System.out.println("command_temp, after delete = "+command_temp);*//*

        command_temp.append(format("%02X", number));
        command_temp.append("48");
        command_temp.append(format("%04X", addr));
        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        list.forEach(p-> System.out.print(p+" "));
*/



    }


    @Test
    public void day1() throws Exception {
        ModBusServiceImpl modBusService=new ModBusServiceImpl();
        LocalDateTime ldt = LocalDateTime.now().minusDays(2);

        List<String> list=modBusService.day(0,ldt,5);
        list.forEach(p-> System.out.print(p+" "));


        String uu="00 48 0A B4 00 6D 00 63 00 06 00 0C 00 01 01 0C 17 12 00 00 00 01 00 00 00 00";
        System.out.println("\n\n"+uu);

    }

}