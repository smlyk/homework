package com.smlyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yekai
 */
@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaDemoApplication.class, args);

        Producer producer = applicationContext.getBean(Producer.class);

        for (int i=0;i<3;i++){
            producer.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
