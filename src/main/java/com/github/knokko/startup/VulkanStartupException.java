package com.github.knokko.startup;

import java.util.ArrayList;
import java.util.List;

public class VulkanStartupException extends SimpleStartupException {

    private static List<String> createDescription(String message) {
        List<String> description = new ArrayList<>(2);
        description.add(message);
        description.add("This is probably a programming error");
        return description;
    }

    public VulkanStartupException(RuntimeException vulkanFailureException) {
        super(
                "A Vulkan function call returned an error result code",
                createDescription(vulkanFailureException.getMessage())
        );
    }
}
