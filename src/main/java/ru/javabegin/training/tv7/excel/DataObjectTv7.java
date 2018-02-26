package ru.javabegin.training.tv7.excel;

import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.reports.Tupel;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by Николай on 29.10.2017.
 */
@Component
public class DataObjectTv7 implements Serializable{

    private Timestamp data;
    private String staticval2;

    private Map<String, TupelExcelTv7> optionalValues;
    private List<String> id_coils;

    public List<String> getId_coils() {
        return id_coils;
    }

    public void setId_coils(List<String> id_coils) {
        this.id_coils = id_coils;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getStaticval2() {
        return staticval2;
    }

    public void setStaticval2(String staticval2) {
        this.staticval2 = staticval2;
    }

    public Map<String, TupelExcelTv7> getOptionalValues() {
        return optionalValues;
    }

    public void setOptionalValues(Map<String, TupelExcelTv7> optionalValues) {
        this.optionalValues = optionalValues;
    }




}
