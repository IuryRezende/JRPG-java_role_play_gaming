package org.example.Armory;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BarbarianWeaponsEnum implements ArmoryInterface{
    AXE(50, 0,30,"🪓"),
    HAMMER(100, 0, 70,"🔥"),
    PUNCH(20, 0, 30, "❄");

    private final int damage;
    private final int manaCost;
    private final int staminaCost;
    private final String emoji;
    private List<BarbarianWeaponsEnum> weaponsEnumList = new ArrayList<>(Arrays.asList(BarbarianWeaponsEnum.values()));

    BarbarianWeaponsEnum(int damage, int manaCost, int staminaCost, String emoji){
        this.damage = damage;
        this.manaCost = manaCost;
        this.staminaCost = staminaCost;
        this.emoji = emoji;

    }

    @Override
    public String getName() {return name(); }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getEmoji() { return emoji; }

    @Override
    public int getStaminaCost() { return staminaCost; }

    @Override
    public int getManaCost() { return manaCost; }

    public BarbarianWeaponsEnum getAbilityPerIndex(int index){
        return weaponsEnumList.get(index);
    }

}
