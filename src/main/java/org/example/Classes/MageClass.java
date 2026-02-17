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
    public void describeAbilities(){

        String title = "Description";
        StringBuilder titleSb = new StringBuilder();
        StringBuilder topBorder = new StringBuilder();
        StringBuilder bottomBorder = new StringBuilder();
        int width = 50;
        int sectionWidth = width / spellsList.size();
        int padding = (width - title.length())/2;


        topBorder.append("┏").append("━".repeat(width)).append("┓\n");
        titleSb.append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(padding)).append(" ┃\n");

        bottomBorder.append("┣");
        for (int i = 0; i < spellsList.size(); i++){
            bottomBorder.append("━".repeat(sectionWidth));
            if(i != spellsList.size() - 1){
                bottomBorder.append("┳");
            } else {
                bottomBorder.append("┫\n");
            }

        }


        StringBuilder description = new StringBuilder();

        description.append(topBorder).append(titleSb).append(bottomBorder);

        description.append("┃");

        //Showing the name of spells
        for (MageSpellsEnum spell : spellsList){
            description.append(String.format(" %-" + (sectionWidth - 2) + "s ┃", spell.name()));
        }

        description.append("\n┣");

        //Create the bottom-border of name spells
        for (int i = 0; i < spellsList.size(); i++){
            description.append("━".repeat(sectionWidth));
            if (i != spellsList.size() - 1) {
                description.append("╋");
            } else {
                description.append("┫\n");
            }
        }
        description.append("┃ ");
        for (MageSpellsEnum spell : spellsList) {
            description.append(String.format("%-" + (sectionWidth - 2) + "s ┃ ", "Damage: " + spell.getDamage()));
        }
        description.append("\n┃ ");

        for (MageSpellsEnum spell : spellsList) {
            description.append(String.format("%-" + (sectionWidth - 2) + "s ┃ ", "Mana Cost: " + spell.getManaCost()));
        }

        description.append("\n┗");
        for (int i = 0; i < spellsList.size(); i++){
            description.append("━".repeat(sectionWidth));
            if (i != spellsList.size() - 1){
                description.append("┻");
            } else {
                description.append("┛\n");
            }

        }


        System.out.println(description);

    }

    @Override
    public void showAbilities() {
        int cont = 1;
        StringBuilder abilities = new StringBuilder();
        for (MageSpellsEnum spell : spellsList){
            abilities.append(cont).append("-").append(spell.name()).append(" | ");
            cont++;
        }
        abilities.append(" 4-Describe Abilities");
        System.out.println(abilities);
    }


}
