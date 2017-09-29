package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 26.09.2017.
 */


@Entity
@Table(name = "test")
@NamedQueries({
        @NamedQuery(name="Test.findAll", query="select c from Test c"),
        @NamedQuery(name="Test.findById",
                query="select distinct c from Test c where c.id = :id")

})
@SqlResultSetMapping(
        name="testResult",
        entities=@EntityResult(entityClass=Test.class)
)
public class Test implements Serializable{

    private Long id;
    private String text;
    private Timestamp servetDate;



    private Integer version;



    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Column(name = "SERVER_DATE")
    public Timestamp getServetDate() {
        return servetDate;
    }

    public void setServetDate(Timestamp servetDate) {
        this.servetDate = servetDate;
    }
    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
