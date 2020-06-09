package util;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static final Sound walk = new Sound("/audio/walk.wav");
	public static final Sound cell = new Sound("/audio/cell.wav");
	public static final Sound door = new Sound("/audio/door.wav");
	public static final Sound stairs = new Sound("/audio/stairs.wav");
	public static final Sound hit = new Sound("/audio/hit.wav");
	public static final Sound pickup = new Sound("/audio/item.wav");
	public static final Sound select = new Sound("/audio/select.wav");
	public static final Sound buy = new Sound("/audio/buy.wav");
	public static final Sound die = new Sound("/audio/die.wav");
	public static final Sound criticalHit = new Sound("/audio/critical.wav");
	public static final Sound parry = new Sound("/audio/parry.wav");
	
	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				@Override
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
