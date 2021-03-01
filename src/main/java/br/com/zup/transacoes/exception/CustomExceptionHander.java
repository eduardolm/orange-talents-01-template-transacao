package br.com.zup.transacoes.exception;

import br.com.zup.transacoes.exception.dto.IllegalArgumentExceptionOutputDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHander {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public IllegalArgumentExceptionOutputDto handleIllegalArgumentErrors(IllegalArgumentException exception) {
        List<String> globalErrors = new ArrayList<>();
        globalErrors.add(exception.getLocalizedMessage());

        return buildIllegalArgumentError(globalErrors);
    }

    private IllegalArgumentExceptionOutputDto buildIllegalArgumentError(List<String> globalErrors) {
        IllegalArgumentExceptionOutputDto notFoundErrors = new IllegalArgumentExceptionOutputDto();

        globalErrors.forEach(notFoundErrors::addError);
        return notFoundErrors;
    }
}
