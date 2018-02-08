package ru.javabegin.training.objects;

import org.springframework.stereotype.Component;

/**
 * Created by Николай on 16.06.2017.
 */
@Component
public class BasicView {

    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
