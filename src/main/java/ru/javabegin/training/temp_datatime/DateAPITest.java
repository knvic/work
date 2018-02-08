package ru.javabegin.training.temp_datatime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by Николай on 28.09.2017.
 */
public class DateAPITest {
    public static void main(String[] args) {

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
