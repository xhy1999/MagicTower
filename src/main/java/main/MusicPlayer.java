package main;

import util.PlaySounds;

import java.io.InputStream;

public class MusicPlayer {

    PlaySounds ps;

    private Thread openDoor, upAndDown, dialogueSpace, getItem, getSpecialItem, fight, walk, shopSelect, shopBuySuc, shopBuyFail, underground;

    private String openDoorSoundURL = "/audio/OpenDoor.mp3";
    private String upAndDownSoundURL = "/audio/UpAndDown.mp3";
    private String dialogueSpaceSoundURL = "/audio/DialogueSpace.mp3";
    private String getItemSoundURL = "/audio/GetItem.mp3";
    private String getSpecialItemSoundURL = "/audio/GetSpecialItem.mp3";
    private String fightSoundURL = "/audio/Fight.mp3";
    private String walkSoundURL = "/audio/Walk.mp3";
    private String shopSelectSoundURL = "/audio/ShopSelect.mp3";
    private String shopBuySucSoundURL = "/audio/ShopBuySuc.mp3";
    private String shopBuyFailSoundURL = "/audio/ShopBuyFail.mp3";

    private String undergroundSoundURL = "/audio/Underground.mp3";

    public MusicPlayer() {
        openDoor = creatSoundThread(this.getClass().getResourceAsStream(openDoorSoundURL));
        upAndDown = creatSoundThread(this.getClass().getResourceAsStream(upAndDownSoundURL));
        dialogueSpace = creatSoundThread(this.getClass().getResourceAsStream(dialogueSpaceSoundURL));
        getItem = creatSoundThread(this.getClass().getResourceAsStream(getItemSoundURL));
        getSpecialItem = creatSoundThread(this.getClass().getResourceAsStream(getSpecialItemSoundURL));
        fight = creatSoundThread(this.getClass().getResourceAsStream(fightSoundURL));
        walk = creatSoundThread(this.getClass().getResourceAsStream(walkSoundURL));
        shopSelect = creatSoundThread(this.getClass().getResourceAsStream(shopSelectSoundURL));
        shopBuySuc = creatSoundThread(this.getClass().getResourceAsStream(shopBuySucSoundURL));
        shopBuyFail = creatSoundThread(this.getClass().getResourceAsStream(shopBuyFailSoundURL));

        underground = creatSoundThread(this.getClass().getResourceAsStream(undergroundSoundURL));
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

    public void getSpecialItem() {
        getSpecialItem.start();
        getSpecialItem = creatSoundThread(this.getClass().getResourceAsStream(getSpecialItemSoundURL));
    }

    public void fight() {
        fight.start();
        fight = creatSoundThread(this.getClass().getResourceAsStream(fightSoundURL));
    }

    public void walk() {
        walk.start();
        walk = creatSoundThread(this.getClass().getResourceAsStream(walkSoundURL));
    }

    public void shopSelect() {
        shopSelect.start();
        shopSelect = creatSoundThread(this.getClass().getResourceAsStream(shopSelectSoundURL));
    }

    public void shopBuySuc() {
        shopBuySuc.start();
        shopBuySuc = creatSoundThread(this.getClass().getResourceAsStream(shopBuySucSoundURL));
    }

    public void shopBuyFail() {
        shopBuyFail.start();
        shopBuyFail = creatSoundThread(this.getClass().getResourceAsStream(shopBuyFailSoundURL));
    }

    public void underground() {
        underground.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!underground.isAlive()) {
                        underground = creatSoundThread(this.getClass().getResourceAsStream(undergroundSoundURL));
                        underground.start();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
