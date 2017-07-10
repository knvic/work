package ru.javabegin.training.objects;

/**
 * Created by Николай on 10.07.2017.
 */

import org.springframework.stereotype.Component;

@Component
public class ListenerView {
    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void handleKeyEvent() {
        text = text.toUpperCase();
    }
}
