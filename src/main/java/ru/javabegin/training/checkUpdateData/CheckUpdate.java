package ru.javabegin.training.checkUpdateData;

import ru.javabegin.training.tv7.reports.DataCustomerListTV7;
import ru.javabegin.training.tv7.reports.DataCustomerTV7;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.reports.DataCustomer;

import java.time.LocalDateTime;
import java.util.List;

public interface CheckUpdate {

    List<DataCustomerTV7> update(CustomerService customerService, LocalDateTime date, DataCustomerListTV7 dcsTV7) throws InterruptedException;

}
