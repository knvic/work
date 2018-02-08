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
    private Long idCount;

    private Operation operation;

    public Measurements() {
    }

    public Measurements(int id, String name, String text, String ed, Integer znak, Integer size,String type) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.ed = ed;
        this.znak = znak;
        this.size = size;
        this.type = type;
            }

    public Measurements(int id, String name, String text, String measurText, String quality, String qualityText, String ns) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.measurText = measurText;
        this.quality = quality;
        this.qualityText = qualityText;
        this.ns = ns;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_COUNT", nullable = false)
    public Long getIdCount() {
        return idCount;
    }

    public void setIdCount(Long idCount) {
        this.idCount = idCount;
    }

    @Basic
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
    @JoinColumn(name = "OPERATION_ID")

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

   /* public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }*/

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
                ", measurInt=" + measurInt +
                ", measurFloat=" + measurFloat +
                ", measurText='" + measurText + '\'' +
                ", quality='" + quality + '\'' +
                ", qualityText='" + qualityText + '\'' +
                ", ns='" + ns + '\'' +
                ", idCount=" + idCount +
                ", operation=" + operation +
                '}';
    }
}
