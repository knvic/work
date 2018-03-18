package ru.javabegin.training.recoveryData;

import ru.javabegin.training.vkt7.entities.Customer;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface RecoveryService {

    void recoveryDay(String name) throws IOException;
    List<String> info(String line);
    List<String> naimenovaniya(String line);
    List<String> edIzmer(String line);
    void processDay(Customer customer, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer,List<String> list);

    void Recovery_month(String name);
}
