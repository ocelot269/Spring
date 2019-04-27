package org.formacio.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Aquesta seria una excepcio "normal" que emprariem en el codi.
 * El fet d'anotarla amb @ResponseStatus fa que Spring MVC sàpiga en quin error 
 * HTTP convertir-la
 * L'atribut codi es l'estat HTTP i convé emprar les constants definides a HttpStatus
 * La resposta HTTP, a part del codi, també té un texte anomenat reason que podem configurar
 */
@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR, reason="Operacio incorrecte")
public class OperacioException extends RuntimeException {

}
