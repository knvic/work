package ru.javabegin.training.vkt7.auxiliary_programs;

import org.junit.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by user on 18.11.2017.
 */
public class AuxiliaryServiceImplTest {
    @Test
    public void from_the_beginning_of_month() throws Exception {
        Date input = new Date();
        System.out.println("Вывод массива с датами");
        List<Date> dateList=auxiliaryService.from_the_beginning_of_month(input);
        for(Date data:dateList){
            System.out.println(data);
        }
    }

    @Test
    public void from_the_beginning_of_month_str() throws Exception {
        System.out.println("Вывод массива со строками ");
        Date input = new Date();
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

    }

    @Test
    public void addTime1() throws Exception {

    }

    @Test
    public void date_TimeStamp() throws Exception {

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