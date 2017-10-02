package ru.javabegin.training.vkt7.object;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by Николай on 01.10.2017.
 */

@Component
@ViewScoped
public class Set_cust implements Serializable{
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer cust() {
        customer=null;
        return customer;
    }
}
