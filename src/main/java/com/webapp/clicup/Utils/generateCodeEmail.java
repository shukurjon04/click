package com.webapp.clicup.Utils;

import lombok.Data;

import java.util.Random;

@Data
public class generateCodeEmail {
    public static String CodeGenerate(int bound){
        return String.valueOf(new Random().nextInt(numbercode(bound)));
    }

    public static int numbercode(int bound){
        String str ="";
        while (bound!=0){
            str = str.concat("9");
            bound--;
        }
        return Integer.parseInt(str);
    }
}
