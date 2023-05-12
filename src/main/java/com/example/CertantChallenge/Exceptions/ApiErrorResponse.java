package com.example.CertantChallenge.Exceptions;

import java.time.LocalDateTime;

public record ApiErrorResponse(String message, int status, LocalDateTime localDateTime) {
}