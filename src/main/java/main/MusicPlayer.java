package main;

import util.Audio;

public class MusicPlayer {

    Audio ps;

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

    private String undergroundSound0URL = "/audio/Underground0.mp3";
    private String undergroundSound1URL = "/audio/Underground1.mp3";
    private String undergroundSound2URL = "/audio/Underground2.mp3";
    private String undergroundSound3URL = "/audio/Underground3.mp3";

    public MusicPlayer() {
        openDoor = creatSoundThread(getClass().getResource(openDoorSoundURL).getPath(), false);
        upAndDown = creatSoundThread(getClass().getResource(upAndDownSoundURL).getPath(), false);
        dialogueSpace = creatSoundThread(getClass().getResource(dialogueSpaceSoundURL).getPath(), false);
        getItem = creatSoundThread(getClass().getResource(getItemSoundURL).getPath(), false);
        getSpecialItem = creatSoundThread(getClass().getResource(getSpecialItemSoundURL).getPath(), false);
        fight = creatSoundThread(getClass().getResource(fightSoundURL).getPath(), false);
        walk = creatSoundThread(getClass().getResource(walkSoundURL).getPath(), false);
        shopSelect = creatSoundThread(getClass().getResource(shopSelectSoundURL).getPath(), false);
        shopBuySuc = creatSoundThread(getClass().getResource(shopBuySucSoundURL).getPath(), false);
        shopBuyFail = creatSoundThread(getClass().getResource(shopBuyFailSoundURL).getPath(), false);

        underground = creatSoundThread(getClass().getResource(undergroundSound0URL).getPath(), true);
    }

    private Thread creatSoundThread(String path, boolean isLoop) {
        ps = new Audio(path, isLoop);
        return (new Thread(ps));
    }

    public void openDoor() {
        openDoor.start();
        openDoor = creatSoundThread(getClass().getResource(openDoorSoundURL).getPath(), false);
    }

    public void upAndDown() {
        upAndDown.start();
        upAndDown = creatSoundThread(getClass().getResource(upAndDownSoundURL).getPath(), false);
    }

    public void dialogueSpace() {
        dialogueSpace.start();
        dialogueSpace = creatSoundThread(getClass().getResource(dialogueSpaceSoundURL).getPath(), false);
    }

    public void getItem() {
        getItem.start();
        getItem = creatSoundThread(getClass().getResource(getItemSoundURL).getPath(), false);
    }

    public void getSpecialItem() {
        getSpecialItem.start();
        getSpecialItem = creatSoundThread(getClass().getResource(getSpecialItemSoundURL).getPath(), false);
    }

    public void fight() {
        fight.start();
        fight = creatSoundThread(getClass().getResource(fightSoundURL).getPath(), false);
    }

    public void walk() {
        walk.start();
        walk = creatSoundThread(getClass().getResource(walkSoundURL).getPath(), false);
    }

    public void shopSelect() {
        shopSelect.start();
        shopSelect = creatSoundThread(getClass().getResource(shopSelectSoundURL).getPath(), false);
    }

    public void shopBuySuc() {
        shopBuySuc.start();
        shopBuySuc = creatSoundThread(getClass().getResource(shopBuySucSoundURL).getPath(), false);
    }

    public void shopBuyFail() {
        shopBuyFail.start();
        shopBuyFail = creatSoundThread(getClass().getResource(shopBuyFailSoundURL).getPath(), false);
    }

    private int musicNo = -1, newMusicNo = 0;

    public void playBackgroundMusic(int floor) {
        if (floor < 2) {
            newMusicNo = 1;
        }
        else if (floor == 2) {
            newMusicNo = 0;
        }
        else if (floor < 10) {
            newMusicNo = 2;
        } else {
            newMusicNo = 3;
        }
        if (musicNo != newMusicNo) {
            underground.stop();
            switch (newMusicNo) {
                case 0:
                    underground = creatSoundThread(getClass().getResource(undergroundSound0URL).getPath(), true);
                    underground.start();
                    break;
                case 1:
                    underground = creatSoundThread(getClass().getResource(undergroundSound1URL).getPath(), true);
                    underground.start();
                    break;
                case 2:
                    underground = creatSoundThread(getClass().getResource(undergroundSound2URL).getPath(), true);
                    underground.start();
                    break;
                case 3:
                    underground = creatSoundThread(getClass().getResource(undergroundSound3URL).getPath(), true);
                    underground.start();
                    break;
            }
            musicNo = newMusicNo;
        }
    }

}
