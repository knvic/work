package ru.javabegin.training.vkt7.object;

import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;


/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class SelectionCustomer implements Serializable {

private Customer cust;

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }
}



