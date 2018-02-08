package ru.javabegin.training.vkt7.reports;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Николай on 29.10.2017.
 */

public class Tupel_str implements Serializable {

    private String id;
    private String value;

    public Tupel_str() {
    }

    public Tupel_str(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
