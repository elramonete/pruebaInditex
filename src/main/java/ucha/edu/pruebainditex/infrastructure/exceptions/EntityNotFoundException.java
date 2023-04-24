package ucha.edu.pruebainditex.infrastructure.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }

}
