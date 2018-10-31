package com.connectfour.data;

import java.nio.ByteBuffer;

/**
 * Converting a double to an eight-byte array and back to a double
 * using both high level and low level methods.
 *
 * @author Saad Khan
 * @author Seb
 */
public class DoubleToBytes {

    /**
     * Convert a double to a byte array using the high-level method
     *
     * @param numDouble to convert to a byte array
     * @return byte array containing the bytes from the converted double
     */
    public static byte[] doubleToByteArray(double numDouble) {
        // Wrap is used in order to turn the byte array we receive into an object so that we can use
        // the non static methods on the instance of this array.
        // PutDouble turns a double into 8 bytes and adds each byte to its corresponding spot in the
        // ByteBuffer.
        // The array method takes the ByteBuffer object and returns all the bytes that were stored
        // within it as an array.
        return ByteBuffer.wrap(new byte[8]).putDouble(numDouble).array();
    }

    /**
     * Convert a byte array into a long using the low level method
     *
     * @param arrayByte to convert to a double
     * @return double converted from the byte array
     */
    public static double byteArrayToDouble(byte[] arrayByte) {
        //Validate the received byte array to test that it is not invalid
        if(arrayByte == null || arrayByte.length == 0){
            return 0;
        }
        // Wrap is used in order to be able to store the received byte array into a ByteBuffer
        // object so that we can use the non static methods on the instance of this object.
        // GetDouble reads the next 8 bytes in the ByteBuffer object and returns a byte array.
        return ByteBuffer.wrap(arrayByte).getDouble();
    }
}