package com.example.recipenowwebappbackend.exceptions;

import java.util.Date;


public record ErrorDetails(Date timestamp, String errorCode, String message, String status) {
}
