package com.connectfour.app;

import com.connectfour.data.Board;

/**
 * @author Seb
 */
public final class MainApp {

    public void perform() {
        // Create classes
        Board board = new Board();
        
        // Call presentation
    }

    /**
     * Begin Execution
     *
     * @param args
     */
    public static void main(String[] args) {
        MainApp m = new MainApp();
        m.perform();
        System.exit(0);
    }
}