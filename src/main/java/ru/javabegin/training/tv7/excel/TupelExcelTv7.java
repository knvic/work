package ru.javabegin.training.tv7.excel;

import java.io.Serializable;

public class TupelExcelTv7 implements Serializable {

    private String id;
    private String value;


    public TupelExcelTv7() {
    }

    public TupelExcelTv7(String id, String value) {
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
