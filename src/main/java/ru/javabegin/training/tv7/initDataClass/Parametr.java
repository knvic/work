package ru.javabegin.training.tv7.initDataClass;

public class Parametr {
    String name;
    String nameString;
    int addr;
    int size;
    String type;
    String value;


    public Parametr(String name, String nameString, int size, String type) {
        this.name = name;
        this.nameString = nameString;
        this.size = size;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    @Override
    public String toString() {
        return "Parametr{" +
                "name='" + name + '\'' +
                ", nameString='" + nameString + '\'' +
                ", addr=" + addr +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
