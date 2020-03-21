package com.company;



import javax.swing.*;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;

import java.awt.*;

import java.awt.event.*;

import java.io.File;



public class AudioGUI {

    public JButton setDirectoryButton;

    public JTextField directoryTextField;

    public JList songList;

    public JSlider slider1;

    public JTextField textField2;

    public JPanel multitrackGuiPanel;

    public JButton muteButton;

    public JButton restartSongButton;

    public JTextField inst1;

    private JButton playButton;

    public JScrollBar horizontalScrollBar;



    public AudioGUI() {

        setDirectoryButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent actionEvent) {



                String directory = AudioTool.directoryDialog().toString();



                directoryTextField.setText(directory);



                File path = new File(directoryTextField.getText());



                String multitrackFolders[] = path.list();



                for(int i=0; i<multitrackFolders.length; i++) {

                    songList.setListData(multitrackFolders);

                }



                inst1.setText("Test");



            }

        });





        playButton.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                SwingWorker swl = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        AudioTool player = new AudioTool(directoryTextField.getText()  + "\\" + songList.getSelectedValue());

                        player.getFiles(directoryTextField.getText()  + "\\" + songList.getSelectedValue());

                        return null;
                    }
                };

                swl.execute();



            }

        });

        muteButton.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {

                super.mouseClicked(e);

                muteButton.setBackground(Color.red);

            }

        });



    }





}
