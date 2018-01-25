package ru.javabegin.training.tv7.auxillary;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.tv7.recieve.ModBusRServiceImpl;
import ru.javabegin.training.tv7.recieve.Tupel_date;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void checkOfDate() throws Exception {

        String str1="3A30313033333630423136304131313030303030373139313731313030303030433146313731303030303030373139313731313030303030313135303931323030303030313134313731323030303030433146313731313030303030313134313731323030303030433143304631303332333946360D0A";
        String hex="01 03 36 0B 16 0A 11 00 00 07 19 17 11 00 00 0C 1F 17 10 00 00 07 19 17 11 00 00 01 15 09 12 00 00 01 14 17 12 00 00 0C 1F 17 11 00 00 01 14 17 12 00 00 0C 1C 0F 10 32 39 F6";

        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(str1);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
        inHex.forEach(p->System.out.print(p+" "));

        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        Map<String,Tupel_date> infOfDate= modBusRService.infOfDate(inHex);
//// Сам тест для СУТОЧНЫЕ
        AuxServiceImpl auxService=new AuxServiceImpl();
        LocalDateTime ldt=LocalDateTime.now().minusMonths(6);
        boolean check =auxService.checkOfDate("day",infOfDate,ldt);
        System.out.println("Результат проверки : "+check);
        System.out.println("проверяемая дата : "+ldt);

       /* //// Сам тест для СУТОЧНЫЕ
        ldt=LocalDateTime.now().minusMonths(5);
        check =auxService.checkOfDate("day",infOfDate,ldt);
        System.out.println("Результат проверки : "+check);
        System.out.println("проверяемая дата : "+ldt);*/
    }

}