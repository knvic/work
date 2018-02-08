package ru.javabegin.training.vkt7.propert.entities;



import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;

@XmlRootElement(name = "properties")
// определяем последовательность тегов в XML

public class Entries {

    private LinkedList<Properts> entry;

   // @XmlElement(name = "entry")
    //@XmlElementWrapper
    public void setEntry(LinkedList<Properts> entry) {
        this.entry = entry;
    }

    public LinkedList<Properts> getEntry() {
        return entry;
    }

    @Override
    public String toString() {
        return "Entries{" +
                "entry=" + entry +
                '}';
    }
    /* @Override
    public String toString() {
        String res = "entries{" + ", entry{";
        if(entry != null){
            for(Test p : entry){
                res += p.toString();
            }
        }
        res += "}}";
        return res;
    }*/
}
