package ru.javabegin.training.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.javabegin.training.security.MyUsDet;

import java.io.Serializable;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;




/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class SelectionView implements Serializable {

private MyUsDet ud;

    public MyUsDet getUd() {
        return ud;
    }

    public void setUd(MyUsDet ud) {
        this.ud = ud;
    }
}
