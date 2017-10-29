package ru.javabegin.training.vkt7.reports;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Николай on 27.10.2017.
 */
@Component
public class Archive implements Serializable {

    private BigDecimal t1;
    private BigDecimal t2;
    private BigDecimal t3;
    private BigDecimal v1;
    private BigDecimal v2;
    private BigDecimal v3;
    private BigDecimal m1;
    private BigDecimal m2;
    private BigDecimal m3;
    private BigDecimal p1;
    private BigDecimal p2;
    private BigDecimal mg;
    private BigDecimal Qo;
    private BigDecimal Qg;
    private BigDecimal dt;
    private BigDecimal tx;
    private BigDecimal vnr;
    private BigDecimal vos;
    private Integer ns;

    public Archive() {
    }

    public BigDecimal getT1() {
        return t1;
    }

    public void setT1(BigDecimal t1) {
        this.t1 = t1;
    }

    public BigDecimal getT2() {
        return t2;
    }

    public void setT2(BigDecimal t2) {
        this.t2 = t2;
    }

    public BigDecimal getT3() {
        return t3;
    }

    public void setT3(BigDecimal t3) {
        this.t3 = t3;
    }

    public BigDecimal getV1() {
        return v1;
    }

    public void setV1(BigDecimal v1) {
        this.v1 = v1;
    }

    public BigDecimal getV2() {
        return v2;
    }

    public void setV2(BigDecimal v2) {
        this.v2 = v2;
    }

    public BigDecimal getV3() {
        return v3;
    }

    public void setV3(BigDecimal v3) {
        this.v3 = v3;
    }

    public BigDecimal getM1() {
        return m1;
    }

    public void setM1(BigDecimal m1) {
        this.m1 = m1;
    }

    public BigDecimal getM2() {
        return m2;
    }

    public void setM2(BigDecimal m2) {
        this.m2 = m2;
    }

    public BigDecimal getM3() {
        return m3;
    }

    public void setM3(BigDecimal m3) {
        this.m3 = m3;
    }

    public BigDecimal getP1() {
        return p1;
    }

    public void setP1(BigDecimal p1) {
        this.p1 = p1;
    }

    public BigDecimal getP2() {
        return p2;
    }

    public void setP2(BigDecimal p2) {
        this.p2 = p2;
    }

    public BigDecimal getMg() {
        return mg;
    }

    public void setMg(BigDecimal mg) {
        this.mg = mg;
    }

    public BigDecimal getQo() {
        return Qo;
    }

    public void setQo(BigDecimal qo) {
        Qo = qo;
    }

    public BigDecimal getQg() {
        return Qg;
    }

    public void setQg(BigDecimal qg) {
        Qg = qg;
    }

    public BigDecimal getDt() {
        return dt;
    }

    public void setDt(BigDecimal dt) {
        this.dt = dt;
    }

    public BigDecimal getTx() {
        return tx;
    }

    public void setTx(BigDecimal tx) {
        this.tx = tx;
    }

    public BigDecimal getVnr() {
        return vnr;
    }

    public void setVnr(BigDecimal vnr) {
        this.vnr = vnr;
    }

    public BigDecimal getVos() {
        return vos;
    }

    public void setVos(BigDecimal vos) {
        this.vos = vos;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }




}
