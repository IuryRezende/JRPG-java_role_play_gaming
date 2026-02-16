package org.example.Classes;


import org.example.Armory.MageSpellsEnum;
import org.example.model.Player;

import java.util.*;


public class MageClass extends Player {

    private final int defaultIntelligence = 2;
    private final List<MageSpellsEnum> spellsList = new ArrayList<>(Arrays.asList(MageSpellsEnum.values()));

    private int calcDamage(int damage, int enemyDefense){ return enemyDefense >= damage ? 0 : damage - enemyDefense; };

    private boolean checkIfHasMana(int manaCost){ return mana >= manaCost; }


    public MageClass(){};


    public MageClass(String name, int strength, int agility, int intelligence) {
        super(name, strength, agility, intelligence);
        this.intelligence += defaultIntelligence;
    }

    public void attack(int numOfSpell, Player player){
        Map<String, String> spell = Map.of(
                "name", spellsList.get(numOfSpell - 1).name(),
                "emoji", spellsList.get(numOfSpell - 1).getEmoji(),
                "manaCost", String.valueOf(spellsList.get(numOfSpell - 1).getManaCost()),
                "damage", String.valueOf(spellsList.get(numOfSpell - 1).getDamage())
        );

        try {
            if (!checkIfHasMana(Integer.parseInt(spell.get("manaCost")))){
                throw new RuntimeException("Not enough mana to conjure this spell❌");
            }
            int damage = calcDamage(Integer.parseInt(spell.get("damage")), player.getDefense());
            player.takeDamage(damage);
            System.out.println("\n=============================================\n"
                    + this.getName()
                    + " conjured "
                    + spell.get("name")
                    + " "
                    + spell.get("emoji")
                    + "\n"
                    + player.getName()
                    + " suffered: "
                    + damage
                    + " points of damage"
                    + "\n=============================================\n");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

}
