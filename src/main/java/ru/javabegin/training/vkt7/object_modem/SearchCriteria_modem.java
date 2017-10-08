package ru.javabegin.training.vkt7.object_modem;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.enums.SearchType;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope("singleton")

public class SearchCriteria_modem implements Serializable{

    private Long id;
    private String text;

    private SearchType searchType= SearchType.TITLE;

   // private Character letter;
   private String letter;

    private Hobby hobby;
    private Customer customer;
    private Operation operation;

    public Date getDay_of() {
        return day_of;
    }

    public void setDay_of(Date day_of) {
        this.day_of = day_of;
    }

    private Date day_of;

    public String getModem_operation() {
        return modem_operation;
    }



    public void setModem_operation(String modem_operation) {
        this.modem_operation = modem_operation;
    }

    private String modem_operation;






    public String getText() { return text; }
    public void setText(String text) {
        this.text = text; }

    public SearchType getSearchType() {
        return searchType; }
    public void setSearchType(SearchType searchType) {
        this.searchType = searchType; }

    /*public Character getLetter() { return letter;}
    public void setLetter(Character letter) { this.letter = letter; }*/

    public String getLetter() { return letter;}
    public void setLetter(String letter) {
        this.letter = letter; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }


    public void set_all_modem(String modem_operation, Date day_of) {

        this.modem_operation = modem_operation;
        this.day_of = day_of;
    }


    //////////
    public Hobby getHobby() { return hobby; }
    public void setHobby(Hobby hobby) { this.hobby = hobby; }



}
