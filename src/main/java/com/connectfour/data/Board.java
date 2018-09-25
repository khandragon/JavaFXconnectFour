package com.connectfour.data;

import java.util.Arrays;

/**
 * @author Seb
 */
public class Board {
    private char[][] board = new char[6][7];
    public Board(){}
    //TODO:
    //logs
    //make methods private/remove if not needed
    //make more selon request~
    
    
    /**
     * Return a deep copy of the board
     * 
     * @return  board
     */
    public char[][] getBoard(){
        return deepCopy2D(board);
    }
    
    /**
     * Return true if the board is full
     * 
     * @return true if board is complete 
     */
    public boolean isComplete(){ 
        for(int i = 0; i < board.length; i++){
            if(this.checkIfPossibleMove(i)){
                return false;
            }
        }
        return true;
    }
            
    /**
     * Checks if a player has connected four pieces on the board
     * 
     * @return true if valid connect four
     */
    public boolean checkIfWin(){ 
        // Horizontal
        System.out.println("--- Testing Horizontal ---");
        char player;
        int counter;
        for(int i=0; i<board.length; i++){
            player = board[i][0];
            counter = 0;
            for(int j=0; j<board[1].length; j++){
                if(board[i][j] == player && player != '\0'){
                    counter++;
                    if (counter == 4){
                        System.out.println();
                        return true;
                    }
                } else {
                    counter = 1;
                    player = board[i][j];
                }
            }
        }
        
        // Vertical
        System.out.println("--- Testing Vertical ---");
        for(int j=0; j<board[1].length; j++){
            player = board[0][j];
            counter = 0;
            for(int i=0; i<board.length; i++){
                if(board[i][j] == player && player != '\0'){
                   counter++;
                   if (counter == 4){
                       System.out.println();
        
                       return true;
                   }
                } else {
                    player = board[i][j];
                    counter = 1;
                }
            }
        }
        
        // Diagonal Up
        System.out.println("--- Testing diagonal up ---");
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                player = board[i][j];
                if(player == board[i+1][j+1]
                        && player == board[i+2][j+2]
                        && player == board[i+3][j+3]){
                    System.out.println();
        
                   return true;
                }
            }
        }
        
        // Diagonal Down
        System.out.println("--- Testing diagonal down ---");
        for(int i=5; i>2; i--){
            for(int j=0; j<4; j++){
                player = board[i][j];
                if(player == board[i-1][j+1]
                        && player == board[i-2][j+2]
                        && player == board[i-3][j+3]){
                    System.out.println();
        
                    return true;
                }
            }
        }
        System.out.println();
        return false;
    }
    
    /**
     * Returns true if the game has ended in a tie (no possible moves, no win)
     * 
     * @return true if board is a tie
     */
    public boolean checkIfTie(){ 
        for(int i=0; i < 7; i++){
            if(checkIfPossibleMove(i) == true || this.checkIfWin()){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Verifies if a move can be made for the selected line
     * 
     * @param line of the board
     * @return true the column is not full
     */
    public boolean checkIfPossibleMove(int line){
        return (board[5][line]  == '\0');
    }
    
    /**
     * Add a piece to the board
     * 
     * @param line of the board 
     * @param player representing the player
     */
    public void addMove(int line, char player){ 
        if(checkIfPossibleMove(line)){
            for(int j=0; j<board[1].length; j++){
                if(board[j][line] == '\0'){
                    board[j][line] = player;
                    return;
                }
            }
        }
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
    
    //test method
    private void printBoard(){
        for(int i = board.length-1; i>=0; i--){
            for(int j = 0; j<board[1].length; j++){
                System.out.print(board[i][j] + "-");
            }
            System.out.println();
        }
        System.out.println();
    }
}
