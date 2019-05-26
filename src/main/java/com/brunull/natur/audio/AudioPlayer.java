package com.brunull.natur.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	public static void playSound(String path) {
	      try {
	    	  Clip clip = AudioSystem.getClip();
	          AudioInputStream inputStream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResourceAsStream(path));
	          clip.open(inputStream);
	          clip.start(); 
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	}
}