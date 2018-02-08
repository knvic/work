package ru.javabegin.training.tv7.send;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ModBusService {
    List<String> typeUnit(int number);
    List<String> day(int number, int addrR,int countR,int addrW,int countW, int countByte, int requestCount, List<String> data);

    /**
     *
     * @param number
     * @param data
     * @param type Тип архива. Принимает значения: 0–часовой архив, 1–суточный архив, 2–месячный архив, 3–итоговый архив;
     * @param commandCount
     * @return
     */
    List<String> archive(int number,Date data, int type, int commandCount);
    List<String> archive(int number,LocalDateTime data,int type, int commandCount);
    List<String> total(int number,Date data, int commandCount);
    List<String> total(int number,LocalDateTime data, int commandCount);
    List<String> infOfDate(int number, int commandCount);
}
