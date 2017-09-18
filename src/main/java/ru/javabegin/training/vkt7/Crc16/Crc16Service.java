package ru.javabegin.training.vkt7.Crc16;

import java.util.List;

/**
 * Created by Николай on 18.08.2017.
 */
public interface Crc16Service {
    boolean crc16_valid(List<String> items);
   List<String> crc16_t(List<String> items);
    //  String addcrc16(String s);
  //  String addcrc16(LinkedList<String> data);
}
