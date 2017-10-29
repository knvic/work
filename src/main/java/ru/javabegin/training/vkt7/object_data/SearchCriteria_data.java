package ru.javabegin.training.vkt7.object_data;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.enums.SearchType;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.reports.Archive;
import ru.javabegin.training.vkt7.reports.DataObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@Scope("singleton")

public class SearchCriteria_data implements Serializable{

    private Long id;
    private String text;

    private SearchType searchType= SearchType.TITLE;

   // private Character letter;
   private String letter;

    private Hobby hobby;
    private Customer customer;
    private Operation operation;
    private Archive archive;

    private List<DataObject> data;
    private List<String> id_item;

    public List<String> getId_item() {
        return id_item;
    }

    public void setId_item(List<String> id_item) {
        this.id_item = id_item;
    }

    public List<DataObject> getData() {

        return data;
    }


    public void setData(List<DataObject> data) {
        this.data = data;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

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


    public void set_all_modem(String modem_operation, Date day_of, Customer customer) {

        this.modem_operation = modem_operation;
        this.day_of = day_of;
        this.customer = customer;

    }


    //////////
    public Hobby getHobby() { return hobby; }
    public void setHobby(Hobby hobby) { this.hobby = hobby; }



}
