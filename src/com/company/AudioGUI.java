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
    public JSlider slider2;
    public JSlider slider3;
    public JSlider slider4;
    public JSlider slider5;
    public JSlider slider6;
    public JSlider slider7;
    public JSlider slider8;
    public JSlider slider9;
    public JSlider slider10;
    public JSlider slider11;
    public JSlider slider12;
    public JSlider slider13;
    public JSlider slider14;
    public JSlider slider15;
    public JSlider slider16;
    public JSlider slider17;
    public JSlider slider18;
    public JSlider slider19;
    public JSlider slider20;
    public JSlider slider21;
    public JSlider slider22;
    public JSlider slider23;
    public JSlider slider24;
    public JSlider slider25;
    public JSlider slider26;
    public JTextField textField2;
    public JTextField textField3;
    public JTextField textField4;
    public JTextField textField5;
    public JTextField textField6;
    public JTextField textField7;
    public JTextField textField8;
    public JTextField textField9;
    public JTextField textField10;
    public JTextField textField11;
    public JTextField textField12;
    public JTextField textField13;
    public JTextField textField14;
    public JTextField textField15;
    public JTextField textField16;
    public JTextField textField17;
    public JTextField textField18;
    public JTextField textField19;
    public JTextField textField20;
    public JTextField textField21;
    public JTextField textField22;
    public JTextField textField23;
    public JTextField textField24;
    public JTextField textField25;
    public JTextField textField26;
    public JTextField textField27;
    public JButton muteButton;
    public JButton muteButton1;
    public JButton muteButton4;
    public JButton muteButton5;
    public JButton muteButton7;
    public JButton restartSongButton;
    public JPanel multitrackGuiPanel;
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
