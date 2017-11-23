package ru.javabegin.training.vkt7.reports;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Николай on 29.10.2017.
 */
@Component
public class DataObject_str implements Serializable{

    private String data;
    private String staticval2;

    private Map<String, Tupel_str> optionalValues;


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStaticval2() {
        return staticval2;
    }

    public void setStaticval2(String staticval2) {
        this.staticval2 = staticval2;
    }

    public Map<String, Tupel_str> getOptionalValues() {
        return optionalValues;
    }

    public void setOptionalValues(Map<String, Tupel_str> optionalValues) {
        this.optionalValues = optionalValues;
    }
}
