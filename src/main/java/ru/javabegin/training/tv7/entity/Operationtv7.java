package ru.javabegin.training.tv7.entity;

import ru.javabegin.training.vkt7.entities.Customer;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "operationtv7")
@NamedQueries({
        @NamedQuery(name="Operationnv7.findAll", query="select c from Operationtv7 c")
        /*@NamedQuery(name="Contact.findById",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),*/

})
@SqlResultSetMapping(
        name="operationtv7Result",
        entities=@EntityResult(entityClass=Operationtv7.class)
)
public class Operationtv7 {
    private int id;
    private String customerName;
    private String typeOperation;
    private String error;
    private Timestamp chronoligical;
    private Long idCustomer;
    private String t1Tv1;
    private String p1Tv1;
    private String v1Tv1;
    private String m1Tv1;
    private String t2Tv1;
    private String p2Tv1;
    private String v2Tv1;
    private String m2Tv1;
    private String t3Tv1;
    private String p3Tv1;
    private String v3Tv1;
    private String m3Tv1;
    private String t1Tv2;
    private String p1Tv2;
    private String v1Tv2;
    private String m1Tv2;
    private String t2Tv2;
    private String p2Tv2;
    private String v2Tv2;
    private String m2Tv2;
    private String t3Tv2;
    private String p3Tv2;
    private String v3Tv2;
    private String m3Tv2;
    private String tnvTv1;
    private String txTv1;
    private String pxTv1;
    private String dtTv1;
    private String dMTv1;
    private String qtvTv1;
    private String q12Tv1;
    private String qgTv1;
    private String vnrTv1;
    private String vosTv1;
    private String tnvTv2;
    private String txTv2;
    private String pxTv2;
    private String dtTv2;
    private String dMTv2;
    private String qtvTv2;
    private String q12Tv2;
    private String qgTv2;
    private String vnrTv2;
    private String vosTv2;
    private String dp;
    private String ns1Tv1;
    private String ns2Tv1;
    private String ns3Tv1;
    private String ns1Tv2;
    private String ns2Tv2;
    private String ns3Tv2;
    private String nsTv1;
    private String nsTv2;
    private String nsDp;
    private String signsOfEvents;
    private String durationOf220;
    private String durationDisplay;
    private String durationOut;
    private String siTv1;
    private String activeBdTv1;
    private String frtTv1;
    private String kt3Tv1;
    private String siTv2;
    private String activeBdTv2;
    private String frtTv2;
    private String kt3Tv2;

    private Customer customer;

    public Operationtv7() {
    }

    public Operationtv7(int id, String v1Tv1, String m1Tv1,Timestamp chronoligical) {
        this.id = id;
        this.v1Tv1 = v1Tv1;
        this.m1Tv1 = m1Tv1;
        this.chronoligical =chronoligical;

    }


    public Operationtv7(Long id,String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1) {
        this.t1Tv1 = t1Tv1;
        this.p1Tv1 = p1Tv1;
        this.v1Tv1 = v1Tv1;
        this.m1Tv1 = m1Tv1;
        this.t2Tv1 = t2Tv1;
    }



    public Operationtv7(String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1) {
        this.t1Tv1 = t1Tv1;
        this.p1Tv1 = p1Tv1;
        this.v1Tv1 = v1Tv1;
        this.m1Tv1 = m1Tv1;
        this.t2Tv1 = t2Tv1;
    }

