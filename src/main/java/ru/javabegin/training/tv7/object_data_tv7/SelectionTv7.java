package ru.javabegin.training.tv7.object_data_tv7;


import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;

@Component
@ViewScoped
public class SelectionTv7 implements Serializable {

    private Customer cust;

    private boolean check;

    private Date day_of;

    private Date day_to;


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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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
}
