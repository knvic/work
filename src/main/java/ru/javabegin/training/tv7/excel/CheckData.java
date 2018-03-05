package ru.javabegin.training.tv7.excel;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;

public class CheckData {
    @Autowired
    CustomerService customerService;

    public void checkTotal(Customer customer, LocalDateTime date){

        List<Operationtv7> listtv7 = customerService.findOperationtv7ByDate("day",customer.getId(),date);
        List<Operationtv7T> listtv7t = customerService.findOperationtv7TByDate(customer.getId(),date);
    }
}
