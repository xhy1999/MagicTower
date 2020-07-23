package main;

import util.Audio;

import java.net.URL;

public class MusicPlayer {

    Audio audio;

    private Thread openDoor, openSpecialDoor, upAndDown, dialogueSpace, getItem, getSpecialItem, fight, walk, floorTransferSelect, shopSelect, shopBuySuc, shopExpBuySuc, shopBuyFail, underground;

    private String openDoorSoundURL = "/audio/OpenDoor.mp3";
    private String openSpecialDoorSoundURL = "/audio/OpenSpecialDoor.mp3";
    private String upAndDownSoundURL = "/audio/UpAndDown.mp3";
    private String dialogueSpaceSoundURL = "/audio/DialogueSpace.mp3";
    private String getItemSoundURL = "/audio/GetItem.mp3";
    private String getSpecialItemSoundURL = "/audio/GetSpecialItem.mp3";
    private String fightSoundURL = "/audio/Fight.mp3";
    private String walkSoundURL = "/audio/Walk.mp3";
    private String floorTransferSelectSoundURL = "/audio/FloorTransferSelect.mp3";
    private String shopSelectSoundURL = "/audio/ShopSelect.mp3";
    private String shopBuySucSoundURL = "/audio/ShopBuySuc.mp3";
    private String shopExpBuySucSoundURL = "/audio/ShopExpBuySuc.mp3";
    private String shopBuyFailSoundURL = "/audio/ShopBuyFail.mp3";

    private String undergroundSound0URL = "/audio/Underground0.mp3";
    private String undergroundSound1URL = "/audio/Underground1.mp3";
    private String undergroundSound2URL = "/audio/Underground2.mp3";
    private String undergroundSound3URL = "/audio/Underground3.mp3";

    public MusicPlayer() {
        openDoor = creatSoundThread(getClass().getResource(openDoorSoundURL), false);
        openSpecialDoor = creatSoundThread(getClass().getResource(openSpecialDoorSoundURL), false);
        upAndDown = creatSoundThread(getClass().getResource(upAndDownSoundURL), false);
        dialogueSpace = creatSoundThread(getClass().getResource(dialogueSpaceSoundURL), false);
        getItem = creatSoundThread(getClass().getResource(getItemSoundURL), false);
        getSpecialItem = creatSoundThread(getClass().getResource(getSpecialItemSoundURL), false);
        fight = creatSoundThread(getClass().getResource(fightSoundURL), false);
        walk = creatSoundThread(getClass().getResource(walkSoundURL), false);
        floorTransferSelect = creatSoundThread(getClass().getResource(floorTransferSelectSoundURL), false);
        shopSelect = creatSoundThread(getClass().getResource(shopSelectSoundURL), false);
        shopBuySuc = creatSoundThread(getClass().getResource(shopBuySucSoundURL), false);
        shopExpBuySuc = creatSoundThread(getClass().getResource(shopExpBuySucSoundURL), false);
        shopBuyFail = creatSoundThread(getClass().getResource(shopBuyFailSoundURL), false);

        underground = creatSoundThread(getClass().getResource(undergroundSound0URL), true);
    }

    private Thread creatSoundThread(URL path, boolean isLoop) {
        audio = new Audio(path, isLoop);
        return (new Thread(audio));
    }

    public void openDoor() {
        openDoor.start();
        openDoor = creatSoundThread(getClass().getResource(openDoorSoundURL), false);
    }

    public void openSpecialDoor() {
        openSpecialDoor.start();
        openSpecialDoor = creatSoundThread(getClass().getResource(openSpecialDoorSoundURL), false);
    }

    public void upAndDown() {
        upAndDown.start();
        upAndDown = creatSoundThread(getClass().getResource(upAndDownSoundURL), false);
    }

    public void dialogueSpace() {
        dialogueSpace.start();
        dialogueSpace = creatSoundThread(getClass().getResource(dialogueSpaceSoundURL), false);
    }

    public void getItem() {
        getItem.start();
        getItem = creatSoundThread(getClass().getResource(getItemSoundURL), false);
    }

    public void getSpecialItem() {
        getSpecialItem.start();
        getSpecialItem = creatSoundThread(getClass().getResource(getSpecialItemSoundURL), false);
    }

    public void fight() {
        fight.start();
        fight = creatSoundThread(getClass().getResource(fightSoundURL), false);
    }

    public void walk() {
        walk.start();
        walk = creatSoundThread(getClass().getResource(walkSoundURL), false);
    }

    public void floorTransferSelect() {
        floorTransferSelect.start();
        floorTransferSelect = creatSoundThread(getClass().getResource(floorTransferSelectSoundURL), false);
    }

    public void shopSelect() {
        shopSelect.start();
        shopSelect = creatSoundThread(getClass().getResource(shopSelectSoundURL), false);
    }

    public void shopBuySuc() {
        shopBuySuc.start();
        shopBuySuc = creatSoundThread(getClass().getResource(shopBuySucSoundURL), false);
    }

    public void shopExpBuySuc() {
        shopExpBuySuc.start();
        shopExpBuySuc = creatSoundThread(getClass().getResource(shopExpBuySucSoundURL), false);
    }

    public void shopBuyFail() {
        shopBuyFail.start();
        shopBuyFail = creatSoundThread(getClass().getResource(shopBuyFailSoundURL), false);
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
            //注意! 这里只能使用stop(),不能使用interrupt()
            underground.stop();
            switch (newMusicNo) {
                case 0:
                    underground = creatSoundThread(getClass().getResource(undergroundSound0URL), true);
                    underground.start();
                    break;
                case 1:
                    underground = creatSoundThread(getClass().getResource(undergroundSound1URL), true);
                    underground.start();
                    break;
                case 2:
                    underground = creatSoundThread(getClass().getResource(undergroundSound2URL), true);
                    underground.start();
                    break;
                case 3:
                    underground = creatSoundThread(getClass().getResource(undergroundSound3URL), true);
                    underground.start();
                    break;
            }
            musicNo = newMusicNo;
        }
    }

}
