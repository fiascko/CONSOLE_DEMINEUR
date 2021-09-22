package com.cegepst;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    static boolean cheat = false;

    public static void showTitle() {
        System.out.println("|--------------------------------------------------|");
        System.out.println("|---------------------Démineur---------------------|");
        System.out.println("|--------------------------------------------------|");
    }

    public static void showStartingQuestion() {
        System.out.println("|------- Voulez-vous faire une partie?(O/N) -------|");
    }

    public static void showCheatQuestion() {
        System.out.println("|------- Activation du code de triche?(O/N) -------|");
    }

    public static void showGameLoopQuestion() {
        System.out.println("----------------------------------------------------");
        System.out.println("1 - Placer / Supprimer un flag.\n" +
                            "2 - Découvrir une case.\n" +
                            "3 - Consulter les scores.\n" +
                            "4 - Ragequit." +
                            "\n----------------------------------------------------");
    }

    public static void showAxisQuestion(String axis) {
        if (axis.equalsIgnoreCase("ROW")) {
            System.out.println("|------- Choisir la ligne de la case. (A/L) -------|");
            return;
        }
        System.out.println("|------ Choisir la colonne de la case. (A/L) ------|");
    }

    public static void showGameOverText(int gameOver) {
        switch (gameOver) {
            case 1:
                System.out.println("\n|--------------------------------------------------|");
                System.out.println("|------- Meilleure chance la prochaine fois -------|");
                break;
            case 2:
                System.out.println("\n|--------------------------------------------------|");
                System.out.println("|------- Vous êtes mort, une mine a explosé -------|");
                break;
            case 3:
                System.out.println("\n|--------------------------------------------------|");
                System.out.println("|--------- Félicitations vous avez gagné! ---------|");
                break;
        }
    }

    public static void showLastPositionEnter(Position position, Grid grid) {
        System.out.println("\n\n\n----------------------------------------------------");
        System.out.println("La dernière position que vous avez entrée est: "
                + grid.getLettersArray()[position.row] + grid.getLettersArray()[position.col]);
    }

    public static void showAlreadyOpenTileAlert() {
        System.out.println("----------------------------------------------------");
        System.out.println("La position entrée est déja découverte.");
    }

    public static void showDiscoverFlagAlert() {
        System.out.println("----------------------------------------------------");
        System.out.println("Supprimer d'abord le drapeau.");
    }

    public static void stayAwhileAndListen() {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------------------------------------");
        System.out.println("|------- Appuyer sur 'ENTER' pour continuer. -------|");
        scan.nextLine();
    }

    public static void showTreasureAlert(int treasuresValue) {
        System.out.println("----------------------------------------------------");
        System.out.println("Bonus trésor qui ajoute " + treasuresValue + " à votre score.");
    }

    public static void showRemainingMines(int mine) {
        System.out.println("Mines restante: " + mine);
    }

    public static void showActualTime(long timerMinutes, long timerSeconds) {
        System.out.println("----------------------------------------------------");
        System.out.println("Temps écoulé: " + timerMinutes + ":" + timerSeconds);
    }

    public static void showActualScore(int score, int highScore, String highUser) {
        System.out.println("----------------------------------------------------");
        System.out.println("Score actuel: " + score + "          Score record: " + highUser + " " + highScore);
    }


    public static void showTimerScoreBonus(int scoreBonusByTimer) {
        System.out.println("----------------------------------------------------");
        System.out.println("Bonus au score pour le temps écoulé: " + scoreBonusByTimer);
    }

    public static void showScoreList(ArrayList<User> listUser) {
        final int userLimit = 5;
        int listSize = Math.min(listUser.size(), userLimit);
        System.out.println("|------------------- HIGHSCORES -------------------|");
        for (int i = 0; i < listSize; i++) {
            System.out.println("Joueur: " + listUser.get(i).getUserName() + " Score: " + listUser.get(i).userScore);
        }
    }

    public static void showSaveUserQuestion() {
        System.out.println("\n|--------------------------------------------------|");
        System.out.println("|---- Entrer votre username (Max 6 caractères) ----|\n");
    }

    public static void showCheatedGrid(Tile[][] grid, char[] letters) {
        showGridHeader(letters);
        showCheatedGridBody(grid, letters);
    }

    public static void showGrid(Tile[][] grid, char[] letters) {
        showGridHeader(letters);
        showGridBody(grid, letters);
    }

    private static void showGridHeader(char[] letters) {
        System.out.print("\n      ");
        for (char letter : letters) {
            System.out.print(letter + "   ");
        }
        System.out.println("\n");
    }

    private static void showCheatedGridBody(Tile[][] grid, char[] letters) {
        for (int i = 0; i < grid.length; i++) {
            System.out.print(letters[i]+ "     ");
            for (int j = 0; j < grid.length; j++) {
                showCheatedTilesValue(grid, i, j);
            }
            System.out.println("");
        }
    }

    private static void showGridBody(Tile[][] grid, char[] letters) {
        for (int i = 0; i < grid.length; i++) {
            System.out.print(letters[i]+ "     ");
            for (int j = 0; j < grid.length; j++) {
                showTilesValue(grid, i, j);
            }
            System.out.println("");
        }
    }

    private static void showCheatedTilesValue(Tile[][] grid, int i, int j) {
        if (grid[i][j].isMine){
            System.out.print("M   ");
        }
        if (grid[i][j].mineAround > 0 && !grid[i][j].isMine){
            System.out.print( grid[i][j].mineAround + "   ");
        }
        if (!grid[i][j].isMine && grid[i][j].mineAround == 0){
            System.out.print("_   ");
        }
    }

    private static void showTilesValue(Tile[][] grid, int i, int j) {
        if (!grid[i][j].isFlag){
            if (!grid[i][j].isDiscover){
                System.out.print("_   ");
            } else  {
                if (grid[i][j].mineAround != 0){
                    System.out.print( grid[i][j].mineAround + "   ");
                } else {
                    System.out.print("    ");
                }
            }
        } else {
            System.out.print("F   ");
        }
    }
}
