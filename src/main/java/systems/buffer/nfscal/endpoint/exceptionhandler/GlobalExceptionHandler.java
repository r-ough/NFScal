package systems.buffer.nfscal.endpoint.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import systems.buffer.nfscal.exception.NotFoundException;
import systems.buffer.nfscal.exception.ServiceException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    protected ResponseEntity<Object> handleServiceException(RuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e.getCause());
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e.getCause());
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException e, WebRequest request) {
        log.error(e.getMessage(), e.getCause());
        return handleExceptionInternal(e, "An error has occurred", new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }
}
