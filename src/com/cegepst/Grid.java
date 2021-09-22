package com.cegepst;

import java.util.Random;

public class Grid {

    private final static int MINE_NUMBER = 30;
    private final static int MAX_TREASURE_NUMBER = 30;
    private final static int GRID_ROW = 12;
    private final static int GRID_COL = 12;
    private final static int TILES_AROUND = 8;

    private final Tile[][] grid = new Tile[GRID_ROW][GRID_COL];
    private final int[] proxRowArray = {-1, -1, 0, 1, 1,  1,  0, -1};
    private final int[] proxColArray = { 0,  1, 1, 1, 0, -1, -1, -1};
    private final static char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L'};
    private int flags = 0;

    public Grid() {
        fillGrid();
        placeMines();
        placeTreasures();
        placeProxNumbers();
    }

    public void showGrid() {
        if (Console.cheat) {
            Console.showCheatedGrid(grid, letters);
            Console.cheat = false;
        } else {
            Console.showGrid(grid, letters);
        }
    }

    public int findPosition(String axis) {
        int positionValue;
        char position;
        Console.showAxisQuestion(axis);
        position = Read.readAxisPosition();
        positionValue = findPositionValue(position);
        return positionValue;
    }

    public void placeFlag(Position position) {
        if (!isTileDiscover(position)) {
            if (!isFlag(position) && !getMaxFlag()) {
                beFlag(position);
            } else {
                removeFlag(position);
            }
        } else {
            Console.showAlreadyOpenTileAlert();
        }
    }

    public boolean isWin() {
        int counter = 0;
        for (int i = 0; i < GRID_ROW; i++) {
            for (int j = 0; j < GRID_COL; j++) {
                if (grid[i][j].isDiscover) {
                    counter++;
                }
            }
        }
        return counter == (GRID_ROW * GRID_COL) - MINE_NUMBER && flags == MINE_NUMBER;
    }

    private void fillGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Tile();
            }
        }
    }

    private void placeMines() {
        for (int i = 0; i < MINE_NUMBER; i++) {
            placeMine(grid);
        }
    }

    private void placeMine(Tile[][] grid) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(GRID_ROW);
            int col = random.nextInt(GRID_COL);
            if (!grid[row][col].isMine) {
                this.grid[row][col].isMine = true;
                break;
            }
        }
    }

    private void placeTreasures() {
        Random random = new Random();
        final int treasureNumber = random.nextInt(MAX_TREASURE_NUMBER + 1);
        final int totalTreasuresScore = random.nextInt(201) + 100;
        final int treasureValueRange = totalTreasuresScore / (treasureNumber + 1);
        for (int i = 0; i < treasureNumber; i++) {
            placeTreasure(treasureValueRange);
        }
    }

    private void placeTreasure(int treasureValueRange) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(GRID_ROW);
            int col = random.nextInt(GRID_COL);
            if (!grid[row][col].isMine && !grid[row][col].isTreasure) {
                this.grid[row][col].isTreasure = true;
                this.grid[row][col].treasureValue += random.nextInt(treasureValueRange + 1) + 5;
                break;
            }
        }
    }

    private void placeProxNumbers() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                for (int k = 0; k < TILES_AROUND; k++) {
                    Position position = new Position( i + proxRowArray[k], j + proxColArray[k]);
                    if (position.isValid(this)) {
                        if (isPositionMine(position)) {
                            grid[i][j].mineAround++;
                        }
                    }
                }
            }
        }
    }

    private int findPositionValue(char position) {
        int index = 0;
        for (int i = 0; i < letters.length; i++){
            if (position == letters[i]){
                index = i;
            }
        }
        return index;
    }

    public static boolean isValidLetter(char choice) {
        for (char letter : letters) {
            if (choice == letter) {
                return true;
            }
        }
        return false;
    }

    private void addFlag() {
        flags++;
    }

    private void minusFlag() {
        flags--;
    }

    public boolean isFlag(Position position) {
        return grid[position.row][position.col].isFlag;
    }

    public void beFlag(Position position) {
        addFlag();
        grid[position.row][position.col].isFlag = true;
    }

    public void removeFlag(Position position) {
        minusFlag();
        grid[position.row][position.col].isFlag = false;
    }

    public boolean getMaxFlag() {
        return flags == MINE_NUMBER;
    }

    public boolean isTileDiscover(Position position) {
        return grid[position.row][position.col].isDiscover;
    }

    public boolean isPositionMine(Position position) {
        return grid[position.row][position.col].isMine;
    }

    public boolean isPositionTreasure(Position position) {
        return grid[position.row][position.col].isTreasure;
    }

    public int addTileToScore(Position position) {
        return grid[position.row][position.col].tileValue;
    }

    public void tileBeDiscover(Position position) {
        grid[position.row][position.col].isDiscover = true;
    }

    public int getTreasureValue(Position position) {
        return grid[position.row][position.col].treasureValue;
    }

    public int getTileNumber(Position position) {
        return grid[position.row][position.col].mineAround;
    }

    public int getMines() {
        return MINE_NUMBER;
    }

    public int getFlags() {
        return flags;
    }

    public int[] getProxRowArray() {
        return proxRowArray;
    }

    public int[] getProxColArray() {
        return proxColArray;
    }

    public char[] getLettersArray() {
        return letters;
    }

    public int getGridRow() {
        return GRID_ROW;
    }

    public int getGridCol() {
        return GRID_COL;
    }

    public int getTilesAround() {
        return TILES_AROUND;
    }

}
