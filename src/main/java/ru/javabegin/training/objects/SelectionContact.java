package ru.javabegin.training.objects;

import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.security.MyUsDet;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;


/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class SelectionContact implements Serializable {

private Contact ud;

    public Contact getUd() {
        return ud;
    }

    public void setUd(Contact ud) {
        this.ud = ud;
    }
}
