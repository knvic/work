package ru.javabegin.training.tv7.ASCII;

public class ByteToHex {

    public static void main(String[] argv) {
        //System.out.println(byteToHex((byte) 116));
        System.out.println(byteToHex((byte) 116));
    }

    public static String byteToHex(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i);
    }
}
