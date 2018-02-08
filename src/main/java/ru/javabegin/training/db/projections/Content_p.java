package ru.javabegin.training.db.projections;

import java.io.Serializable;

/**
 * Created by Николай on 08.06.2017.
 */
public class Content_p implements Serializable {
    String contact_id;

    public Content_p(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getContact_id() {

        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    @Override
    public String toString() {
        return "ID contact for content: " +  contact_id;
    }


}
