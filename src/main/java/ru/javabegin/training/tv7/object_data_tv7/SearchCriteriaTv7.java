package ru.javabegin.training.tv7.object_data_tv7;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.Serializable;

@Component
@Scope("singleton")
public class SearchCriteriaTv7 implements Serializable {

    private Customer customer;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
