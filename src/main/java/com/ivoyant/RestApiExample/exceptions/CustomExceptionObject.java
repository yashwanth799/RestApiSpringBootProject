package com.ivoyant.RestApiExample.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CustomExceptionObject {
    private int status;
    private String message;
    private Timestamp timestamp;
    private String details;
    private String exception;
    private String path;
}