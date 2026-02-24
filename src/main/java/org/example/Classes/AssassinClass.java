package org.example.Classes;

import org.example.Armory.ArmoryInterface;
import org.example.Armory.AssassinWeaponsEnum;
import org.example.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssassinClass extends Player {

    private final List<AssassinWeaponsEnum> weaponsList = new ArrayList<>(Arrays.asList(AssassinWeaponsEnum.values()));

    public AssassinClass(){};

    public AssassinClass(String name, int strength, int agility, int intelligence) {
        super(name, strength, agility + 2, intelligence);

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
        System.out.println("━".repeat(82));
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
        for (AssassinWeaponsEnum weapon : weaponsList){
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
        for (AssassinWeaponsEnum weapon : weaponsList) {
            description.append(String.format("%-" + (sectionWidth - 2) + "s ┃ ", "Damage: " + weapon.getDamage()));
        }
        description.append("\n┃ ");

        for (AssassinWeaponsEnum weapon : weaponsList) {
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
        for (AssassinWeaponsEnum weapon : weaponsList){
            abilities.append(cont).append("-").append(weapon.name()).append(" | ");
            cont++;
        }
        abilities.append(" 0-Describe Abilities");
        System.out.println(abilities);
    }

    @Override
    protected int calcDamage(ArmoryInterface weapon, int enemyDefense) {
        int damage = weapon.getDamage();
        int totalDamage = (int)(damage * (1 + (getStrength() * 0.05)));

        if (criticalDamage()){
            totalDamage *= 2;
            System.out.println("\n"+"━".repeat(30));
            System.out.println(" ".repeat(5) + "Critical damage !!!");
            System.out.print("━".repeat(30));
        }
        return Math.max(0, totalDamage - enemyDefense);
    }

    public int getWeaponListLength(){
        return weaponsList.toArray().length;
    }
}
