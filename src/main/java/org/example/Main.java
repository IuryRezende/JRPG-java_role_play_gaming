package org.example;

import org.example.Armory.*;
import org.example.Classes.*;
import org.example.Exceptions.InvalidValueException;
import org.example.SystemTools.SystemCombat;
import org.example.model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner sc = new Scanner(System.in);
    private static String formatName(String name){
        return name.substring(0, Math.min(10, name.length()));
    }

    private static int chooseClass(){
        try {
            System.out.println("┏"+"━".repeat(40) + "┓");
            System.out.println("┃"+ " ".repeat(8) + "Choose the class to play" + " ".repeat(8)+ "┃");
            System.out.println("┃"+ " ".repeat(3) + "1-Mage    2-Barbarian   3-Assassin" + " ".repeat(3) + "┃");
            System.out.println("┗" + "━".repeat(40) + "┛");
            byte choosedClass = sc.nextByte();
            sc.nextLine();
            if (choosedClass < 1 || choosedClass > 3) {
                throw new InvalidValueException("Invalid value, try again!");
            }


            System.out.print("Are you sure? (y/n)  ");
            char areSure = sc.nextLine().charAt(0);

            return Character.toLowerCase(areSure) == 'y' ? choosedClass : chooseClass();
        } catch (InvalidValueException e){
            System.out.println(e.getMessage());
            return chooseClass();
        } catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("Invalid value, try again");
            return chooseClass();
        }
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
            System.out.print("Enter the name of P1: ");
            String p1Name = formatName(sc.nextLine());
            Player p1 = createClass(p1Name);


            System.out.print("Enter the name of P2: ");
            String p2Name = formatName(sc.nextLine());
            Player p2 = createClass(p2Name);


            SystemCombat.startCombat(p1, p2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}