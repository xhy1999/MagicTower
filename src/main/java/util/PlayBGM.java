package util;

import java.io.InputStream;

/**
 * @author Xhy
 */
public class PlayBGM extends PlaySounds {

    public PlayBGM(InputStream input) {
        super(input);
    }

    @Override
    public synchronized void run() {
        try {
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
