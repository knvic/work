package ru.javabegin.training.vkt7.propert.entities;

/**
 * Created by Николай on 21.08.2017.
 */


// определяем корневой элемент

public class ReadyProperties {
    int id;
    String name;
    String text;
    String ed;
    int znak;
    int size;

    public ReadyProperties(){

    }

    public ReadyProperties(int id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
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

    @Override
    public String toString() {
        return "Properts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", ed='" + ed + '\'' +
                ", znak=" + znak +
                ", size=" + size +
                '}';
    }
}
