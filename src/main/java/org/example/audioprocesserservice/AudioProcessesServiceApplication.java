package org.example.audioprocesserservice;

import org.example.audioprocesserservice.aws.S3clientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import software.amazon.awssdk.services.s3.S3Client;

@SpringBootApplication
public class AudioProcessesServiceApplication implements ApplicationRunner {

    @Autowired
    S3clientService clientService;

    public static void main(String[] args) {
        SpringApplication.run(AudioProcessesServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("list of files");
        System.out.println(clientService.listFiles());

    }
}
