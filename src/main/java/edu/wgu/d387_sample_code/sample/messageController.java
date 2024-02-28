package edu.wgu.d387_sample_code.sample
        ;

import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/welcome")
public class messageController {


    @GetMapping("/messages")
    public CompletableFuture<String[]> loadMessages() {
        CompletableFuture<String[]> future = new CompletableFuture<>();

        CompletableFuture.runAsync(() -> {
            String[] messagesEnUS = loadMessagesFromFile("welcome_en_US.properties");
            String[] messagesFrCA = loadMessagesFromFile("welcome_fr_CA.properties");
            String[] combinedMessages = new String[]{messagesEnUS[0], messagesFrCA[0]};
            future.complete(combinedMessages);
        });

        return future;
    }

    private String[] loadMessagesFromFile(String fileName) {
        try {
            Properties properties = new Properties();
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (stream != null) {
                properties.load(stream);
                String welcomeMessage = properties.getProperty("welcomeMessage");
                return new String[]{welcomeMessage};
            } else {
                return new String[]{"Error loading messages from " + fileName};
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"Error loading messages from " + fileName};
        }
    }
}
