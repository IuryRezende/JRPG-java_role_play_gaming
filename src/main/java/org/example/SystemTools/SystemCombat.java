package org.example.SystemTools;

import org.example.Exceptions.InvalidValueException;
import org.example.model.Player;

import java.util.Map;
import java.util.Scanner;

public class SystemCombat {
    private final static Scanner sc = new Scanner(System.in);

    private static int getAvailableSpace(String name){
        int length = name.length();
        return Math.min(length, 10);
    }

    private static void showArena(Player playerOfTurn, Player p2){
        StringBuilder arena = new StringBuilder();
        String title = playerOfTurn.getName() + "'s Turn";
        int width = 80;
        int padding = (width - title.length())/2;

        arena.append("┏").append("━".repeat(width)).append("┓\n");
        arena.append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(getAvailableSpace(playerOfTurn.getName()) % 2 == 0 ? padding + 1 : padding)).append("┃\n");
        arena.append("┣").append("━".repeat(width)).append("┫\n");

        //Make the card
        for (int i = 0; i < 12; i++){
            int boxWidth = width/4;
            int boxPaddingP1 = (boxWidth - playerOfTurn.getName().length())/2;
            int boxPaddingP2 = (boxWidth - p2.getName().length())/2;
            int distanceAtTheCard1 = width / 8;
            int distanceBtwCards = width/6;
            int distanceAtTheCard2 = width/6;
            int availableSpaceP1 = getAvailableSpace(playerOfTurn.getName());
            int availableSpaceP2 = getAvailableSpace(p2.getName());

            switch (i) {
                //the top border of card
                case 0:
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));
                    arena.append("┏").append("━".repeat(boxWidth)).append("┓");
                    arena.append(" ".repeat((distanceBtwCards)));
                    arena.append("┏").append("━".repeat(boxWidth)).append("┓");
                    arena.append(" ".repeat((distanceAtTheCard2))).append("┃\n");
                    break;

                //the card name
                case 1:
                    //p1 card
                    arena.append("┃")
                            .append(" ".repeat(distanceAtTheCard1))
                            .append("┃").append(" ".repeat(boxPaddingP1))
                            .append(String.format("%-" + availableSpaceP1 + "s", playerOfTurn.getName()))
                            .append(" ".repeat(getAvailableSpace(playerOfTurn.getName()) % 2 == 0 ? boxPaddingP1 : boxPaddingP1 + 1)).append("┃");

                    arena.append(" ".repeat((distanceBtwCards)));
                    //p2 card
                    arena.append("┃").append(" ".repeat(boxPaddingP2))
                            .append(String.format("%-" + availableSpaceP2 + "s", p2.getName()))
                            .append(" ".repeat(getAvailableSpace(p2.getName()) % 2 == 0 ? boxPaddingP2 : boxPaddingP2 + 1)).append("┃");

                    arena.append(" ".repeat((distanceAtTheCard2))).append("┃\n");
                    break;

                case 2:
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));

                    //first card
                    arena.append("┣").append("━".repeat(boxWidth)).append("┫");

                    arena.append(" ".repeat(distanceBtwCards));

                    //second card
                    arena.append("┣").append("━".repeat(boxWidth)).append("┫");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;

                case 3:
                    String titleVS = "VS";
                    String p1HPTitle = "HP: " + playerOfTurn.getLife() + "/" + playerOfTurn.getMaxLife();
                    int boxPaddingHP1 = (boxWidth - p1HPTitle.length())/2;
                    int vsPadding = (distanceBtwCards - titleVS.length())/2;
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));
                    arena.append("┃").append(" ".repeat(boxPaddingHP1))
                            .append(String.format("%-12s", p1HPTitle))
                            .append(" ".repeat(boxPaddingHP1)).append("┃");

                    arena.append(" ".repeat(vsPadding))
                            .append(titleVS)
                            .append(" ".repeat(vsPadding + 1));

                    String p2HPTitle = "HP: " + p2.getLife() + "/" + p2.getMaxLife();
                    int boxPaddingHP2 = (boxWidth - p2HPTitle.length())/2;
                    arena.append("┃").append(" ".repeat(boxPaddingHP2))
                            .append(String.format("%-12s", p2HPTitle))
                            .append(" ".repeat(boxPaddingHP2)).append("┃");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;

                case 4:
                    String p1ManaTitle = "Mana: " + playerOfTurn.getMana() + "/" + playerOfTurn.getMaxMana();
                    int boxPaddingManaP1 = (boxWidth - p1ManaTitle.length())/2;
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));
                    arena.append("┃").append(" ".repeat(boxPaddingManaP1))
                            .append(String.format("%-12s", p1ManaTitle))
                            .append(" ".repeat(boxPaddingManaP1 + 1)).append("┃");

                    arena.append(" ".repeat(distanceBtwCards));

                    String p2ManaTitle = "Mana: " + p2.getMana() + "/" + p2.getMaxMana();
                    int boxPaddingManaP2 = (boxWidth - p2ManaTitle.length())/2;
                    arena.append("┃").append(" ".repeat(boxPaddingManaP2))
                            .append(String.format("%-12s", p2ManaTitle))
                            .append(" ".repeat(boxPaddingManaP2 + 1)).append("┃");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;

                case 5:
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));

                    //first card
                    arena.append("┗").append("━".repeat(boxWidth)).append("┛");

                    arena.append(" ".repeat(distanceBtwCards));

                    //second card
                    arena.append("┗").append("━".repeat(boxWidth)).append("┛");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;


                case 11:
                    arena.append("┃").append(" ".repeat(width/4))
                            .append(getActions())
                            .append(" ".repeat((width/4)+1)).append("┃\n");
                    break;
                default:
                    arena.append("┃").append(" ".repeat(width)).append("┃\n");
                    break;
            }
        }
        arena.append("┗").append("━".repeat(width)).append("┛\n");

        System.out.println(arena);
    }

    public static void awaitEnter(){
        sc.nextLine();
        sc.nextLine();
    }
    private static String getActions(){
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
