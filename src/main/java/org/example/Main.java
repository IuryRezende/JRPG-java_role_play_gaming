package org.example;

import org.example.Armory.MageSpellsEnum;
import org.example.Classes.AssassinClass;
import org.example.Classes.MageClass;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            MageClass p1 = new MageClass("PaulinPinho", 2, 4, 4);
            MageClass p2 = new MageClass("Fimosis", 4, 3, 3);

            System.out.println(p1.getSheet());
            System.out.println("\n\n");
            System.out.println(p2.getSheet());

            p1.attack(1, p2);

            if(p2.isLive()){
                throw new RuntimeException(p2.getName() + " morreu");
            }
            System.out.println(p2.getSheet());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}