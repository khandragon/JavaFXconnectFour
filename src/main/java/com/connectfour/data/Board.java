package com.connectfour.data;

import java.util.Arrays;

/**
 * @author Seb
 */
public class Board {
    private char[][] board = new char[6][7];/*{{'f','f','f','f','f','f', 'f'},
            {'e','e','e','e','e','e', 'e'},
            {'d','d','d','d','d','d', 'd'}, 
            {'c','c','c','c','c','c', 'c'},
            {'b','b','b','b','b','b', 'b'},
            {'a','a','a','a','a','a', 'a'}}; */
    public Board(){}
    
    
    public boolean checkIfPossibleMove(int line){
        return true;
    }
    
    public char[][] getBoard(){
        return deepCopy2D(board);
    }
    
    public boolean checkIfDone(){ // board completed?
        return true;
    }
            
    public boolean checkIfWin(){ // no need for player
        printBoard();
        
        return true;
    }
    
    public boolean checkIfTie(){ // needed?
        return true;
    }
    
    public void addMove(int line, char player){ // X and O?
        if(checkIfPossibleMove(line)){
            for(int j=0; j<board[1].length; j++){
                if((board[j][line] == '\0')){
                    board[j][line] = player;
                    return;
                }
            }
                
        }
    }
    
    //test method
    private void printBoard(){
        System.out.println(board.length + " print i" );
        for(int i = board.length; i>0; i--){
            for(int j = 0; j<board[1].length; j++){
                System.out.print(board[i][j]+ "i:" + i+ "j:" + j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * Makes a deep copy of a 2D array
     * 
     * @param original array
     * @return copy of array
     */
    private char[][] deepCopy2D(char[][] original) {
    if (original == null) {
        return null;
    }
    char[][] result = new char[original.length][];
    for (int i = 0; i < original.length; i++) {
        result[i] = Arrays.copyOf(original[i], original[i].length);
    }
        return result;
    }
}
