package ru.javabegin.training.vkt7.object;

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


/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class Selection implements Serializable {
    private Contact ud;
    private Customer cust;
    private Operation oper;
    private Measurements meas;

    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    private String data_Q;

    public Contact getUd() {
        return ud;
    }

    public void setUd(Contact ud) {
        this.ud = ud;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public Operation getOper() {
        return oper;
    }

    public void setOper(Operation oper) {
        this.oper = oper;
    }

    public Measurements getMeas() {
        return meas;
    }

    public void setMeas(Measurements meas) {
        this.meas = meas;
    }



    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Потребитель выбран", ((Customer) event.getObject()).getFirstName());
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
