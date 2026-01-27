package es.fplumara.dam1.actividades.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
       super();
   }
   public NotFoundException(String message){
       super(message);
   }
}
