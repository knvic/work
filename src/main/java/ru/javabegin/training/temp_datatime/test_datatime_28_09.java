package ru.javabegin.training.temp_datatime;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Николай on 28.09.2017.
 */
public class test_datatime_28_09 {



    public static void main(String[] args){

        LocalDate today2 = LocalDate.now();
        System.out.println("Текущая дата : " + today2);

        //Создадим LocalDate и в качестве аргументов
        //укажем год месяц и день
        LocalDate specificDate = LocalDate.of(2017, Month.NOVEMBER, 30);
        System.out.println("Дата с указанием года, месяца и дня : " + specificDate);


        //А теперь попробуем хитрость. Укажем дату с ошибкой
        //LocalDate invDate = LocalDate.of(2014, Month.JULY, 33);
        //Но получим исключение java.time.DateTimeException:
        //Invalid value for DayOfMonth (valid values 1 - 28/31): 33

        //Получаем дату, например с 01.01.1970
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("Дата с начала отсчета времени : " + dateFromBase);

        LocalDate day256_2017 = LocalDate.ofYearDay(2014, 256);
        System.out.println("256 день 2017 : " + day256_2017);

////////////////////
        LocalDateTime today1 = LocalDateTime.now();
        System.out.println("Получаем текущее время : " + today1);

        //Создаем новую дату с помощью LocalDate и LocalTime
        today1 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("DateTime : " + today1);

        //Создаем LocalDateTime передавая в качестве аргументов
       //год, месяц, день, час, минуту, сукенду
        LocalDateTime randDate = LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22);
        System.out.println("LocalDateTime с указанной датой : " + randDate);

        //Получаем дату через 2000 секунд после 01.01.1970
        LocalDateTime dateFromBase1 = LocalDateTime.ofEpochSecond(2000, 0, ZoneOffset.UTC);
        System.out.println("Через 2000 секунд после 01.01.1970 : " + dateFromBase1);
///////////////////////////////////////////////
        /////////////////////////////////////


        LocalDate today = LocalDate.now();

        //Получаем год, проверям его на высокосность
        System.out.println("Год " + today.getYear() + " - высокосный? : " + today.isLeapYear());

        //Сравниваем два LocalDate: до и после
        System.out.println("Сегодня — это до 02.03.2017? : " + today.isBefore(LocalDate.of(2017,3,2)));

        //Создаем LocalDateTime с LocalDate
        System.out.println("Текущее время : " + today.atTime(LocalTime.now()));

        //Операции + и - с датами
        System.out.println("9 дней после сегодняшнего дня будет: " + today.plusDays(9));
        System.out.println("3 недели после сегодняшнего дня будет: " + today.plusWeeks(3));
        System.out.println("20 месяцев после сегодняшнего дня будет: " + today.plusMonths(20));

        System.out.println("9 дней до сегодняшнего дня будет: " + today.minusDays(9));
        System.out.println("3 недели до сегодняшнего дня будет: " + today.minusWeeks(3));
        System.out.println("20 месяцев до сегодняшнего дня будет: " + today.minusMonths(20));

        // А теперь поиграемся с датой
        System.out.println("Первый день этого месяца : " + today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Последний день этой года : " + lastDayOfYear);

        Period period = today.until(lastDayOfYear);
        System.out.println("Находим время между жвумя датами : "+period);
        System.out.println("В этом году осталось " + period.getMonths() + " месяц(ев)");


    }
}
