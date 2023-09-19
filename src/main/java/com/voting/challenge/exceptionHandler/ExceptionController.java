package com.voting.challenge.exceptionHandler;


import com.voting.challenge.exceptions.VotingException;
import com.voting.challenge.exceptions.VotingExceptionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VotingException.class)
    protected ResponseEntity<VotingExceptionRequest> handleVotingException(VotingException ex) {
        var errorResponse = VotingExceptionRequest.builder()
                .errorMessage(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
