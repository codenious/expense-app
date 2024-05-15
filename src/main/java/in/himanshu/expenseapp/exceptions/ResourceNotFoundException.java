package in.himanshu.expenseapp.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends   RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L; //Why we need this?

    public ResourceNotFoundException(String message){
        super(message);
    }
}
