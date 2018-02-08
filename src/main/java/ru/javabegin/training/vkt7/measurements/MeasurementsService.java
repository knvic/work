package ru.javabegin.training.vkt7.measurements;

import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.util.List;

/**
 * Created by Николай on 06.09.2017.
 */
public interface MeasurementsService {
    List<Object> current_command(List<Properts> active_items);
    List<Object> archive_command (List<Properts> active_items);
    List<Object> total_current_command (List<Properts> active_items);


    List<Measurements> current(List<Properts> active_items);


    List<Measurements> current_total();
    List<Measurements> archive();
    List<Measurements> archive_total();
}
