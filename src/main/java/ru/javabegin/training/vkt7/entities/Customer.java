package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 26.09.2017.
 */


@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name="Customer.findAll", query="select c from Customer c"),
        @NamedQuery(name="Customer.findById",
                query="select distinct c from Customer c left join fetch c.operationSet t where c.id = :id"),
        @NamedQuery(name="Customer.findById_tv7",
                query="select distinct c from Customer c left join fetch c.operationtv7Set h where c.id = :id"),
        @NamedQuery(name="Customer.findById_tv7_t",
                query="select distinct c from Customer c left join fetch c.operationtv7TSet h where c.id = :id"),

      /*  @NamedQuery(name="Contact.findById",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),*/
        @NamedQuery(name="Customer.findAllWithDetail",
                query="select distinct c from Customer c   left join fetch c.operationSet t where c.unitType like 'ВКТ7' "),
        @NamedQuery(name="Customer.findAllWithDetail_not_block",
                query="select distinct c from Customer c   left join fetch c.operationSet t where c.unitType like 'ВКТ7' and c.status like 'В р%'"),
        @NamedQuery(name="Customer.findAllWithDetail_tv7",
                query="select distinct c from Customer c left join fetch c.operationtv7Set h left join fetch c.operationtv7TSet p where c.unitType like 'ТВ7' "),
        @NamedQuery(name="Customer.findAllWithDetail_tv7_not_block",
                query="select distinct c from Customer c left join fetch c.operationtv7Set h left join fetch c.operationtv7TSet p where c.unitType like 'ТВ7' and c.status like 'В р%'"),
        @NamedQuery(name="Customer.findAllWithDetail_tv7_t",
                query="select distinct c from Customer c left join fetch c.operationtv7TSet h "),
        @NamedQuery(name="Customer.findTv7Cusromers",
                query="select distinct c from Customer c where c.unitType like 'ТВ7' ")
})
@SqlResultSetMapping(
        name="customerResult",
        entities=@EntityResult(entityClass=Customer.class)
)
public class Customer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String telModem;
    private Integer unitNumber;
    private String eMail;
    private int version;
    private String branch;
    private String address;
    private String unitType;
    private String status;
    private String reason;



    private Set<Operation> operationSet = new HashSet<Operation>();

    private Set<Operationtv7> operationtv7Set = new HashSet<Operationtv7>();

    private Set<Operationtv7T> operationtv7TSet = new HashSet<Operationtv7T>();


    private Long customerId;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "BRANCH")
    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Column(name = "ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @Column(name = "TEL_NUMBER")
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Column(name = "TEL_MODEM")
    public String getTelModem() {
        return telModem;
    }

    public void setTelModem(String telModem) {
        this.telModem = telModem;
    }


    @Column(name = "UNIT_NUMBER")
    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }


    @Column(name = "E_MAIL")
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "UNIT_TYPE")
    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "REASON")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,
            orphanRemoval=true)

    public Set<Operation> getOperationSet() {
        return operationSet;
    }

    public void setOperationSet(Set<Operation> operationSet) {
        this.operationSet = operationSet;
    }



    public void addOperation(Operation operation) {
        operation.setCustomer(this);
        getOperationSet().add(operation);
    }

    public void removeOperation(Operation operation) {
        getOperationSet().remove(operation);
    }



    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,
            orphanRemoval=true)

    public Set<Operationtv7> getOperationtv7Set() {
        return operationtv7Set;
    }

    public void setOperationtv7Set(Set<Operationtv7> operationtv7Set) {
        this.operationtv7Set = operationtv7Set;
    }



    public void addOperationtv7(Operationtv7 operationtv7) {
        operationtv7.setCustomer(this);
        getOperationtv7Set().add(operationtv7);
    }

    public void removeOperationtv7(Operationtv7 operationtv7) {
        getOperationtv7Set().remove(operationtv7);
    }




    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,
            orphanRemoval=true)

    public Set<Operationtv7T> getOperationtv7TSet() {
        return operationtv7TSet;
    }

    public void setOperationtv7TSet(Set<Operationtv7T> operationtv7TSet) {
        this.operationtv7TSet = operationtv7TSet;
    }



    public void addOperationtv7T(Operationtv7T operationtv7T) {
        operationtv7T.setCustomer(this);
        getOperationtv7TSet().add(operationtv7T);
    }

    public void removeOperationtv7T(Operationtv7T operationtv7T) {
        getOperationtv7TSet().remove(operationtv7T);
    }




    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telNumber='" + telNumber + '\'' +
                ", telModem='" + telModem + '\'' +
                ", unitNumber=" + unitNumber +
                ", eMail='" + eMail + '\'' +
                ", version=" + version +
                ", branch='" + branch + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
