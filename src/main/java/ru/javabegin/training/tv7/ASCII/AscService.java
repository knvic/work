package ru.javabegin.training.tv7.ASCII;

import java.util.List;

public interface AscService {


    List<String> enctypt(List<String> hexList);
    List<String> dectypt(String str);
    String convertStringToHex(String str);
    String convertHexToString(String hex);

    /**
     *Метод преобразует цепочку HEX символoв. Каждый HEX символ (двоичный байт, напр 27(0x1B)) преобразуется
     * в пару Hex символов.27(0x1B) - ‘1’ (0x31 - символьное представление старших 4-х битов) и ‘B’ (0x42 - символьное представление младших 4-х битов).
     * т.е. 3142
     * @param asciiValue
     * @return Строковую последовательнойсть HEX
     */
    String asciiToHex(String asciiValue);

    /**
     * Как и предыдущий метод, только возвращает List<String> из hex символов
     * @param asciiValue
     * @return
     */
    List<String> asciiToHexlist(String asciiValue);

    String toHex02X(int num);
    String toHex04X(int num);
    int hexToDec(String hex);
    byte[] hex2ByteArray( String hexString );
    String hex_to_ascii(String hex);

    void toHex(String str);
    List<String> toTv7(String str);


}
