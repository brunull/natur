package com.brunull.natur.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class AudioPlayer {
	
	private static Clip clip;
	
	public static void playSound(String path) {
	      try {
	    	  clip = AudioSystem.getClip();
	    	  AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream(path));
	          clip.open(inputStream);
	          clip.start(); 
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	
	public static void stop() {
		clip.stop();
	}
}