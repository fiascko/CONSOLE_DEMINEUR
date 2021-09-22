package com.cegepst;

public class Time {

    private long timerStart = 0;

    public Time() {
        startGameTimer();
    }

    public void showTimer() {
        long timerMinutes = calculateMinutes();
        long timerSeconds = calculateSeconds();
        Console.showActualTime(timerMinutes, timerSeconds);
    }

    private void startGameTimer() {
        timerStart = System.currentTimeMillis();
    }

    private long calculateSeconds() {
        long timerEnd = System.currentTimeMillis();
        return ((timerEnd - timerStart) / 1000) % 60;
    }

    private long calculateMinutes() {
        long timerEnd = System.currentTimeMillis();
        return ((timerEnd - timerStart) / 1000) / 60;
    }

    public long getMinutes(){
        return calculateMinutes();
    }

}
