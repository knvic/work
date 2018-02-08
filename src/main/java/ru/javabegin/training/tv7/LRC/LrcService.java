package ru.javabegin.training.tv7.LRC;

import java.util.List;

public interface LrcService {
    /**
     * Преобразует входеной массив HEX символов для Modbus ASC
     * вычисляется LRC
     * @param hexList
     * @return
     */
    List<String> lrcAdd(List<String> hexList);

    /**
     * Преобразуется входящий массив ModbusAsc в HEX
     * Проверяется LRC
     * на выходе массив Hex ответа от счетчика
     * @param asciiList
     * @return
     */
    boolean lrcCheck(List<String> asciiList);

}
