package entity;

public class FightCalc {

    private Monster monster;
    private Player player;
    public boolean canAttack;
    public int mDamageTotal;

    /**
     * 玩家伤害临界值
     */
    private short playerCriticalVal = 20;

    public FightCalc(Player player, Monster monster) {
        this.player = player;
        this.monster = monster;
        if (!monster.hostile) {
            return;
        }
        int mHP = monster.getHp();
        int pHP = player.hp;
        int mDamage;
        //魔法师系的怪物攻击玩家为真实伤害
        if (monster.getId().contains("monster04")) {
            mDamage = monster.getAttack();
        } else {
            mDamage = monster.getAttack() - player.defense;
        }
        int pDamage = player.attack - monster.getDefense();
        //-20为怪物伤害临界值
        if (mDamage <= 0 && mDamage > -20) {
            mDamage = 1;
        } else if (mDamage <= -20) {
            mDamage = 0;
        }
        if (pDamage <= 0 && pDamage > -playerCriticalVal) {
            pDamage = 1;
        } else if (pDamage <= -20) {
            pDamage = 0;
        }
        //System.out.println("怪物攻击一次的伤害:" + mDamage);
        //System.out.println("玩家攻击一次的伤害:" + pDamage);
        if (pDamage <= 0) {
            return;
        }
        /**
         * 战斗结果计算 默认玩家先攻
         */
        short pAttackNo = 0;
        short attackNo = 0;
        int pDamageTotal = 0;
        int mDamageTotal = 0;
        while (mHP > 0 && pHP > 0) {
            if (attackNo % 2 == 0) {
                pAttackNo++;
                mHP -= pDamage;
                pDamageTotal += pDamage;
            } else {
                pHP -= mDamage;
                mDamageTotal += mDamage;
            }
            attackNo++;
        }
        //System.out.println("玩家攻击次数:" + pAttackNo);
        //System.out.println("怪物攻击次数:" + (attackNo - pAttackNo));
        this.mDamageTotal = mDamageTotal;
        if (mDamageTotal < player.hp) {
            canAttack = true;
        }
    }

    public Monster getMonster() {
        return monster;
    }

}
