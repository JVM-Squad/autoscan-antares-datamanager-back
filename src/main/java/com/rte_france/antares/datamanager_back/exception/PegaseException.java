package com.rte_france.antares.datamanager_back.exception;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mathieu BAGUE {@literal <mathieu.bague at rte-france.com>}
 */
@ToString
@Getter
public class PegaseException extends RuntimeException {

    /**
     * Code Error
     */
    private final PegaseErrorCode pegaseErrorCode;

    /**
     * List of message variables
     */
    private final List<String> errorMessageArguments = new ArrayList<>();


    /**
     * Exception date
     */
    private final LocalDateTime date = LocalDateTime.now();


    /**
     * The http status to return.
     */
    private final HttpStatus httpStatus;



    protected PegaseException(@NonNull PegaseErrorCode pegaseErrorCode, @NonNull String message, List<String> errorMessageArguments, HttpStatus httpStatus) {
        super(message);

        this.pegaseErrorCode = pegaseErrorCode;
        this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
        if (errorMessageArguments != null) {
            this.errorMessageArguments.addAll(errorMessageArguments);
        }
    }
    protected PegaseException(@NonNull PegaseErrorCode pegaseErrorCode, @NonNull String message, HttpStatus httpStatus) {
        super(message);
        this.pegaseErrorCode = pegaseErrorCode;
        this.httpStatus = httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    protected PegaseException(PegaseErrorCode pegaseErrorCode, String message, List<String> errorMessageArguments, Throwable cause) {
        super(message, cause);

        this.pegaseErrorCode = pegaseErrorCode != null ? pegaseErrorCode : PegaseErrorCode.SERVER_ERROR;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (errorMessageArguments != null) {
            this.errorMessageArguments.addAll(errorMessageArguments);
        }
    }

    protected PegaseException(PegaseErrorCode pegaseErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.pegaseErrorCode = pegaseErrorCode != null ? pegaseErrorCode : PegaseErrorCode.SERVER_ERROR;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
