package cards.exception;

import java.lang.reflect.MalformedParametersException;

public class CheatingDeckOfCardsException extends MalformedParametersException {

    public CheatingDeckOfCardsException() {
    }

    public CheatingDeckOfCardsException(String reason) {
        super(reason);
    }
}
