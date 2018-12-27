

/**
 * Couse 3 week 1 part 1.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (12/25/2018)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
public class Encryption {
    
    public String encryptTwoKeys(String input, int key, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for (int k=0;k < encrypted.length(); k+=1){
            if (k%2==0) {
                char newChar = encryptChar( k,encrypted, key);
                encrypted.setCharAt(k, newChar); 
            }
            
            if (k%2==1) {
                char newChar = encryptChar( k,encrypted, key2);
                encrypted.setCharAt(k, newChar); 
            }
        }
        System.out.println(encrypted.toString());
        return encrypted.toString();
    }
    
    public char encryptChar( int input ,StringBuilder encrypted, int key) {
       String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
       char currChar = encrypted.charAt(input);
       if (Character.isUpperCase(currChar)) { 
           int idx = alphabet.indexOf(currChar);
           if (idx !=-1) {
               currChar = shiftedAlphabet.charAt(idx);             
           } 
       } 
            
       if (Character.isLowerCase(currChar)) { 
          int idx = alphabet.indexOf(Character.toUpperCase(currChar));
          if (idx !=-1) {
          currChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));                
          } 
       }
       
       return currChar;
    }
    
    public String encryptOneKey(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        for(int k=0;k < encrypted.length(); k+=1){
            char currChar = encrypted.charAt(k);
            if (Character.isUpperCase(currChar)) { 
                int idx = alphabet.indexOf(currChar);
                if (idx !=-1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(k, newChar);                
                } 
            } 
            
            if (Character.isLowerCase(currChar)) { 
                int idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx !=-1) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    encrypted.setCharAt(k, Character.toLowerCase(newChar));                
                } 
            }
        }
        return encrypted.toString();
    }
    
    
    public String emphasize(String s, char ch) {
        StringBuilder result = new StringBuilder(s);
        for (int k= 0; k< s.length(); k+=1) {
            if (isVowel((s.charAt(k)))==true && s.charAt(k)==ch) {
                if (k%2==0) {
                    result.setCharAt(k,'*');
                }
                else {
                    result.setCharAt(k,'+');
                }
            }        
        }
        String resultString = result.toString();
        System.out.println(resultString);
        return resultString;
    }
    
    
    public String replaceVowel(String s, char ch) {
        StringBuilder result = new StringBuilder(s);
        for( int k =0; k< s.length(); k+=1) {
            if (isVowel((s.charAt(k)))==true) {
                result.setCharAt(k, ch);
            }
        }
        String resultString = result.toString();
        System.out.println(resultString);
        return resultString;
    }
    
    
    public boolean isVowel(char ch) {
        char a = Character.toUpperCase(ch);
        if ( a=='A'||a=='E'||a=='I'||a=='O'||a=='U') {
            return true;
        }    
        return false;
    }
    
    
    //TEST
    
    public void testencryptOneKey() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptOneKey(message, 23);
        System.out.println("key is " + 23 + "\n" + encrypted);
    }

}
