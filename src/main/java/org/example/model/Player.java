package org.example.model;

import org.example.Interface.Combatent;

public abstract class Player implements Combatent {
    protected String name;
    private int defaultLife = 200;
    protected int life;
    protected int strength; // +defense | +life | +damage w/ two hand weapons per strength
    protected int agility;// +dodge | +criticalChance | +chance to deal 2 attack in one turn
    protected int intelligence;// +mana | +debuffResistance (-chance to receive debuff, -rounds receiving debuff, -damage of debuff)|+damage on spells
    protected int defense;
    protected double dodge;
    protected int mana;
    protected double criticalChance;
    protected int debuffResistance;
    private Combatent combatent;

    public Player() {}

    public Player(String name, int strength, int agility, int intelligence) {
        this.name = name;
        this.strength = strength;
        this.agility += agility;
        this.intelligence += intelligence;
        life = defaultLife + (strength * 20);
        defense = strength * 4;
        dodge = ((double) agility * 2)/100;
        mana = intelligence * 50;
        criticalChance = ((double) agility * 3)/100;
        debuffResistance = intelligence * 5;
    }

    public int getAgility() { return agility; }

    public void setAgility(int agility) { this.agility = agility; }

    public int getIntelligence() { return intelligence; }

    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getStrength() { return strength; }

    public int getDefense() { return defense; }

    public double getDodge() { return dodge; }

    public int getMana() { return mana; }

    public String getName() { return name; }

    public int getDebuffResistance() { return debuffResistance; }

    public double getCriticalChance() { return criticalChance; }

    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public void takeDamage(int damage) { life -= damage; }

    public void attack(Combatent target){}

    public void defense(){}

    public boolean dodge(){ return Math.random() <= dodge;} //dodge não é uma ação, é uma passiva

    @Override
    public boolean isLive(){ return life > 0; }

    public String getSheet(){
        String title = name + " Sheet";

        StringBuilder sbSheet = new StringBuilder();

        int width = title.length() + 12;
        int padding = (width - title.length())/2;

        sbSheet.append("=".repeat(width)).append("\n")
                .append(" ".repeat(padding)).append(title).append(" ".repeat(padding)).append("\n")
                .append("=".repeat(width)).append("\n")
                .append(String.format("| %-" + (width - 4) + "s |%n", "Life: " + life))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Strength: " + strength))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Agility: " + agility))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Intelligence: " + intelligence))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Defense: " + defense))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Dodge: " + dodge * 100 + "%"))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Mana: " + mana))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Critical Chance: " + criticalChance * 100 + "%"))
                .append(String.format("| %-" + (width - 4) + "s |%n", "Debuff Resistance: " + debuffResistance))
                .append("=".repeat(width));

        return sbSheet.toString();

    }

    public String getHabilities(){
        return habilitiesList
    }
}
