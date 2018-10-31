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
        return ByteBuffer.wrap(new byte[8]).putDouble(numDouble).array();
    }

    /**
     * Convert a byte array into a long using the low level method
     *
     * @param arrayByte to convert to a double
     * @return double converted from the byte array
     */
    public static double byteArrayToDouble(byte[] arrayByte) {
        return ByteBuffer.wrap(arrayByte).getDouble();
    }
}