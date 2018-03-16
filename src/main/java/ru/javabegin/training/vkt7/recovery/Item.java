package ru.javabegin.training.vkt7.recovery;


import lombok.Data;
import lombok.Generated;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    private String dir;
}
