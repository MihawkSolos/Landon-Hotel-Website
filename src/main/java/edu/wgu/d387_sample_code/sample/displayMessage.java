package edu.wgu.d387_sample_code.sample;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class displayMessage {

    static ExecutorService messageExecutor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        String[] messages = new String[2];

        messageExecutor.execute(() -> {
            messages[0] = loadMessages("welcome_en_US.properties");
        });

        messageExecutor.execute(() -> {
            messages[1] = loadMessages("welcome_fr_CA.properties");
        });

        // Shutdown the executor to prevent new tasks from being submitted
        messageExecutor.shutdown();

        // Wait for both tasks to complete
        while (!messageExecutor.isTerminated()) {
            // Wait
        }

        // Print messages
        for (String message : messages) {
            System.out.println(message);
        }
    }

    public static String loadMessages(String fileName) {
        try {
            Properties properties = new Properties();
            InputStream stream = new ClassPathResource(fileName).getInputStream();
            properties.load(stream);
            return properties.getProperty("welcomeMessage");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
