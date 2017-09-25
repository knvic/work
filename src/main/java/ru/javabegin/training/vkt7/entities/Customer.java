package ru.javabegin.training.vkt7.entities;



import ru.javabegin.training.db.ContactTelDetail;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Николай on 24.09.2017.
 */
@Entity

@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name="Customer.findAll", query="select c from Customer c"),
        @NamedQuery(name="Customer.findById",
                query="select distinct c from Customer c left join fetch c.results t where c.id = :id"),
       /* @NamedQuery(name="Contact.findAllWithDetail",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")*/
})
@SqlResultSetMapping(
        name="customerResult",
        entities=@EntityResult(entityClass=Customer.class)
)

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String telNumber;
    private String telModem;
    private String unitNumber;
    private String eMail;
    private Integer version;
    private Set<Result> results = new HashSet<Result>();


    private int customerId;


    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = true, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "TEL_NUMBER", nullable = true, length = 12)
    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Basic
    @Column(name = "TEL_MODEM", nullable = true, length = 12)
    public String getTelModem() {
        return telModem;
    }

    public void setTelModem(String telModem) {
        this.telModem = telModem;
    }

    @Basic
    @Column(name = "UNIT_NUMBER", nullable = true, length = 4)
    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Basic
    @Column(name = "E_MAIL", nullable = true, length = 45)
    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "VERSION", nullable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }





    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL,
            orphanRemoval=true)
    public Set<Result> getResults() {
        return this.results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        result.setCustomer(this);
        getResults().add(result);
    }

    public void removeResult(Result result) {
        getResults().remove(result);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (telNumber != null ? !telNumber.equals(customer.telNumber) : customer.telNumber != null) return false;
        if (telModem != null ? !telModem.equals(customer.telModem) : customer.telModem != null) return false;
        if (unitNumber != null ? !unitNumber.equals(customer.unitNumber) : customer.unitNumber != null) return false;
        if (eMail != null ? !eMail.equals(customer.eMail) : customer.eMail != null) return false;
        if (version != null ? !version.equals(customer.version) : customer.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (telNumber != null ? telNumber.hashCode() : 0);
        result = 31 * result + (telModem != null ? telModem.hashCode() : 0);
        result = 31 * result + (unitNumber != null ? unitNumber.hashCode() : 0);
        result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
