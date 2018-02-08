package ru.javabegin.training.vkt7.reports;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Николай on 31.10.2017.
 */
public class ReportCustomers implements Serializable{

    private Long id;
    private String firstName;
    private String telModem;
    private BigDecimal q;
    private BigDecimal tarifRUR;
    private BigDecimal sumRUR;
    //private BigDecimal ndsRUR;
   // private BigDecimal totalRUR;

    public ReportCustomers() {
    }

    public ReportCustomers(Long id, String firstName, String telModem, BigDecimal q, BigDecimal tarifRUR, BigDecimal sumRUR) {
        this.id = id;
        this.firstName = firstName;
        this.telModem = telModem;
        this.q = q;
        this.tarifRUR = tarifRUR;
        this.sumRUR = sumRUR;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelModem() {
        return telModem;
    }

    public void setTelModem(String telModem) {
        this.telModem = telModem;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public BigDecimal getTarifRUR() {
        return tarifRUR;
    }

    public void setTarifRUR(BigDecimal tarifRUR) {
        this.tarifRUR = tarifRUR;
    }

    public BigDecimal getSumRUR() {
        return sumRUR;
    }

    public void setSumRUR(BigDecimal sumRUR) {
        this.sumRUR = sumRUR;
    }
}
