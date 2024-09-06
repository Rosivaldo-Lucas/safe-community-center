package com.github.rosivaldolucas.safe_community_centers_back.exceptionhandler;

import com.github.rosivaldolucas.safe_community_centers_back.exception.DomainException;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          @NonNull final MethodArgumentNotValidException ex,
          @NonNull final HttpHeaders headers,
          @NonNull final HttpStatusCode status,
          @NonNull final WebRequest request
  ) {
    final List<ApiErrorField> listFieldsErrors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map((error) -> new ApiErrorField(error.getField(), error.getDefaultMessage()))
            .toList();

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Erro de validação");
    problemDetail.setDetail("Erro de validação no conteúdo do corpo da requisição enviada");
    problemDetail.setProperty("erros", listFieldsErrors);
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, headers, status, request);
  }

  @ExceptionHandler({ DomainException.class })
  protected ResponseEntity<Object> handleDomainException(final DomainException ex, final WebRequest request) {
    final HttpStatusCode status = HttpStatus.NOT_FOUND;

    final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problemDetail.setTitle("Error");
    problemDetail.setDetail(ex.getMessage());
    problemDetail.setProperty("cause", ex.getMessage());
    problemDetail.setProperty("timestamp", Instant.now());

    return this.handleExceptionInternal(ex, problemDetail, null, status, request);
  }

}
