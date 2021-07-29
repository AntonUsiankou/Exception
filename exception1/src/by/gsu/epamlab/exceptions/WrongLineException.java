package by.gsu.epamlab.exceptions;

 public class WrongLineException extends Exception{
     public WrongLineException(){
     }

     public WrongLineException(String message){
         super(message);
     }

     public WrongLineException(String message, Throwable cause){
         super(message, cause);
     }

     public WrongLineException(Throwable cause){
         super(cause);
     }


}
