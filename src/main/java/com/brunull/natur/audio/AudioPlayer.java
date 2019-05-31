package com.brunull.natur.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	private static HashMap<String, Clip> cache = new HashMap<String, Clip>();
	
	//private static Clip clip;
	
	public static void playSound(String path, boolean loop) {
	      try {
	    	  
	    	  Clip clip = null;
	    	  
	    	  if (cache.containsKey(path)) {
	    		  clip = cache.get(path);
	    	  } else {
	    	  
	    		  clip = AudioSystem.getClip();
	    		  
	    		  InputStream is = AudioPlayer.class.getResourceAsStream(path);
	    		  BufferedInputStream bis = new BufferedInputStream(is);	    	  
	    		  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
		    	  
		          clip.open(audioInputStream);
		          
		          cache.put(path, clip);
		          
		          System.out.println("DDDDDDDDD");
	    	  }
	    	   
    		  clip.setFramePosition(0);
	          clip.start();
	          
	          if (loop)
	        	  clip.loop(Clip.LOOP_CONTINUOUSLY);
	          
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
	
	public static void stop(String clipToStop) {
		cache.get(clipToStop).stop();
	}
}