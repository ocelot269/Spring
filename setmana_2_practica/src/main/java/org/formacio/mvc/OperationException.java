package org.formacio.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="El contacto no existe")
public class OperationException extends RuntimeException{
	private static final long serialVersionUID = 01L;
}

