package ru.javabegin.training.tv7.object_data_tv7;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope("singleton")
public class SearchCriteriaTv7 implements Serializable {

    private Customer customer;

    private Date day_of;
    private Date day_to;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDay_of() {
        return day_of;
    }

    public void setDay_of(Date day_of) {
        this.day_of = day_of;
    }

    public Date getDay_to() {
        return day_to;
    }

    public void setDay_to(Date day_to) {
        this.day_to = day_to;
    }


    public void set_all_data(Date day_of, Date day_to) {


        this.day_of = day_of;
        this.day_to = day_to;


    }
}
