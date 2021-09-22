package com.cegepst;

public class Game {

    private final static int GAMELOOP = 0;
    private final static int RAGEQUIT = 1;
    private final static int  EXPLODED = 2;
    private final static int WIN = 3;

    private Grid grid;
    private Time time;
    private Score score;
    private int gameOver = 0;

    public Game() {
        this.time = new Time();
        this.score = new Score();
        this.grid = new Grid();
        startGameLoop();
        endGame();
    }

    private void startGameLoop() {
        do {
            grid.showGrid();
            menuInfos();
            menuActions();
            if (grid.isWin()) {
                winTheGame();
            }
        } while (gameOver == GAMELOOP);
    }

    private void menuInfos() {
        time.showTimer();
        Console.showActualScore(score.getScore(), score.getHighScore(), score.getHighUser());
        Console.showRemainingMines(grid.getMines() - grid.getFlags());
    }

    private void menuActions() {
        Console.showGameLoopQuestion();
        int action = Read.readIntChoice();
        gameActionsChoice(action);
    }

    private void endGame() {
        Console.showGameOverText(gameOver);
        if (gameOver != RAGEQUIT){
            score.saveUserScore();
        }
    }

    private void gameActionsChoice(int action) {
        Position position;
        switch (action) {
            case 0 -> {
                Console.showCheatQuestion();
                Console.cheat = Read.readBooleanChoice();
            }
            case 1 -> {
                position = enterPosition();
                grid.placeFlag(position);
            }
            case 2 -> {
                position = enterPosition();
                score.resetTreasureScore();
                discover(position);
                addTreasuresToScore();
            }
            case 3 -> score.createScoreList();
            case 4 -> gameOver = RAGEQUIT;
        }
    }

    private Position enterPosition() {
        Position position = new Position(grid.findPosition("ROW"), grid.findPosition("COL"));
        Console.showLastPositionEnter(position, grid);
        return position;
    }

    public void discover(Position position) {
        if (!grid.isFlag(position)) {
            if (!grid.isTileDiscover(position)) {
                boolean stopDiscovery = isGameOver(position);
                if (!stopDiscovery) {
                    cumulativeDiscovery(position);
                }
            } else {
                Console.showAlreadyOpenTileAlert();
            }
        } else {
            Console.showDiscoverFlagAlert();
        }
    }

    private void cumulativeDiscovery(Position position) {
        if (grid.isFlag(position) || grid.isPositionMine(position)) {
            return;
        }
        if (!grid.isTileDiscover(position)) {
            discoverTile(position);
        }
    }

    private void discoverTile(Position position){
        grid.tileBeDiscover(position);
        score.updateScore(position, grid);
        if (grid.getTileNumber(position) == 0) {
            for (int k = 0; k < grid.getTilesAround(); k++) {
                Position newPosition = new Position( position.row + grid.getProxRowArray()[k], position.col + grid.getProxColArray()[k]);
                if (newPosition.isValid(grid)) {
                    cumulativeDiscovery(newPosition);
                }
            }
        } else {
            return;
        }
    }

    private void addTreasuresToScore() {
        if (score.getTreasuresScoreUpdate() != 0){
            Console.showTreasureAlert(score.getTreasuresScoreUpdate());
            score.setScore(score.getTreasuresScoreUpdate());
        }
    }

    private boolean isGameOver(Position position) {
        if (grid.isPositionMine(position)){
            loseTheGame();
            return true;
        }
        return false;
    }

    private void winTheGame() {
        gameOver = WIN;
        score.updateScoreByTimer(time.getMinutes());
        Console.showActualScore(score.getScore(), score.getHighScore(), score.getHighUser());
    }

    private void loseTheGame() {
        gameOver = EXPLODED;
        Console.showActualScore(score.getScore(), score.getHighScore(), score.getHighUser());
    }

}
