package org.example.audioprocesserservice;

import org.example.audioprocesserservice.aws.S3clientService;
import org.example.audioprocesserservice.service.AudioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AudioProcessesServiceApplication implements ApplicationRunner {

  @Autowired S3clientService clientService;
  @Autowired AudioServiceImpl audioService;

  public static void main(String[] args) {
    SpringApplication.run(AudioProcessesServiceApplication.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    var loc = audioService.convertAudioToM3u8("input_2.mp3");

    System.out.println("resigned location: " + clientService.generateResignedUrl(loc));

    //        System.out.println("list of files");
    //        System.out.println(clientService.listFiles());

  }
}
