package org.example.Classes;

import org.example.Armory.ArmoryInterface;
import org.example.Armory.BarbarianWeaponsEnum;
import org.example.Exceptions.AttackMissed;
import org.example.Exceptions.InsufficientMana;
import org.example.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.SystemTools.SystemCombat.awaitEnter;

public class BarbarianClass extends Player{
    private final List<BarbarianWeaponsEnum> weaponsList = new ArrayList<>(Arrays.asList(BarbarianWeaponsEnum.values()));

    public BarbarianClass(){};

    public BarbarianClass(String name, int strength, int agility, int intelligence) {
        super(name, strength + 2, agility, intelligence);

    }


    @Override
    public ArmoryInterface getAbility(int index) {
        return weaponsList.get(index);
    }

    @Override
    public void describeAbilities(){

        String title = "Description";
        StringBuilder titleSb = new StringBuilder();
        StringBuilder topBorder = new StringBuilder();
        StringBuilder bottomBorder = new StringBuilder();
        int width = 80;
        int sectionWidth = width / weaponsList.size();
        int padding = (width - title.length())/2;


        topBorder.append("┏").append("━".repeat(width)).append("┓\n");
        titleSb.append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(padding)).append(" ┃\n");

        bottomBorder.append("┣");
        for (int i = 0; i < weaponsList.size(); i++){
            bottomBorder.append("━".repeat(sectionWidth));
            if(i != weaponsList.size() - 1){
                bottomBorder.append("┳");
            } else {
                bottomBorder.append("┫\n");
            }

        }


        StringBuilder description = new StringBuilder();

        description.append(topBorder).append(titleSb).append(bottomBorder);

        description.append("┃");

        //Showing the name of weapons
        for (BarbarianWeaponsEnum weapon : weaponsList){
            int sectionPadding = (sectionWidth - weapon.name().length())/2;
            description.append(String.format(" ".repeat(sectionPadding)
                    + "%s"
                    + " ".repeat(weapon.name().length() % 2 == 0 ? sectionPadding : sectionPadding + 1) + "┃", weapon.name()));
        }

        description.append("\n┣");

        //Create the bottom-border of name weapons
        for (int i = 0; i < weaponsList.size(); i++){
            description.append("━".repeat(sectionWidth));
            if (i != weaponsList.size() - 1) {
                description.append("╋");
            } else {
                description.append("┫\n");
            }
        }
        description.append("┃ ");
        for (BarbarianWeaponsEnum weapon : weaponsList) {
            description.append(String.format("%-" + (sectionWidth - 2) + "s ┃ ", "Damage: " + weapon.getDamage()));
        }
        description.append("\n┃ ");

        for (BarbarianWeaponsEnum weapon : weaponsList) {
            description.append(String.format("%-" + (sectionWidth - 2) + "s ┃ ", "Stamina Cost: " + weapon.getStaminaCost()));
        }

        description.append("\n┗");
        for (int i = 0; i < weaponsList.size(); i++){
            description.append("━".repeat(sectionWidth));
            if (i != weaponsList.size() - 1){
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
        for (BarbarianWeaponsEnum weapon : weaponsList){
            abilities.append(cont).append("-").append(weapon.name()).append(" | ");
            cont++;
        }
        abilities.append(" 4-Describe Abilities");
        System.out.println(abilities);
    }
}
