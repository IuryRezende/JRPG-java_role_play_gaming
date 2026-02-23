package org.example;

import org.example.Armory.*;
import org.example.Classes.*;
import org.example.SystemTools.SystemCombat;
import org.example.model.Player;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    private static String formatName(String name){
        return name.substring(0, Math.min(10, name.length()));
    }

    private static int chooseClass(){
        System.out.println("Choose the class to play");
        System.out.println("1-Mage    2-Barbarian   3-Assassin");
        byte choosedClass = sc.nextByte();
        sc.nextLine();
        if (choosedClass < 1 || choosedClass > 3){
            System.out.println("Invalid argument, try again");
            chooseClass();
        }

        System.out.println("Are you sure? (y/n)");
        char areSure = sc.nextLine().charAt(0);

        return areSure == 'y' ? choosedClass : chooseClass();

    };

    private static Player createClass(String name){
        int arquetype = chooseClass();

        if(arquetype == 1) {
            return new MageClass(name, 3, 3, 4);
        } else if(arquetype == 2) {
            return new BarbarianClass(name, 4, 3, 3);
        } else {
            return new AssassinClass(name, 3, 4, 3);
        }
    }

    public static void main(String[] args) {

        try {
            System.out.println("Enter the name of P1: ");
            String p1Name = formatName(sc.nextLine());
            Player p1 = createClass(p1Name);


            System.out.println("Enter the name of P2: ");
            String p2Name = formatName(sc.nextLine());
            Player p2 = createClass(p2Name);


            SystemCombat.startCombat(p1, p2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}