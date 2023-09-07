package com.github.knokko.startup;

import javax.swing.*;
import java.util.List;

public class SimpleStartupException extends StartupException {

    private final List<String> description;

    public SimpleStartupException(String title, List<String> description) {
        super(title, 600, 200);
        this.description = description;
    }

    @Override
    public void init(JFrame target) {
        StringBuilder text = new StringBuilder();
        for (int index = 0; index < description.size() - 1; index++) {
            text.append(description.get(index));
            text.append("\r\n");
        }
        if (description.size() > 0) text.append(description.get(description.size() - 1));

        JTextArea textArea = new JTextArea(text.toString());
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        target.add(textArea);
    }
}
