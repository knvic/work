package ru.javabegin.training.vkt7.propert.entities;

/**
 * Created by Николай on 21.08.2017.
 */


// определяем корневой элемент

public class Properts {
    int id;
    String name;
    String text;
    String ed;
    int id_ed;
    int id_znak;
    int znak;
    int size;



    String type;

    public Properts(){

    }

    public Properts(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }


    public Properts(int id, String name, String text, int id_ed,int id_znak, String type) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.id_ed = id_ed;
        this.id_znak = id_znak;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public int getId_ed() {
        return id_ed;
    }

    public void setId_ed(int id_ed) {
        this.id_ed = id_ed;
    }

    public int getId_znak() {
        return id_znak;
    }

    public void setId_znak(int id_znak) {
        this.id_znak = id_znak;
    }

    public int getZnak() {
        return znak;
    }

    public void setZnak(int znak) {
        this.znak = znak;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Properts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", ed='" + ed + '\'' +
                ", id_ed=" + id_ed +
                ", id_znak=" + id_znak +
                ", znak=" + znak +
                ", size=" + size +
                ", type='" + type + '\'' +
                '}';
    }
}
