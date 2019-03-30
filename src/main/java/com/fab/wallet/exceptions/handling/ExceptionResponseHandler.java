package com.fab.wallet.exceptions.handling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestControllerAdvice
public class ExceptionResponseHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionResponseHandler.class);

//    @ExceptionHandler(value = {IOException.class})
//    public Response sendErrorResponse(IOException ioEx) {
//        Response response;
//        if (ioEx instanceof ExecuteException) {
//
//            logger.error("File Not Found", ioEx);
//            response = new Response(String.valueOf(((ExecuteException) ioEx).getExitValue()));
//            response.setErrors(ioEx.toString());
//
//        } else {
//            logger.error("File Not Found", ioEx);
//            response = new Response(String.valueOf(ErrorCodes.ERROR_TERMINATE));
//            response.setErrors(ioEx.toString());
//        }
//
//
//        return response;
//    }
//
//    @ExceptionHandler(value = {Exception.class})
//    public Response sendErrorResponse(Exception e) {
//        logger.error("Script execution failed! ", e);
//        Response response = new Response(String.valueOf(ErrorCodes.ERROR_TERMINATE));
//        response.setOutput(e.toString());
//        return response;
//    }


}
