package com.example.task_management_system_ampada.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BoardNotFoundException extends RuntimeException {
}
