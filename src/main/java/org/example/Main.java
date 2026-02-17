package org.example;

import org.example.Armory.*;
import org.example.Classes.*;
import org.example.SystemTools.SystemCombat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            MageClass p1 = new MageClass("IuryDoGr", 2, 4, 4);
            MageClass p2 = new MageClass("12", 4, 3, 3);

            SystemCombat.startCombat(p1, p2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}