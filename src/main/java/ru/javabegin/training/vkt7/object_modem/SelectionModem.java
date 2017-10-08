package ru.javabegin.training.vkt7.object_modem;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class SelectionModem implements Serializable {

    private Customer cust;
    private String modem_operation;
    private Date day_of;
    private Date day_to;
    private Date hour;

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public String getModem_operation() {
        return modem_operation;
    }

    public void setModem_operation(String modem_operation) {
        this.modem_operation = modem_operation;
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

    public Date getHour() {
        return hour;
    }

    public void setHour(Date hour) {
        this.hour = hour;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Customer) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public Customer onRowSelect_model(SelectEvent event) {
         Customer select=((Customer) event.getObject());
        return select;
    }
}
