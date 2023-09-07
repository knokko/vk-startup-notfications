package playground;

import com.github.knokko.startup.ExtensionStartupException;

import java.util.HashSet;
import java.util.Set;

public class TryExtensionStartupException {

    public static void main(String[] args) {
        Set<String> availableExtensions = new HashSet<>();
        availableExtensions.add("silly");
        availableExtensions.add("superfluous");
        availableExtensions.add("important");

        Set<String> requiredExtensions = new HashSet<>();
        requiredExtensions.add("important");
        requiredExtensions.add("missing");

        new ExtensionStartupException(
                "Missing some kind of extension",
                "An important extension is missing :(",
                availableExtensions, requiredExtensions,
                "extension"
        ).showNotification();
    }
}
