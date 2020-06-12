package main;

import util.PlaySounds;

import java.io.InputStream;

public class MusicPlayer {

    PlaySounds ps;

    private Thread openDoor, upAndDown, dialogueSpace, getItem, fight, walk;

    private String openDoorSoundURL = "/audio/OpenDoor.mp3";
    private String upAndDownSoundURL = "/audio/UpAndDown.mp3";
    private String dialogueSpaceSoundURL = "/audio/DialogueSpace.mp3";
    private String getItemSoundURL = "/audio/GetItem.mp3";
    private String fightSoundURL = "/audio/Fight.mp3";
    private String walkSoundURL = "/audio/Walk.mp3";

    public MusicPlayer() {
        openDoor = creatSoundThread(this.getClass().getResourceAsStream(openDoorSoundURL));
        upAndDown = creatSoundThread(this.getClass().getResourceAsStream(upAndDownSoundURL));
        dialogueSpace = creatSoundThread(this.getClass().getResourceAsStream(dialogueSpaceSoundURL));
        getItem = creatSoundThread(this.getClass().getResourceAsStream(getItemSoundURL));
        fight = creatSoundThread(this.getClass().getResourceAsStream(fightSoundURL));
        walk = creatSoundThread(this.getClass().getResourceAsStream(walkSoundURL));
    }

    private Thread creatSoundThread(InputStream sound) {
        ps = new PlaySounds(sound);
        return (new Thread(ps));
    }

    public void openDoor() {
        openDoor.start();
        openDoor = creatSoundThread(this.getClass().getResourceAsStream(openDoorSoundURL));
    }

    public void upAndDown() {
        upAndDown.start();
        upAndDown = creatSoundThread(this.getClass().getResourceAsStream(upAndDownSoundURL));
    }

    public void dialogueSpace() {
        dialogueSpace.start();
        dialogueSpace = creatSoundThread(this.getClass().getResourceAsStream(dialogueSpaceSoundURL));
    }

    public void getItem() {
        getItem.start();
        getItem = creatSoundThread(this.getClass().getResourceAsStream(getItemSoundURL));
    }

    public void fight() {
        fight.start();
        fight = creatSoundThread(this.getClass().getResourceAsStream(fightSoundURL));
    }

    public void walk() {
        walk.start();
        walk = creatSoundThread(this.getClass().getResourceAsStream(walkSoundURL));
    }

}
