

/**
 * Couse 3 week 1 part 1.
 * 
 * @author (Anotida George Chigunwe) 
 * @version (12/25/2018)
 */
 import edu.duke.*;
 import java.io.*;
 import org.apache.commons.csv.*;
public class Decrypt {
    
    public String decryptTwoKey(String encrypted) {
        String halfOne = halfOfString(encrypted, 0);
        String halfTwo = halfOfString(encrypted, 1);
        //System.out.println("the 2 halfs encrypted are  \t" + halfOne +"   " + halfTwo);
        String halfOneDecryp = decryptOneKey(halfOne);
        String halfTwoDecryp = decryptOneKey(halfTwo);
        //System.out.println( "the 2 halfs are decrypted  \t" + halfOneDecryp +"   " + halfTwoDecryp);
        String result = joinTwoStrings(halfOneDecryp,halfTwoDecryp);
        System.out.println("result is  \t " + result);
        return result;        
    }
    
    public String decryptOneKey(String encrypted) {
        Encryption encry = new Encryption();
        int[] freqs = countLetters(encrypted);
        int maxCount = maxCount(freqs);
        int dkey = maxCount - 4;        
        System.out.println("The key is \t" + dkey);
        if (maxCount < 4) {
            dkey = 26 - (4-maxCount);
        }
        return encry.encryptOneKey(encrypted, 26-dkey);
    }
    
    public String joinTwoStrings(String messageOne, String messageTwo ) {
        //StringBuilder result = new StringBuilder();
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
        //System.out.println("The joint string is \t" + result);
        return result.toString();
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder result = new StringBuilder();
        for (int k=start; k < message.length(); k+=2) {
            char currChar = message.charAt(k);
            result.append(currChar);
        }
        return result.toString();
    }    
        
    public int maxCount( int[] counts) {
        int maxCount =0;
        int idxE = 0;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//test
        for (int k=0; k< counts.length; k++) {
            int currCount = counts[k];
            if (currCount > maxCount) {
                maxCount = currCount;
                idxE = k;
                //char ch = Character.toUpperCase(alphabet.charAt(k));//test
            }
        }
        System.out.println("index of E is \t" + idxE);
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
    
    public void countWordLengths(FileResource fr, int[] counts){
        for (String word : fr.words()) {
            int startIndex = 0;
            int endIndex = word.length()-1;
            int length= word.length();
            if (!Character.isLetter(word.charAt(startIndex))){
                length=-1;
            }
            
            if (!Character.isLetter(word.charAt(endIndex))){
                length=-1;
            }
            
            if (length >= counts.length) {
                length = counts.length -1;
            }
            
            if (length > 0) {
                counts[length] +=1;
            }
        }
        
        for (int k=0; k< counts.length; k++) {
            System.out.println("words of length " + k + "are /t " + counts[k]);
        }
    }
    
    //TEST
    public void testDecryptTwoKey(){
        FileResource fr = new FileResource();
        String encryped =  fr.asString();
        String decryped = decryptTwoKey(encryped);
        System.out.println("The encypted full string is \t" + decryped);
    }
    
    public void testCountLetters(){
        int [] counts = countLetters("zA34BC53z2eD35EFGH435IJ3426eeKL62MNOPQRSz236TU3246eVzzWXYZ");
        maxCount( counts);
    }
    
    public void testJoinTwoStrings(){
        joinTwoStrings("aoia" , "ntd"); 
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int [] counts = new int [18];
        countWordLengths(fr,counts);
    }
}
