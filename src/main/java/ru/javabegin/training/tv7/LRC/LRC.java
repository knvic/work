package ru.javabegin.training.tv7.LRC;

public class LRC {

    public  byte calculateLRC(byte[] bytes) {
        byte LRC = 0;
        for (int i = 0; i < bytes.length; i++) {
            LRC ^= bytes[i];
        }
        return LRC;
    }


    public byte getLRCCheckSum(final byte[] data, int offset, int length) {
        byte checkSum = 0;

        if(data == null) {
            throw new NullPointerException("Argument data can not be null !");
        }
        if((offset < 0) || (length < 0) || (length > (data.length - offset))) {
            throw new IndexOutOfBoundsException("Index violation detected !");
        }
        if(!(data instanceof byte[])) {
            throw new IllegalArgumentException("Argument data is not byte type array !");
        }

        for (int i = offset; i < offset + length; i++) {
            checkSum ^= data[i];
        }

        return checkSum;
    }

    public void lrcHex(){



    }

}
