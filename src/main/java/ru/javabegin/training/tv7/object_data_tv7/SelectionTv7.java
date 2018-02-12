package ru.javabegin.training.tv7.object_data_tv7;


import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@ViewScoped
public class SelectionTv7 implements Serializable {

    private Customer cust;

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Потребитель выбран", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }
}
