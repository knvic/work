package ru.javabegin.training.tv7.entity;

import ru.javabegin.training.vkt7.entities.Customer;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "operationtv7_t", schema = "lukoil" )
@NamedQueries({
        @NamedQuery(name="Operationnv7Tt.findAll", query="select c from Operationtv7T c")
        /*@NamedQuery(name="Contact.findById",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),*/

})
@SqlResultSetMapping(
        name="operationtv7TResult",
        entities=@EntityResult(entityClass=Operationtv7T.class)
)
public class Operationtv7T {
    private int id;
    private String customerName;
    private String error;
    private Timestamp chronoligical;
    private Long idCustomer;
    private String v1Tv1;
    private String m1Tv1;
    private String v2Tv1;
    private String m2Tv1;
    private String v3Tv1;
    private String m3Tv1;
    private String v1Tv2;
    private String m1Tv2;
    private String v2Tv2;
    private String m2Tv2;
    private String v3Tv2;
    private String m3Tv2;
    private String dMTv1;
    private String qtvTv1;
    private String q12Tv1;
    private String qgTv1;
    private String vnrTv1;
    private String vosTv1;
    private String tvminTv1;
    private String tvmaxTv1;
    private String tdtTv1;
    private String tno220Tv1;
    private String tterrTv1;
    private String dMTv2;
    private String qtvTv2;
    private String q12Tv2;
    private String qgTv2;
    private String vnrTv2;
    private String vosTv2;
    private String tvminTv2;
    private String tvmaxTv2;
    private String tdtTv2;
    private String tno220Tv2;
    private String tterrTv2;
    private String dp;
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
    @Column(name = "Tvmin_tv1", nullable = true, length = 45)
    public String getTvminTv1() {
        return tvminTv1;
    }

    public void setTvminTv1(String tvminTv1) {
        this.tvminTv1 = tvminTv1;
    }

    @Basic
    @Column(name = "Tvmax_tv1", nullable = true, length = 45)
    public String getTvmaxTv1() {
        return tvmaxTv1;
    }

    public void setTvmaxTv1(String tvmaxTv1) {
        this.tvmaxTv1 = tvmaxTv1;
    }

    @Basic
    @Column(name = "Tdt_tv1", nullable = true, length = 45)
    public String getTdtTv1() {
        return tdtTv1;
    }

    public void setTdtTv1(String tdtTv1) {
        this.tdtTv1 = tdtTv1;
    }

    @Basic
    @Column(name = "Tno220_tv1", nullable = true, length = 45)
    public String getTno220Tv1() {
        return tno220Tv1;
    }

    public void setTno220Tv1(String tno220Tv1) {
        this.tno220Tv1 = tno220Tv1;
    }

    @Basic
    @Column(name = "Tterr_tv1", nullable = true, length = 45)
    public String getTterrTv1() {
        return tterrTv1;
    }

