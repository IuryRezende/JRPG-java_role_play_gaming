package org.example.Classes;


import org.example.Armory.MageSpellsEnum;
import org.example.model.Player;

import java.util.*;


public class MageClass extends Player {

    private final List<MageSpellsEnum> spellsList = new ArrayList<>(Arrays.asList(MageSpellsEnum.values()));

    private int calcDamage(int damage, int enemyDefense){ return enemyDefense >= damage ? 0 : damage - enemyDefense; };

    private boolean checkIfHasMana(int manaCost){ return mana >= manaCost; }


    public MageClass(){};


    public MageClass(String name, int strength, int agility, int intelligence) {
        super(name, strength, agility, intelligence);
        int defaultIntelligence = 2;
        this.intelligence += defaultIntelligence;

    }

    public void attack(int numOfAbility, Player target){
        Map<String, String> spell = Map.of(
                "name", spellsList.get(numOfAbility - 1).name(),
                "emoji", spellsList.get(numOfAbility - 1).getEmoji(),
                "manaCost", String.valueOf(spellsList.get(numOfAbility - 1).getManaCost()),
                "damage", String.valueOf(spellsList.get(numOfAbility - 1).getDamage())
        );

        try {
            if (!checkIfHasMana(Integer.parseInt(spell.get("manaCost")))){
                throw new RuntimeException("Not enough mana to conjure this spell❌");
            }
            int damage = calcDamage(Integer.parseInt(spell.get("damage")), target.getDefense());
            target.takeDamage(damage);
            System.out.println("\n=============================================\n"
                    + this.getName()
                    + " conjured "
                    + spell.get("name")
                    + " "
                    + spell.get("emoji")
                    + "\n"
                    + target.getName()
                    + " suffered: "
                    + damage
                    + " points of damage"
                    + "\n=============================================\n");
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getAbilities() {
        StringBuilder abilities = new StringBuilder();
        for (MageSpellsEnum spell : spellsList){
            abilities.append(spell.name()).append(" | ");
        }
        return abilities.toString();
    }


}
