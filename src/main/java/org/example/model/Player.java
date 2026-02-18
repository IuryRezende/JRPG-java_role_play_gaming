package org.example.model;



public abstract class Player{
    protected String name;
    private boolean defending = false;
    private final int bonusDefending = 15;
    private final int defaultLife = 200;
    protected int life;
    protected int strength; // +defense ┃ +life | +damage w/ two hand weapons per strength
    protected int agility;// +dodge | +criticalChance | +chance to deal 2 attack in one turn
    protected int intelligence;// +mana | +debuffResistance (-chance to receive debuff, -rounds receiving debuff, -damage by debuff)|+damage on spells

    protected int defense;
    protected double dodge;
    protected int mana;
    protected double criticalChance;
    protected int debuffResistance;

    protected int maxLife;
    protected int maxMana;



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
        criticalChance = ((double) this.agility * 3)/100;
        debuffResistance = this.intelligence * 5;
    }

    public int getAgility() { return agility; }

    public void setAgility(int agility) { this.agility = agility; }

    public int getIntelligence() { return intelligence; }

    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getStrength() { return strength; }

    public int getDefense() { return defense; }

    public void removeDefending(){
        defense -= bonusDefending;
    }

    public double getDodge() { return dodge; }

    public int getMana() { return mana; }

    public String getName() { return name; }

    public int getDebuffResistance() { return debuffResistance; }

    public double getCriticalChance() { return criticalChance; }

    public void setStrength(int strength) { this.strength = strength; }

    public boolean criticalDamage(){ return Math.random() <= criticalChance;}

    public int getLife() { return life; }

    public int getMaxLife() { return maxLife; }

    public int getMaxMana() { return maxMana; }

    public void takeDamage(int damage) { life -= damage; }

    public abstract void attack(int numOfAbility, Player target);

    public void defense(){
        defending = true;
        defense += bonusDefending;
    }

    public boolean isDefending(){ return defending;}

    public boolean dodge(){ return Math.random() <= dodge;} //dodge it's not an action, it's a passive

    public boolean isLive(){ return life > 0; }

    public final void getStatus(){
        String title = name + " Status";

        StringBuilder sbStatus = new StringBuilder();

        int width = title.length() + 12;
        int padding = (width - title.length())/2;

        sbStatus.append("┏").append("━".repeat(width)).append("┓\n")
                .append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(padding)).append("┃\n")
                .append("┣").append("━".repeat(width)).append("┫\n")
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Life: " + life))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Strength: " + strength))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Agility: " + agility))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Intelligence: " + intelligence))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Defense: " + defense))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Dodge: " + dodge * 100 + "%"))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Mana: " + mana))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Critical Chance: " + criticalChance * 100 + "%"))
                .append(String.format("┃ %-" + (width - 2) + "s ┃%n", "Debuff Resistance: " + debuffResistance))
                .append("┗").append("━".repeat(width)).append("┛");

        System.out.println(sbStatus.toString());

    }

    public abstract void showAbilities();

    public abstract void describeAbilities();
}
