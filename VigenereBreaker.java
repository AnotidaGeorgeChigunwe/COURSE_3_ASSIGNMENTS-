import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder result = new StringBuilder();
        for (int k=whichSlice; k < message.length(); k+=totalSlices) {
            char currChar = message.charAt(k);
            result.append(currChar);
        }
        //System.out.println("the half string is \t:\t" + result.toString());
        return result.toString();
    }
    

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] slices = new String[klength];
        //SLICE THE STRING WITH THE KEY
        for (int k = 0; k< klength;k++) {
            String slice = sliceString(encrypted,k,klength);
            slices[k] =slice;
        }
        //GET KEY FOR EACH SLICE
        for (int k = 0; k< klength;k++){
            CaesarCracker caeCra = new CaesarCracker(mostCommon);
            String slice = slices[k];
            int dkey = caeCra.getKey(slice);
            key[k] = dkey;
        }
        //TEST PRINT
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource ();
        String encrypted = fr.asString();
        HashMap<String,HashSet<String>> languages = languages(new DirectoryResource());
        String dycrypted =  breakForAllLangs(encrypted,languages);
        System.out.println(dycrypted);
    }
    //CREATING DICTIONARY FROM FILE RESOURCE 
    public HashSet<String> readDictionary (FileResource fr) {
        HashSet<String> dictionary = new HashSet<String>();
        for (String w : fr.lines()) {
            dictionary.add(w.toLowerCase());
        }
        return dictionary;
    }
    //COUNTING HOW MANY WORDS ARE ACTUALLY WORDS 
    public int countWords(String encrypted, HashSet<String> dictionary) {
        int count =0;
        for (String w : encrypted.split("\\W+")) {
            //System.out.println(w);
            if (dictionary.contains(w.toLowerCase())) {
                count++;
            }
        }
        //System.out.println("The Final  count is : " + count );
        return count;
    }
    //
    public HashMap<String,Integer> breakForLanguage (String encrypted,HashSet<String> dictionary, char mostCommon){
        String bestString = "*NO STRING*";
        int maxWordCount = 0;
        int[] key = new int[4];
        for (int k=1; k<101;k++) {
            //TRY KEY 
            int[] triedKey = tryKeyLength(encrypted,k,mostCommon);
            VigenereCipher vigenereCip = new VigenereCipher(triedKey);
            String triedDiscrpted = vigenereCip.decrypt(encrypted);
            int currWordCount = countWords(triedDiscrpted, dictionary);
            //System.out.println("\n\n\n\n" + triedDiscrpted );//TEST
            if ( currWordCount > maxWordCount) {
                bestString = triedDiscrpted;
                maxWordCount = currWordCount;
                key = triedKey;
            }
            //TEST PRINT VALID WORDS AT KEY LENGTH K 
            /**if ( k == 38) {
                System.out.println("At " + K + " the vaidwords are  : " + currWordCount);
            }*/
        }
        //System.out.println("The counts in the valid file are : " + maxWordCount);
        //System.out.println("\n The key size is :" + key.length +"\n");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(bestString,maxWordCount);
        return map;
    }
    //MOST COMMON CHAR
    public char mostCommonCharIn(HashSet<String> dictionary){
        StringBuilder alph = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        HashMap<Character,Integer> commonChar =  new HashMap<Character,Integer> ();
        char mostCommonChar = '#';
        for (int k =0; k<26;k++){
            char currChar = alph.charAt(k);
            commonChar.put(currChar,1);
        }
        
        for (String s : dictionary){
            for (int k =0; k<26; k++){
                char currChar = alph.charAt(k);
                int index = s.indexOf(currChar);
                int chatCountinWord = 0;
                while (index !=-1) {
                    commonChar.put(currChar,1+commonChar.get(currChar));
                    index = s.indexOf(currChar,index+1);
                }
            }
        }
        
        int maxCharCount = 0;
        for (Character v : commonChar.keySet()) {
            char currChar = v;
            if (commonChar.get(v)> maxCharCount) {
               maxCharCount =  commonChar.get(v);
               mostCommonChar = v;
            }
        }
        return mostCommonChar;
    }
    //DIRCTORY HASHMAP 
    private HashMap<String,HashSet<String>> languages(DirectoryResource dr){
        HashMap<String,HashSet<String>> dictionaries = new HashMap<String,HashSet<String>>();
        for ( File f : dr.selectedFiles()){
            FileResource fr = new FileResource (f);
            dictionaries.put(f.getName(),readDictionary(fr));
        }
        return dictionaries;        
    }
        
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int maxWordMatch = 0;
        String dycrypted = "*NOSTRING*";
        String langOfMessage ="*NOLANGUAGE*";
        for (String s : languages.keySet()){
            char mostCommon = mostCommonCharIn(languages.get(s));
            HashMap<String,Integer> discrptedAndWordCount = breakForLanguage (encrypted,languages.get(s), mostCommon);
            for (String w: discrptedAndWordCount.keySet()){
                if(discrptedAndWordCount.get(w) > maxWordMatch){
                    maxWordMatch = discrptedAndWordCount.get(w);
                    dycrypted = w;
                    langOfMessage = s;
                }
            }         
        }
        System.out.println("The language is : " + langOfMessage);
        return dycrypted;
    }
 }
