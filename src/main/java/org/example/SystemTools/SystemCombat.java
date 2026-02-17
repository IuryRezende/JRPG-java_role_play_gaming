package org.example.SystemTools;

import org.example.Exceptions.InvalidValueException;
import org.example.model.Player;

import java.util.Scanner;

public class SystemCombat {
    private final static Scanner sc = new Scanner(System.in);

    private static void showArena(Player playerOfTurn, Player p2){
        StringBuilder arena = new StringBuilder();
        String title = playerOfTurn.getName() + "'s Turn";
        int width = 50;
        int padding = (width - title.length())/2;

        arena.append("┏").append("━".repeat(width)).append("┓\n");
        arena.append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(padding)).append("┃\n");
        arena.append("┣").append("━".repeat(width)).append("┫\n");
        for (int i = 0; i < 16; i++){
            int boxWidth = width/2;
            int boxPadding = (boxWidth - playerOfTurn.getName().length())/2;
            if (i == 0){
                arena.append("┃").append(" ".repeat(width/4));
                arena.append("┏").append("━".repeat(width/2)).append("┓");
                arena.append(" ".repeat((width/4) -1)).append("┃\n");
            }
            else if(i == 1){
                arena.append("┃")
                        .append(" ".repeat(width/4))
                        .append("┃").append(" ".repeat(boxPadding))
                        .append(playerOfTurn.getName())
                        .append(" ".repeat(boxPadding)).append("┃")
                        .append(" ".repeat((width/4)-1)).append("┃\n");
            }
            else if(i == 15){
                arena.append("┃").append(" ".repeat(6))
                        .append(showActions())
                        .append(" ".repeat(5)).append("┃\n");
            }
            else {
                arena.append("┃").append(" ".repeat(width)).append("┃\n");
            }
        }
        arena.append("┗");
        for (int i = 0; i < 3; i++){
            arena.append("━".repeat(width/3));
            if (i != 2){
                arena.append("┻");
            } else{
                arena.append("┛\n");
            }

        }

        System.out.println(arena);
    }

    private static void awaitEnter(){
        sc.nextLine();
        sc.nextLine();
    }
    private static String showActions(){
        return "1-Attack"
                + " ".repeat(2)
                + "┃" + " ".repeat(2) +
                "2-Defense"
                + " ".repeat(2)
                + "┃" + " ".repeat(2)
                + "3-Get Status";
    }

    private static void startRound(Player playerOfTurn, Player p2) throws InvalidValueException {
        showArena(playerOfTurn, p2);

        byte option = sc.nextByte();
        switch (option) {
            case 1:
                playerOfTurn.showAbilities();
                int choosedAbility = sc.nextInt();

                if (choosedAbility == 4){
                    playerOfTurn.describeAbilities();
                    awaitEnter();
                    startRound(playerOfTurn, p2);
                    break;
                }
                playerOfTurn.attack(choosedAbility, p2);
                break;

            case 2:
                playerOfTurn.defense();
                break;

            case 3:
                playerOfTurn.getStatus();
                awaitEnter();
                startRound(playerOfTurn, p2);
                break;

            default:
                throw new InvalidValueException();
        }
    }


    public static void startCombat(Player p1, Player p2){
        while (p1.isLive() && p2.isLive()){
            try{
                //first argument is the player of the turn
                startRound(p1, p2);

                if (p2.isLive()){
                    startRound(p2, p1);
                }


            } catch (InvalidValueException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
