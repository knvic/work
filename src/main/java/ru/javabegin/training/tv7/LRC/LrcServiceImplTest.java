package ru.javabegin.training.tv7.LRC;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LrcServiceImplTest {


    @Test
    public void lrcAdd() throws Exception {

        List<String> list=new ArrayList<>();
        list.add("1B");
        list.add("03");
        list.add("03");
        list.add("26");
        list.add("00");
        list.add("12");
        list.forEach(p-> System.out.print(p+" "));
        System.out.println();

        LrcServiceImpl serviceImp=new LrcServiceImpl();
        List<String> listLrc=serviceImp.lrcAdd(list);
        listLrc.forEach(p-> System.out.print(p+" "));
    }
    @Test
    public void lrcCheck() throws Exception {


        //String str="1B C8 00 0E 00 01";

        //String str="1B 48 00 1C 00 02 21 66 00 02 00 04 00 01 00 00 00 00";
        //String str="1B 03 03 26 00 12";
        //String str="1B 10 00 1C 00 04 08 00 09 06 1B 06 01 FF CF";

        //String str="3A 31 42 30 33 32 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 42 45 0D 0A";
        String str="3A 31 42 34 38 30 30 31 43 30 30 30 32 32 31 36 36 30 30 30 32 30 30 30 34 30 30 30 31 30 30 30 30 30 30 30 30 46 31 0D 0A";
        AscServiceImpl ascService=new AscServiceImpl();
        List<String> list=ascService.dectypt(str);




       // List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));



        list.forEach(p-> System.out.print(p+" "));
        System.out.println();

        LrcServiceImpl serviceImp=new LrcServiceImpl();
        System.out.println("Проверка контрольной суммы LRC : "+serviceImp.lrcCheck(list));


    }

}