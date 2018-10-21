package com.connectfour.data;

/**
 * class containing byte constants to use for certain situations
 *
 * @author Saad
 * @author Anthony
 * @author Seb
 */
public class PacketInfo {

    //specifies the game mode
    public static final byte MOVE = 1;
    public static final byte QUIT = 2;
    public static final byte PLAY = 3;
    public static final byte WIN = 4;
    public static final byte TIE = 5;

    //specifies which player just moved
    public static final byte PLAYER_ONE = 6;
    public static final byte PLAYER_TWO = 7;

    //used as place holder
    public static final byte SPACE = 99;
    
    //board button states
    public static final boolean LOCK_BUTTONS = true;
    public static final boolean UNLOCK_BUTTONS = false;
}
