package ru.javabegin.training.tv7.NS;

import java.util.List;

public interface NsService {
    StringBuilder hexToBin(String hex,int size);
    List<String> nsT (String hex);
    List<String> nsTV (String hex);
    List<String> nsDP (String hex);
    List<String> nsSE (String hex);


}
