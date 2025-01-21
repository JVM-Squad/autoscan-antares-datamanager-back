package com.rte_france.antares.datamanager_back.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class BusinessException extends PegaseException {

    @Builder
    public BusinessException(PegaseErrorCode pegaseErrorCode, String message, List<String> errorMessageArguments, HttpStatus httpStatus) {
        super(pegaseErrorCode, message, errorMessageArguments, httpStatus);
    }
}
