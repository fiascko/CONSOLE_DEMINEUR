package com.cegepst;

import java.util.Scanner;

public class Read {

    public static int readIntChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (!choice.equals("0") && !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
            do{
                System.out.println("Entrez une valeur correcte. (1/4)");
                choice = scanner.next();
            } while(!choice.equals("0") && !choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4"));
        }
        return Integer.parseInt(choice);
    }

    public static char readAxisPosition() {
        Scanner scanner = new Scanner(System.in);
        char choice;
        do {
            choice = scanner.next().toUpperCase().charAt(0);
            if (!Grid.isValidLetter(choice)){
                System.out.println("Entrez une valeur correcte. (A/L)");
            }
        } while (!Grid.isValidLetter(choice));
        return choice;
    }

    public static boolean readBooleanChoice() {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("o")) {
            do{
                System.out.println("Entrez une valeur correcte. (O/N)");
                choice = scanner.next();
            } while(!choice.equalsIgnoreCase("n") && !choice.equalsIgnoreCase("o"));
        }
        return choice.equalsIgnoreCase("o");
    }

    public static String readStringUser() {
        Scanner scanner = new Scanner(System.in);
        final int maxChar = 6;
        String user = scanner.nextLine();
        if (user.length() > maxChar || user.length() < 1) {
            do{
                System.out.println("Entrez une valeur correcte. (Max 6 caractères)");
                user = scanner.next();
            } while(user.length() > maxChar || user.length() < 1);
        }
        return user.toUpperCase();
    }

}
