package org.example.SystemTools;

import org.example.Exceptions.InsufficientMana;
import org.example.Exceptions.InsufficientStamina;
import org.example.Exceptions.InvalidValueException;
import org.example.Exceptions.PlayerDied;
import org.example.model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SystemCombat {
    private static int roundCount = 1;
    private final static Scanner sc = new Scanner(System.in);

    public static int getAvaliableSpace(String name){
        int length = name.length();
        return Math.min(length, 10);
    }


    private static void showArena(Player playerOfTurn, Player p2){
        StringBuilder arena = new StringBuilder();
        String title = playerOfTurn.getName() + "'s Turn";
        int width = 80;
        int padding = (width - title.length())/2;

        arena.append("┏").append("━".repeat(width)).append("┓\n");
        arena.append("┃").append(" ".repeat(padding)).append(title).append(" ".repeat(getAvaliableSpace(title) % 2 == 0 ? padding + 1 : padding)).append("┃\n");
        arena.append("┣").append("━".repeat(width)).append("┫\n");

        //Make the card
        for (int i = 0; i < 12; i++){
            int boxWidth = width/4;
            int boxPaddingP1 = (boxWidth - playerOfTurn.getName().length())/2;
            int boxPaddingP2 = (boxWidth - p2.getName().length())/2;
            int distanceAtTheCard1 = width / 8;
            int distanceBtwCards = width/6;
            int distanceAtTheCard2 = width/6;
            int availableSpaceP1 = getAvaliableSpace(playerOfTurn.getName());
            int availableSpaceP2 = getAvaliableSpace(p2.getName());

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
                            .append(" ".repeat(getAvaliableSpace(playerOfTurn.getName()) % 2 == 0 ? boxPaddingP1 : boxPaddingP1 + 1)).append("┃");

                    arena.append(" ".repeat((distanceBtwCards)));
                    //p2 card
                    arena.append("┃").append(" ".repeat(boxPaddingP2))
                            .append(String.format("%-" + availableSpaceP2 + "s", p2.getName()))
                            .append(" ".repeat(getAvaliableSpace(p2.getName()) % 2 == 0 ? boxPaddingP2 : boxPaddingP2 + 1)).append("┃");

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

                //HP line
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

                //mana line
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

                //Stamina Line
                case 5:
                    String p1StaminaTitle = "Stamina: " + playerOfTurn.getStamina() + "/" + playerOfTurn.getMaxStamina();
                    int boxPaddingStaminaP1 = (boxWidth - p1StaminaTitle.length())/2;
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));
                    arena.append("┃").append(" ".repeat(boxPaddingStaminaP1))
                            .append(String.format("%-12s", p1StaminaTitle))
                            .append(" ".repeat(getAvaliableSpace(p1StaminaTitle) % 2 == 0 ? boxPaddingStaminaP1 : boxPaddingStaminaP1 + 1)).append("┃");

                    arena.append(" ".repeat(distanceBtwCards));

                    String p2StaminaTitle = "Stamina: " + p2.getStamina() + "/" + p2.getMaxStamina();
                    int boxPaddingStaminaP2 = (boxWidth - p2StaminaTitle.length())/2;
                    arena.append("┃").append(" ".repeat(boxPaddingStaminaP2))
                            .append(String.format("%-12s", p2StaminaTitle))
                            .append(" ".repeat(getAvaliableSpace(p1StaminaTitle) % 2 == 0 ? boxPaddingStaminaP1 : boxPaddingStaminaP1 + 1)).append("┃");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;
                //Def line
                case 6:
                    String p1DefTitle = "Def: " + playerOfTurn.getDefense();
                    int boxPaddingDefP1 = (boxWidth - p1DefTitle.length())/2;
                    arena.append("┃").append(" ".repeat(distanceAtTheCard1));
                    arena.append("┃").append(" ".repeat(boxPaddingDefP1))
                            .append(String.format("%-"+ p1DefTitle.length() +"s", p1DefTitle))
                            .append(" ".repeat(getAvaliableSpace(p1DefTitle) % 2 == 0 ? boxPaddingDefP1 : boxPaddingDefP1 + 1)).append("┃");

                    arena.append(" ".repeat(distanceBtwCards));

                    String p2DefTitle = "Def: " + p2.getDefense();
                    int boxPaddingDefP2 = (boxWidth - p2DefTitle.length())/2;
                    arena.append("┃").append(" ".repeat(boxPaddingDefP2))
                            .append(String.format("%-8s", p2DefTitle))
                            .append(" ".repeat(getAvaliableSpace(p2DefTitle) % 2 == 0 ? boxPaddingDefP2 - 2 : boxPaddingDefP2)).append("┃");

                    arena.append(" ".repeat(distanceAtTheCard2)).append("┃\n");
                    break;

                //Bottom style of the card
                case 7:
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


    private static String getActions(){
        return "1-Attack"
                + " ".repeat(2)
                + "┃" + " ".repeat(2) +
                "2-Defense"
                + " ".repeat(2)
                + "┃" + " ".repeat(2)
                + "3-Get Status";
    }

    private static void startRound(Player playerOfTurn, Player p2) throws PlayerDied, InputMismatchException, InvalidValueException {
        if (playerOfTurn.isDefending()){
            playerOfTurn.removeDefending();
        }

        showArena(playerOfTurn, p2);

        System.out.print("Enter the action: ");
        byte option = sc.nextByte();

        switch (option) {
            case 1:
                playerOfTurn.showAbilities();
                byte choosedAbility = sc.nextByte();

                if (choosedAbility < 0 || choosedAbility > playerOfTurn.getWeaponListLength()){
                    throw new InvalidValueException("Invalid value");
                }

                if (choosedAbility == 0){
                    playerOfTurn.describeAbilities();
                    awaitEnter();

                    startCombat(playerOfTurn, p2);
                    break;
                }
                playerOfTurn.attack(choosedAbility, p2);
                break;

            case 2:
                if (playerOfTurn.isDefending()){
                    System.out.println("You're already defending!");
                    awaitEnter();

                    startCombat(playerOfTurn,p2);
                    break;
                }
                playerOfTurn.defense();
                break;

            case 3:
                playerOfTurn.getStatus();
                awaitEnter();

                startCombat(playerOfTurn, p2);
                break;

            default:
                throw new InvalidValueException("Invalid value, try again");
        }
    }


    public static void awaitEnter(){
        sc.nextLine();
        System.out.print("Press ENTER to continue");
        sc.nextLine();
    }

    public static void startCombat(Player p1, Player p2){
        while (true){
            try{
                //first argument is the player of the turn
                startRound(p1, p2);

                if (!p2.isLive()){
                    throw new PlayerDied(p2.getName(), p1.getName());
                }

                startRound(p2, p1);

                if (!p1.isLive()){
                    throw new PlayerDied(p1.getName(), p2.getName());
                }


                roundCount++;

            } catch (PlayerDied e) {
                System.out.println(e.getMessage());
                break;
            } catch (InsufficientMana | InsufficientStamina | InvalidValueException e){
                System.out.println(e.getMessage());
                awaitEnter();

                startCombat(p1,p2);
            } catch (InputMismatchException e){
                sc.nextLine();
                System.out.println(e.getMessage());
                awaitEnter();

                startCombat(p1,p2);
            }
        }
    }
}
