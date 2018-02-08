package ru.javabegin.training.objects;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.enums.SearchType;

import java.io.Serializable;

@Component
@Scope("singleton")
public class SearchCriteria implements Serializable{

    private String text;

    private SearchType searchType= SearchType.TITLE;

   // private Character letter;
   private String letter;

    private Hobby hobby;

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

    public Hobby getHobby() { return hobby; }
    public void setHobby(Hobby hobby) { this.hobby = hobby; }



}
