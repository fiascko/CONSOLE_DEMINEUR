package com.cegepst;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Score {

    private int score = 0;
    private int highScore = 0;
    private String highUser;
    private int treasuresScoreUpdate = 0;

    private final ArrayList<User> listUser = new ArrayList<>();
    private final String FILE_PATH = "./scores.txt";

    public Score() {
        loadHighScore();
    }

    public void saveUserScore() {
        Console.showSaveUserQuestion();
        String user = Read.readStringUser();
        writeScore(user);
    }

    public void updateScore(Position position, Grid grid) {
        if (grid.isPositionTreasure(position)) {
            treasuresScoreUpdate += grid.getTreasureValue(position);
        }
        if (!grid.isPositionMine(position)) {
            score += grid.addTileToScore(position);
        }
    }

    public void createScoreList() {
        Collections.sort(listUser, new Comparator<User>() {
            public int compare(User user1, User user2) {
                return user1.userScore - user2.userScore;
            }
        });
        Collections.reverse(listUser);
        Console.showScoreList(listUser);
        Console.stayAwhileAndListen();
    }

    public void updateScoreByTimer(long timerMinutes) {
        final int[] timerScoreArrayConditions = {2, 5, 7, 8, 9, 10, 10};
        final int[] timerScoreArrayValues = {5400, 2700, 1800, 900, 600, 300, 100};
        for (int i = 0; i < timerScoreArrayConditions.length; i++) {
            if (timerMinutes < timerScoreArrayConditions[i]) {
                score += timerScoreArrayValues[i];
                Console.showTimerScoreBonus(timerScoreArrayValues[i]);
                return;
            }
            if (timerMinutes >= timerScoreArrayConditions[timerScoreArrayConditions.length - 1]) {
                score += timerScoreArrayValues[timerScoreArrayConditions.length - 1];
                Console.showTimerScoreBonus(timerScoreArrayConditions.length - 1);
                return;
            }
        }
    }

    public void resetTreasureScore() {
        treasuresScoreUpdate = 0;
    }

    private void loadHighScore() {
        File file = new File(FILE_PATH);
        if(!file.exists()) {
            writeNewFile();
        }
        readFile();
        searchHighScore();
    }

    private void writeNewFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
            bw.write("AUCUN\n");
            bw.write("0");
            bw.close();
        } catch (Exception ex) { return; }
    }

    private void readFile() {
        try {
            Scanner scan = new Scanner(new File(FILE_PATH));
            int i = 0;
            while (scan.hasNextLine()) {
                listUser.add(new User(scan.nextLine(), scan.nextLine()));
                i += 2;
            }
            scan.close();
        } catch (Exception ex){ return; }
    }

    private void writeScore(String user) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
            bw.write("\n" + user);
            bw.write("\n" + score);
            bw.close();
        } catch (Exception ex){ return; }
    }

    private void searchHighScore() {
        if( !(listUser.isEmpty()) ) {
            int maxScore = listUser.get(0).userScore;
            highUser = listUser.get(0).getUserName();

            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).userScore > maxScore) {
                    maxScore = listUser.get(i).userScore;
                    highUser = listUser.get(i).getUserName();
                }
            }
            highScore = maxScore;
        }
    }

    public void setScore(int newScore) {
        score += newScore;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getHighUser() {
        return highUser;
    }

    public int getTreasuresScoreUpdate() {
        return treasuresScoreUpdate;
    }

}
