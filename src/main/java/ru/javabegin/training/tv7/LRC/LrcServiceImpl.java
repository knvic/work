package ru.javabegin.training.tv7.LRC;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class LrcServiceImpl implements LrcService{

    @Override
    public List<String> lrcAdd(List<String> hexList){
        int sum = 0;
        for (String hex : hexList) {
            sum += Integer.parseInt(hex, 16);
            if (sum > 255) {
                sum -= 256;
            }
        }

       /* String lrc = Integer.toHexString(256 - sum);
        System.out.println("lrc = " + lrc);*/

        String lrc=format("%02X", 256-sum);
        System.out.println("hex = " + lrc);

        hexList.add(lrc);

        return hexList;
    }

    @Override
    public  boolean lrcCheck(List<String> hexListIn){
         boolean checksumm=false;
        int sum = 0;
       /* for (String hex : hexListIn) {
            sum += Integer.parseInt(hex, 16);
            if (sum > 255) {
                sum -= 256;
            }
        }*/
        System.out.println("hexListIn-1 " + hexListIn.get(hexListIn.size()-1));
        for(int i=0; i<hexListIn.size()-1; i++){
            sum += Integer.parseInt(hexListIn.get(i), 16);
            if (sum > 255) {
                sum -= 256;
            }
        }

        String lrc=format("%02X", 256-sum);
        System.out.println("hex = " + lrc);
        if(hexListIn.get(hexListIn.size()-1).equals(lrc)){
            checksumm=true;
        }


        return checksumm;
    }


}
