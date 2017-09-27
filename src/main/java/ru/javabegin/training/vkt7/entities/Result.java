package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 25.09.2017.
 */
@Entity

@Table(name = "result")
@NamedQueries({
        @NamedQuery(name="Result.findAll", query="select c from Result c"),
        /*@NamedQuery(name="Result.findById",
                query="select distinct c from Result c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
        @NamedQuery(name="Contact.findAllWithDetail",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")*/
})
@SqlResultSetMapping(
        name="resultResult",
        entities=@EntityResult(entityClass=Result.class)
)



public class Result {
    private int id;
    private Integer customerId;
    private String typeResult;
    private String serverVersion;
    private String programmVersion;
    private String shemaTv13Ff9;
    private String tp3Tv1;
    private String t5Tv1;
    private String shemaTv23Ff9;
    private String tp3Tv2;
    private String t5Tv2;
    private String identificator;
    private String netNumber;
    private String resultDate3Ff9;
    private String model;
    private Timestamp beginHourDate;
    private Timestamp currentDate;
    private Timestamp beginDayDate;
    private String shemaTv13Ecd;
    private String shemaTv23F5B;
    private String numberBase;
    private String status;
    private String error;
    private Timestamp dateResultServer;
    private int version;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUSTOMER_ID", nullable = true)
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "TYPE_RESULT", nullable = true, length = 45)
    public String getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(String typeResult) {
        this.typeResult = typeResult;
    }

    @Basic
    @Column(name = "SERVER_VERSION", nullable = true, length = 4)
    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    @Basic
    @Column(name = "PROGRAMM_VERSION", nullable = true, length = 4)
    public String getProgrammVersion() {
        return programmVersion;
    }

    public void setProgrammVersion(String programmVersion) {
        this.programmVersion = programmVersion;
    }

    @Basic
    @Column(name = "SHEMA_TV_1_3FF9", nullable = true, length = 2)
    public String getShemaTv13Ff9() {
        return shemaTv13Ff9;
    }

    public void setShemaTv13Ff9(String shemaTv13Ff9) {
        this.shemaTv13Ff9 = shemaTv13Ff9;
    }

    @Basic
    @Column(name = "TP3_TV_1", nullable = true, length = 2)
    public String getTp3Tv1() {
        return tp3Tv1;
    }

    public void setTp3Tv1(String tp3Tv1) {
        this.tp3Tv1 = tp3Tv1;
    }

    @Basic
    @Column(name = "T5_TV_1", nullable = true, length = 2)
    public String getT5Tv1() {
        return t5Tv1;
    }

    public void setT5Tv1(String t5Tv1) {
        this.t5Tv1 = t5Tv1;
    }

    @Basic
    @Column(name = "SHEMA_TV_2_3FF9", nullable = true, length = 2)
    public String getShemaTv23Ff9() {
        return shemaTv23Ff9;
    }

    public void setShemaTv23Ff9(String shemaTv23Ff9) {
        this.shemaTv23Ff9 = shemaTv23Ff9;
    }

    @Basic
    @Column(name = "TP3_TV_2", nullable = true, length = 2)
    public String getTp3Tv2() {
        return tp3Tv2;
    }

    public void setTp3Tv2(String tp3Tv2) {
        this.tp3Tv2 = tp3Tv2;
    }

    @Basic
    @Column(name = "T5_TV_2", nullable = true, length = 2)
    public String getT5Tv2() {
        return t5Tv2;
    }

    public void setT5Tv2(String t5Tv2) {
        this.t5Tv2 = t5Tv2;
    }

    @Basic
    @Column(name = "IDENTIFICATOR", nullable = true, length = 45)
    public String getIdentificator() {
        return identificator;
    }

    public void setIdentificator(String identificator) {
        this.identificator = identificator;
    }

    @Basic
    @Column(name = "NET_NUMBER", nullable = true, length = 4)
    public String getNetNumber() {
        return netNumber;
    }

    public void setNetNumber(String netNumber) {
        this.netNumber = netNumber;
    }

    @Basic
    @Column(name = "RESULT_DATE_3FF9", nullable = true, length = 45)
    public String getResultDate3Ff9() {
        return resultDate3Ff9;
    }

    public void setResultDate3Ff9(String resultDate3Ff9) {
        this.resultDate3Ff9 = resultDate3Ff9;
    }

    @Basic
    @Column(name = "MODEL", nullable = true, length = 4)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "BEGIN_HOUR_DATE", nullable = true)
    public Timestamp getBeginHourDate() {
        return beginHourDate;
    }

    public void setBeginHourDate(Timestamp beginHourDate) {
        this.beginHourDate = beginHourDate;
    }

    @Basic
    @Column(name = "CURRENT_DATE", nullable = true)
    public Timestamp getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Timestamp currentDate) {
        this.currentDate = currentDate;
    }

    @Basic
    @Column(name = "BEGIN_DAY_DATE", nullable = true)
    public Timestamp getBeginDayDate() {
        return beginDayDate;
    }

    public void setBeginDayDate(Timestamp beginDayDate) {
        this.beginDayDate = beginDayDate;
    }

    @Basic
    @Column(name = "SHEMA_TV_1_3ECD", nullable = true, length = 2)
    public String getShemaTv13Ecd() {
        return shemaTv13Ecd;
    }

    public void setShemaTv13Ecd(String shemaTv13Ecd) {
        this.shemaTv13Ecd = shemaTv13Ecd;
    }

    @Basic
    @Column(name = "SHEMA_TV_2_3F5B", nullable = true, length = 2)
    public String getShemaTv23F5B() {
        return shemaTv23F5B;
    }

    public void setShemaTv23F5B(String shemaTv23F5B) {
        this.shemaTv23F5B = shemaTv23F5B;
    }

    @Basic
    @Column(name = "NUMBER_BASE", nullable = true, length = 2)
    public String getNumberBase() {
        return numberBase;
    }

    public void setNumberBase(String numberBase) {
        this.numberBase = numberBase;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "DATE_RESULT_SERVER", nullable = true)
    public Timestamp getDateResultServer() {
        return dateResultServer;
    }

    public void setDateResultServer(Timestamp dateResultServer) {
        this.dateResultServer = dateResultServer;
    }

    @Basic
    @Column(name = "VERSION", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (id != result.id) return false;
        if (version != result.version) return false;
        if (customerId != null ? !customerId.equals(result.customerId) : result.customerId != null) return false;
        if (typeResult != null ? !typeResult.equals(result.typeResult) : result.typeResult != null) return false;
        if (serverVersion != null ? !serverVersion.equals(result.serverVersion) : result.serverVersion != null)
            return false;
        if (programmVersion != null ? !programmVersion.equals(result.programmVersion) : result.programmVersion != null)
            return false;
        if (shemaTv13Ff9 != null ? !shemaTv13Ff9.equals(result.shemaTv13Ff9) : result.shemaTv13Ff9 != null)
            return false;
        if (tp3Tv1 != null ? !tp3Tv1.equals(result.tp3Tv1) : result.tp3Tv1 != null) return false;
        if (t5Tv1 != null ? !t5Tv1.equals(result.t5Tv1) : result.t5Tv1 != null) return false;
        if (shemaTv23Ff9 != null ? !shemaTv23Ff9.equals(result.shemaTv23Ff9) : result.shemaTv23Ff9 != null)
            return false;
        if (tp3Tv2 != null ? !tp3Tv2.equals(result.tp3Tv2) : result.tp3Tv2 != null) return false;
        if (t5Tv2 != null ? !t5Tv2.equals(result.t5Tv2) : result.t5Tv2 != null) return false;
        if (identificator != null ? !identificator.equals(result.identificator) : result.identificator != null)
            return false;
        if (netNumber != null ? !netNumber.equals(result.netNumber) : result.netNumber != null) return false;
        if (resultDate3Ff9 != null ? !resultDate3Ff9.equals(result.resultDate3Ff9) : result.resultDate3Ff9 != null)
            return false;
        if (model != null ? !model.equals(result.model) : result.model != null) return false;
        if (beginHourDate != null ? !beginHourDate.equals(result.beginHourDate) : result.beginHourDate != null)
            return false;
        if (currentDate != null ? !currentDate.equals(result.currentDate) : result.currentDate != null) return false;
        if (beginDayDate != null ? !beginDayDate.equals(result.beginDayDate) : result.beginDayDate != null)
            return false;
        if (shemaTv13Ecd != null ? !shemaTv13Ecd.equals(result.shemaTv13Ecd) : result.shemaTv13Ecd != null)
            return false;
        if (shemaTv23F5B != null ? !shemaTv23F5B.equals(result.shemaTv23F5B) : result.shemaTv23F5B != null)
            return false;
        if (numberBase != null ? !numberBase.equals(result.numberBase) : result.numberBase != null) return false;
        if (status != null ? !status.equals(result.status) : result.status != null) return false;
        if (error != null ? !error.equals(result.error) : result.error != null) return false;
        if (dateResultServer != null ? !dateResultServer.equals(result.dateResultServer) : result.dateResultServer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (typeResult != null ? typeResult.hashCode() : 0);
        result = 31 * result + (serverVersion != null ? serverVersion.hashCode() : 0);
        result = 31 * result + (programmVersion != null ? programmVersion.hashCode() : 0);
        result = 31 * result + (shemaTv13Ff9 != null ? shemaTv13Ff9.hashCode() : 0);
        result = 31 * result + (tp3Tv1 != null ? tp3Tv1.hashCode() : 0);
        result = 31 * result + (t5Tv1 != null ? t5Tv1.hashCode() : 0);
        result = 31 * result + (shemaTv23Ff9 != null ? shemaTv23Ff9.hashCode() : 0);
        result = 31 * result + (tp3Tv2 != null ? tp3Tv2.hashCode() : 0);
        result = 31 * result + (t5Tv2 != null ? t5Tv2.hashCode() : 0);
        result = 31 * result + (identificator != null ? identificator.hashCode() : 0);
        result = 31 * result + (netNumber != null ? netNumber.hashCode() : 0);
        result = 31 * result + (resultDate3Ff9 != null ? resultDate3Ff9.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (beginHourDate != null ? beginHourDate.hashCode() : 0);
        result = 31 * result + (currentDate != null ? currentDate.hashCode() : 0);
        result = 31 * result + (beginDayDate != null ? beginDayDate.hashCode() : 0);
        result = 31 * result + (shemaTv13Ecd != null ? shemaTv13Ecd.hashCode() : 0);
        result = 31 * result + (shemaTv23F5B != null ? shemaTv23F5B.hashCode() : 0);
        result = 31 * result + (numberBase != null ? numberBase.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (error != null ? error.hashCode() : 0);
        result = 31 * result + (dateResultServer != null ? dateResultServer.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
