package cards.exception;

import java.lang.reflect.MalformedParametersException;

public class MalformedCardListException extends MalformedParametersException {

    public MalformedCardListException() {
    }

    public MalformedCardListException(String reason) {
        super(reason);
    }
}
