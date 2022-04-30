package com.ezoqc.defijava.helpers;

import java.util.Optional;

import com.ezoqc.defijava.exceptions.NotFoundException;

public class Validators {

	public static <T> void isFound(Optional<T> o) {
		if (!o.isPresent()) {
			throw new NotFoundException("La ressource demandée n’a pas été trouvé.");
		}
	}
}
