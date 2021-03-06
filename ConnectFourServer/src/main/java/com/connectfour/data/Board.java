package com.connectfour.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will deal with the game logic of connect four.  
 *  
 * @author Seb
 * @author Anthony
 */
public class Board {
    private final static Logger LOG = LoggerFactory.getLogger(Board.class);
    private byte[][] board = new byte[6][7];

    public Board() {
    }

    /**
     * Return a deep copy of the board     
     *      
     * @return board
     * @author Seb
     */
    public byte[][] getBoard() {
        return deepCopy2D(board);
    }

    /**
     * Return true if the board is full      
     *      
     * @return true if board is complete
     * @author Seb
     */
    public boolean isComplete() {
        for (int i = 0; i < board.length; i++) {
            if (this.checkIfPossibleMove((byte) i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a player has connected four pieces on the board      
     *      
     * @return true if valid connect four      
     * @author Seb
     */
    public boolean checkIfWin() {
        LOG.debug("--- Testing Horizontal ---");
        byte player;
        int counter;
        for (int i = 0; i < board.length; i++) {
            player = board[i][0];
            counter = 0;
            for (int j = 0; j < board[1].length; j++) {
                if (board[i][j] == player && player != (byte) 0) {
                    counter++;
                    if (counter == 4) {
                        LOG.info("Found a horizontal win");
                        return true;
                    }
                } else {
                    counter = 1;
                    player = board[i][j];
                }
            }
        }
        LOG.debug("--- Testing Vertical ---");
        for (int j = 0; j < board[1].length; j++) {
            player = board[0][j];
            counter = 0;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == player && player != (byte) 0) {
                    counter++;
                    if (counter == 4) {
                        LOG.info("Found a vertical win");
                        return true;
                    }
                } else {
                    player = board[i][j];
                    counter = 1;
                }
            }
        }
        LOG.debug("--- Testing Diagonal Up ---");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                player = board[i][j];
                if (player == board[i + 1][j + 1] && player == board[i + 2][j + 2] && player == board[i + 3][j + 3] && player != (byte) 0) {
                    LOG.info("Found a diagonal win");
                    return true;
                }
            }
        }
        LOG.debug("--- Testing Diagonal Down ---");
        for (int i = 5; i > 2; i--) {
            for (int j = 0; j < 4; j++) {
                player = board[i][j];
                if (player == board[i - 1][j + 1] && player == board[i - 2][j + 2] && player == board[i - 3][j + 3] && player != (byte) 0) {
                    LOG.info("Found a diagonal win");
                    return true;
                }
            }
        }
        LOG.info("No win conditions found");
        return false;
    }

    /**
     * Verifies if a move can be made for the selected line
     *
     * @param line of the board
     * @return true the column is not full
     * @author Seb
     */
    public boolean checkIfPossibleMove(int line) {
        return (board[5][line] == (byte) 0);
    }

