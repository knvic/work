package ru.javabegin.training.vkt7.reports;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Николай on 29.10.2017.
 */

public class Tupel implements Serializable {

    private String id;
    private BigDecimal value;

    public Tupel() {
    }

    public Tupel(String id, BigDecimal value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
