package com.brunull.natur.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	private static Clip clip;
	
	public static void playSound(String path, boolean loop) {
	      try {
	    	  clip = AudioSystem.getClip();
	    	  
	    	  InputStream is = AudioPlayer.class.getResourceAsStream(path);
	    	  InputStream bis = new BufferedInputStream(is);	    	  
	    	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
	    	  
	          clip.open(audioInputStream);
	          clip.start();
	          
	          if (loop)
	        	  clip.loop(Clip.LOOP_CONTINUOUSLY);
	          
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	
	public static void stop() {
		clip.stop();
	}
}