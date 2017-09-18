package ru.javabegin.training.vkt7.send;

import java.util.List;

/**
 * Created by Николай on 20.08.2017.
 */
public interface Send03Service {
    /**
     * 4.5 Запрос на чтение данных
     * @param number номер узла
     */
    List<String> s_3FFE(String number);
    List<String> s_3FF9(String number);
    List<String> s_3FF6(String number);
    List<String> s_3FFC(String number);
    List<String> s_3ECD(String number);
    List<String> s_3F5B(String number);
    List<String> s_3FE9(String number);
}
