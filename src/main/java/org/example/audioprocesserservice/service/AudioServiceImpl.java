package org.example.audioprocesserservice.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.example.audioprocesserservice.aws.S3clientService;
import org.example.audioprocesserservice.util.AudioUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Yohannes k Yimam
 */
@Service
public class AudioServiceImpl implements AudioService {

  private final Logger logger = LoggerFactory.getLogger(AudioServiceImpl.class);

  private final S3clientService s3clientService;

  public AudioServiceImpl(S3clientService s3clientService) {
    this.s3clientService = s3clientService;
  }

  public String convertAudioToM3u8(String filename) throws IOException {

    File sourceFile = s3clientService.downloadFile(filename);

    String folderName = "";

    if (filename.split("\\.").length > 0) {
      folderName = filename.split("\\.")[0];
    }

    // create temp output location
    File outputFile = Files.createTempDirectory("output_" + folderName).toFile();
    // transcode the audio file
    AudioUtil.transcodeToM3u8(sourceFile, outputFile);

    // create folder in s3 bucket
    var s3folderName = s3clientService.createFolder(folderName);
    // upload to s3 bucket
    s3clientService.uploadFolderToS3(outputFile, s3folderName);

    // delete temp files
    deleteTempFolder(outputFile);
    deleteTempFolder(sourceFile);
    //        return location
    return s3folderName + "output.m3u8";
  }

  private void deleteTempFolder(File fine) {
    boolean delete = fine.delete();
    if (delete) {
      logger.info("Delete temp folder: {}", fine);
    } else {
      logger.error("Delete temp folder on successful");
    }
  }
}