    public Operationtv7(String customerName, String typeOperation, String error, Timestamp chronoligical, Long idCustomer, String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1, String p2Tv1, String v2Tv1, String m2Tv1, String t3Tv1, String p3Tv1, String v3Tv1, String m3Tv1, String t1Tv2, String p1Tv2, String v1Tv2, String m1Tv2, String t2Tv2, String p2Tv2, String v2Tv2, String m2Tv2, String t3Tv2, String p3Tv2, String v3Tv2, String m3Tv2, String tnvTv1, String txTv1, String pxTv1, String dtTv1, String dMTv1, String qtvTv1, String q12Tv1, String qgTv1, String vnrTv1, String vosTv1, String tnvTv2, String txTv2, String pxTv2, String dtTv2, String dMTv2, String qtvTv2, String q12Tv2, String qgTv2, String vnrTv2, String vosTv2, String dp, String ns1Tv1, String ns2Tv1, String ns3Tv1, String ns1Tv2, String ns2Tv2, String ns3Tv2, String nsTv1, String nsTv2, String nsDp, String signsOfEvents, String durationOf220, String durationDisplay, String durationOut, String siTv1, String activeBdTv1, String frtTv1, String kt3Tv1, String siTv2, String activeBdTv2, String frtTv2, String kt3Tv2) {
        this.customerName = customerName;
        this.typeOperation = typeOperation;
        this.error = error;
        this.chronoligical = chronoligical;
        this.idCustomer = idCustomer;
        this.t1Tv1 = t1Tv1;
        this.p1Tv1 = p1Tv1;
        this.v1Tv1 = v1Tv1;
        this.m1Tv1 = m1Tv1;
        this.t2Tv1 = t2Tv1;
        this.p2Tv1 = p2Tv1;
        this.v2Tv1 = v2Tv1;
        this.m2Tv1 = m2Tv1;
        this.t3Tv1 = t3Tv1;
        this.p3Tv1 = p3Tv1;
        this.v3Tv1 = v3Tv1;
        this.m3Tv1 = m3Tv1;
        this.t1Tv2 = t1Tv2;
        this.p1Tv2 = p1Tv2;
        this.v1Tv2 = v1Tv2;
        this.m1Tv2 = m1Tv2;
        this.t2Tv2 = t2Tv2;
        this.p2Tv2 = p2Tv2;
        this.v2Tv2 = v2Tv2;
        this.m2Tv2 = m2Tv2;
        this.t3Tv2 = t3Tv2;
        this.p3Tv2 = p3Tv2;
        this.v3Tv2 = v3Tv2;
        this.m3Tv2 = m3Tv2;
        this.tnvTv1 = tnvTv1;
        this.txTv1 = txTv1;
        this.pxTv1 = pxTv1;
        this.dtTv1 = dtTv1;
        this.dMTv1 = dMTv1;
        this.qtvTv1 = qtvTv1;
        this.q12Tv1 = q12Tv1;
        this.qgTv1 = qgTv1;
        this.vnrTv1 = vnrTv1;
        this.vosTv1 = vosTv1;
        this.tnvTv2 = tnvTv2;
        this.txTv2 = txTv2;
        this.pxTv2 = pxTv2;
        this.dtTv2 = dtTv2;
        this.dMTv2 = dMTv2;
        this.qtvTv2 = qtvTv2;
        this.q12Tv2 = q12Tv2;
        this.qgTv2 = qgTv2;
        this.vnrTv2 = vnrTv2;
        this.vosTv2 = vosTv2;
        this.dp = dp;
        this.ns1Tv1 = ns1Tv1;
        this.ns2Tv1 = ns2Tv1;
        this.ns3Tv1 = ns3Tv1;
        this.ns1Tv2 = ns1Tv2;
        this.ns2Tv2 = ns2Tv2;
        this.ns3Tv2 = ns3Tv2;
        this.nsTv1 = nsTv1;
        this.nsTv2 = nsTv2;
        this.nsDp = nsDp;
        this.signsOfEvents = signsOfEvents;
        this.durationOf220 = durationOf220;
        this.durationDisplay = durationDisplay;
        this.durationOut = durationOut;
        this.siTv1 = siTv1;
        this.activeBdTv1 = activeBdTv1;
        this.frtTv1 = frtTv1;
        this.kt3Tv1 = kt3Tv1;
        this.siTv2 = siTv2;
        this.activeBdTv2 = activeBdTv2;
        this.frtTv2 = frtTv2;
        this.kt3Tv2 = kt3Tv2;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = true, length = 45)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "TYPE_OPERATION", nullable = true, length = 45)
    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Basic
    @Column(name = "ERROR", nullable = true, length = 45)
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Basic
    @Column(name = "CHRONOLIGICAL", nullable = true)
    public Timestamp getChronoligical() {
        return chronoligical;
    }

    public void setChronoligical(Timestamp chronoligical) {
        this.chronoligical = chronoligical;
    }

    @Basic
    @Column(name = "ID_CUSTOMER", nullable = true)
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Basic
    @Column(name = "t1_tv1", nullable = true, length = 45)
    public String getT1Tv1() {
        return t1Tv1;
    }

    public void setT1Tv1(String t1Tv1) {
        this.t1Tv1 = t1Tv1;
    }

    @Basic
    @Column(name = "P1_tv1", nullable = true, length = 45)
    public String getP1Tv1() {
        return p1Tv1;
    }

    public void setP1Tv1(String p1Tv1) {
        this.p1Tv1 = p1Tv1;
    }

    @Basic
    @Column(name = "V1_tv1", nullable = true, length = 45)
    public String getV1Tv1() {
        return v1Tv1;
    }

    public void setV1Tv1(String v1Tv1) {
        this.v1Tv1 = v1Tv1;
    }

    @Basic
    @Column(name = "M1_tv1", nullable = true, length = 45)
    public String getM1Tv1() {
        return m1Tv1;
    }

    public void setM1Tv1(String m1Tv1) {
        this.m1Tv1 = m1Tv1;
    }

    @Basic
    @Column(name = "t2_tv1", nullable = true, length = 45)
    public String getT2Tv1() {
        return t2Tv1;
    }

    public void setT2Tv1(String t2Tv1) {
        this.t2Tv1 = t2Tv1;
    }

    @Basic
    @Column(name = "P2_tv1", nullable = true, length = 45)
    public String getP2Tv1() {
        return p2Tv1;
    }

    public void setP2Tv1(String p2Tv1) {
        this.p2Tv1 = p2Tv1;
    }

    @Basic
    @Column(name = "V2_tv1", nullable = true, length = 45)
    public String getV2Tv1() {
        return v2Tv1;
    }

    public void setV2Tv1(String v2Tv1) {
        this.v2Tv1 = v2Tv1;
    }

    @Basic
    @Column(name = "M2_tv1", nullable = true, length = 45)
    public String getM2Tv1() {
        return m2Tv1;
    }

    public void setM2Tv1(String m2Tv1) {
        this.m2Tv1 = m2Tv1;
    }

    @Basic
    @Column(name = "t3_tv1", nullable = true, length = 45)
    public String getT3Tv1() {
        return t3Tv1;
    }

    public void setT3Tv1(String t3Tv1) {
        this.t3Tv1 = t3Tv1;
    }

    @Basic
    @Column(name = "P3_tv1", nullable = true, length = 45)
    public String getP3Tv1() {
        return p3Tv1;
    }

    public void setP3Tv1(String p3Tv1) {
        this.p3Tv1 = p3Tv1;
    }

    @Basic
    @Column(name = "V3_tv1", nullable = true, length = 45)
    public String getV3Tv1() {
        return v3Tv1;
    }

    public void setV3Tv1(String v3Tv1) {
        this.v3Tv1 = v3Tv1;
    }

    @Basic
    @Column(name = "M3_tv1", nullable = true, length = 45)
    public String getM3Tv1() {
        return m3Tv1;
    }

    public void setM3Tv1(String m3Tv1) {
        this.m3Tv1 = m3Tv1;
    }

    @Basic
    @Column(name = "t1_tv2", nullable = true, length = 45)
    public String getT1Tv2() {
        return t1Tv2;
    }

    public void setT1Tv2(String t1Tv2) {
        this.t1Tv2 = t1Tv2;
    }

    @Basic
    @Column(name = "P1_tv2", nullable = true, length = 45)
    public String getP1Tv2() {
        return p1Tv2;
    }

    public void setP1Tv2(String p1Tv2) {
        this.p1Tv2 = p1Tv2;
    }

    @Basic
    @Column(name = "V1_tv2", nullable = true, length = 45)
    public String getV1Tv2() {
        return v1Tv2;
    }

    public void setV1Tv2(String v1Tv2) {
        this.v1Tv2 = v1Tv2;
    }

    @Basic
    @Column(name = "M1_tv2", nullable = true, length = 45)
    public String getM1Tv2() {
        return m1Tv2;
    }

    public void setM1Tv2(String m1Tv2) {
        this.m1Tv2 = m1Tv2;
    }

    @Basic
    @Column(name = "t2_tv2", nullable = true, length = 45)
    public String getT2Tv2() {
        return t2Tv2;
    }

    public void setT2Tv2(String t2Tv2) {
        this.t2Tv2 = t2Tv2;
    }

    @Basic
    @Column(name = "P2_tv2", nullable = true, length = 45)
    public String getP2Tv2() {
        return p2Tv2;
    }

    public void setP2Tv2(String p2Tv2) {
        this.p2Tv2 = p2Tv2;
    }

    @Basic
    @Column(name = "V2_tv2", nullable = true, length = 45)
    public String getV2Tv2() {
        return v2Tv2;
    }

    public void setV2Tv2(String v2Tv2) {
        this.v2Tv2 = v2Tv2;
    }

    @Basic
    @Column(name = "M2_tv2", nullable = true, length = 45)
    public String getM2Tv2() {
        return m2Tv2;
    }

    public void setM2Tv2(String m2Tv2) {
        this.m2Tv2 = m2Tv2;
    }

    @Basic
    @Column(name = "t3_tv2", nullable = true, length = 45)
    public String getT3Tv2() {
        return t3Tv2;
    }

    public void setT3Tv2(String t3Tv2) {
        this.t3Tv2 = t3Tv2;
    }

    @Basic
    @Column(name = "P3_tv2", nullable = true, length = 45)
    public String getP3Tv2() {
        return p3Tv2;
    }

    public void setP3Tv2(String p3Tv2) {
        this.p3Tv2 = p3Tv2;
    }

    @Basic
    @Column(name = "V3_tv2", nullable = true, length = 45)
    public String getV3Tv2() {
        return v3Tv2;
    }

    public void setV3Tv2(String v3Tv2) {
        this.v3Tv2 = v3Tv2;
    }

    @Basic
    @Column(name = "M3_tv2", nullable = true, length = 45)
    public String getM3Tv2() {
        return m3Tv2;
    }

    public void setM3Tv2(String m3Tv2) {
        this.m3Tv2 = m3Tv2;
    }

    @Basic
    @Column(name = "tnv_tv1", nullable = true, length = 45)
    public String getTnvTv1() {
        return tnvTv1;
    }

    public void setTnvTv1(String tnvTv1) {
        this.tnvTv1 = tnvTv1;
    }

    @Basic
    @Column(name = "tx_tv1", nullable = true, length = 45)
    public String getTxTv1() {
        return txTv1;
    }

    public void setTxTv1(String txTv1) {
        this.txTv1 = txTv1;
    }

    @Basic
    @Column(name = "Px_tv1", nullable = true, length = 45)
    public String getPxTv1() {
        return pxTv1;
    }

    public void setPxTv1(String pxTv1) {
        this.pxTv1 = pxTv1;
    }

    @Basic
    @Column(name = "dt_tv1", nullable = true, length = 45)
    public String getDtTv1() {
        return dtTv1;
    }

    public void setDtTv1(String dtTv1) {
        this.dtTv1 = dtTv1;
    }

    @Basic
    @Column(name = "dM_tv1", nullable = true, length = 45)
    public String getdMTv1() {
        return dMTv1;
    }

    public void setdMTv1(String dMTv1) {
        this.dMTv1 = dMTv1;
    }

    @Basic
    @Column(name = "Qtv_tv1", nullable = true, length = 45)
    public String getQtvTv1() {
        return qtvTv1;
    }

    public void setQtvTv1(String qtvTv1) {
        this.qtvTv1 = qtvTv1;
    }

    @Basic
    @Column(name = "Q12_tv1", nullable = true, length = 45)
    public String getQ12Tv1() {
        return q12Tv1;
    }

    public void setQ12Tv1(String q12Tv1) {
        this.q12Tv1 = q12Tv1;
    }

    @Basic
    @Column(name = "Qg_tv1", nullable = true, length = 45)
    public String getQgTv1() {
        return qgTv1;
    }

    public void setQgTv1(String qgTv1) {
        this.qgTv1 = qgTv1;
    }

    @Basic
    @Column(name = "VNR_tv1", nullable = true, length = 45)
    public String getVnrTv1() {
        return vnrTv1;
    }

    public void setVnrTv1(String vnrTv1) {
        this.vnrTv1 = vnrTv1;
    }

    @Basic
    @Column(name = "VOS_tv1", nullable = true, length = 45)
    public String getVosTv1() {
        return vosTv1;
    }

    public void setVosTv1(String vosTv1) {
        this.vosTv1 = vosTv1;
    }

    @Basic
    @Column(name = "tnv_tv2", nullable = true, length = 45)
    public String getTnvTv2() {
        return tnvTv2;
    }

    public void setTnvTv2(String tnvTv2) {
        this.tnvTv2 = tnvTv2;
    }

    @Basic
    @Column(name = "tx_tv2", nullable = true, length = 45)
    public String getTxTv2() {
        return txTv2;
    }

    public void setTxTv2(String txTv2) {
        this.txTv2 = txTv2;
    }

    @Basic
    @Column(name = "Px_tv2", nullable = true, length = 45)
    public String getPxTv2() {
        return pxTv2;
    }

    public void setPxTv2(String pxTv2) {
        this.pxTv2 = pxTv2;
    }

    @Basic
    @Column(name = "dt_tv2", nullable = true, length = 45)
    public String getDtTv2() {
        return dtTv2;
    }

    public void setDtTv2(String dtTv2) {
        this.dtTv2 = dtTv2;
    }

    @Basic
    @Column(name = "dM_tv2", nullable = true, length = 45)
    public String getdMTv2() {
        return dMTv2;
    }

    public void setdMTv2(String dMTv2) {
        this.dMTv2 = dMTv2;
    }

    @Basic
    @Column(name = "Qtv_tv2", nullable = true, length = 45)
    public String getQtvTv2() {
        return qtvTv2;
    }

    public void setQtvTv2(String qtvTv2) {
        this.qtvTv2 = qtvTv2;
    }

    @Basic
    @Column(name = "Q12_tv2", nullable = true, length = 45)
    public String getQ12Tv2() {
        return q12Tv2;
    }

    public void setQ12Tv2(String q12Tv2) {
        this.q12Tv2 = q12Tv2;
    }

    @Basic
    @Column(name = "Qg_tv2", nullable = true, length = 45)
    public String getQgTv2() {
        return qgTv2;
    }

    public void setQgTv2(String qgTv2) {
        this.qgTv2 = qgTv2;
    }

    @Basic
    @Column(name = "VNR_tv2", nullable = true, length = 45)
    public String getVnrTv2() {
        return vnrTv2;
    }

    public void setVnrTv2(String vnrTv2) {
        this.vnrTv2 = vnrTv2;
    }

    @Basic
    @Column(name = "VOS_tv2", nullable = true, length = 45)
    public String getVosTv2() {
        return vosTv2;
    }

    public void setVosTv2(String vosTv2) {
        this.vosTv2 = vosTv2;
    }

    @Basic
    @Column(name = "dp", nullable = true, length = 45)
    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    @Basic
    @Column(name = "ns1_tv1", nullable = true, length = 45)
    public String getNs1Tv1() {
        return ns1Tv1;
    }

    public void setNs1Tv1(String ns1Tv1) {
        this.ns1Tv1 = ns1Tv1;
    }

    @Basic
    @Column(name = "ns2_tv1", nullable = true, length = 45)
    public String getNs2Tv1() {
        return ns2Tv1;
    }

    public void setNs2Tv1(String ns2Tv1) {
        this.ns2Tv1 = ns2Tv1;
    }

    @Basic
    @Column(name = "ns3_tv1", nullable = true, length = 45)
    public String getNs3Tv1() {
        return ns3Tv1;
    }

    public void setNs3Tv1(String ns3Tv1) {
        this.ns3Tv1 = ns3Tv1;
    }

    @Basic
    @Column(name = "ns1_tv2", nullable = true, length = 45)
    public String getNs1Tv2() {
        return ns1Tv2;
    }

    public void setNs1Tv2(String ns1Tv2) {
        this.ns1Tv2 = ns1Tv2;
    }

    @Basic
    @Column(name = "ns2_tv2", nullable = true, length = 45)
    public String getNs2Tv2() {
        return ns2Tv2;
    }

    public void setNs2Tv2(String ns2Tv2) {
        this.ns2Tv2 = ns2Tv2;
    }

    @Basic
    @Column(name = "ns3_tv2", nullable = true, length = 45)
    public String getNs3Tv2() {
        return ns3Tv2;
    }

    public void setNs3Tv2(String ns3Tv2) {
        this.ns3Tv2 = ns3Tv2;
    }

    @Basic
    @Column(name = "NS_tv1", nullable = true, length = 45)
    public String getNsTv1() {
        return nsTv1;
    }

    public void setNsTv1(String nsTv1) {
        this.nsTv1 = nsTv1;
    }

    @Basic
    @Column(name = "NS_tv2", nullable = true, length = 45)
    public String getNsTv2() {
        return nsTv2;
    }

    public void setNsTv2(String nsTv2) {
        this.nsTv2 = nsTv2;
    }

    @Basic
    @Column(name = "NS_DP", nullable = true, length = 45)
    public String getNsDp() {
        return nsDp;
    }

    public void setNsDp(String nsDp) {
        this.nsDp = nsDp;
    }

    @Basic
    @Column(name = "signs_of_events", nullable = true, length = 45)
    public String getSignsOfEvents() {
        return signsOfEvents;
    }

    public void setSignsOfEvents(String signsOfEvents) {
        this.signsOfEvents = signsOfEvents;
    }

    @Basic
    @Column(name = "duration_of_220", nullable = true, length = 45)
    public String getDurationOf220() {
        return durationOf220;
    }

    public void setDurationOf220(String durationOf220) {
        this.durationOf220 = durationOf220;
    }

    @Basic
    @Column(name = "duration_display", nullable = true, length = 45)
    public String getDurationDisplay() {
        return durationDisplay;
    }

    public void setDurationDisplay(String durationDisplay) {
        this.durationDisplay = durationDisplay;
    }

    @Basic
    @Column(name = "duration_out", nullable = true, length = 45)
    public String getDurationOut() {
        return durationOut;
    }

    public void setDurationOut(String durationOut) {
        this.durationOut = durationOut;
    }

    @Basic
    @Column(name = "si_tv1", nullable = true, length = 45)
    public String getSiTv1() {
        return siTv1;
    }

    public void setSiTv1(String siTv1) {
        this.siTv1 = siTv1;
    }

    @Basic
    @Column(name = "activeBD_tv1", nullable = true, length = 45)
    public String getActiveBdTv1() {
        return activeBdTv1;
    }

    public void setActiveBdTv1(String activeBdTv1) {
        this.activeBdTv1 = activeBdTv1;
    }

    @Basic
    @Column(name = "frt_tv1", nullable = true, length = 45)
    public String getFrtTv1() {
        return frtTv1;
    }

    public void setFrtTv1(String frtTv1) {
        this.frtTv1 = frtTv1;
    }

    @Basic
    @Column(name = "kt3_tv1", nullable = true, length = 45)
    public String getKt3Tv1() {
        return kt3Tv1;
    }

    public void setKt3Tv1(String kt3Tv1) {
        this.kt3Tv1 = kt3Tv1;
    }

    @Basic
    @Column(name = "si_tv2", nullable = true, length = 45)
    public String getSiTv2() {
        return siTv2;
    }

    public void setSiTv2(String siTv2) {
        this.siTv2 = siTv2;
    }

    @Basic
    @Column(name = "activeBD_tv2", nullable = true, length = 45)
    public String getActiveBdTv2() {
        return activeBdTv2;
    }

    public void setActiveBdTv2(String activeBdTv2) {
        this.activeBdTv2 = activeBdTv2;
    }

    @Basic
    @Column(name = "frt_tv2", nullable = true, length = 45)
    public String getFrtTv2() {
        return frtTv2;
    }

    public void setFrtTv2(String frtTv2) {
        this.frtTv2 = frtTv2;
    }

    @Basic
    @Column(name = "kt3_tv2", nullable = true, length = 45)
    public String getKt3Tv2() {
        return kt3Tv2;
    }

    public void setKt3Tv2(String kt3Tv2) {
        this.kt3Tv2 = kt3Tv2;
    }


    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID" )
    //  @JoinColumn(name = "CUSTOMER_ID",insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operationtv7 that = (Operationtv7) o;

        if (id != that.id) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (typeOperation != null ? !typeOperation.equals(that.typeOperation) : that.typeOperation != null)
            return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (chronoligical != null ? !chronoligical.equals(that.chronoligical) : that.chronoligical != null)
            return false;
        if (idCustomer != null ? !idCustomer.equals(that.idCustomer) : that.idCustomer != null) return false;
        if (t1Tv1 != null ? !t1Tv1.equals(that.t1Tv1) : that.t1Tv1 != null) return false;
        if (p1Tv1 != null ? !p1Tv1.equals(that.p1Tv1) : that.p1Tv1 != null) return false;
        if (v1Tv1 != null ? !v1Tv1.equals(that.v1Tv1) : that.v1Tv1 != null) return false;
        if (m1Tv1 != null ? !m1Tv1.equals(that.m1Tv1) : that.m1Tv1 != null) return false;
        if (t2Tv1 != null ? !t2Tv1.equals(that.t2Tv1) : that.t2Tv1 != null) return false;
        if (p2Tv1 != null ? !p2Tv1.equals(that.p2Tv1) : that.p2Tv1 != null) return false;
        if (v2Tv1 != null ? !v2Tv1.equals(that.v2Tv1) : that.v2Tv1 != null) return false;
        if (m2Tv1 != null ? !m2Tv1.equals(that.m2Tv1) : that.m2Tv1 != null) return false;
        if (t3Tv1 != null ? !t3Tv1.equals(that.t3Tv1) : that.t3Tv1 != null) return false;
        if (p3Tv1 != null ? !p3Tv1.equals(that.p3Tv1) : that.p3Tv1 != null) return false;
        if (v3Tv1 != null ? !v3Tv1.equals(that.v3Tv1) : that.v3Tv1 != null) return false;
        if (m3Tv1 != null ? !m3Tv1.equals(that.m3Tv1) : that.m3Tv1 != null) return false;
        if (t1Tv2 != null ? !t1Tv2.equals(that.t1Tv2) : that.t1Tv2 != null) return false;
        if (p1Tv2 != null ? !p1Tv2.equals(that.p1Tv2) : that.p1Tv2 != null) return false;
        if (v1Tv2 != null ? !v1Tv2.equals(that.v1Tv2) : that.v1Tv2 != null) return false;
        if (m1Tv2 != null ? !m1Tv2.equals(that.m1Tv2) : that.m1Tv2 != null) return false;
        if (t2Tv2 != null ? !t2Tv2.equals(that.t2Tv2) : that.t2Tv2 != null) return false;
        if (p2Tv2 != null ? !p2Tv2.equals(that.p2Tv2) : that.p2Tv2 != null) return false;
        if (v2Tv2 != null ? !v2Tv2.equals(that.v2Tv2) : that.v2Tv2 != null) return false;
        if (m2Tv2 != null ? !m2Tv2.equals(that.m2Tv2) : that.m2Tv2 != null) return false;
        if (t3Tv2 != null ? !t3Tv2.equals(that.t3Tv2) : that.t3Tv2 != null) return false;
        if (p3Tv2 != null ? !p3Tv2.equals(that.p3Tv2) : that.p3Tv2 != null) return false;
        if (v3Tv2 != null ? !v3Tv2.equals(that.v3Tv2) : that.v3Tv2 != null) return false;
        if (m3Tv2 != null ? !m3Tv2.equals(that.m3Tv2) : that.m3Tv2 != null) return false;
        if (tnvTv1 != null ? !tnvTv1.equals(that.tnvTv1) : that.tnvTv1 != null) return false;
        if (txTv1 != null ? !txTv1.equals(that.txTv1) : that.txTv1 != null) return false;
        if (pxTv1 != null ? !pxTv1.equals(that.pxTv1) : that.pxTv1 != null) return false;
        if (dtTv1 != null ? !dtTv1.equals(that.dtTv1) : that.dtTv1 != null) return false;
        if (dMTv1 != null ? !dMTv1.equals(that.dMTv1) : that.dMTv1 != null) return false;
        if (qtvTv1 != null ? !qtvTv1.equals(that.qtvTv1) : that.qtvTv1 != null) return false;
        if (q12Tv1 != null ? !q12Tv1.equals(that.q12Tv1) : that.q12Tv1 != null) return false;
        if (qgTv1 != null ? !qgTv1.equals(that.qgTv1) : that.qgTv1 != null) return false;
        if (vnrTv1 != null ? !vnrTv1.equals(that.vnrTv1) : that.vnrTv1 != null) return false;
        if (vosTv1 != null ? !vosTv1.equals(that.vosTv1) : that.vosTv1 != null) return false;
        if (tnvTv2 != null ? !tnvTv2.equals(that.tnvTv2) : that.tnvTv2 != null) return false;
        if (txTv2 != null ? !txTv2.equals(that.txTv2) : that.txTv2 != null) return false;
        if (pxTv2 != null ? !pxTv2.equals(that.pxTv2) : that.pxTv2 != null) return false;
        if (dtTv2 != null ? !dtTv2.equals(that.dtTv2) : that.dtTv2 != null) return false;
        if (dMTv2 != null ? !dMTv2.equals(that.dMTv2) : that.dMTv2 != null) return false;
        if (qtvTv2 != null ? !qtvTv2.equals(that.qtvTv2) : that.qtvTv2 != null) return false;
        if (q12Tv2 != null ? !q12Tv2.equals(that.q12Tv2) : that.q12Tv2 != null) return false;
        if (qgTv2 != null ? !qgTv2.equals(that.qgTv2) : that.qgTv2 != null) return false;
        if (vnrTv2 != null ? !vnrTv2.equals(that.vnrTv2) : that.vnrTv2 != null) return false;
        if (vosTv2 != null ? !vosTv2.equals(that.vosTv2) : that.vosTv2 != null) return false;
        if (dp != null ? !dp.equals(that.dp) : that.dp != null) return false;
        if (ns1Tv1 != null ? !ns1Tv1.equals(that.ns1Tv1) : that.ns1Tv1 != null) return false;
        if (ns2Tv1 != null ? !ns2Tv1.equals(that.ns2Tv1) : that.ns2Tv1 != null) return false;
        if (ns3Tv1 != null ? !ns3Tv1.equals(that.ns3Tv1) : that.ns3Tv1 != null) return false;
        if (ns1Tv2 != null ? !ns1Tv2.equals(that.ns1Tv2) : that.ns1Tv2 != null) return false;
        if (ns2Tv2 != null ? !ns2Tv2.equals(that.ns2Tv2) : that.ns2Tv2 != null) return false;
        if (ns3Tv2 != null ? !ns3Tv2.equals(that.ns3Tv2) : that.ns3Tv2 != null) return false;
        if (nsTv1 != null ? !nsTv1.equals(that.nsTv1) : that.nsTv1 != null) return false;
        if (nsTv2 != null ? !nsTv2.equals(that.nsTv2) : that.nsTv2 != null) return false;
        if (nsDp != null ? !nsDp.equals(that.nsDp) : that.nsDp != null) return false;
        if (signsOfEvents != null ? !signsOfEvents.equals(that.signsOfEvents) : that.signsOfEvents != null)
            return false;
        if (durationOf220 != null ? !durationOf220.equals(that.durationOf220) : that.durationOf220 != null)
            return false;
        if (durationDisplay != null ? !durationDisplay.equals(that.durationDisplay) : that.durationDisplay != null)
            return false;
        if (durationOut != null ? !durationOut.equals(that.durationOut) : that.durationOut != null) return false;
        if (siTv1 != null ? !siTv1.equals(that.siTv1) : that.siTv1 != null) return false;
        if (activeBdTv1 != null ? !activeBdTv1.equals(that.activeBdTv1) : that.activeBdTv1 != null) return false;
        if (frtTv1 != null ? !frtTv1.equals(that.frtTv1) : that.frtTv1 != null) return false;
        if (kt3Tv1 != null ? !kt3Tv1.equals(that.kt3Tv1) : that.kt3Tv1 != null) return false;
        if (siTv2 != null ? !siTv2.equals(that.siTv2) : that.siTv2 != null) return false;
        if (activeBdTv2 != null ? !activeBdTv2.equals(that.activeBdTv2) : that.activeBdTv2 != null) return false;
        if (frtTv2 != null ? !frtTv2.equals(that.frtTv2) : that.frtTv2 != null) return false;
        if (kt3Tv2 != null ? !kt3Tv2.equals(that.kt3Tv2) : that.kt3Tv2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (typeOperation != null ? typeOperation.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (chronoligical != null ? chronoligical.hashCode() : 0);
        result = 31 * result + (idCustomer != null ? idCustomer.hashCode() : 0);
        result = 31 * result + (t1Tv1 != null ? t1Tv1.hashCode() : 0);
        result = 31 * result + (p1Tv1 != null ? p1Tv1.hashCode() : 0);
        result = 31 * result + (v1Tv1 != null ? v1Tv1.hashCode() : 0);
        result = 31 * result + (m1Tv1 != null ? m1Tv1.hashCode() : 0);
        result = 31 * result + (t2Tv1 != null ? t2Tv1.hashCode() : 0);
        result = 31 * result + (p2Tv1 != null ? p2Tv1.hashCode() : 0);
        result = 31 * result + (v2Tv1 != null ? v2Tv1.hashCode() : 0);
        result = 31 * result + (m2Tv1 != null ? m2Tv1.hashCode() : 0);
        result = 31 * result + (t3Tv1 != null ? t3Tv1.hashCode() : 0);
        result = 31 * result + (p3Tv1 != null ? p3Tv1.hashCode() : 0);
        result = 31 * result + (v3Tv1 != null ? v3Tv1.hashCode() : 0);
        result = 31 * result + (m3Tv1 != null ? m3Tv1.hashCode() : 0);
        result = 31 * result + (t1Tv2 != null ? t1Tv2.hashCode() : 0);
        result = 31 * result + (p1Tv2 != null ? p1Tv2.hashCode() : 0);
        result = 31 * result + (v1Tv2 != null ? v1Tv2.hashCode() : 0);
        result = 31 * result + (m1Tv2 != null ? m1Tv2.hashCode() : 0);
        result = 31 * result + (t2Tv2 != null ? t2Tv2.hashCode() : 0);
        result = 31 * result + (p2Tv2 != null ? p2Tv2.hashCode() : 0);
        result = 31 * result + (v2Tv2 != null ? v2Tv2.hashCode() : 0);
        result = 31 * result + (m2Tv2 != null ? m2Tv2.hashCode() : 0);
        result = 31 * result + (t3Tv2 != null ? t3Tv2.hashCode() : 0);
        result = 31 * result + (p3Tv2 != null ? p3Tv2.hashCode() : 0);
        result = 31 * result + (v3Tv2 != null ? v3Tv2.hashCode() : 0);
        result = 31 * result + (m3Tv2 != null ? m3Tv2.hashCode() : 0);
        result = 31 * result + (tnvTv1 != null ? tnvTv1.hashCode() : 0);
        result = 31 * result + (txTv1 != null ? txTv1.hashCode() : 0);
        result = 31 * result + (pxTv1 != null ? pxTv1.hashCode() : 0);
        result = 31 * result + (dtTv1 != null ? dtTv1.hashCode() : 0);
        result = 31 * result + (dMTv1 != null ? dMTv1.hashCode() : 0);
        result = 31 * result + (qtvTv1 != null ? qtvTv1.hashCode() : 0);
        result = 31 * result + (q12Tv1 != null ? q12Tv1.hashCode() : 0);
        result = 31 * result + (qgTv1 != null ? qgTv1.hashCode() : 0);
        result = 31 * result + (vnrTv1 != null ? vnrTv1.hashCode() : 0);
        result = 31 * result + (vosTv1 != null ? vosTv1.hashCode() : 0);
        result = 31 * result + (tnvTv2 != null ? tnvTv2.hashCode() : 0);
        result = 31 * result + (txTv2 != null ? txTv2.hashCode() : 0);
        result = 31 * result + (pxTv2 != null ? pxTv2.hashCode() : 0);
        result = 31 * result + (dtTv2 != null ? dtTv2.hashCode() : 0);
        result = 31 * result + (dMTv2 != null ? dMTv2.hashCode() : 0);
        result = 31 * result + (qtvTv2 != null ? qtvTv2.hashCode() : 0);
        result = 31 * result + (q12Tv2 != null ? q12Tv2.hashCode() : 0);
        result = 31 * result + (qgTv2 != null ? qgTv2.hashCode() : 0);
        result = 31 * result + (vnrTv2 != null ? vnrTv2.hashCode() : 0);
        result = 31 * result + (vosTv2 != null ? vosTv2.hashCode() : 0);
        result = 31 * result + (dp != null ? dp.hashCode() : 0);
        result = 31 * result + (ns1Tv1 != null ? ns1Tv1.hashCode() : 0);
        result = 31 * result + (ns2Tv1 != null ? ns2Tv1.hashCode() : 0);
        result = 31 * result + (ns3Tv1 != null ? ns3Tv1.hashCode() : 0);
        result = 31 * result + (ns1Tv2 != null ? ns1Tv2.hashCode() : 0);
        result = 31 * result + (ns2Tv2 != null ? ns2Tv2.hashCode() : 0);
        result = 31 * result + (ns3Tv2 != null ? ns3Tv2.hashCode() : 0);
        result = 31 * result + (nsTv1 != null ? nsTv1.hashCode() : 0);
        result = 31 * result + (nsTv2 != null ? nsTv2.hashCode() : 0);
        result = 31 * result + (nsDp != null ? nsDp.hashCode() : 0);
        result = 31 * result + (signsOfEvents != null ? signsOfEvents.hashCode() : 0);
        result = 31 * result + (durationOf220 != null ? durationOf220.hashCode() : 0);
        result = 31 * result + (durationDisplay != null ? durationDisplay.hashCode() : 0);
        result = 31 * result + (durationOut != null ? durationOut.hashCode() : 0);
        result = 31 * result + (siTv1 != null ? siTv1.hashCode() : 0);
        result = 31 * result + (activeBdTv1 != null ? activeBdTv1.hashCode() : 0);
        result = 31 * result + (frtTv1 != null ? frtTv1.hashCode() : 0);
        result = 31 * result + (kt3Tv1 != null ? kt3Tv1.hashCode() : 0);
        result = 31 * result + (siTv2 != null ? siTv2.hashCode() : 0);
        result = 31 * result + (activeBdTv2 != null ? activeBdTv2.hashCode() : 0);
        result = 31 * result + (frtTv2 != null ? frtTv2.hashCode() : 0);
        result = 31 * result + (kt3Tv2 != null ? kt3Tv2.hashCode() : 0);
        return result;
    }
}
