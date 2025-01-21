package com.rte_france.antares.datamanager_back.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;


@Getter
public class PegaseExceptionDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final PegaseErrorCode pegaseErrorCode;

    private final List<String> errorMessageArguments;

    private final LocalDateTime date;
    
    private final String type;

    private final String message;


    public PegaseExceptionDto(PegaseErrorCode pegaseErrorCode, List<String> errorMessageArguments, LocalDateTime date, String type, String message) {
        this.pegaseErrorCode = pegaseErrorCode;
        this.errorMessageArguments = errorMessageArguments;
        this.date = date;
        this.type = type;
        this.message = message;
    }

    public PegaseExceptionDto(RuntimeException exception, String message) {
        this.message = message;
        this.pegaseErrorCode = PegaseErrorCode.SERVER_ERROR;
        this.errorMessageArguments = null;
        this.type = PegaseErrorType.TECHNICAL.name();
        this.date = now();
    }
}
