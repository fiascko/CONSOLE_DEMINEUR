package com.cegepst;

public class Position {

    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isValid(Grid grid) {
        return (row >= 0 && row < grid.getGridRow() && col >= 0 && col < grid.getGridCol());
    }

}
