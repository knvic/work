package ru.javabegin.training.tv7.auxillary;

import ru.javabegin.training.tv7.recieve.Tupel_date;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public interface AuxService {
    StringBuilder localDate_to_tv7(LocalDateTime ldt);
    StringBuilder Date_to_tv7(Date ldt);
    String l2b (String str);
    String hexToBinary(String hex);



    /**
     *
     * @param hex
     * @param size размер в байтах
     * @return
     */
    String hexToBinary(String hex, int size);




    /**
     * Day
     * Проверяем запрашиваемую для архива дату. Смотрим из ответа информации о датах, дату начала архива и
     * проверяем запрашиваемая дата есть после начала архива или до (true/false)
     * @return
     */
    boolean checkOfDate(String typeOfDate, Map<String,Tupel_date> infOfDate, LocalDateTime ldt);



}
