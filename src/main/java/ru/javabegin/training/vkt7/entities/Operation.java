package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.ContactTelDetail;
import ru.javabegin.training.db.Hobby;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 29.09.2017.
 */
@Entity
@Table(name = "operation")
@NamedQueries({
        @NamedQuery(name="Operation.findAll", query="select c from Operation c"),
        /*@NamedQuery(name="Contact.findById",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),*/
        @NamedQuery(name="Operation.findAllWithDetail",
                query="select distinct c from Operation c left join fetch c.measurementsSet t")
})
@SqlResultSetMapping(
        name="operationResult",
        entities=@EntityResult(entityClass=Operation.class)
)
public class Operation implements Serializable{
    private Long id;
  //private Long customerId;

    private Long idCustomer;
    private String customerName;

    private String typeOperation;
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
    private Timestamp currentDate3Ff6;
    private Timestamp beginDayDate;
    private Timestamp dateVkt3Ffb;
    private Timestamp dateServer;
    private String shemaTv13Ecd;
    private String shemaTv23F5B;
    private String baseNumber;
    private String status;
    private String error;
    private int version;

    private Customer customer;

    private Set<Measurements> measurementsSet = new HashSet<Measurements>();


    private int operationId;





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
    @Column(name = "ID_CUSTOMER", nullable = true)
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }



    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = true)
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
    @Column(name = "NET_NUMBER", nullable = true, length = 44)
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
    @Column(name = "CURRENT_DATE_3FF6", nullable = true)
    public Timestamp getCurrentDate3Ff6() {
        return currentDate3Ff6;
    }

    public void setCurrentDate3Ff6(Timestamp currentDate3Ff6) {
        this.currentDate3Ff6 = currentDate3Ff6;
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
    @Column(name = "DATE_VKT_3FFB", nullable = true)
    public Timestamp getDateVkt3Ffb() {
        return dateVkt3Ffb;
    }

    public void setDateVkt3Ffb(Timestamp dateVkt3Ffb) {
        this.dateVkt3Ffb = dateVkt3Ffb;
    }

    @Basic
    @Column(name = "DATE_SERVER", nullable = true)
    public Timestamp getDateServer() {
        return dateServer;
    }

    public void setDateServer(Timestamp dateServer) {
        this.dateServer = dateServer;
    }

    @Basic
    @Column(name = "SHEMA_TV1_3ECD", nullable = true, length = 2)
    public String getShemaTv13Ecd() {
        return shemaTv13Ecd;
    }

    public void setShemaTv13Ecd(String shemaTv13Ecd) {
        this.shemaTv13Ecd = shemaTv13Ecd;
    }

    @Basic
    @Column(name = "SHEMA_TV2_3F5B", nullable = true, length = 2)
    public String getShemaTv23F5B() {
        return shemaTv23F5B;
    }

    public void setShemaTv23F5B(String shemaTv23F5B) {
        this.shemaTv23F5B = shemaTv23F5B;
    }

    @Basic
    @Column(name = "BASE_NUMBER", nullable = true, length = 2)
    public String getBaseNumber() {
        return baseNumber;
    }

    public void setBaseNumber(String baseNumber) {
        this.baseNumber = baseNumber;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 10)
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
    @Column(name = "VERSION", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }






    ///////////////////////////////
    @OneToMany(mappedBy = "operation", cascade=CascadeType.ALL,
            orphanRemoval=true)

    public Set<Measurements> getMeasurementsSet() {
        return measurementsSet;
    }

    public void setMeasurementsSet(Set<Measurements> measurementsSet) {
        this.measurementsSet = measurementsSet;
    }



    public void addMeasurements(Measurements measurements) {
        measurements.setOperation(this);
        getMeasurementsSet().add(measurements);
    }

    public void removeMeasurements(Measurements measurements) {
        getMeasurementsSet().remove(measurements);
    }




    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
//    @JoinColumn(name = "CUSTOMER_ID",insertable = false, updatable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", idCustomer=" + idCustomer +
                ", customerName='" + customerName + '\'' +
                ", typeOperation='" + typeOperation + '\'' +
                ", serverVersion='" + serverVersion + '\'' +
                ", programmVersion='" + programmVersion + '\'' +
                ", shemaTv13Ff9='" + shemaTv13Ff9 + '\'' +
                ", tp3Tv1='" + tp3Tv1 + '\'' +
                ", t5Tv1='" + t5Tv1 + '\'' +
                ", shemaTv23Ff9='" + shemaTv23Ff9 + '\'' +
                ", tp3Tv2='" + tp3Tv2 + '\'' +
                ", t5Tv2='" + t5Tv2 + '\'' +
                ", identificator='" + identificator + '\'' +
                ", netNumber='" + netNumber + '\'' +
                ", resultDate3Ff9='" + resultDate3Ff9 + '\'' +
                ", model='" + model + '\'' +
                ", beginHourDate=" + beginHourDate +
                ", currentDate3Ff6=" + currentDate3Ff6 +
                ", beginDayDate=" + beginDayDate +
                ", dateVkt3Ffb=" + dateVkt3Ffb +
                ", dateServer=" + dateServer +
                ", shemaTv13Ecd='" + shemaTv13Ecd + '\'' +
                ", shemaTv23F5B='" + shemaTv23F5B + '\'' +
                ", baseNumber='" + baseNumber + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", version=" + version +
                ", customer=" + customer +
                ", measurementsSet=" + measurementsSet +
                ", operationId=" + operationId +
                '}';
    }
}
