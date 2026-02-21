package org.example.Armory;

public enum AssassinWeaponsEnum implements ArmoryInterface{
    DOUBLE_DAGGERS(50, 0,30,"🪓", " hacked with an AXE"),
    POISONED_KNIFE(100, 0, 70,"🔨", " slammed with a HAMMER"),
    PUNCH(30, 0, 15, "👊", " punched 3 times");

    private final int damage;
    private final int manaCost;
    private final int staminaCost;
    private final String emoji;
    private final String hitDescription;

    AssassinWeaponsEnum(int damage, int manaCost, int staminaCost, String emoji, String hitDescription){
        this.damage = damage;
        this.manaCost = manaCost;
        this.staminaCost = staminaCost;
        this.emoji = emoji;
        this.hitDescription = hitDescription;
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

    public String getHitDescription() { return hitDescription + " " + emoji; }
}
