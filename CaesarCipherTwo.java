
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 
public class CaesarCipherTwo {
   
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
   public CaesarCipherTwo (int key1,int key2) {
       alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
       shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
       mainKey1 = key1;
       mainKey2 = key2;
   }
   
   public String encryptTwoKey(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for (int k=0;k < encrypted.length(); k+=1){
            if (k%2==0) {
                char newChar = encryptChar( k,encrypted, mainKey1,shiftedAlphabet1);
                encrypted.setCharAt(k, newChar); 
            }
            
            if (k%2==1) {
                char newChar = encryptChar( k,encrypted, mainKey2,shiftedAlphabet2);
                encrypted.setCharAt(k, newChar); 
            }
        }
        //System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    
   public char encryptChar( int input ,StringBuilder encrypted, int key, String shiftedAlp) {
         
       char currChar = encrypted.charAt(input);
       if (Character.isUpperCase(currChar)) { 
           int idx = alphabet.indexOf(currChar);
           if (idx !=-1) {
               currChar = shiftedAlp.charAt(idx);             
           } 
       } 
            
       if (Character.isLowerCase(currChar)) { 
          int idx = alphabet.indexOf(Character.toUpperCase(currChar));
          if (idx !=-1) {
          currChar = Character.toLowerCase(shiftedAlp.charAt(idx));                
          } 
       }       
       return currChar;
    }
   
    
   public String decryptTwoKey(String encrypted) {
        int dkey1 = 26 - mainKey1; 
        int dkey2 = 26 - mainKey2;
        CaesarCipherTwo cct= new CaesarCipherTwo (dkey1, dkey2);
        String decrypted = cct.encryptTwoKey(encrypted);
        //System.out.println("The decrpted string is \t:\t" + decrypted);
        return decrypted;
   }
   
}
