package ru.javabegin.training.tv7.excel;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeService;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CheckData {
    @Autowired
    CustomerService customerService;

    @Autowired
    AuxiliaryService auxiliaryService;


    public void checkTotal(Customer customer, LocalDateTime ldt,LocalDateTime day_of,LocalDateTime day_to ){


        AuxDateTimeServiceImpl auxDateTimeService= new AuxDateTimeServiceImpl();
        List<LocalDateTime> dateTimes=auxDateTimeService.from_the_beginning_of_month(day_to);
        boolean athive= true;
        for (LocalDateTime localDateTime:dateTimes) {


            List<Operationtv7> listtv7 = customerService.findOperationtv7ByDate("day",customer.getId(),localDateTime);
            try{
                System.out.println(customer.getFirstName()+" Архивные за "+ listtv7.get(0).getChronoligical()+" присутствуют");
            }catch (Exception e){

            }


        }





        //List<Operationtv7T> listtv7t = customerService.findOperationtv7TByDate(customer.getId(),date);

    }
}
