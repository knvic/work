package ru.javabegin.training.vkt7.object;

import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Test;

import javax.faces.bean.ViewScoped;
import java.io.Serializable;


/**
 * Created by Николай on 10.07.2017.
 */
@Component
@ViewScoped
public class SelectionTest implements Serializable {

    public Test getTes() {
        return tes;
    }

    public void setTes(Test tes) {
        this.tes = tes;
    }

    private Test tes;


}



