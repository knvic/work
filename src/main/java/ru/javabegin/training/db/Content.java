package ru.javabegin.training.db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Николай on 19.06.2017.
 */
@Entity
@Table(name = "content")
public class Content implements Serializable {
    private int id;
    private byte[] content;
    private  Contact contact;


    public Content() {
    }



    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONTENT", nullable = true)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }



    @OneToOne
    @JoinColumn(name = "CONTACT_ID")
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Content - Id: " + id + ", Content: " + content;
    }


}
