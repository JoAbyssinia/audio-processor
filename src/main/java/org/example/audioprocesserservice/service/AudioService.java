package org.example.audioprocesserservice.service;

import java.io.IOException;

/**
 * @author Yohannes k Yimam
 */
public interface AudioService {

    void convertAudioToM3u8(String filename) throws IOException;

}
