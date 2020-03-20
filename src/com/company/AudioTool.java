package com.company;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AudioTool extends Component implements Runnable {

    String songPath;

    Clip clip;

    AudioInputStream audioInputStream1;

    SourceDataLine audioLine = null;

    String[] threadList;

    static volatile boolean exit = false;

    public AudioTool(String path) {

        songPath = path;



    }


    @Override
    public void run() {

        try {

            // create AudioInputStream object
            audioInputStream1 =
                    AudioSystem.getAudioInputStream(new File(songPath).getAbsoluteFile());

            // get format
            AudioFormat format = audioInputStream1.getFormat();

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            audioLine = (SourceDataLine) AudioSystem.getLine(info);

            audioLine.open(format);

            audioLine.start();


            int BUFFER_SIZE = 64*1024;

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = audioInputStream1.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }




            AudioTool player = new AudioTool(songPath);

            //player.run();



            if(!audioLine.isRunning()) {
                audioLine.stop();

                audioLine.flush();

                audioLine.drain();


                audioLine.close();
            }



        } catch (Exception ex) {
            System.out.println("Error Playing File");
        }

    }


    public static void getFiles(String folderPath) {

        String temp;
        AudioTool files = null;
        Thread fileThread = null;

        try (Stream<Path> walk = Files.walk(Paths.get(folderPath))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(x -> x.toString()).collect(Collectors.toList());

            for (int i=0; i<result.size(); i++) {


                 temp = result.get(i);

                 files = new AudioTool(temp);

                  fileThread = new Thread(files);

                 fileThread.start();


                System.out.println(files.getName());


            }


            fileThread.run();



        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static File directoryDialog() {
        int result;

        JFileChooser chooser = new JFileChooser("user.home");
        chooser.setDialogTitle("Select Multitrack Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile().getAbsoluteFile();

    }

    public static void stop(){
        exit = true;
    }

    }





