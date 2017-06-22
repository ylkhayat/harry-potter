package harrypotter.controller;

import sun.audio.*;

import java.awt.BorderLayout;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Sound
{
	private JFrame frame;
	private AudioInputStream ais;
	private Clip clip;
	public Sound(String fileName) throws LineUnavailableException, UnsupportedAudioFileException, IOException, InvocationTargetException, InterruptedException{
        clip = AudioSystem.getClip();
        ais = AudioSystem.getAudioInputStream( new File(fileName));
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        SwingUtilities.invokeLater(new Runnable() {
			
        	public void run() {
                    try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                frame = new JFrame();
            }
		});
    }
	public void stop()
	{
		clip.stop();
	}
	public JFrame getFrame() {
		return frame;
	}
	public static void main(String[] args) {
		try {
			Sound x = new Sound("C:/Users/Youssef/workspace/GUITrial/src/harrypotter.wav");
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
