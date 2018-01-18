package ru.javabegin.training.tv7.recieve;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Николай on 29.10.2017.
 */

public class Tupel_tv7 implements Serializable {

    private String id;
    private BigDecimal value;

    public Tupel_tv7() {
    }

    public Tupel_tv7(String id, BigDecimal value) {
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
