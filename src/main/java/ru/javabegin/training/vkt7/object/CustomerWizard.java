package ru.javabegin.training.vkt7.object;

import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.CustomerServiceImpl;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CustomerWizard implements Serializable {








    private Customer customer = new Customer();

    private boolean skip;

    public Map<String, String> getBranches() {
        return branches;
    }

    public void setBranches(Map<String, String> branches) {
        this.branches = branches;
    }

    private Map<String,String> branches = new HashMap<String, String>();


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void save() {

        FacesMessage msg = new FacesMessage("Выполнено ", "Добален :" + customer.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);


    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }



    @PostConstruct
    public void init() {

        branches = new HashMap<String, String>();
        branches.put("ЗГПА","ЗГПА");
        branches.put("Восточный луч", "Восточный луч");
        branches.put("Южный луч","Южный луч");
        branches.put("Западный луч","Западный луч");


    }
}
