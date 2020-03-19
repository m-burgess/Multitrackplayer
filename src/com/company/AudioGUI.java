package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

public class AudioGUI {
    public JButton setDirectoryButton;
    public JTextField directoryTextField;
    public JList songList;
    public JSlider slider1;
    public JTextField textField2;
    public JPanel multitrackGuiPanel;
    private JButton muteButton;
    private JButton restartSongButton;
    private JScrollBar horizontalScrollBar;

    public AudioGUI() {
        setDirectoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String directory = AudioTool.directoryDialog().toString();
                directoryTextField.setText(directory);
                File path = new File(directoryTextField.getText());
                String multitrackFolders[] = path.list();
                for(int i=0; i<multitrackFolders.length; i++) {
                    String songs = multitrackFolders[i].replace("Multitrack-", " ");
                    songList.setListData(multitrackFolders);
                }

            }
        });


        songList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {

                if(!listSelectionEvent.getValueIsAdjusting()) {

                    System.out.println(directoryTextField.getText() + "\\" + songList.getSelectedValue());
                    AudioTool.getFiles(directoryTextField.getText() + "\\" + songList.getSelectedValue());
                }
            }
        });
    }


}
