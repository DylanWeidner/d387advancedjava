package edu.wgu.d387_sample_code;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;


@Service
public class MessageService {

    private static final ExecutorService messageExecutor = newFixedThreadPool(2);
    private final List<String> messages = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void loadMessages() {
        messageExecutor.execute(() -> loadProperties("languages_en_CA.properties"));
        messageExecutor.execute(() -> loadProperties("languages_fr_CA.properties"));
    }

    private void loadProperties(String resourcePath) {
        Properties properties = new Properties();
        try (InputStream stream = new ClassPathResource(resourcePath).getInputStream()) {
            properties.load(stream);
            String message = properties.getProperty("hello") + " " + properties.getProperty("welcome");
            messages.add(message);
            // the below print statements Show that 2 separate threads are running Asynchronously
            System.out.println(messages);
            System.out.println("Current Thread: " + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        messageExecutor.shutdown();
    }

    public List<String> getMessages() {
        return this.messages;
    }
}

