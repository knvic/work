package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 26.09.2017.
 */


@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name="Customer.findAll", query="select c from Customer c")
      /*  @NamedQuery(name="Contact.findById",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id = :id"),
        @NamedQuery(name="Contact.findAllWithDetail",
                query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h")*/
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
    private String unitNumber;
    private String eMail;
    private int version;


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
    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
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


}
