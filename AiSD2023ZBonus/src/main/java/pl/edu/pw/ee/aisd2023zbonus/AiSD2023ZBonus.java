package pl.edu.pw.ee.aisd2023zbonus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AiSD2023ZBonus {

    /*public static int countPalindroms (String text){
        int n = 0;
        if (text.length() >= 9_999)
            return -1;
        int start = 0;
        int end = text.length()-2;
        while (start < end){
            for(int i = start; i < text.length(); i++) {
                String temp = text.substring(start, start + i + 1);
                System.out.println(start + i + 1);
                if(temp.length() == 1){
                } else if ( isPalindrom( temp ) == 1) {
                    n++;
                }
                System.out.println("temp =" + temp + " n = " + n);
            }
            start ++;
        }
        return n;
    }
    public static int countPalindroms (String text){
    public static int isPalindrom(String text){
        for(int i = 0 ; i < text.length(); i++){
            if( text.charAt(i) != text.charAt(text.length()-i-1) ){
                return -1;
            }
        }
        return 1;
    }

     */
    public static int countPalindroms(String text) {
        int n = 0;
        for (int i = 0; i <= text.length(); i++) {
            for (int j = i; j < text.length() - 1; j++) {
                if (isPalindrom(text.substring(i, j + 2))) {
                    n++;
                }
            }
        }
        return n;
    }

    public static boolean isPalindrom(String text) {
        for (int i = 0; i <= text.length() / 2; i++)
            if (text.charAt(i) != text.charAt(text.length() - 1 - i))
                return false;
        return true;
    }


}
