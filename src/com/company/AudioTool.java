package com.company;

import com.sun.jdi.event.ThreadStartEvent;
import com.sun.jdi.request.ThreadStartRequest;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AudioTool implements Runnable {

    String songPath;

    Clip clip;

    AudioInputStream audioInputStream1;

    public AudioTool(String path) {

        songPath = path;


    }


    @Override
    public void run() {

        try {

            // create AudioInputStream object
            audioInputStream1 =
                    AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile());

            // create clip reference
            AudioFormat format = audioInputStream1.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);

            audioLine.open(format);

            audioLine.start();

            int BUFFER_SIZE = 4096;

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = audioInputStream1.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }

            audioLine.drain();

            audioLine.close();

            audioInputStream1.close();


            AudioTool player = new AudioTool(songPath);
            player.run();


        } catch (Exception ex) {
            System.out.println("Error Playing File");
        }

    }


    public static void getFiles(String folderPath) {

        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());

            for (int i=0; i<result.size(); i++) {
                System.out.println(result.get(i));

                 String temp = result.get(i);

                 AudioTool files = new AudioTool(temp);

                 Thread thread = new Thread(files);

                 thread.start();



            }










        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }










}