 
/**
 * Write a description of TestCaeserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 
public class TestCaesarCipherOne {
       
    public String breakCaesarCipherOneKey(String input) {
        int[] freqs = countLetters(input);
        int maxIndex = maxIndex(freqs);
        int key = 0;
        if (maxIndex < 4) {
            key = 26- (4-maxIndex);
        }
        else {
            key = maxIndex - 4;
        }
        System.out.println("The key is \t:\t" + key);
        CaesarCipherOne cc = new CaesarCipherOne(key);
        String decrypted = cc.decryptOneKey(input);
        //System.out.println("the decrypted string inside BreakMethod is \t:\t " + decrypted);
        return decrypted;
    }
    
    
    public int maxIndex( int[] counts) {
        int maxCount =0;
        int idxE = 0;
        for (int k=0; k< counts.length; k++) {
            int currCount = counts[k];
            if (currCount > maxCount) {
                maxCount = currCount;
                idxE = k;
            }
        }
        return idxE;
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
    
    //TEST
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String testString = fr.asString();
        CaesarCipherOne cc = new CaesarCipherOne(18);
        String encrypted = cc.encryptOneKey(testString);
        System.out.println("the encrypted string is \t:\t " + encrypted);
        String decrypted = cc.decryptOneKey(encrypted);
        System.out.println("the decrypted string is \t:\t " + decrypted);
        String decrypted2 = breakCaesarCipherOneKey(encrypted);
        System.out.println("the second decrypted string is \t:\t " + decrypted2);
    }
}
