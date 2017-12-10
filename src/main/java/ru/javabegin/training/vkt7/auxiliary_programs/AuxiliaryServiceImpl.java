package ru.javabegin.training.vkt7.auxiliary_programs;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 14.11.2017.
 */

@Service("jpaAuxiliaryService")
public class AuxiliaryServiceImpl implements AuxiliaryService {



    @Override
    public List<Date> from_the_beginning_of_month(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
        //System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<Date>  dateList=new ArrayList<>();
        while(day.isBefore(ldt)){
            dateList.add(localDateTime_to_date(day));
            day=day.plusDays(1);
        }
        return dateList;
    }


    @Override
    public List<String> from_the_beginning_of_month_str(Date date){
        LocalDateTime dateLocalDateTime = date_to_localDateTime(date);
        LocalDateTime ldt=addTime(dateLocalDateTime,"22");
       // System.out.println("Первый день этого месяца : " + ldt.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDateTime day=ldt.with(TemporalAdjusters.firstDayOfMonth());
        List<String>  dateListStr=new ArrayList<>();
        while(day.isBefore(ldt)){
          //  System.out.println("Рассматриваемая дата "+ day+ " находится перед "+ ldt );
            dateListStr.add(date_to_vktString(day));
            day=day.plusDays(1);
        }
        return dateListStr;
    }



    @Override
    public Date stringDate_to_Date(String stringDate){
        List<String> d = new ArrayList<String>(Arrays.asList(stringDate.split(":")));
        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), Integer.parseInt(d.get(3)), 0, 0);
        Date date=localDateTime_to_date(ldt2);
        System.out.println("Date = " + date);

        System.out.println("LocalDateTime ldt2 = " + ldt2);
        Timestamp timestamp_daily = Timestamp.valueOf(ldt2);
        System.out.println(" timestamp для суточного измерения = " + timestamp_daily);

        return date;

    }

    @Override
    public LocalDateTime stringDate_to_LocalDateTime(String stringDate){
        List<String> d = new ArrayList<String>(Arrays.asList(stringDate.split(":")));
        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), Integer.parseInt(d.get(3)), 0, 0);


        return ldt2;

    }


    @Override
    public Timestamp stringDate_to_TimeStamp(String stringDate){

        List<String> d = new ArrayList<String>(Arrays.asList(stringDate.split(":")));
        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), Integer.parseInt(d.get(3)), 0, 0);
        Timestamp timestamp_daily = Timestamp.valueOf(ldt2);
        System.out.println(" timestamp для суточного измерения = " + timestamp_daily);

        return timestamp_daily;
    }

    @Override
    public String timeStamp_to_string(Timestamp date){
        LocalDateTime localDateTime=timestamp_to_localDateTime(date);

        String date_str = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/uu"));
        return date_str;

    }


    @Override
    public Timestamp getLastDayPrevisionMoth(Timestamp timestamp){
        LocalDateTime ldt =timestamp_to_localDateTime(timestamp);
       LocalDate ld=ldt.toLocalDate();
        Timestamp tstamp = Timestamp.valueOf(LocalDateTime.of(ld.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00)));


        return tstamp;
    }

    @Override
    public Timestamp minusDay(Timestamp timestamp,int day){
        LocalDateTime ldt =timestamp_to_localDateTime(timestamp);
        LocalDate ld=ldt.toLocalDate();

        Timestamp tstamp = Timestamp.valueOf(LocalDateTime.of(ld.minusDays(day),LocalTime.of(23,00)));

        return tstamp;
    }




    @Override
    public LocalDate date_to_localDate(Date data){
        LocalDate dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       // System.out.println("Перевели в  LocalDate " + dateLocalDate);

        return dateLocalDate;
    }

    @Override
    public LocalDateTime date_to_localDateTime(Date data){
        LocalDateTime dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    //    System.out.println("Перевели в  LocalDate " + dateLocalDate);

        return dateLocalDate;
    }



    @Override
    public Date localDateTime_to_date(LocalDateTime ldt){
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
     //   System.out.println("value of LocalDateTime: " + ldt);
      //  System.out.println("value of Date: " + output);

        return output;
    }


    @Override
    public LocalDateTime addTime (LocalDateTime data, String hour){
    LocalDate  dateLocalDate =data.toLocalDate();
    LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(23, 0, 0));
    return ldt;

    }

    @Override
    public Date addTime(Date data, String hour){
      LocalDateTime dataldt=date_to_localDateTime(data);
        LocalDate  dateLocalDate =dataldt.toLocalDate();
        int h=Integer.parseInt(hour);
        LocalDateTime ldt =LocalDateTime.of(dateLocalDate, LocalTime.of(h, 0, 0));
        Date dateDate=localDateTime_to_date(ldt);
        return dateDate;

    }


    @Override
    public Timestamp date_TimeStamp (Date data){
        LocalDateTime dateLocalDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Timestamp timestamp = valueOf(dateLocalDate);

        return timestamp;
    }

    @Override
    public Timestamp localDateTime_TimeStamp (LocalDateTime data){
        Timestamp timestamp = valueOf(data);
        return timestamp;

    }

    @Override
    public LocalDateTime timestamp_to_localDateTime(Timestamp ts){
        LocalDateTime ldt = ts.toLocalDateTime();
        return ldt;
    }

    @Override
    public String date_to_vktString(Date date){
        LocalDateTime dateLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String dataStr =dateLocalDate.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));

        return dataStr;

    }

    @Override
    public void saveMessage(File file, String message) throws IOException {

     FileWriter writer = new FileWriter(file, true);


        //message= LocalDateTime.now() +" :: "+ message;
        message= new Date() +" :: "+ message+ "\n";
        writer.write(message);
        writer.flush();
        writer.close();

    }


    @Override
    public String date_to_vktString(LocalDateTime date){

        String dataStr =date.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));

        return dataStr;

    }


    @Override
    public  String forUpdateMoth(){
        String str="";
        LocalDateTime ldt=LocalDateTime.now();

        ldt=ldt.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        //str= ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        //ldt=addTime(ldt,"24");
        str= ldt.format(DateTimeFormatter.ofPattern("dd/MM/uu 24:00"));


    return str;
    }


    @Override
    public Timestamp stringDate_to_TimeStamp_forUpdateMoth(String stringDate){
/**
 * Дата Дата приходит в формате 30/11/1724:00
 * Поэтому используем substring(0, 2) при получении года
 */
        List<String>d=new ArrayList<String>(Arrays.asList(stringDate.split("/")));

        LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2).substring(0, 2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), 23, 0, 0);
        Timestamp timestamp_moth = Timestamp.valueOf(ldt2);
        //System.out.println(" timestamp для суточного измерения = " + timestamp_moth);




        return timestamp_moth;
    }



    public int rrr(int a,int b){
        return a+b;
    }




}
