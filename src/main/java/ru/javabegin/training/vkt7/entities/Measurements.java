package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Николай on 24.09.2017.
 */
@Entity
@Table(name = "measurements")
public class Measurements implements Serializable{
    private int id;
    private String name;
    private String text;
    private String ed;
    private Integer znak;
    private Integer size;
    private String type;
    private Integer measurInt;
    private Float measurFloat;
    private String measurText;
    private String quality;
    private String qualityText;
    private String ns;

    private Result result;

    public Measurements() {
    }

    public Measurements(int id, String name, String text, String ed, Integer znak, Integer size) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.ed = ed;
        this.znak = znak;
        this.size = size;
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
    @Column(name = "NAME", nullable = true, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TEXT", nullable = true, length = 50)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "ED", nullable = true, length = 45)
    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    @Basic
    @Column(name = "ZNAK", nullable = true)
    public Integer getZnak() {
        return znak;
    }

    public void setZnak(Integer znak) {
        this.znak = znak;
    }

    @Basic
    @Column(name = "SIZE", nullable = true)
    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "MEASUR_INT", nullable = true)
    public Integer getMeasurInt() {
        return measurInt;
    }

    public void setMeasurInt(Integer measurInt) {
        this.measurInt = measurInt;
    }

    @Basic
    @Column(name = "MEASUR_FLOAT", nullable = true, precision = 0)
    public Float getMeasurFloat() {
        return measurFloat;
    }

    public void setMeasurFloat(Float measurFloat) {
        this.measurFloat = measurFloat;
    }

    @Basic
    @Column(name = "MEASUR_TEXT", nullable = true, length = 45)
    public String getMeasurText() {
        return measurText;
    }

    public void setMeasurText(String measurText) {
        this.measurText = measurText;
    }

    @Basic
    @Column(name = "QUALITY", nullable = true, length = 45)
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "QUALITY_TEXT", nullable = true, length = 45)
    public String getQualityText() {
        return qualityText;
    }

    public void setQualityText(String qualityText) {
        this.qualityText = qualityText;
    }

    @Basic
    @Column(name = "NS", nullable = true, length = 45)
    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }




    @ManyToOne
    @JoinColumn(name = "RESULT_ID")

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

   /* public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }*/



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurements that = (Measurements) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (ed != null ? !ed.equals(that.ed) : that.ed != null) return false;
        if (znak != null ? !znak.equals(that.znak) : that.znak != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (measurInt != null ? !measurInt.equals(that.measurInt) : that.measurInt != null) return false;
        if (measurFloat != null ? !measurFloat.equals(that.measurFloat) : that.measurFloat != null) return false;
        if (measurText != null ? !measurText.equals(that.measurText) : that.measurText != null) return false;
        if (quality != null ? !quality.equals(that.quality) : that.quality != null) return false;
        if (qualityText != null ? !qualityText.equals(that.qualityText) : that.qualityText != null) return false;
        if (ns != null ? !ns.equals(that.ns) : that.ns != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (ed != null ? ed.hashCode() : 0);
        result = 31 * result + (znak != null ? znak.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (measurInt != null ? measurInt.hashCode() : 0);
        result = 31 * result + (measurFloat != null ? measurFloat.hashCode() : 0);
        result = 31 * result + (measurText != null ? measurText.hashCode() : 0);
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        result = 31 * result + (qualityText != null ? qualityText.hashCode() : 0);
        result = 31 * result + (ns != null ? ns.hashCode() : 0);
        return result;
    }
}
