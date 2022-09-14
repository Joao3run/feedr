package br.com.brn.feedrapi.application.exception;

public class DuplicateUserException extends Exception {

    public DuplicateUserException() {
    }

    public DuplicateUserException(String msg) {
        super(msg);
    }
}
