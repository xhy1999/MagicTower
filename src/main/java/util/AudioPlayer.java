package util;

import org.newdawn.easyogg.OggClip;

import java.io.IOException;

/**
 * @author Xhy
 */
public class AudioPlayer {

    int lastFloor = 1, lastRange = 0;
    
    private OggClip[] sounds;
    
    public AudioPlayer() {
    	try {
    		sounds = new OggClip[3];
            sounds[0] = new OggClip(this.getClass().getResourceAsStream("/audio/Undergrounds.ogg"));
			sounds[1] = new OggClip(this.getClass().getResourceAsStream("/audio/HometownDomina.ogg"));
			sounds[2] = new OggClip(this.getClass().getResourceAsStream("/audio/HometownDomina.ogg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void startBackgroundMusic(int floor) {
    	int floorRange = 0;
        if(floor > 0 && floor <= 16) {
        	floorRange = 1;
        }
        if(floor <= 0) {
        	floorRange = 0;
        }
        if(floor > 16) {
        	floorRange = 2;
        }
        
        if (floorRange != lastRange) {
        	sounds[lastRange].stop();
        	sounds[floorRange].loop();
        }
        lastRange = floorRange;
        lastFloor = floor;
    }
}
