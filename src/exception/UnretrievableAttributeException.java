//File created by Andrew Chafos: 8/28/15 @ 8:04 PM
package V1.exception;

public class UnretrievableAttributeException extends RuntimeException{
    
    public UnretrievableAttributeException(){
        super("The attribute attempting to be accessed was not part of the class.");
    }
    
    public UnretrievableAttributeException(String attr, String classN){
        super("The class " + classN + " does not contain the attribute " + attr);
    }
}
