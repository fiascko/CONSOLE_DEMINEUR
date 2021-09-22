package com.cegepst;

public class Tile {

    int mineAround;
    boolean isMine;
    boolean isFlag;
    boolean isDiscover;
    boolean isTreasure;
    int treasureValue;
    int tileValue;

    public Tile() {
        this.mineAround = 0;
        this.isDiscover = false;
        this.isFlag = false;
        this.isMine = false;
        this.isTreasure = false;
        this.treasureValue = 0;
        this.tileValue = 5;
    }

}
