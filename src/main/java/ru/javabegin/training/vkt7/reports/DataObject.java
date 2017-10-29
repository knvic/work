package ru.javabegin.training.vkt7.reports;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Николай on 29.10.2017.
 */
@Component
public class DataObject implements Serializable{

    private String staticval1;
    private String staticval2;

    private Map<String, Tupel> optionalValues;



    public String getStaticval1() {
        return staticval1;
    }

    public void setStaticval1(String staticval1) {
        this.staticval1 = staticval1;
    }

    public String getStaticval2() {
        return staticval2;
    }

    public void setStaticval2(String staticval2) {
        this.staticval2 = staticval2;
    }

    public Map<String, Tupel> getOptionalValues() {
        return optionalValues;
    }

    public void setOptionalValues(Map<String, Tupel> optionalValues) {
        this.optionalValues = optionalValues;
    }




}