    /**
     * Add a piece to the board
     *
     * @param line   of the board
     * @param player representing the player
     * @return boolean showing if it succeeded or not
     * @author Seb
     */
    public boolean addMove(byte line, byte player) {
        if (checkIfPossibleMove(line)) {
            for (int j = 0; j < board[1].length; j++) {
                if (board[j][line] == (byte) 0) {
                    board[j][line] = player;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This will let the computer decide what is the best possible move for itself is.
     *
     * @return an int representing what would be the best move for it.
     * @author Anthony
     */
    public byte computerMove() {
        int[] points = new int[7];
        byte line = 0;
        //Evaluating each line for points
        while (line < 7) {
            points[line] = evaluatePoints(line);
            line++;
        }
        List<Integer> choice = new ArrayList<>();
        choice.add(0);
        line = 1;
        //Iterating through each line to see which options are the best
        while (line < 7) {
            if (points[choice.get(0)] <= points[line]) {
                if (points[choice.get(0)] < points[line]) {
                    choice.clear();
                }
                choice.add((int) line);
            }
            line++;
        }
        int best;
        /**
         * If there is more than 1 option for the computer, then it randomly
         * decides its move.
         */
        if (choice.size() == 1) {
            best = choice.get(0);
        } else {
            Random rand = new Random();
            best = choice.get(rand.nextInt(choice.size() - 1));
        }
        return (byte) best;
    }

    /**
     * Makes a deep copy of a 2D array
     *
     * @param original array
     * @return copy of array
     * @author Anthony
     */
    private byte[][] deepCopy2D(byte[][] original) {
        if (original == null) {
            return null;
        }
        byte[][] result = new byte[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }

    /**
     * This will check if the board has a possible win
     *
     * @param line representing the line that is to be checked.
     * @return a boolean representing if it is possible to win or not.
     * @author Anthony
     */
    private boolean checkIfPossibleWin(byte line, byte player) {
        byte YAxis = getAvailableYAxis(line);
        /**
         * The first possibility is that the piece is the final piece of the
         * connect, so this part will deal with finishing lines.
         **/
        if (line <= 3) {
            if (board[YAxis][line + 1] == player && board[YAxis][line + 2] == player && board[YAxis][line + 3] == player) {
                return true;
            }
        }
        if (line >= 3) {
            if (board[YAxis][line - 1] == player && board[YAxis][line - 2] == player && board[YAxis][line - 3] == player) {
                return true;
            }
        }
        if (YAxis >= 3) {
            if (board[YAxis - 1][line] == player && board[YAxis - 2][line] == player && board[YAxis - 3][line] == player) {
                return true;
            }
            if (line <= 3) {
                if (board[YAxis - 1][line + 1] == player && board[YAxis - 2][line + 2] == player && board[YAxis - 3][line + 3] == player) {
                    return true;
                }
            }
            if (line >= 3) {
                if (board[YAxis - 1][line - 1] == player && board[YAxis - 2][line - 2] == player && board[YAxis - 3][line - 3] == player) {
                    return true;
                }
            }
        }
        if (YAxis <= 2) {
            if (line <= 3) {
                if (board[YAxis + 1][line + 1] == player && board[YAxis + 2][line + 2] == player && board[YAxis + 3][line + 3] == player) {
                    return true;
                }
            }
            if (line >= 3) {
                if (board[YAxis + 1][line - 1] == player && board[YAxis + 2][line - 2] == player && board[YAxis + 3][line - 3] == player) {
                    return true;
                }
            }
        }
        /**
         * This second portion deals with the case that the piece falls in
         * between to be a line.
         */
        if (line >= 1 && line <= 4) {
            if (board[YAxis][line - 1] == player && board[YAxis][line + 1] == player && board[YAxis][line + 2] == player) {
                return true;
            }
            if (YAxis >= 1 && YAxis <= 3) {
                if (board[YAxis - 1][line - 1] == player && board[YAxis + 1][line + 1] == player && board[YAxis + 2][line + 2] == player) {
                    return true;
                }
            }
            if (YAxis <= 4 && YAxis >= 2) {
                if (board[YAxis + 1][line - 1] == player && board[YAxis - 1][line + 1] == player && board[YAxis - 2][line + 2] == player) {
                    return true;
                }
            }
        }
        if (line <= 5 && line >= 2) {
            if (board[YAxis][line + 1] == player && board[YAxis][line - 1] == player && board[YAxis][line - 2] == player) {
                return true;
            }
            if (YAxis >= 1 && YAxis <= 3) {
                if (board[YAxis - 1][line + 1] == player && board[YAxis + 1][line - 1] == player && board[YAxis + 2][line - 2] == player) {
                    return true;
                }
            }
            if (YAxis <= 4 && YAxis >= 2) {
                if (board[YAxis + 1][line + 1] == player && board[YAxis - 1][line - 1] == player && board[YAxis - 2][line - 2] == player) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This will check if the computer can block an opponent.
     *
     * @param line representing if it is possible to block an opponent or not
     * @return a boolean representing if it is possible to block or not.
     * @author Anthony
     */
    private boolean checkIfPossibleBlock(byte line, byte opponent) {
        /**          *
         * By checking if the opponent can win, we can easily save time
         * by blocking that possibility.
         * */
        return (checkIfPossibleWin(line, opponent));
    }

    /**
     * This will return the point amount of a given position            
     *
     * @param line representing the line that is to be evaluated      
     * @return an int representing how many points a line has
     * @author Anthony
     */
    private int evaluatePoints(byte line) {
        //Automatic assumptions get checked first
        if (!(checkIfPossibleMove(line))) {
            return -1;
        }
        if (checkIfPossibleWin(line, PacketInfo.PLAYER_TWO)) {
            return 100;
        }
        if (checkIfPossibleBlock(line, PacketInfo.PLAYER_ONE)) {
            return 50;
        }
        int YAxis = getAvailableYAxis(line);
        if (YAxis == -1) {
            throw new IllegalArgumentException("Somehow a line that was said to" + " be able to have an available space does not have an" + "available space");
        }
        /**
         * If previous options don't get thrown, then the evaluated points is
         * based on the amount of other pieces surrounding the location, such
         * that there are possibilities to block or to get a better position for
         * an automatic win. The point is known as the line given and the next
         * available placement
         */
        int points = 0;
        //Checking downwards from point
        if (YAxis > 0) {
            if (board[YAxis - 1][line] == PacketInfo.PLAYER_ONE || board[YAxis - 1][line] == PacketInfo.PLAYER_TWO) {
                points++;
            }
            if (YAxis > 1) {
                if (board[YAxis - 2][line] == PacketInfo.PLAYER_ONE || board[YAxis - 2][line] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
            }
        }
        //Checking the right side of the given points (sideways, diagonal)
        if (line < 6) {
            if (board[YAxis][line + 1] == PacketInfo.PLAYER_ONE || board[YAxis][line + 1] == PacketInfo.PLAYER_TWO) {
                points++;
            }
            if (line < 5) {
                if (board[YAxis][line + 2] == PacketInfo.PLAYER_ONE || board[YAxis][line + 2] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
            }
            if (YAxis > 0) {
                if (board[YAxis - 1][line + 1] == PacketInfo.PLAYER_ONE || board[YAxis - 1][line + 1] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
                if (YAxis > 1 && line < 5) {
                    if (board[YAxis - 2][line + 2] == PacketInfo.PLAYER_ONE || board[YAxis - 2][line + 2] == PacketInfo.PLAYER_TWO) {
                        points++;
                    }
                }
            }
            if (YAxis < 5) {
                if (board[YAxis + 1][line + 1] == PacketInfo.PLAYER_ONE || board[YAxis + 1][line + 1] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
                if (YAxis < 4 && line < 5) {
                    if (board[YAxis + 2][line + 2] == PacketInfo.PLAYER_ONE || board[YAxis + 2][line + 2] == PacketInfo.PLAYER_TWO) {
                        points++;
                    }
                }
            }
        }
        //Checking the left side of the given points (sideways, diagnol)
        if (line > 0) {
            if (board[YAxis][line - 1] == PacketInfo.PLAYER_ONE || board[YAxis][line - 1] == PacketInfo.PLAYER_TWO) {
                points++;
            }
            if (line > 1) {
                if (board[YAxis][line - 2] == PacketInfo.PLAYER_ONE || board[YAxis][line - 2] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
            }
            if (YAxis > 0) {
                if (board[YAxis - 1][line - 1] == PacketInfo.PLAYER_ONE || board[YAxis - 1][line - 1] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
                if (YAxis > 1 && line > 1) {
                    if (board[YAxis - 2][line - 2] == PacketInfo.PLAYER_ONE || board[YAxis - 2][line - 2] == PacketInfo.PLAYER_TWO) {
                        points++;
                    }
                }
            }
            if (YAxis < 5) {
                if (board[YAxis + 1][line - 1] == PacketInfo.PLAYER_ONE || board[YAxis + 1][line - 1] == PacketInfo.PLAYER_TWO) {
                    points++;
                }
                if (YAxis < 4 && line > 1) {
                    if (board[YAxis + 2][line - 2] == PacketInfo.PLAYER_ONE || board[YAxis + 2][line - 2] == PacketInfo.PLAYER_TWO) {
                        points++;
                    }
                }
            }
        }
        LOG.info("Calculated points = " + points);
        return points;
    }

    /**
     * This will check to see what is the next empty Y axis of a given line is
     *
     * @param line representing the X axis to check      
     * @return an int representing the line to chose.
     * @author Anthony
     */
    private byte getAvailableYAxis(byte line) {
        int YAxis = 0;
        while (true) {
            if (board[YAxis][line] == (byte) 0) {
                break;
            }
            YAxis++;
            if (YAxis >= 6) {
                YAxis = -1;
                break;
            }
        }
        return (byte) YAxis;
    }
}

