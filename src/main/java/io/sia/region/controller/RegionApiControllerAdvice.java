package io.sia.region.controller;

import io.sia.common.BindingErrorResponse;
import io.sia.common.ErrorResponse;
import io.sia.region.exception.RegionDuplicatedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice("io.sia.region.controller")
public class RegionApiControllerAdvice {

    @ExceptionHandler(RegionDuplicatedException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleAoiDuplicatedException(final RegionDuplicatedException e) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("RegionApiController-Region001");
        errorResponse.setTitle("Duplicated Region Information Exception");
        errorResponse.setMessage("입력하신 " + e.getInformation() + " (은/는) 이미 시스템에 등록된 정보입니다.");
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public BindingErrorResponse handleBindingException(final MethodArgumentNotValidException e) {
        final BindingErrorResponse errorResponse = new BindingErrorResponse();
        errorResponse.setCode("RegionApiController-Region002");
        errorResponse.setTitle("Binding Exception");
        errorResponse.setMessage("입력하신 정보가 유효하지 않습니다.");
        errorResponse.setErrors(e.getBindingResult().getFieldErrors());
        return errorResponse;
    }
}
