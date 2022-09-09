package sudoku.computationalLogic;

import sudoku.GameState.GameState;
import sudoku.GameState.Rows;
import sudoku.problemDomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static sudoku.GameState.Rows.TOP;
import static sudoku.problemDomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int[][] grid){
        if(sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if(tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid)) return true;
        if(columnsAreInvalid(grid)) return true;
        if(squaresAreInvalid(grid)) return true;
        else return false;

    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for(int y = 0; y < GRID_BOUNDARY; y++){
            List<Integer> row = new ArrayList<>();
            for(int x = 0; x < GRID_BOUNDARY; x++) {
                row.add(grid[x][y]);
            }

            if(collectionHasRepeats(row)) return true;
        }
        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for(int x = 0; x < GRID_BOUNDARY; x++){
            List<Integer> row = new ArrayList<>();
            for(int y = 0; y < GRID_BOUNDARY; y++) {
                row.add(grid[x][y]);
            }

            if(collectionHasRepeats(row)) return true;
        }
        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowOfSqureIsInvalid(TOP, grid)) return true;
        if(rowOfSqureIsInvalid(Rows.MIDDLE, grid)) return true;
        if(rowOfSqureIsInvalid(Rows.BOTTOM, grid)) return true;
        else return false;
    }

    private static boolean rowOfSqureIsInvalid(Rows value, int[][] grid) {
        switch(value){
            case TOP:
                if(squareIsInvalid(0, 0, grid)) return true;
                if(squareIsInvalid(0, 3, grid)) return true;
                if(squareIsInvalid(0, 6, grid)) return true;
                return false;
            case MIDDLE:
                if(squareIsInvalid(3, 0, grid)) return true;
                if(squareIsInvalid(3, 3, grid)) return true;
                if(squareIsInvalid(3, 6, grid)) return true;
                return false;
            case BOTTOM:
                if(squareIsInvalid(6, 0, grid)) return true;
                if(squareIsInvalid(6, 3, grid)) return true;
                if(squareIsInvalid(6, 6, grid)) return true;
                return false;
            default:
                return false;
        }
    }

    public static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex +3;

        List<Integer> square = new ArrayList<>();

        while(yIndex < yIndexEnd){
            while(xIndex < xIndexEnd){
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }
            xIndex += 3;
            yIndex++;
        }
        if(collectionHasRepeats(square)) return true;
        return false;
    }

    public static boolean collectionHasRepeats(List<Integer> collection) {
        for(int i = 1; i <= GRID_BOUNDARY; i++){
            if(Collections.frequency(collection, i)>1) return true;
        }
        return false;
    }

    public static boolean tilesAreNotFilled(int[][] grid) {
        for(int x = 0; x < GRID_BOUNDARY; x++){
            for(int y = 0; y < GRID_BOUNDARY; y++) {
                if (grid[x][y] == 0) return true;
            }
        }
        return false;
    }
}
