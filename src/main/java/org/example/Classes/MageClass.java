package org.example.Classes;


import org.example.Armory.ArmoryInterface;
import org.example.Armory.MageSpellsEnum;
import org.example.Exceptions.AttackMissed;
import org.example.Exceptions.InsufficientMana;
import org.example.model.Player;

import java.util.*;

import static org.example.SystemTools.SystemCombat.awaitEnter;


public class MageClass extends Player {

    private final List<MageSpellsEnum> spellsList = new ArrayList<>(Arrays.asList(MageSpellsEnum.values()));




    public MageClass(){};


    public MageClass(String name, int strength, int agility, int intelligence) {
        super(name, strength, agility, intelligence + 2);

    }

    private void recoverMana(){
        if(isDefending()){
            if(getMana() < getMaxMana() - manaRecover){
                int totalMana = getMana() + manaRecover;
                setMana(totalMana);
            } else {
                setMana(getMaxMana());
            }
        }
    }
    private void recoverStamina(){
        if(isDefending()){
            if(getStamina() < getMaxStamina() - staminaRecover){
                int totalStamina = getStamina() + staminaRecover;
                setStamina(totalStamina);
            } else {
                setStamina(getMaxStamina());
            }
        }
    }

    @Override
    protected void recoverStatus() {
        recoverMana();
        recoverStamina();
    }

    @Override
    public void defense(){

        defending = true;
        setDefense(getDefense() + bonusDefending);
        recoverStatus();

        System.out.println("━".repeat(82));
        System.out.println(" ".repeat(5) + getName() + " started to defending, your defense was increased +" + bonusDefending + " by 1 turn");
        System.out.println(" ".repeat(5) + "Recovered +" + staminaRecover + " points of stamina");
        System.out.println(" ".repeat(5) + "Recovered +" + manaRecover + " points of mana");
        System.out.println("━".repeat(82));
    }

    @Override
    protected int calcDamage(ArmoryInterface weapon, int enemyDefense) {
        int damage = weapon.getDamage();
        int totalDamage = (int)(damage * (1 + (getIntelligence() * 0.05)));
        if (criticalDamage()){
            totalDamage *= 2;
            System.out.println("\n"+"━".repeat(30));
            System.out.println(" ".repeat(5) + "Critical damage !!!");
            System.out.print("━".repeat(30));
        }
        return Math.max(0, totalDamage - enemyDefense);
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
    public MageSpellsEnum getAbility(int index){
        return spellsList.get(index);
    }

    @Override
    public void showAbilities() {
        int cont = 1;
        StringBuilder abilities = new StringBuilder();
        for (MageSpellsEnum spell : spellsList){
            abilities.append(cont).append("-").append(spell.name()).append(" | ");
            cont++;
        }
        abilities.append(" 0-Describe Abilities");
        System.out.println(abilities);
    }

    public int getWeaponListLength(){
        return spellsList.toArray().length;
    }

}
