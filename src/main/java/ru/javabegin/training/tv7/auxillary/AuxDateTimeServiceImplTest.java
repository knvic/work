package ru.javabegin.training.tv7.auxillary;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.recieve.ModBusRServiceImpl;
import ru.javabegin.training.tv7.recieve.Tupel_date;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class AuxDateTimeServiceImplTest {
    @Test
    public void from_the_beginning_of_month() throws Exception {
        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();

        LocalDateTime ldt = LocalDateTime.now();

        List<LocalDateTime> list=dateTimeService.from_the_beginning_of_month(ldt);
        list.forEach(p-> System.out.println(p));

    }

    @Test
    public void checkBeginArchive() {


        String str1="3A30313033333630423136304131313030303030373139313731313030303030433146313731303030303030373139313731313030303030313135303931323030303030313134313731323030303030433146313731313030303030313134313731323030303030433143304631303332333946360D0A";
        String hex="01 03 36 0B 16 0A 11 00 00 07 19 17 11 00 00 0C 1F 17 10 00 00 07 19 17 11 00 00 01 15 09 12 00 00 01 14 17 12 00 00 0C 1F 17 11 00 00 01 14 17 12 00 00 0C 1C 0F 10 32 39 F6";

        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(str1);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
        inHex.forEach(p->System.out.print(p+" "));

        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        Map<String,Tupel_date> infOfDate =modBusRService.infOfDate(inHex);

        // Проверяем СУТОЧНЫЕ Создаем дату
        LocalDateTime ldt1=LocalDateTime.now();
        LocalDateTime ldt2=LocalDateTime.of(2017,7,25,22,00,0);
        AuxDateTimeServiceImpl auxDateTimeService=new AuxDateTimeServiceImpl();
        boolean result = auxDateTimeService.checkBeginArchive(infOfDate, ldt1, "day");
        System.out.println("Результат = "+result);

        result = auxDateTimeService.checkBeginArchive(infOfDate, ldt2, "day");
        System.out.println("начало массива DAY = "+infOfDate.get("begin_day").getLocalDateTime());
        System.out.println("проверяемая дата = "+ldt2);
        System.out.println("Результат = "+result);



        // Проверяем МЕСЯЦ Создаем дату
        LocalDateTime ldt3=LocalDateTime.of(2016,11,25,22,00,0);
        result = auxDateTimeService.checkBeginArchive(infOfDate, ldt3, "month");
        System.out.println("начало массива MONTH = "+infOfDate.get("begin_month").getLocalDateTime());
        System.out.println("проверяемая дата = "+ldt3);
        System.out.println("Результат = "+result);

        // Проверяем TOTAL Создаем дату
        LocalDateTime ldt4=LocalDateTime.of(2017,7,25,22,00,0);

        result = auxDateTimeService.checkBeginArchive(infOfDate, ldt4, "total");
        System.out.println("начало массива TOTAL = "+infOfDate.get("begin_total").getLocalDateTime());
        System.out.println("проверяемая дата = "+ldt4);
        System.out.println("Результат = "+result);

    }

    @Test
    public void timeStamp_to_stringData() {
        AuxDateTimeServiceImpl auxDateTimeService=new AuxDateTimeServiceImpl();
        Timestamp ts=Timestamp.valueOf(LocalDateTime.now());
        System.out.println("ts = "+ts);
        System.out.println("Результат = "+auxDateTimeService.timeStamp_to_stringData(ts));


    }
}