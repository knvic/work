package ru.javabegin.training.tv7.recieve;


import java.time.LocalDateTime;

public class Tupel_date {
    private String id;
    private LocalDateTime localDateTime;

    public Tupel_date() {
    }

    public Tupel_date(String id, LocalDateTime localDateTime) {
        this.id = id;
        this.localDateTime = localDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Tupel_date{" +
                "id='" + id + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
