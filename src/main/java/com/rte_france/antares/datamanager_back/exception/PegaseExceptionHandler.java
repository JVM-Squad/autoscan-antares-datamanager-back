package com.rte_france.antares.datamanager_back.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

@AutoConfiguration
@RestControllerAdvice
@Slf4j
@Order(1)
public class PegaseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<PegaseExceptionDto> businessExceptionHandler(BusinessException ex) {
        BusinessException businessException = ex;
        if (!CollectionUtils.isEmpty(ex.getErrorMessageArguments())) {
            String formatMessageWithArguments = MessageFormat.format(ex.getMessage(), ex.getErrorMessageArguments());
            businessException = new BusinessException(ex.getPegaseErrorCode(), formatMessageWithArguments, ex.getErrorMessageArguments(), ex.getHttpStatus());
        }
        log.debug(businessException.getMessage());

        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new PegaseExceptionDto(ex.getPegaseErrorCode(), ex.getErrorMessageArguments(), ex.getDate(), PegaseErrorType.BUSINESS.name(), businessException.getMessage()));
    }


    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<PegaseExceptionDto> technicalExceptionHandler(TechnicalException ex) {
        TechnicalException technicalException = ex;
        if (!CollectionUtils.isEmpty(ex.getErrorMessageArguments()) && !StringUtils.isEmpty(ex.getMessage())) {
            String formatMessageWithArguments = MessageFormat.format(ex.getMessage(), ex.getErrorMessageArguments());
            technicalException = new TechnicalException(ex.getPegaseErrorCode(), formatMessageWithArguments, ex.getErrorMessageArguments(), ex.getCause());
        }
        log.error(technicalException.toString());
        log.debug(technicalException.toString(), technicalException);
        PegaseExceptionDto pegaseExceptionDto = new PegaseExceptionDto(ex.getPegaseErrorCode(), ex.getErrorMessageArguments(), ex.getDate(), PegaseErrorType.TECHNICAL.name(), technicalException.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(pegaseExceptionDto);
    }

    @ExceptionHandler({RuntimeException.class, TechnicalAntaresDataMangerException.class})
    public ResponseEntity<PegaseExceptionDto> runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.toString(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new PegaseExceptionDto(ex, ex.getMessage()));
    }
}
