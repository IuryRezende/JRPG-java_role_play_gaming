package org.example.Armory;


public enum MageSpellsEnum implements ArmoryInterface{
    KATCHAU(50, 60, 0,"⚡", " electrified with KATCHAU"),
    FIREBALL(30, 50, 0,"🔥", " burned with FIREBALL"),
    FREEZE(10, 30, 0,"❄", " freezed with FREEZE");

    private final int damage;
    private final int manaCost;
    private final int staminaCost;
    private final String emoji;
    private final String hitDescription;

    MageSpellsEnum(int damage, int manaCost, int staminaCost, String emoji, String hitDescription){
        this.damage = damage;
        this.manaCost = manaCost;
        this.staminaCost = staminaCost;
        this.emoji = emoji;
        this.hitDescription = hitDescription;

    }

    @Override
    public String getName() { return name(); }

    @Override
    public int getDamage() { return damage; }

    @Override
    public String getEmoji() { return emoji; }

    @Override
    public int getStaminaCost() { return staminaCost; }

    @Override
    public int getManaCost() { return manaCost; }

    public String getHitDescription() { return hitDescription + " " + emoji; }
}
