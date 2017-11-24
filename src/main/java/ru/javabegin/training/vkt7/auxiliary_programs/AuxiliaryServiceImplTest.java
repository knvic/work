package ru.javabegin.training.vkt7.auxiliary_programs;

import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 18.11.2017.
 */
public class AuxiliaryServiceImplTest {
    @Test
    public void getLastDayPrevisionMoth() throws Exception {
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println("ldt="+ldt);
        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(ldt);


        Timestamp tstamp = auxiliaryService.getLastDayPrevisionMoth(ts);

        System.out.println("tstamp="+tstamp);


    }

    @Test
    public void saveMessage() throws Exception {
        File file = new File("C:\\Work\\Java\\work\\logRevizor1.txt");
        String str="Тест записи";

        auxiliaryService.saveMessage(file,str);

    }

    @Test
    public void stringDate_to_TimeStamp() throws Exception {

        String s = "19:11:17:23";
       Timestamp date=auxiliaryService.stringDate_to_TimeStamp(s);

    }

    @Test
    public void stringDate_to_Date() throws Exception {

        String s = "19:11:17:23";
        Date date=auxiliaryService.stringDate_to_Date(s);


    }

    @Test
    public void from_the_beginning_of_month() throws Exception {
        Date input = new Date();
        System.out.println("Вывод массива с датами");
        List<Date> dateList=auxiliaryService.from_the_beginning_of_month(input);
        for(Date data:dateList){

            System.out.println(data);
            java.sql.Timestamp dd=auxiliaryService.date_TimeStamp(data);
            System.out.println("ts"+ dd);
        }

    }

    @Test
    public void from_the_beginning_of_month_str() throws Exception {
        System.out.println("Вывод массива со строками ");
        Date input = new Date();
        System.out.println("Дата сегодня "+input);
        List<String> dateListStr=auxiliaryService.from_the_beginning_of_month_str(input);

        for(String data:dateListStr){
            System.out.println(data);
        }
    }

    @Test
    public void date_to_localDate() throws Exception {

    }

    @Test
    public void date_to_localDateTime1() throws Exception {

    }

    @Test
    public void localDateTime_to_date() throws Exception {

    }

    @Test
    public void addTime() throws Exception {

        Date d=auxiliaryService.addTime(new Date(),"23");
        System.out.println("дата со временем = "+d);


    }

    @Test
    public void addTime1() throws Exception {

    }

    @Test
    public void date_TimeStamp() throws Exception {
        Date input = new Date();
        input=auxiliaryService.addTime(input,"23");
        java.sql.Timestamp ts=auxiliaryService.date_TimeStamp(input);
        System.out.println("data= "+input );
        System.out.println("datatoTS= "+ts );
    }

    @Test
    public void localDateTime_TimeStamp() throws Exception {

    }

    @Test
    public void timestamp_to_localDateTime() throws Exception {

    }

    @Test
    public void date_to_vktString() throws Exception {

    }

    @Test
    public void date_to_vktString1() throws Exception {

    }


    private  AuxiliaryServiceImpl auxiliaryService;


    @Before
    public void setUp() throws Exception {
        auxiliaryService=new AuxiliaryServiceImpl();
    }

    @After
    public void tearDown() throws Exception {
        auxiliaryService=null;
    }

    @org.junit.Test
    public void rrr() throws Exception {

assertTrue(auxiliaryService.rrr(2,3)==5);
    }



    @Test
    public void date_to_localDateTime() throws Exception {
        Date data=new Date();


        LocalDateTime l1=auxiliaryService.date_to_localDateTime(data);
        String d1=auxiliaryService.date_to_vktString(l1);
        String d2=auxiliaryService.date_to_vktString(LocalDateTime.now());
        System.out.println(" LocalDateTime =" + d1);
        System.out.println(" LocalDateTime =" + d2);
       // assertTrue(d1.equals(d2));
    }
}