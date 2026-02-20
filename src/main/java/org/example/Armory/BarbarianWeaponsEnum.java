package org.example.Armory;


public enum BarbarianWeaponsEnum implements ArmoryInterface{
    AXE(50, 0,30,"🪓"),
    HAMMER(100, 0, 70,"🔥"),
    PUNCH(20, 0, 30, "❄");

    private final int damage;
    private final int manaCost;
    private final int staminaCost;
    private final String emoji;

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

}
