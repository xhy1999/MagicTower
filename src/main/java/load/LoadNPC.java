package load;

import entity.Dialogue;
import entity.NPC;
import entity.Stair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadNPC {

    //01-elf:精灵
    //02-elder:老者
    //03-businessman:商人
    //04-thief:小偷
    //05-princess:公主

    public Map<String, NPC> initNPC() {
        Map<String, NPC> npcMap = new HashMap<>();

        List<Dialogue> dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "......"));
        dialogues.add(new Dialogue("elf", "你醒啦!"));
        dialogues.add(new Dialogue("player", "......你是谁,我在哪里?"));
        dialogues.add(new Dialogue("elf", "我是这里的仙子,刚才你被这里的小怪打晕了。"));
        dialogues.add(new Dialogue("player", "......剑,剑,我的剑呢?"));
        dialogues.add(new Dialogue("elf", "你的剑被他们抢走了,我只来得及将你救出来。"));
        dialogues.add(new Dialogue("player", "那,公主呢?我是来救公主的。"));
        dialogues.add(new Dialogue("elf", "公主还在里面,你这样进去是打不过小怪的。"));
        dialogues.add(new Dialogue("player", "那,我怎么办?我答应了国王一定要把公主救出来的,那我现在应该怎么办呢?"));
        dialogues.add(new Dialogue("elf", "放心吧,我把我的力量借给你,你就可以打赢那些小怪了。不过,你得先帮我去找一样东西,找到"));
        dialogues.add(new Dialogue("elf", "了再来这里找我。"));
        dialogues.add(new Dialogue("player", "找东西?找什么东西?"));
        dialogues.add(new Dialogue("elf", "是一个十字架,中间有一颗红色的宝石。"));
        dialogues.add(new Dialogue("player", "那个东西有什么用吗?"));
        dialogues.add(new Dialogue("elf", "我本是这座塔的守护者,可不久前,从北方来了一批恶魔,他们占领了这座塔,并将我的魔力封印"));
        dialogues.add(new Dialogue("elf", "在这个十字架里面,如果你能将它带出塔来,那我的魔力便会慢慢地恢复,到那时候,我便可以把"));
        dialogues.add(new Dialogue("elf", "力量借给你去救公主了。"));
        dialogues.add(new Dialogue("elf", "要记住:只有用我的魔力才可以打开21层的门。"));
        dialogues.add(new Dialogue("player", "......好吧,我试试看。"));
        dialogues.add(new Dialogue("elf", "刚才我去看过了,你的剑被放在3楼,你的盾在5楼上,那个十字架被放在7楼。要到7楼,你得先取"));
        dialogues.add(new Dialogue("elf", "回你的剑和盾。另外,在塔里的其他楼层上,还存放了好几百年的宝剑和宝物,如果得到他们,对"));
        dialogues.add(new Dialogue("elf", "于你对付里面的怪物将有很大的帮助。"));
        dialogues.add(new Dialogue("player", "可是,我怎么进去呢。"));
        dialogues.add(new Dialogue("elf", "我这里有3把钥匙,你先拿去,在塔里面还有很多这样的钥匙,你一定要珍惜使用。"));
        dialogues.add(new Dialogue("elf", "勇敢的去吧,勇士!"));

        NPC npc = new NPC("npc01_1", "仙子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc01_2", "仙子", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

         npc = new NPC("npc02_1", "老者", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc03_1", "商人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc04_1", "小偷", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc04_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc04_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc05_1", "公主", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc05_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc05_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        return npcMap;
    }

}
