package org.mow.it.now.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MowItNowBatchApplication {
    public static void main(String[] args) {
        new SpringApplication(MowItNowBatchApplication.class).run(args);
    }
}
