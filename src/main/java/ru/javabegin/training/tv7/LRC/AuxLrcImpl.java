package ru.javabegin.training.tv7.LRC;

public class AuxLrcImpl {
    public byte[] hex2ByteArray( String hexString ) {
        String hexVal = "0123456789ABCDEF";
        byte[] out = new byte[hexString.length() / 2];

        int n = hexString.length();

        for( int i = 0; i < n; i += 2 ) {
            //make a bit representation in an int of the hex value
            int hn = hexVal.indexOf( hexString.charAt( i ) );
            int ln = hexVal.indexOf( hexString.charAt( i + 1 ) );

            //now just shift the high order nibble and add them together
            out[i/2] = (byte)( ( hn << 4 ) | ln );
        }

        return out;
    }


    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }



    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)+ Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }



}
