package com.rte_france.antares.datamanager_back.exception;

import lombok.Builder;

import java.util.List;

/**
 * Base exception for all technical exception
 */
public class TechnicalException extends PegaseException {

    @Builder
    public TechnicalException(PegaseErrorCode pegaseErrorCode, String message, List<String> errorMessageArguments, Throwable cause) {
        super(pegaseErrorCode, message, errorMessageArguments, cause);
    }
}
