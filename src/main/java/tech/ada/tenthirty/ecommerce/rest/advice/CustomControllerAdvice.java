package tech.ada.tenthirty.ecommerce.rest.advice;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.ada.tenthirty.ecommerce.exception.NotFoundException;
import tech.ada.tenthirty.ecommerce.exception.QuantidadeIndisponivelException;
import tech.ada.tenthirty.ecommerce.rest.model.DefaultResponse;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<DefaultResponse> handleNotFoundExceptionHandler(NotFoundException notFoundException){

        HttpStatus notFound = HttpStatus.NOT_FOUND;
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatusResponse(notFound.value());
        defaultResponse.setMessage(notFoundException.getMessage());
        return new ResponseEntity<>(defaultResponse, notFound);
    }

    @ExceptionHandler(value = QuantidadeIndisponivelException.class)
    protected ResponseEntity<DefaultResponse> handleQuantidadeProdutoInferior(QuantidadeIndisponivelException quantidadeIndisponivelException){
        HttpStatus notFound = HttpStatus.BAD_REQUEST;
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setStatusResponse(notFound.value());
        defaultResponse.setMessage(quantidadeIndisponivelException.getMessage());
        return new ResponseEntity<>(defaultResponse, notFound);
    }

}
