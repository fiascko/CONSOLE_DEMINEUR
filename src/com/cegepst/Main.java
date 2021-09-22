package com.cegepst;

public class Main {

    public static void main(String[] args) {
        Console.showTitle();
        Console.showStartingQuestion();
        boolean start = Read.readBooleanChoice();
        if (start) {
            new Game();
        }
    }

}
