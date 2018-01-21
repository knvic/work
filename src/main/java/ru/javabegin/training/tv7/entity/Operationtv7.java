package ru.javabegin.training.tv7.entity;

import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

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
    private Long id;
    private String customerName;
    private String typeOperation;
    private String error;
    private Timestamp chronoligical;
    private Integer idCustomer;
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
    private String tнвTv1;
    private String txTv1;
    private String pxTv1;
    private String dtTv1;
    private String dMTv1;
    private String qтвTv1;
    private String q12Tv1;
    private String qгTv1;
    private String внрTv1;
    private String восTv1;
    private String tнвTv2;
    private String txTv2;
    private String pxTv2;
    private String dtTv2;
    private String dMTv2;
    private String qтвTv2;
    private String q12Tv2;
    private String qгTv2;
    private String внрTv2;
    private String восTv2;
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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
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
    @Column(name = "tнв_tv1", nullable = true, length = 45)
    public String getTнвTv1() {
        return tнвTv1;
    }

    public void setTнвTv1(String tнвTv1) {
        this.tнвTv1 = tнвTv1;
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
    @Column(name = "Qтв_tv1", nullable = true, length = 45)
    public String getQтвTv1() {
        return qтвTv1;
    }

    public void setQтвTv1(String qтвTv1) {
        this.qтвTv1 = qтвTv1;
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
    @Column(name = "Qг_tv1", nullable = true, length = 45)
    public String getQгTv1() {
        return qгTv1;
    }

    public void setQгTv1(String qгTv1) {
        this.qгTv1 = qгTv1;
    }

    @Basic
    @Column(name = "ВНР_tv1", nullable = true, length = 45)
    public String getВнрTv1() {
        return внрTv1;
    }

    public void setВнрTv1(String внрTv1) {
        this.внрTv1 = внрTv1;
    }

    @Basic
    @Column(name = "ВОС_tv1", nullable = true, length = 45)
    public String getВосTv1() {
        return восTv1;
    }

    public void setВосTv1(String восTv1) {
        this.восTv1 = восTv1;
    }

    @Basic
    @Column(name = "tнв_tv2", nullable = true, length = 45)
    public String getTнвTv2() {
        return tнвTv2;
    }

    public void setTнвTv2(String tнвTv2) {
        this.tнвTv2 = tнвTv2;
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
    @Column(name = "Qтв_tv2", nullable = true, length = 45)
    public String getQтвTv2() {
        return qтвTv2;
    }

    public void setQтвTv2(String qтвTv2) {
        this.qтвTv2 = qтвTv2;
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
    @Column(name = "Qг_tv2", nullable = true, length = 45)
    public String getQгTv2() {
        return qгTv2;
    }

    public void setQгTv2(String qгTv2) {
        this.qгTv2 = qгTv2;
    }

    @Basic
    @Column(name = "ВНР_tv2", nullable = true, length = 45)
    public String getВнрTv2() {
        return внрTv2;
    }

    public void setВнрTv2(String внрTv2) {
        this.внрTv2 = внрTv2;
    }

    @Basic
    @Column(name = "ВОС_tv2", nullable = true, length = 45)
    public String getВосTv2() {
        return восTv2;
    }

    public void setВосTv2(String восTv2) {
        this.восTv2 = восTv2;
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
    @Column(name = "duration_of 220", nullable = true, length = 45)
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


}
