package ru.javabegin.training.vkt7.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 25.09.2017.
 */
@Entity

@Table(name = "result")
@NamedQueries({
        @NamedQuery(name="Result.findAll", query="select c from Result c")
      /* @NamedQuery(name="Result.findById",
                query="select distinct c from Result c left join fetch c.measurementsSet t where c.id = :id"),*/
        /* @NamedQuery(name="Contact.findAllWithDetail",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")*/
})
@SqlResultSetMapping(
        name="resultResult",
        entities=@EntityResult(entityClass=Result.class)
)



public class Result {
    private Long id;
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

   /* private Set<Measurements> measurementsSet = new HashSet<Measurements>();

   */ //private int resultId;/


    public Result() {
    }

    public Result(String identificator) {
        this.identificator = identificator;
    }

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


    @Column(name = "DATE_RESULT_SERVER", nullable = true)
    public Timestamp getDateResultServer() {
        return dateResultServer;
    }

    public void setDateResultServer(Timestamp dateResultServer) {
        this.dateResultServer = dateResultServer;
    }

@Version
    @Column(name = "VERSION", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }



   /* @OneToMany(mappedBy = "result", cascade=CascadeType.ALL,
            orphanRemoval=true)


    public Set<Measurements> getMeasurementsSet() {
        return measurementsSet;
    }

    public void setMeasurementsSet(Set<Measurements> measurementsSet) {
        this.measurementsSet = measurementsSet;
    }

    public void addMeasurements(Measurements measurements) {
        measurements.setResult(this);
        getMeasurementsSet().add(measurements);
    }

    public void removeMeasurements(Measurements measurements) {
        getMeasurementsSet().remove(measurements);
    }*/


}
