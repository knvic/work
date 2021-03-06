package ru.javabegin.training.vkt7.object;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.enums.SearchType;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.io.Serializable;

@Component
@Scope("singleton")
public class SearchCriteria_oper implements Serializable{

    private Long id;
    private String text;

    private SearchType searchType= SearchType.TITLE;

   // private Character letter;
   private String letter;

    private Operation operation;
    private Measurements measurements;



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



    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }
}
