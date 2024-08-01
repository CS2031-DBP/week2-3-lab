package org.edu.demo.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {
    String message;
    String status;

    public ErrorMessage(String message, String string) {
        this.message = message;
        this.status = string;
    }
}
