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
    protected int dodge;
    protected int mana;
    protected int criticalChance;
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
        dodge = agility * 3;
        mana = intelligence * 50;
        criticalChance = agility * 2;
        debuffResistance = intelligence * 5;
    }

    public int getAgility() { return agility; }

    public void setAgility(int agility) { this.agility = agility; }

    public int getIntelligence() { return intelligence; }

    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getStrength() { return strength; }

    public int getDefense() { return defense; }

    public int getDodge() { return dodge; }

    public int getMana() { return mana; }

    public String getName() { return name; }

    public int getDebuffResistance() { return debuffResistance; }

    public int getCriticalChance() { return criticalChance; }

    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public void takeDamage(int damage) { life -= damage; }

    public void attack(Combatent target){}

    public void defense(){}

    public void agility(){}

    @Override
    public boolean isLive(){ return life > 0; }
}
