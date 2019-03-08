package com.mtgpeasant.card.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exceptions handler
 */
@ControllerAdvice
public class HandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerController.class);

    /**
     * Handle controller exceptions
     *
     * @param e handle exception
     * @return ResponseEntity with {@link Error} in body and {@link HttpStatus} corresponding to the given exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionRouting(Exception e) {

        Error responseBody = new Error();
        HttpStatus responseStatus;

        if (e instanceof CardNotFoundException) {

            LOGGER.debug("[exceptionRouting] CardNotFoundException");
            responseBody.setCode(Code.CARD_NOT_FOUND);
            responseBody.setDescription(e.getMessage());
            responseStatus = HttpStatus.NOT_FOUND;

        } else {
            responseBody.setCode(Code.INTERNAL_SERVER_ERROR);
            responseBody.setDescription("Unknown error occurred.");
            responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            LOGGER.error("Default error : ", e);
            LOGGER.error(e.getMessage());
            LOGGER.error(e.toString());
        }
        return new ResponseEntity<>(responseBody, responseStatus);
    }
}
