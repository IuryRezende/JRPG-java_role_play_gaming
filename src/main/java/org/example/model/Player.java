package org.example.model;


import org.example.Armory.ArmoryInterface;
import org.example.Exceptions.AttackMissed;
import org.example.Exceptions.InsufficientMana;
import org.example.Exceptions.InsufficientStamina;
import org.example.SystemTools.SystemCombat;

import static org.example.SystemTools.SystemCombat.awaitEnter;
import static org.example.SystemTools.SystemCombat.getAvaliableSpace;

public abstract class Player{
    private String name;
    protected boolean defending;
    protected final int bonusDefending = 15;
    private final int defaultLife = 200;
    private int life;
    private int strength; // +defense ┃ +life | +damage w/ two hand weapons per strength
    private int agility;// +dodge | +criticalChance | +chance to deal 2 attack in one turn
    private int intelligence;// +mana | +debuffResistance (-chance to receive debuff, -rounds receiving debuff, -damage by debuff)|+damage on spells

    private int defense;
    private double dodge;
    private int mana;
    private int stamina;
    private double criticalChance;
    private int debuffResistance;

    private int maxLife;
    private int maxMana;
    private int maxStamina;
    protected int staminaRecover;
    protected int manaRecover;



    public Player() {}

    public Player(String name, int strength, int agility, int intelligence) {
        this.name = name;
        this.strength = strength;
        this.agility += agility;
        this.intelligence += intelligence;
        life = defaultLife + (this.strength * 20);
        maxLife = life;
        defense += this.strength * 4;
        dodge = ((double) this.agility * 3)/100;
        mana = this.intelligence * 50;
        maxMana = mana;
        manaRecover = (this.intelligence/2) * 20;
        stamina = (this.strength * 20) + (this.agility * 25);
        maxStamina = stamina;
        staminaRecover = (this.agility/2) * 20;
        criticalChance = ((double) this.agility * 3)/100;
        debuffResistance = this.intelligence * 5;
    }

    public int getLife() { return life; }

    public int getMaxLife() { return maxLife; }

    public int getAgility() { return agility; }

    public int getIntelligence() { return intelligence; }

    public int getStrength() { return strength; }

    public int getDefense() { return defense; }

    public void setDefense(int defense) { this.defense = defense; }

    public double getDodge() { return dodge; }

    public int getMana() { return mana; }

    public void setMana(int mana) { this.mana = mana; }

    public int getMaxMana() { return maxMana; }

    public int getStamina() { return stamina; }

    public void setStamina(int stamina) { this.stamina = stamina; }

    public int getMaxStamina() { return maxStamina; }

    public String getName() { return name; }

    public int getDebuffResistance() { return debuffResistance; }

    public double getCriticalChance() { return criticalChance; }

    public boolean criticalDamage(){ return Math.random() <= criticalChance;}


    public void takeDamage(int damage) { life -= damage; }

    public void attack(int numOfAbility, Player target){
         ArmoryInterface weapon = getAbility(numOfAbility - 1);

        try{
            dealDamage(weapon, target);

        } catch (InsufficientMana | AttackMissed e){
            System.out.println("━".repeat(50));
            System.out.println(e.getMessage());
            System.out.println("━".repeat(50));
            awaitEnter();
        }

    }

    public abstract void defense();

    public void removeDefending(){
        defending = false;
        defense -= bonusDefending;
    }

    public boolean isDefending(){ return defending;}

    public boolean dodge(){ return Math.random() <= dodge;} //dodge it's not an action, it's a passive

    public boolean isLive(){ return life > 0; }

    protected abstract void recoverStatus();

    protected void consumeMana(int manaCost){
        if (checkIfHasMana(manaCost)){
            mana -= manaCost;
        } else {
            throw new InsufficientMana("Not enough mana to conjure this spell❌");
        }
    }

    protected void consumeStamina(int staminaCost){
        if (checkIfHasStamina(staminaCost)){
            stamina -= staminaCost;
        } else {
            throw new InsufficientStamina("Not enough stamina to realize this attack❌");
        }
    }

    protected boolean checkIfHasMana(int manaCost){ return mana >= manaCost; }

    protected boolean checkIfHasStamina(int staminaCost){ return stamina >= staminaCost; }

    protected abstract int calcDamage(ArmoryInterface weapon, int enemyDefense);

    protected void dealDamage(ArmoryInterface weapon, Player target){
        consumeStamina(weapon.getStaminaCost());
        consumeMana(weapon.getManaCost());

        if (target.dodge()){
            throw new AttackMissed(target.getName(), this.getName());
        }

        int damage = calcDamage(weapon, target.getDefense());

        target.takeDamage(damage);

        System.out.println("\n"+"━".repeat(60) +"\n"
                + " ".repeat(5) + this.getName()
                + weapon.getHitDescription()
                + "\n"
                + " ".repeat(5) + target.getName()
                + " suffered: "
                + damage
                + " points of damage"
                + "\n"+"━".repeat(60) +"\n");
        awaitEnter();
    }

    public final void getStatus(){
        String title = name + " Status";

        StringBuilder sbStatus = new StringBuilder();

        int width = 28;
        int padding = (width - title.length())/2;

        sbStatus.append("┏").append("━".repeat(width)).append("┓\n")
                .append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(getAvaliableSpace(title) % 2 == 0 ? padding + 1 : padding)).append("┃\n")
                .append("┣").append("━".repeat(width)).append("┫\n")
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Class: " + getClass().getSimpleName().replace("Class", "")))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Life: " + life))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Strength: " + strength))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Agility: " + agility))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Intelligence: " + intelligence))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Defense: " + defense))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Dodge: " + dodge * 100 + "%"))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Mana: " + mana))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Stamina: " + stamina))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Critical Chance: " + criticalChance * 100 + "%"))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Debuff Resistance: " + debuffResistance))
                .append("┗").append("━".repeat(width)).append("┛");

        System.out.println(sbStatus.toString());

    }

    public abstract ArmoryInterface getAbility(int index);

    public abstract void showAbilities();

    public abstract void describeAbilities();

    public abstract int getWeaponListLength();

}
