package edu.nju.controller;

/**
 * Created by Dora on 2016/4/29.
 */
public class MemberLoginException extends RuntimeException {


    public MemberLoginException() {
    }

    public MemberLoginException(String message) {
        super(message);
    }

    public MemberLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberLoginException(Throwable cause) {
        super(cause);
    }

    public MemberLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
