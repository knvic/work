package ru.javabegin.training.vkt7.recieve;


import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Николай on 20.08.2017.
 */
public interface Recieve03Service {

    /**
     *
     */
    int r_3FFE_1(String str); // получаем 65 байт версию сервера
    List<String> r_3FF9(String str);
    List<Properts> r_3FFE(String str, List<Properts> prop_session);
    ArrayList<Timestamp> r_3FF6(String str) throws ParseException;
    List<Properts> r_3FFC(String str, List<Properts> prop_completed);
    List<Measurements> r_3FFE_Measurements(String str, List<Measurements> current_measur);
    int r_3ECD(String str);
    int r_3F5B(String str);
    int r_3FE9(String str);


    String hextostr(String hex);

}
