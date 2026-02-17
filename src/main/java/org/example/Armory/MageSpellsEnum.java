package org.example.Armory;


public enum MageSpellsEnum {
    KATCHAU(50, 45, "⚡"),
    FIREBALL(30, 15, "🔥"),
    FREEZE(10, 30, "❄");

    private int damage;
    private int manaCost;
    private String emoji;

    MageSpellsEnum(int damage, int manaCost, String emoji){
        this.damage = damage;
        this.manaCost = manaCost;
        this.emoji = emoji;

    }

    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }

    public String getEmoji() { return emoji; }

    public void setEmoji(String emoji) { this.emoji = emoji; }

    public int getManaCost() { return manaCost; }

    public void setManaCost(int manaCost) { this.manaCost = manaCost; }

}