    public void setTterrTv1(String tterrTv1) {
        this.tterrTv1 = tterrTv1;
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
    @Column(name = "Tvmin_tv2", nullable = true, length = 45)
    public String getTvminTv2() {
        return tvminTv2;
    }

    public void setTvminTv2(String tvminTv2) {
        this.tvminTv2 = tvminTv2;
    }

    @Basic
    @Column(name = "Tvmax_tv2", nullable = true, length = 45)
    public String getTvmaxTv2() {
        return tvmaxTv2;
    }

    public void setTvmaxTv2(String tvmaxTv2) {
        this.tvmaxTv2 = tvmaxTv2;
    }

    @Basic
    @Column(name = "Tdt_tv2", nullable = true, length = 45)
    public String getTdtTv2() {
        return tdtTv2;
    }

    public void setTdtTv2(String tdtTv2) {
        this.tdtTv2 = tdtTv2;
    }

    @Basic
    @Column(name = "Tno220_tv2", nullable = true, length = 45)
    public String getTno220Tv2() {
        return tno220Tv2;
    }

    public void setTno220Tv2(String tno220Tv2) {
        this.tno220Tv2 = tno220Tv2;
    }

    @Basic
    @Column(name = "Tterr_tv2", nullable = true, length = 45)
    public String getTterrTv2() {
        return tterrTv2;
    }

    public void setTterrTv2(String tterrTv2) {
        this.tterrTv2 = tterrTv2;
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

        Operationtv7T that = (Operationtv7T) o;

        if (id != that.id) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (error != null ? !error.equals(that.error) : that.error != null) return false;
        if (chronoligical != null ? !chronoligical.equals(that.chronoligical) : that.chronoligical != null)
            return false;
        if (idCustomer != null ? !idCustomer.equals(that.idCustomer) : that.idCustomer != null) return false;
        if (v1Tv1 != null ? !v1Tv1.equals(that.v1Tv1) : that.v1Tv1 != null) return false;
        if (m1Tv1 != null ? !m1Tv1.equals(that.m1Tv1) : that.m1Tv1 != null) return false;
        if (v2Tv1 != null ? !v2Tv1.equals(that.v2Tv1) : that.v2Tv1 != null) return false;
        if (m2Tv1 != null ? !m2Tv1.equals(that.m2Tv1) : that.m2Tv1 != null) return false;
        if (v3Tv1 != null ? !v3Tv1.equals(that.v3Tv1) : that.v3Tv1 != null) return false;
        if (m3Tv1 != null ? !m3Tv1.equals(that.m3Tv1) : that.m3Tv1 != null) return false;
        if (v1Tv2 != null ? !v1Tv2.equals(that.v1Tv2) : that.v1Tv2 != null) return false;
        if (m1Tv2 != null ? !m1Tv2.equals(that.m1Tv2) : that.m1Tv2 != null) return false;
        if (v2Tv2 != null ? !v2Tv2.equals(that.v2Tv2) : that.v2Tv2 != null) return false;
        if (m2Tv2 != null ? !m2Tv2.equals(that.m2Tv2) : that.m2Tv2 != null) return false;
        if (v3Tv2 != null ? !v3Tv2.equals(that.v3Tv2) : that.v3Tv2 != null) return false;
        if (m3Tv2 != null ? !m3Tv2.equals(that.m3Tv2) : that.m3Tv2 != null) return false;
        if (dMTv1 != null ? !dMTv1.equals(that.dMTv1) : that.dMTv1 != null) return false;
        if (qtvTv1 != null ? !qtvTv1.equals(that.qtvTv1) : that.qtvTv1 != null) return false;
        if (q12Tv1 != null ? !q12Tv1.equals(that.q12Tv1) : that.q12Tv1 != null) return false;
        if (qgTv1 != null ? !qgTv1.equals(that.qgTv1) : that.qgTv1 != null) return false;
        if (vnrTv1 != null ? !vnrTv1.equals(that.vnrTv1) : that.vnrTv1 != null) return false;
        if (vosTv1 != null ? !vosTv1.equals(that.vosTv1) : that.vosTv1 != null) return false;
        if (tvminTv1 != null ? !tvminTv1.equals(that.tvminTv1) : that.tvminTv1 != null) return false;
        if (tvmaxTv1 != null ? !tvmaxTv1.equals(that.tvmaxTv1) : that.tvmaxTv1 != null) return false;
        if (tdtTv1 != null ? !tdtTv1.equals(that.tdtTv1) : that.tdtTv1 != null) return false;
        if (tno220Tv1 != null ? !tno220Tv1.equals(that.tno220Tv1) : that.tno220Tv1 != null) return false;
        if (tterrTv1 != null ? !tterrTv1.equals(that.tterrTv1) : that.tterrTv1 != null) return false;
        if (dMTv2 != null ? !dMTv2.equals(that.dMTv2) : that.dMTv2 != null) return false;
        if (qtvTv2 != null ? !qtvTv2.equals(that.qtvTv2) : that.qtvTv2 != null) return false;
        if (q12Tv2 != null ? !q12Tv2.equals(that.q12Tv2) : that.q12Tv2 != null) return false;
        if (qgTv2 != null ? !qgTv2.equals(that.qgTv2) : that.qgTv2 != null) return false;
        if (vnrTv2 != null ? !vnrTv2.equals(that.vnrTv2) : that.vnrTv2 != null) return false;
        if (vosTv2 != null ? !vosTv2.equals(that.vosTv2) : that.vosTv2 != null) return false;
        if (tvminTv2 != null ? !tvminTv2.equals(that.tvminTv2) : that.tvminTv2 != null) return false;
        if (tvmaxTv2 != null ? !tvmaxTv2.equals(that.tvmaxTv2) : that.tvmaxTv2 != null) return false;
        if (tdtTv2 != null ? !tdtTv2.equals(that.tdtTv2) : that.tdtTv2 != null) return false;
        if (tno220Tv2 != null ? !tno220Tv2.equals(that.tno220Tv2) : that.tno220Tv2 != null) return false;
        if (tterrTv2 != null ? !tterrTv2.equals(that.tterrTv2) : that.tterrTv2 != null) return false;
        if (dp != null ? !dp.equals(that.dp) : that.dp != null) return false;
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
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (chronoligical != null ? chronoligical.hashCode() : 0);
        result = 31 * result + (idCustomer != null ? idCustomer.hashCode() : 0);
        result = 31 * result + (v1Tv1 != null ? v1Tv1.hashCode() : 0);
        result = 31 * result + (m1Tv1 != null ? m1Tv1.hashCode() : 0);
        result = 31 * result + (v2Tv1 != null ? v2Tv1.hashCode() : 0);
        result = 31 * result + (m2Tv1 != null ? m2Tv1.hashCode() : 0);
        result = 31 * result + (v3Tv1 != null ? v3Tv1.hashCode() : 0);
        result = 31 * result + (m3Tv1 != null ? m3Tv1.hashCode() : 0);
        result = 31 * result + (v1Tv2 != null ? v1Tv2.hashCode() : 0);
        result = 31 * result + (m1Tv2 != null ? m1Tv2.hashCode() : 0);
        result = 31 * result + (v2Tv2 != null ? v2Tv2.hashCode() : 0);
        result = 31 * result + (m2Tv2 != null ? m2Tv2.hashCode() : 0);
        result = 31 * result + (v3Tv2 != null ? v3Tv2.hashCode() : 0);
        result = 31 * result + (m3Tv2 != null ? m3Tv2.hashCode() : 0);
        result = 31 * result + (dMTv1 != null ? dMTv1.hashCode() : 0);
        result = 31 * result + (qtvTv1 != null ? qtvTv1.hashCode() : 0);
        result = 31 * result + (q12Tv1 != null ? q12Tv1.hashCode() : 0);
        result = 31 * result + (qgTv1 != null ? qgTv1.hashCode() : 0);
        result = 31 * result + (vnrTv1 != null ? vnrTv1.hashCode() : 0);
        result = 31 * result + (vosTv1 != null ? vosTv1.hashCode() : 0);
        result = 31 * result + (tvminTv1 != null ? tvminTv1.hashCode() : 0);
        result = 31 * result + (tvmaxTv1 != null ? tvmaxTv1.hashCode() : 0);
        result = 31 * result + (tdtTv1 != null ? tdtTv1.hashCode() : 0);
        result = 31 * result + (tno220Tv1 != null ? tno220Tv1.hashCode() : 0);
        result = 31 * result + (tterrTv1 != null ? tterrTv1.hashCode() : 0);
        result = 31 * result + (dMTv2 != null ? dMTv2.hashCode() : 0);
        result = 31 * result + (qtvTv2 != null ? qtvTv2.hashCode() : 0);
        result = 31 * result + (q12Tv2 != null ? q12Tv2.hashCode() : 0);
        result = 31 * result + (qgTv2 != null ? qgTv2.hashCode() : 0);
        result = 31 * result + (vnrTv2 != null ? vnrTv2.hashCode() : 0);
        result = 31 * result + (vosTv2 != null ? vosTv2.hashCode() : 0);
        result = 31 * result + (tvminTv2 != null ? tvminTv2.hashCode() : 0);
        result = 31 * result + (tvmaxTv2 != null ? tvmaxTv2.hashCode() : 0);
        result = 31 * result + (tdtTv2 != null ? tdtTv2.hashCode() : 0);
        result = 31 * result + (tno220Tv2 != null ? tno220Tv2.hashCode() : 0);
        result = 31 * result + (tterrTv2 != null ? tterrTv2.hashCode() : 0);
        result = 31 * result + (dp != null ? dp.hashCode() : 0);
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
