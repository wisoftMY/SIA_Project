package io.sia.aoi.controller;

import io.sia.aoi.exception.AoiDuplicatedException;
import io.sia.aoi.exception.AoiNotFoundException;
import io.sia.common.BindingErrorResponse;
import io.sia.common.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice("io.sia.aoi.controller")
public class AoiApiControllerAdvice {

    @ExceptionHandler(AoiDuplicatedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleAoiDuplicatedException(final AoiDuplicatedException e) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("AoiApiController-Aoi001");
        errorResponse.setTitle("Duplicated Aoi Information Exception");
        errorResponse.setMessage("입력하신 " + e.getInformation() + " (은/는) 이미 시스템에 등록된 정보입니다.");
        return errorResponse;
    }

    @ExceptionHandler(AoiNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleAoiNotFoundException(final AoiNotFoundException e) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("AoiApiController-Aoi002");
        errorResponse.setTitle("Aoi Not Found Exception");
        errorResponse.setMessage("입력하신 " + e.getInformation() + "에 해당하는 Aoi가 없습니다.");
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public BindingErrorResponse handleBindingException(final MethodArgumentNotValidException e) {
        final BindingErrorResponse errorResponse = new BindingErrorResponse();
        errorResponse.setCode("AoiApiController-Aoi003");
        errorResponse.setTitle("Binding Exception");
        errorResponse.setMessage("입력하신 정보가 유효하지 않습니다.");
        errorResponse.setErrors(e.getBindingResult().getFieldErrors());
        return errorResponse;
    }
}
