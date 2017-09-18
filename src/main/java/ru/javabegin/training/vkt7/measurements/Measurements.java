package ru.javabegin.training.vkt7.measurements;

/**
 * Created by Николай on 21.08.2017.
 */


// определяем корневой элемент

public class Measurements {
    int id;
    String name;
    String text;
    String ed;
    int znak;
    int size;
    String type;
    int measur_int;
    float measur_float;
    String measur_text;
    String quality;
    String quality_text;
    String ns;

    public Measurements(){

    }

    public Measurements(int id, String name, String text, String ed, int znak, int size) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.ed = ed;
        this.znak = znak;
        this.size = size;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMeasur_int() {
        return measur_int;
    }

    public void setMeasur_int(int measur_int) {
        this.measur_int = measur_int;
    }

    public float getMeasur_float() {
        return measur_float;
    }

    public void setMeasur_float(float measur_float) {
        this.measur_float = measur_float;
    }

    public String getMeasur_text() {
        return measur_text;
    }

    public void setMeasur_text(String measur_text) {
        this.measur_text = measur_text;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getQuality_text() {
        return quality_text;
    }

    public void setQuality_text(String quality_text) {
        this.quality_text = quality_text;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    @Override
    public String toString() {
        return "Measurements{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", ed='" + ed + '\'' +
                ", znak=" + znak +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", measur_int=" + measur_int +
                ", measur_float=" + measur_float +
                ", measur_text='" + measur_text + '\'' +
                ", quality='" + quality + '\'' +
                ", quality_text='" + quality_text + '\'' +
                ", ns='" + ns + '\'' +
                '}';
    }
}
