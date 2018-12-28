

/**
 * Couse 3 week 1 part 1.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (12/25/2018)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
public class CaesarCipherOne {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipherOne(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }

    public String encryptOneKey(String input) {
        StringBuilder sb = new StringBuilder(input);
        for(int k=0;k < sb.length(); k+=1){
            char currChar = sb.charAt(k);
            if (Character.isUpperCase(currChar)) { 
                int idx = alphabet.indexOf(currChar);
                if (idx !=-1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(k, newChar);                
                } 
            } 
            
            if (Character.isLowerCase(currChar)) { 
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx !=-1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(k, Character.toLowerCase(newChar));                
                } 
            }
        }
        //System.out.println(sb.toString());//test print
        return sb.toString();
    }
    
     public int[]countLetters(String encrypted){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counts = new int[26];
        for (int k=0; k<encrypted.length();k++) {
            char ch = Character.toUpperCase(encrypted.charAt(k));
            int idx = alphabet.indexOf(ch);
            if (idx!= -1) {
               counts[idx] ++;
            }
        }
        return counts;
    }
    
    public String decryptOneKey(String input) {
        int key = 26-mainKey;
        CaesarCipherOne cc = new CaesarCipherOne(key);               
        return cc.encryptOneKey(input);
    }
}
