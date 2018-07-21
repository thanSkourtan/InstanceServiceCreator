package com.thanskourtan.exceptions;

/**
 * Created by v-askourtaniotis on 30/5/2018. mailTo: thanskourtan@gmail.com
 */
public class ApplicationException extends Exception{

    public ApplicationException(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return message;
    }


}
