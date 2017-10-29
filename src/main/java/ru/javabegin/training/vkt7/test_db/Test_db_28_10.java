package ru.javabegin.training.vkt7.test_db;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.Timestamp.valueOf;

/**
 * Created by Николай on 13.10.2017.
 */
@Component
public class Test_db_28_10 {

    @Qualifier("jpaOperationService")
    @Autowired
private OperationService operationService;


    @Qualifier("jpaCustomerService")
    @Autowired
    private CustomerService customerService;



    public void test_db() throws SchedulerException {

        LocalDate d=LocalDate.of(2017,10,26);
        LocalTime t=LocalTime.of(23,00);

        Timestamp tstamp1 = Timestamp.valueOf(LocalDateTime.of(d,t));

        Long id_Customer=38L;

        List<Operation> operationList= customerService.findOperation_total_moth(id_Customer, tstamp1, "daily", "OK");
        System.out.println(operationList.size());

        String tel ="+79064427319";
        List<Operation>  operationList1= customerService.findOperationByModemTimeCustomer(tel, tstamp1);
        System.out.println(operationList1.size());


    }
}
