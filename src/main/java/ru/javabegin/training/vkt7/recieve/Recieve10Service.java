package ru.javabegin.training.vkt7.recieve;

/**
 * Created by Николай on 20.08.2017.
 */
public interface Recieve10Service {

    /**
     * 4.7 Ответ  «Начало сеанса связи»
     * @param recieve строка ответа
     */
    boolean r_3FFF_n(String recieve);

    boolean r_3FFF(String recieve);
    boolean r_3FFD(String recieve);

}
