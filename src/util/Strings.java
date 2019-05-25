//File created by Andrew Chafos: 8/30/15 @ 4:48 PM
package util;

//Class which contains static methods to be used by any class to 
//simplify String manipulation
public class Strings {
    
    //capitalizes a single word
    public static String capitalize(String word){
        if(word.length() == 0){
            return word;
        }
        else if(word.length() == 1){
            return word.toUpperCase();
        }
        
        if(word.contains(" ")){
            throw new RuntimeException("This method is used only to capitalize a single word.");
        }
        
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }//end of method
    
    //capitalizes every single word of a sentence
    public static String capitalSentence(String sentence){
        if(sentence.length() == 0 || sentence.length() == 1){
            return capitalize(sentence);
        }
        
        String newSentence = "";
        
        for(int i = 0;i < sentence.length();i++){
            if(sentence.substring(i, i+1).equals(" ")){
                if(i != sentence.length() - 1){
                    newSentence += sentence.substring(i + 1, i + 2).toUpperCase();
                }
            }
            else if(i == 0 && !sentence.substring(0, 1).equals(" ")){
                newSentence += sentence.substring(0, 1).toUpperCase();
            }
            else{
                newSentence += sentence.substring(i, i + 1).toLowerCase();
            }
        }
        
        return newSentence;
    }//end of method
    
}//end of class
