package org.example.SystemTools;

import org.example.model.Player;

import java.util.Scanner;

public class SystemCombat {
    private final static Scanner sc = new Scanner(System.in);

    private static void awaitEnter(){
        sc.nextLine();
    }
    private static void showActions(){
        System.out.println("1-Attack  |  2-Defense  |  3-Get Status");
    }
    private static void showHabilities(Player player){
        System.out.println(player.getAbilities());
    }


    public static void startCombat(Player p1, Player p2){
        while (p1.isLive() && p2.isLive()){
            try{
                System.out.println("=".repeat(15) + " " + p1.getName() + "'s Turn " + "=".repeat(15));
                showActions();
                byte option = sc.nextByte();
                switch (option) {
                    case 1:
                        showHabilities(p1);
                        int choosedHability = sc.nextInt();
                        p1.attack(choosedHability, p2);
                        break;

                    case 2:
                        p1.defense();
                        break;

                    case 3:
                        p1.getSheet();
                }


            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
