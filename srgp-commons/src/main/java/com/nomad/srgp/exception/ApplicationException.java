package com.nomad.srgp.exception;

/**
 * @author Shariful Islam
 */
public class ApplicationException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -4693005231130910458L;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
