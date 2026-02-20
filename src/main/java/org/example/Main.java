package org.example;

import org.example.Armory.*;
import org.example.Classes.*;
import org.example.SystemTools.SystemCombat;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String formatName(String name){
        return name.substring(0, Math.min(10, name.length()));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
//            System.out.println("Enter the name of P1: ");
//            String p1Name = formatName(sc.nextLine());
//
//            System.out.println("Enter the name of P2: ");
//            String p2Name = formatName(sc.nextLine());

            BarbarianClass p1 = new BarbarianClass("Maradonna", 2, 4, 4);
            MageClass p2 = new MageClass("CR7", 4, 3, 3);

            SystemCombat.startCombat(p1, p2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}