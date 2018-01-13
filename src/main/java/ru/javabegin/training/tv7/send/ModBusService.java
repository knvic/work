package ru.javabegin.training.tv7.send;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ModBusService {
    List<String> typeUnit(int number);
    List<String> day(int number, int addrR,int countR,int addrW,int countW, int countByte, int requestCount, List<String> data);
    List<String> day(int number,Date data, int commandCount);
    List<String> day(int number,LocalDateTime data, int commandCount);
}
