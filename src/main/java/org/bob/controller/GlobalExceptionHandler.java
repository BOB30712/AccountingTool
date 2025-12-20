package org.bob.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.bob.record.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String code, String message, HttpServletRequest req){
        ErrorResponse body = new ErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                code,
                message,
                req.getRequestURI()
        );
        return  ResponseEntity.status(status).body(body);
    }

    // 1) @Valid 驗證 RequestBody 失敗（JSON -> DTO）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest req
    ){
        String detail = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e->e.getField()+":"+e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", detail, req);
    }

    // 2) @Validated 驗證 query/path 參數失敗（@RequestParam/@PathVariable）
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest req
    ){
        String detail = ex.getConstraintViolations()
                .stream()
                .map(v->v.getPropertyPath()+":"+v.getMessage())
                .collect(Collectors.joining("; "));
        return build(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", detail, req);
    }


    // 5) 最後一道防線：未預期錯誤
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(
            Exception ex,
            HttpServletRequest req
    ){
        // 這裡務必記錄 log（含 stacktrace），回應避免把內部細節吐給使用者
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
                "Unexpected error", req);
    }
}
