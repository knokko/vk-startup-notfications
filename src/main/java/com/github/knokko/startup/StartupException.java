package com.github.knokko.startup;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ManagementFactory;

public abstract class StartupException extends Exception {

    public final String title;
    public final int width, height;

    public StartupException(String title, int width, int height) {
        super(title);
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public abstract void init(JFrame target);

    public void showNotification() {
        // The -XstartOnFirstThread JVM argument is needed to open native windows on macOS,
        // but also causes the java standard window library to hang.
        if (ManagementFactory.getRuntimeMXBean().getInputArguments().contains("-XstartOnFirstThread")) {
            printStackTrace();
        } else {
            EventQueue.invokeLater(() -> {
                JFrame frame = new JFrame();
                frame.setTitle(title);
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setSize(width, height);
                init(frame);
                frame.setVisible(true);
            });
        }
    }
}
