package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 26.09.2017.
 */


@Entity
@Table(name = "test")
@NamedQueries({
        @NamedQuery(name="Test.findAll", query="select c from Test c")

})
@SqlResultSetMapping(
        name="testResult",
        entities=@EntityResult(entityClass=Test.class)
)
public class Test implements Serializable{

    private Long id;
    private String text;


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

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
