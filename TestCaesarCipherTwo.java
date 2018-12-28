
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
 
public class TestCaesarCipherTwo {
    
       
    public String breakCaesarCipherTwoKey(String encrypted) {
        TestCaesarCipherOne ccOneTest1 = new TestCaesarCipherOne();
        TestCaesarCipherOne ccOneTest2 = new TestCaesarCipherOne();
        String halfOne = halfOfString(encrypted, 0);
        String halfTwo = halfOfString(encrypted, 1);
        String halfOneDecryp = ccOneTest1.breakCaesarCipherOneKey(halfOne);
        String halfTwoDecryp = ccOneTest2.breakCaesarCipherOneKey(halfTwo);
        String result = joinTwoStrings(halfOneDecryp,halfTwoDecryp);
        System.out.println("result is  \t " + result);
        return result;        
    }
    
    private String joinTwoStrings(String messageOne, String messageTwo ) {
        String result = "";
        for (int k=0; k < messageOne.length(); k+=1) {
            char currCharOne = messageOne.charAt(k);
            if (k==messageOne.length()-1 && k==messageTwo.length()) {
                result = result + currCharOne;
            }
            else {
                char currCharTwo = messageTwo.charAt(k);
                result = result + currCharOne + currCharTwo;
             }
            
        }
        return result.toString();
    }
    
    private String halfOfString(String message, int start) {
        StringBuilder result = new StringBuilder();
        for (int k=start; k < message.length(); k+=2) {
            char currChar = message.charAt(k);
            result.append(currChar);
        }
        //System.out.println("the half string is \t:\t" + result.toString());
        return result.toString();
    }

    private int maxIndex ( int[] counts) {
        int maxCount =0;
        int idxE = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//test
        for (int k=0; k< counts.length; k++) {
            int currCount = counts[k];
            if (currCount > maxCount) {
                maxCount = currCount;
                idxE = k;
            }
        }
        //System.out.println("index of E is \t" + idxE);
        return idxE;
    }
    
    private int[]countLetters(String encrypted){
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
    public void TestsbreakCaesarCipher() {
        FileResource fr = new FileResource();
        String s= fr.asString();
        String sdcry = breakCaesarCipherTwoKey(s);
        System.out.println(sdcry);
    }
    
    public void simpleTests() {
        FileResource fr = new FileResource();
        String testString = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(17,3);
        String encrypted = cc.encryptTwoKey(testString);
        System.out.println("the encrypted string is \t:\t " + encrypted);
        String decrypted = cc.decryptTwoKey(encrypted);
        System.out.println("the first decrypted string is \t:\t " + decrypted);
        String decrypted2 =  breakCaesarCipherTwoKey(encrypted);
        System.out.println("the second decrypted string is \t:\t " + decrypted2);
    }
       
}
