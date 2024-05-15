package in.himanshu.expenseapp.exceptions;

import java.io.Serial;

public class ItemAlreadyExistsException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L; //Why we need this?

    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
