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
    //06-monster:怪物

    public Map<String, NPC> initNPC() {
        Map<String, NPC> npcMap = new HashMap<>();

        List<Dialogue> dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b......"));
        dialogues.add(new Dialogue("elf", "\b你醒啦!"));
        dialogues.add(new Dialogue("player", "\b......你是谁,我在哪里?"));
        dialogues.add(new Dialogue("elf", "\b我是这里的仙子,刚才你被这里的小怪打晕了。"));
        dialogues.add(new Dialogue("player", "\b......剑,剑,我的剑呢?"));
        dialogues.add(new Dialogue("elf", "\b你的剑被他们抢走了,我只来得及将你救出来。"));
        dialogues.add(new Dialogue("player", "\b那,公主呢?我是来救公主的。"));
        dialogues.add(new Dialogue("elf", "\b公主还在里面,你这样进去是打不过小怪的。"));
        dialogues.add(new Dialogue("player", "\b那,我怎么办?我答应了国王一定要把公主救出来的,那我现在应该怎么办呢?"));
        dialogues.add(new Dialogue("elf", "\b放心吧,我把我的力量借给你,你就可以打赢那些小怪了。不过,你得先帮我去找一样东西,找到"));
        dialogues.add(new Dialogue("elf", "了再来这里找我。"));
        dialogues.add(new Dialogue("player", "\b找东西?找什么东西?"));
        dialogues.add(new Dialogue("elf", "\b是一个十字架,中间有一颗红色的宝石。"));
        dialogues.add(new Dialogue("player", "\b那个东西有什么用吗?"));
        dialogues.add(new Dialogue("elf", "\b我本是这座塔的守护者,可不久前,从北方来了一批恶魔,他们占领了这座塔,并将我的魔力封"));
        dialogues.add(new Dialogue("elf", "印在这个十字架里面,如果你能将它带出塔来,那我的魔力便会慢慢地恢复,到那时候,我便可以"));
        dialogues.add(new Dialogue("elf", "把力量借给你去救公主了。"));
        dialogues.add(new Dialogue("elf", "\b要记住:只有用我的魔力才可以打开21层的门。"));
        dialogues.add(new Dialogue("player", "\b......好吧,我试试看。"));
        dialogues.add(new Dialogue("elf", "\b刚才我去看过了,你的剑被放在3楼,你的盾在5楼上,那个十字架被放在7楼。要到7楼,你得先"));
        dialogues.add(new Dialogue("elf", "取回你的剑和盾。另外,在塔里的其他楼层上,还存放了好几百年的宝剑和宝物,如果得到他们,"));
        dialogues.add(new Dialogue("elf", "对于你对付里面的怪物将有很大的帮助。"));
        dialogues.add(new Dialogue("player", "\b可是,我怎么进去呢。"));
        dialogues.add(new Dialogue("elf", "\b我这里有3把钥匙,你先拿去,在塔里面还有很多这样的钥匙,你一定要珍惜使用。"));
        dialogues.add(new Dialogue("elf", "\b勇敢的去吧,勇士!"));

        NPC npc = new NPC("npc01_1_1", "仙子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b仙子,我已经将那个十字架找到了。"));
        dialogues.add(new Dialogue("elf", "\b你做的很好。那么,现在我就开始授予你更强的力量!\n...咪啦哆咪哗!"));
        dialogues.add(new Dialogue("elf", "\b好了,我已经将你现在的能量提升了!不过你要记住:如果你没有足够的实力的话,不要去21层!"));
        dialogues.add(new Dialogue("elf", "\b在那一层里,你所有的宝物的法力都会失去作用!"));
        npc = new NPC("npc01_1_2", "仙子", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("elf", "\b嗯?你手里的那个东西是什么?"));
        dialogues.add(new Dialogue("player", "\b这个?这是一个老人交给我的,他是叫我带它来找你的。他说你知道它的来历和作用。"));
        dialogues.add(new Dialogue("elf", "\b这个东西是仙界的圣物,名叫\"灵之杖\",是很久以前的一个圣者留下的。"));
        dialogues.add(new Dialogue("elf", "\b他们一共有三个,分别镶着红、绿、蓝三种颜色的宝石。"));
        dialogues.add(new Dialogue("elf", "\b你现在拿着的是一个镶有蓝宝石的\"冰之灵杖\",应该还有一个镶有绿宝石的\"心之灵杖\"和镶有"));
        dialogues.add(new Dialogue("elf", "红宝石的\"炎之灵杖\"。"));
        dialogues.add(new Dialogue("elf", "\b在这座塔的下面,封印着一只魔界的世兽,名叫\"血影\",这三把\"灵之杖\"就是封印的钥匙。"));
        dialogues.add(new Dialogue("player", "\b封印钥匙?"));
        dialogues.add(new Dialogue("elf", "\b每一个\"灵之杖\"里面都有着很强的魔法力量,如果被恶魔得到了将会使它的力量增倍。"));
        dialogues.add(new Dialogue("elf", "\b如果被恶魔将它们三个找齐的话,\"血影\"的封印便会解除!"));
        dialogues.add(new Dialogue("elf", "\b勇士,你还是快去把我要的东西找来吧,等我恢复了魔力,我就可以帮你将\"灵之杖\"中的魔力都"));
        dialogues.add(new Dialogue("elf", "开放出来!"));
        npc = new NPC("npc01_1_3", "仙子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b仙子,我已经将那个十字架找到了。"));
        dialogues.add(new Dialogue("elf", "\b你做的很好。那么,现在我就开始授予你更强的力量!\n...咪啦哆咪哗!"));
        dialogues.add(new Dialogue("elf", "\b好了,我已经将你现在的能量提升了!不过你要记住:如果你没有足够的实力的话,不要去21层!"));
        dialogues.add(new Dialogue("elf", "\b在那一层里,你所有的宝物的法力都会失去作用!"));
        dialogues.add(new Dialogue("elf", "\b快走吧,杀死魔王后,来第22层上找我!"));
        npc = new NPC("npc01_1_4", "仙子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("elf", "\b做得很好。现在你已经将那个可恶的冥灵魔王给消灭了,快去找另外两个'灵杖'吧,找齐了之"));
        dialogues.add(new Dialogue("elf", "后再来找我!"));
        dialogues.add(new Dialogue("elf", "\b要记住,如果我不把封印解开的话,最底层的怪物你是杀不了的!"));
        npc = new NPC("npc01_1_5", "仙子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b快看,我全部都找到了,我找齐左右灵杖了!"));
        dialogues.add(new Dialogue("elf", "\b嗯,不错,现在我们可以解除这里面的封印了!\n\b那就让我们开始吧!"));
        dialogues.add(new Dialogue("elf", "\b神之灵杖呀,放射出你们的魔力吧!\n\b哈哩咪哆唏咪啦~~~~~"));
        dialogues.add(new Dialogue("player", "\b...(又来了)"));
        dialogues.add(new Dialogue("elf", "\b...好了,我已经将他们三个灵之杖的魔力都开放出来了!"));
        dialogues.add(new Dialogue("elf", "\b公主就由我来就出去,你快去最底层杀了那个大魔头吧!要记住,如果没有万分的把握,一定不"));
        dialogues.add(new Dialogue("elf", "要去进入最后的传送门,一旦进去了,在杀死大魔头之前你将不能再回来!"));
        dialogues.add(new Dialogue("player", "\b好的,我明白了!"));
        npc = new NPC("npc01_1_6", "仙子", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc01_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc01_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc02_0", "老者", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b您已经得救了!"));
        dialogues.add(new Dialogue("elder", "\b哦,我的孩子,真是太感谢你了!"));
        dialogues.add(new Dialogue("elder", "\b这个地方又脏又坏,我真的是快呆不下去了。"));
        dialogues.add(new Dialogue("player", "\b快走吧,我还得去救被关在这里的公主。"));
        dialogues.add(new Dialogue("elder", "\b哦,你是来救公主的,为了表示对你的感谢,这个东西就送给你吧,这还是我年轻时候用过的。"));
        dialogues.add(new Dialogue("elder", "\b拿着它去解救公主吧!"));
        npc = new NPC("npc02_1", "老者", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("elder", "\b你好,勇敢的孩子,你终于来到这里了。"));
        dialogues.add(new Dialogue("elder", "\b我将给你一个非常好的宝物,它可以使你的攻击力提升120点,但你必须用500点经验来交换。"));
        dialogues.add(new Dialogue("elder", "\b考虑一下吧!"));
        npc = new NPC("npc02_2_1", "神秘老人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b我考虑好了,那就将那把剑给我吧!"));
        dialogues.add(new Dialogue("elder", "\b那好吧,这把剑就给你了!"));
        npc = new NPC("npc02_2_2", "神秘老人", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("elder", "\b年轻人,你终于来了!"));
        dialogues.add(new Dialogue("player", "\b您怎么了?"));
        dialogues.add(new Dialogue("elder", "\b我已经快封不住它了,请你将这个东西交给彩蝶仙子,他会告诉你这是什么东西,有什么用的!"));
        dialogues.add(new Dialogue("elder", "\b快去吧,再迟就来不及了!"));
        npc = new NPC("npc02_3", "神秘老人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("elder", "\b看到那个疯子了吗,据说他有不可告人的秘密!"));
        dialogues.add(new Dialogue("player", "\b...???\n\b您还是快走吧,这里危险!"));
        dialogues.add(new Dialogue("elder", "\b等等,你胸前这个是圣光徽吗?"));
        dialogues.add(new Dialogue("player", "\b是这个吗?"));
        dialogues.add(new Dialogue("elder", "\b对对对,我肯定不会看错。你可以按下\bD\b来用它查看怪物信息!"));
        dialogues.add(new Dialogue("player", "\b明白了,谢谢!"));
        npc = new NPC("npc02_4", "老者", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc02_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc02_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        npc = new NPC("npc03_0", "商人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b您已经得救了!"));
        dialogues.add(new Dialogue("businessman", "\b哦,是吗!真是太感谢你了!"));
        dialogues.add(new Dialogue("businessman", "\b我是个商人,不知为什么被抓到这里来了。"));
        dialogues.add(new Dialogue("player", "\b快走吧,现在您已经自由了。"));
        dialogues.add(new Dialogue("businessman", "\b哦,对对对,我已经自由了。"));
        dialogues.add(new Dialogue("businessman", "\b那这个东西就给你吧,本来我是准备卖钱的。"));
        dialogues.add(new Dialogue("businessman", "\b相信它对你一定很有帮助!"));
        npc = new NPC("npc03_1", "商人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("businessman", "\b啊哈,欢迎你的到来!"));
        dialogues.add(new Dialogue("businessman", "\b我这里有一件对你来说非常好的宝物,只要你出得起钱,我就卖给你。"));
        dialogues.add(new Dialogue("player", "\b什么宝物?要多少钱?"));
        dialogues.add(new Dialogue("businessman", "\b是这个游戏里最好的盾牌,防御值可以增加120点,而你只要出500个金币就可以买下。"));
        dialogues.add(new Dialogue("businessman", "\b怎么样?你有500个金币吗?"));
        npc = new NPC("npc03_2_1", "商人", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b我有500个金币。"));
        dialogues.add(new Dialogue("businessman", "\b好,成交!"));
        npc = new NPC("npc03_2_2", "商人", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("businessman", "\b又是挑战者吗?\n\b希望你能够活着回去。\n\b嘻嘻嘻嘻..."));
        npc = new NPC("npc03_3", "奇怪的人", true, false,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("businessman", "\b挑战者,你好!我是这座塔的复刻者Vip、疯子,在这里你可以看到这座塔的所有道具与怪物。"));
        dialogues.add(new Dialogue("businessman", "\b如果你准备回去继续挑战,就来找我吧!"));
        dialogues.add(new Dialogue("player", "\b好的!"));
        npc = new NPC("npc03_4_1", "Vip、疯子", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b我想继续挑战,请把我送回去吧!"));
        dialogues.add(new Dialogue("businessman", "\b好的,坐稳喽!"));
        npc = new NPC("npc03_4_2", "Vip、疯子", true, false,
                new ImageIcon(getClass().getResource("/image/npc/npc03_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc03_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b你已经得救了!"));
        dialogues.add(new Dialogue("thief", "\b啊,那真是太好了,我又可以在这里面寻宝了!"));
        dialogues.add(new Dialogue("thief", "\b哦,还没有自我介绍,我叫杰克,是这附近有名的小偷,什么金银财宝我样样都偷过。"));
        dialogues.add(new Dialogue("thief", "\b不过这次运气可不是太好,刚进来就被抓了。现在你帮我打开了门,那我就帮你做一件事吧。"));
        dialogues.add(new Dialogue("player", "\b快走吧,外面还有很多怪物,我可能顾不上你。"));
        dialogues.add(new Dialogue("thief", "\b不,不,不会有事的。快说吧,叫我做什么?"));
        dialogues.add(new Dialogue("player", "\b......你会开门吗?"));
        dialogues.add(new Dialogue("thief", "\b那当然。"));
        dialogues.add(new Dialogue("player", "\b那就请你帮我打开第二层的门吧!"));
        dialogues.add(new Dialogue("thief", "\b那个简单,不过,如果你能帮我找到嵌了红宝石的铁锒头的话,我还能帮你打通第18层的路。"));
        dialogues.add(new Dialogue("player", "\b嵌了红宝石的铁锒头?好吧,我帮你找找。"));
        dialogues.add(new Dialogue("thief", "\b非常的感谢。一会儿我便会将第二层的门打开。如果你找到那个铁锒头的话,还是来这里找我!"));

        npc = new NPC("npc04_1", "小偷", true, true,
                new ImageIcon(getClass().getResource("/image/npc/npc04_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc04_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b哈,快看,我找到了什么!"));
        dialogues.add(new Dialogue("thief", "\b太好了,这东西果然在这里。\n\b好吧,我这就帮你去修好第18层的路面。"));
        npc = new NPC("npc04_2", "小偷", false, true,
                new ImageIcon(getClass().getResource("/image/npc/npc04_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc04_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b公主!你得救了!"));
        dialogues.add(new Dialogue("princess", "\b啊,你是来救我的吗?"));
        dialogues.add(new Dialogue("player", "\b是的,我奉国王的命令来救你的。请你快随我出去吧!"));
        dialogues.add(new Dialogue("princess", "\b不,我还不想走。"));
        dialogues.add(new Dialogue("player", "\b为什么?这里面到处都是恶魔。"));
        dialogues.add(new Dialogue("princess", "\b正是因为这里面到处都是恶魔,所以才不可以就这样出去,我要看着那个恶魔被杀死!"));
        dialogues.add(new Dialogue("princess", "\b英雄的勇士,如果你能将那个大恶魔杀死,我就和你一起出去!"));
        dialogues.add(new Dialogue("player", "\b大恶魔?我已经杀死了一个魔王!"));
        dialogues.add(new Dialogue("princess", "\b大恶魔就是这座塔的最顶层,你杀死的可能是一个小队长之类的恶魔。"));
        dialogues.add(new Dialogue("player", "\b好,那你等着,等我杀了那个恶魔再来这里找你!"));
        dialogues.add(new Dialogue("princess", "\b大恶魔比你刚才杀死的那个厉害多了。而且他还会变身,变身后的魔王的攻击力和防御力都会"));
        dialogues.add(new Dialogue("princess", "提升至少一半以上,你得小心!请一定要杀死大魔王!"));
        npc = new NPC("npc05_1", "公主", true, false,
                new ImageIcon(getClass().getResource("/image/npc/npc05_1.png")),
                new ImageIcon(getClass().getResource("/image/npc/npc05_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b放弃吧!愚蠢的人类!"));
        dialogues.add(new Dialogue("player", "\b该放弃的是你!魔王。快说,公主关在哪里?"));
        dialogues.add(new Dialogue("monster", "\b等你打赢我再说吧!"));
        npc = new NPC("npc06_1_1", "红衣魔王", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_4_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("player", "\b大魔头,你的死期到了!"));
        dialogues.add(new Dialogue("monster", "\b哈哈哈...你也真是有意思,别以为蝶仙给了你力量你就可以打败我,想打败我你还早着呢!"));
        dialogues.add(new Dialogue("player", "\b废话少说,去死吧!"));
        npc = new NPC("npc06_2_1", "大魔王·格勒第", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b看不出你还有两下子,有本事的话来21楼。在那里,你就可以见识到我真正的实力了!"));
        npc = new NPC("npc06_2_2", "大魔王·格勒第", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b啊...怎么可能,我怎么可能会被你打败呢!\n\b主人,救...救救我!"));
        dialogues.add(new Dialogue("monster", "\b不,不要这样...(随后,一团黑色迷雾吞噬了他)"));
        npc = new NPC("npc06_2_3", "大魔王·格勒第", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster10_15_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b哈哈哈,就凭你还想打败我?\n\b看我影分身之术!"));
        npc = new NPC("npc07_1_1", "血影", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b不!!!我怎么会被区区一个勇士消灭!!!!!!!!!"));
        npc = new NPC("npc07_1_2", "血影", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster11_8_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b哈哈哈,就凭你还想打败我?\n\b看我影分身之术!"));
        npc = new NPC("npc07_2_1", "魔龙", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        dialogues = new ArrayList<>();
        dialogues.add(new Dialogue("monster", "\b不!!!我怎么会被区区一个勇士消灭!!!!!!!!!"));
        npc = new NPC("npc07_2_2", "魔龙", true, true,
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_1.png")),
                new ImageIcon(getClass().getResource("/image/monster/monster12_8_2.png")),
                dialogues);
        npcMap.put(npc.getId(), npc);

        return npcMap;
    }

}
