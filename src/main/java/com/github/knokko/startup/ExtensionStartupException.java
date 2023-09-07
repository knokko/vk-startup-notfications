package com.github.knokko.startup;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.*;

public class ExtensionStartupException extends StartupException {

    private final String description, extensionsWord;
    private final Set<String> availableExtensions, requiredExtensions;

    public ExtensionStartupException(
            String title, String description,
            Set<String> availableExtensions, Set<String> requiredExtensions,
            String extensionsWord
    ) {
        super(title, 800, 600);
        this.description = description;
        this.availableExtensions = availableExtensions;
        this.requiredExtensions = requiredExtensions;
        this.extensionsWord = extensionsWord;
    }

    public ExtensionStartupException(
            String title, String description,
            Set<String> availableExtensions, Set<String> requiredExtensions
    ) {
        this(title, description, availableExtensions, requiredExtensions, "extensions");
    }

    @Override
    public void init(JFrame target) {
        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);

        List<JLabel> availableExtensionLabels = new ArrayList<>(1 + availableExtensions.size());
        availableExtensionLabels.add(new JLabel("Available " + extensionsWord + ":"));
        for (String availableExtension : availableExtensions) {
            availableExtensionLabels.add(new JLabel(availableExtension));
        }

        List<JLabel> requiredExtensionLabels = new ArrayList<>(1 + requiredExtensions.size());
        requiredExtensionLabels.add(new JLabel("Required " + extensionsWord + ":"));
        for (String requiredExtension : requiredExtensions) {
            JLabel label = new JLabel(requiredExtension);
            if (!availableExtensions.contains(requiredExtension)) label.setForeground(Color.RED);
        }

        GroupLayout layout = new GroupLayout(target.getContentPane());
        target.getContentPane().setLayout(layout);

        layout.setAutoCreateContainerGaps(true);;

        GroupLayout.ParallelGroup horizontalGroup = layout.createParallelGroup().addComponent(descriptionArea);
        {
            GroupLayout.ParallelGroup availableExtensionGroup = layout.createParallelGroup();
            availableExtensionLabels.forEach(availableExtensionGroup::addComponent);

            GroupLayout.ParallelGroup requiredExtensionGroup = layout.createParallelGroup();
            requiredExtensionLabels.forEach(requiredExtensionGroup::addComponent);

            GroupLayout.SequentialGroup extensionGroups = layout.createSequentialGroup();
            extensionGroups.addGroup(availableExtensionGroup);
            extensionGroups.addGroup(requiredExtensionGroup);

            horizontalGroup.addGroup(extensionGroups);
        }
        layout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup().addComponent(descriptionArea);
        {
            GroupLayout.SequentialGroup availableExtensionGroup = layout.createSequentialGroup();
            availableExtensionLabels.forEach(availableExtensionGroup::addComponent);

            GroupLayout.SequentialGroup requiredExtensionGroup = layout.createSequentialGroup();
            requiredExtensionLabels.forEach(requiredExtensionGroup::addComponent);

            GroupLayout.ParallelGroup extensionGroups = layout.createParallelGroup();
            extensionGroups.addGroup(availableExtensionGroup);
            extensionGroups.addGroup(requiredExtensionGroup);

            verticalGroup.addGroup(extensionGroups);
        }

        layout.setVerticalGroup(verticalGroup);

        target.pack();
    }
}
